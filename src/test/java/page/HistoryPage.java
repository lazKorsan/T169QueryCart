package page;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.AndroidClickUtils;
import utilities.ConfigReader;
import utilities.ReusableMethods;
import utilities.TouchMethods;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HistoryPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(HistoryPage.class);

    // Ozet kartinda deger, etiketin en fazla bu kadar piksel ustunde olabilir
    private static final int MAX_VALUE_DISTANCE = 150;

    // My Account verilerinin gelmesi icin her acilista beklenen en uzun sure.
    // Normalde veri 1-2 sn'de geliyor; bazen ilk acilista hic gelmiyor (BUG 1).
    private static final int MY_ACCOUNT_LOAD_TIMEOUT_SECONDS = 10;

    // My Account'a en fazla kac kez girilip veri beklenecegi.
    private static final int MY_ACCOUNT_MAX_ATTEMPTS = 5;

    // Bu calistirmada simdiye kadar yapilan basarili giris sayisi (sadece log icin).
    // static: her senaryoda yeni HistoryPage olusturulsa da deger korunur.
    private static int loginCount = 0;

    // Yeni BasePage'de wait yok; kendi bekleme nesnemizi olusturuyoruz
    private final WebDriverWait wait;

    // Sadece My Account verilerinin yuklenmesini beklemek icin
    private final WebDriverWait myAccountWait;

    // Sadece Order History sayfasinin ve kartlarinin gelmesini beklemek icin (US018)
    private final WebDriverWait orderHistoryWait;

    public HistoryPage() {
        super();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // Sayfa veri gelince yeniden cizildiginde eski ogeler "bayatlar"; bu durumda pes etmeyip tekrar dene
        this.wait.ignoring(StaleElementReferenceException.class);

        this.myAccountWait = new WebDriverWait(driver, Duration.ofSeconds(MY_ACCOUNT_LOAD_TIMEOUT_SECONDS));
        this.myAccountWait.ignoring(StaleElementReferenceException.class);

        this.orderHistoryWait = new WebDriverWait(driver, Duration.ofSeconds(ORDER_HISTORY_LOAD_TIMEOUT_SECONDS));
        this.orderHistoryWait.ignoring(StaleElementReferenceException.class);
    }

    // =====================================================================
    // ===== US005 - POPULAR BRANDS =====
    // =====================================================================

    @AndroidFindBy(accessibility = "Popular Brands")
    public WebElement popularBrandsTitle;

    // Popular Brands basliginin hemen altindaki kap ve icindeki isimli marka kartlari.
    // Markalar SADECE bu alanda aranir; ana sayfanin baska bolumundeki ayni isim sayilmaz.
    private static final String BRANDS_AREA_XPATH =
            "//android.view.View[@content-desc='Popular Brands']"
                    + "/following-sibling::android.view.View[1]";

    private final By brandCards = By.xpath(BRANDS_AREA_XPATH + "//android.view.View[@content-desc]");

    public void scrollToSection(String sectionTitle) {
        logger.info("'{}' basligina kadar kaydiriliyor", sectionTitle);
        wait.until(d -> !d.findElements(By.className("android.widget.ScrollView")).isEmpty());

        Dimension screen = driver.manage().window().getSize();
        int x = screen.getWidth() / 2;
        int startY = (int) (screen.getHeight() * 0.75);
        int endY = (int) (screen.getHeight() * 0.30);
        int maxSwipes = 10;

        for (int i = 1; i <= maxSwipes && !isSectionTitleDisplayed(sectionTitle); i++) {
            swipe(x, startY, x, endY);
            logger.info("Sayfa asagi kaydirildi ({}/{})", i, maxSwipes);
            ReusableMethods.wait(1);
        }

        if (!isSectionTitleDisplayed(sectionTitle)) {
            throw new RuntimeException("'" + sectionTitle + "' basligi " + maxSwipes + " kaydirmada bulunamadi!");
        }

        // Baslik ekranin alt yarisindaysa, altindaki kartlar da tam gorunsun diye biraz daha kaydir
        WebElement title = driver.findElement(AppiumBy.accessibilityId(sectionTitle));
        if (title.getLocation().getY() > screen.getHeight() / 2) {
            swipe(x, (int) (screen.getHeight() * 0.7), x, (int) (screen.getHeight() * 0.4));
            ReusableMethods.wait(1);
        }
        logger.info("'{}' basligi ekranda", sectionTitle);
    }

    public boolean isSectionTitleDisplayed(String sectionTitle) {
        List<WebElement> titles = driver.findElements(AppiumBy.accessibilityId(sectionTitle));
        if (titles.isEmpty()) {
            return false;
        }
        try {
            WebElement title = titles.get(0);
            return title.isDisplayed() && isFullyOnScreen(title.getRect());
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    /**
     * Popular Brands basliginin ALTINDA, ekranda TAMAMEN gorunen ve ismi bos olmayan markalari dondurur.
     */
    public List<String> getVisibleBrandNames() {
        List<String> names = new ArrayList<>();
        int titleBottom = getPopularBrandsTitleBottom();

        for (WebElement card : driver.findElements(brandCards)) {
            try {
                String name = card.getAttribute("content-desc");
                Rectangle r = card.getRect();
                if (name != null && !name.isBlank()
                        && card.isDisplayed()
                        && isFullyOnScreen(r)
                        && r.getY() >= titleBottom) {
                    names.add(name.trim());
                }
            } catch (StaleElementReferenceException ignored) {
                // Kart o anda yeniden cizildi; sayilmaz
            }
        }
        logger.info("Popular Brands altinda ekranda gorunen markalar: {}", names);
        return names;
    }

    /**
     * Marka SADECE Popular Brands alaninda aranir; basligin altinda ve ekranda tamamen gorunur olmali.
     */
    public boolean isBrandDisplayed(String brandName) {
        By brandInArea = By.xpath(BRANDS_AREA_XPATH
                + "//android.view.View[@content-desc=" + xpathLiteral(brandName) + "]");
        List<WebElement> cards = driver.findElements(brandInArea);
        if (cards.isEmpty()) {
            return false;
        }
        try {
            WebElement card = cards.get(0);
            Rectangle r = card.getRect();
            boolean visible = card.isDisplayed()
                    && isFullyOnScreen(r)
                    && r.getY() >= getPopularBrandsTitleBottom();
            logger.info("'{}' markasi Popular Brands alaninda -> konum: ({}, {}), tam gorunur: {}",
                    brandName, r.getX(), r.getY(), visible);
            return visible;
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    public void swipeBrandsUntilVisible(String brandName, int maxSwipes) {
        for (int i = 1; i <= maxSwipes; i++) {
            if (isBrandDisplayed(brandName)) {
                logger.info("'{}' markasi {} kaydirmadan sonra bulundu", brandName, i - 1);
                return;
            }
            List<WebElement> cards = driver.findElements(brandCards);
            if (cards.isEmpty()) {
                throw new RuntimeException("Popular Brands altinda marka karti bulunamadi!");
            }
            Rectangle card = cards.get(0).getRect();
            int y = card.getY() + card.getHeight() / 2;
            int width = driver.manage().window().getSize().getWidth();
            swipe((int) (width * 0.85), y, (int) (width * 0.15), y);
            logger.info("Marka listesi yana kaydirildi ({}/{})", i, maxSwipes);
            ReusableMethods.wait(1);
        }
        // Son kaydirmadan sonra bir kez daha bak
        if (isBrandDisplayed(brandName)) {
            logger.info("'{}' markasi {} kaydirmadan sonra bulundu", brandName, maxSwipes);
        } else {
            logger.warn("'{}' markasi {} kaydirmada Popular Brands alaninda tam gorunur olmadi", brandName, maxSwipes);
        }
    }

    // Popular Brands basliginin alt kenarinin y konumu (kartlar bunun altinda olmali)
    private int getPopularBrandsTitleBottom() {
        Rectangle t = driver.findElement(AppiumBy.accessibilityId("Popular Brands")).getRect();
        return t.getY() + t.getHeight();
    }

    // Oge ekranin icinde tamamen gorunuyor mu? (yarisi ekran disindaysa gorunur sayilmaz)
    private boolean isFullyOnScreen(Rectangle r) {
        Dimension screen = driver.manage().window().getSize();
        return r.getWidth() > 0 && r.getHeight() > 0
                && r.getX() >= 0 && r.getY() >= 0
                && r.getX() + r.getWidth() <= screen.getWidth()
                && r.getY() + r.getHeight() <= screen.getHeight();
    }

    // Marka adinda tek tirnak olsa bile xpath bozulmasin diye guvenli metin olusturur
    private String xpathLiteral(String text) {
        if (!text.contains("'")) {
            return "'" + text + "'";
        }
        return "concat('" + text.replace("'", "', \"'\", '") + "')";
    }

    // =====================================================================
    // ===== US016 - DASHBOARD =====
    // =====================================================================

    // Alt bardaki Profile ikonu (content-desc = "Profile")
    @AndroidFindBy(accessibility = "Profile")
    public WebElement profileIcon;

    // "Sign In" yazisi hem baslikta hem butonda var; tiklanabilir olani buton
    private final By signInButton = AppiumBy.androidUIAutomator(
            "new UiSelector().description(\"Sign In\").clickable(true)");

    // E-posta modundayken ilk metin kutusu e-posta alani
    private final By emailBox = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.widget.EditText\").instance(0)");

    // Sifre alani: UiSelector'da password() metodu yok, bu yuzden xpath kullaniyoruz
    private final By passwordBox = By.xpath("//android.widget.EditText[@password='true']");

    // Giris ekranindaki kirmizi hata kutusu ("Error ..." ile baslayan yazi)
    private final By loginErrorBox = By.xpath("//*[contains(@content-desc,'Error')]");

    // E-posta modundaki giris ekraninda gorunen yazi; kaybolursa giris ekrani kapanmistir
    private final By loginScreenMarker = AppiumBy.accessibilityId("*Use Phone Instead");

    // My Account sayfasindaki yazili ogeler (etiketler ve degerler).
    // Kap bazen ScrollView bazen duz View oluyor; bu yuzden kaba bagli degiliz.
    private final By myAccountTexts = By.xpath("//android.view.View[@content-desc]");

    // Siparis karti: content-desc'i '#' ile baslayan (siparis no) tiklanabilir ImageView.
    // Hem My Account hem Order History sayfasinda ayni yapida.
    private final By orderCards = By.xpath("//android.widget.ImageView[starts-with(@content-desc,'#')]");

    public boolean isProfileIconActive() {
        WebElement icon = waitForAccessibility("Profile");
        boolean displayed = icon.isDisplayed();
        boolean enabled = icon.isEnabled();
        boolean clickable = "true".equals(icon.getAttribute("clickable"));
        logger.info("Profile ikonu -> gorunur: {}, enabled: {}, tiklanabilir: {}", displayed, enabled, clickable);
        return displayed && enabled && clickable;
    }

    public void loginWithEmail(String email, String password) {
        logger.info("E-posta ile giris yapiliyor: {}", email);

        // 1) Profile -> Sign In butonu
        clickByAccessibility("Profile");
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();

        // 2) Telefon modundan e-posta moduna gec
        clickByAccessibility("*Use Email Instead");
        waitForAccessibility("*Use Phone Instead");

        // 3) E-posta ve sifreyi yaz
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailBox));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordBox));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);

        hideKeyboardIfOpen();

        // 4) Sign In'e bas.
        // Uygulama ara ara "NoSuchMethodError ... validation" hatasi verebiliyor.
        // Bu durumda 20 sn bekleyip tekrar deniyoruz (en fazla 4 kez).
        int maxAttempts = 4;
        int waitSecondsBetweenAttempts = 20;
        String lastError = "";

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
            logger.info("Sign In butonuna basildi (deneme {}/{})", attempt, maxAttempts);

            // Ya giris ekrani kapanacak ya da kirmizi hata kutusu cikacak
            wait.until(d -> isLoginScreenClosed() || isLoginErrorShown());

            if (isLoginScreenClosed()) {
                loginCount++;
                logger.info("Giris yapildi (bu calistirmadaki {}. giris)", loginCount);
                return;
            }

            lastError = getLoginErrorText();

            // Sifre yanlissa tekrar deneme: hesap kilitlenmesin
            if (lastError.contains("Invalid credentials")) {
                throw new RuntimeException("Giris bilgileri hatali! configuration.properties dosyasindaki "
                        + "historyEmail / historyPassword degerlerini kontrol edin. Uygulama mesaji: " + lastError);
            }

            logger.warn("Giris hatasi (deneme {}/{}): {}", attempt, maxAttempts, lastError);

            if (attempt < maxAttempts) {
                logger.warn("Sunucunun sakinlesmesi icin {} saniye bekleniyor...", waitSecondsBetweenAttempts);
                ReusableMethods.wait(waitSecondsBetweenAttempts);
            }
        }

        throw new RuntimeException("Giris " + maxAttempts + " denemede yapilamadi. Uygulamanin son hatasi: " + lastError);
    }

    public void loginWithMailMethod2() {

        AndroidClickUtils.clickByDescription("Profile");
        ReusableMethods.wait(1);
        AndroidClickUtils.clickByDescription("Sign In");
        ReusableMethods.wait(1);
        AndroidClickUtils.clickByDescription("*Use Email Instead");
        ReusableMethods.wait(1);
        AndroidClickUtils.sendKeysToTextBoxByIndex("huseyinyurttepe.user@querycart.com", 0);
        ReusableMethods.wait(1);
        AndroidClickUtils.sendKeysToTextBoxByIndex("Learn.123", 1);
        WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Sign In\").instance(1)")
        ));
        submitButton.click();




    }

    /**
     * My Account verileri sunucudan gelene kadar sayfada gecici degerler (0, 0, 0, 0) gorunur.
     * Veri geldiginde Wallet Balance '$' ile baslar ($0.00); bunu "yuklendi" isareti olarak kullaniyoruz.
     * BUG (Jira BUG 1): Giristen hemen sonra acilinca veriler gelmiyor. Bu durumda sayfadan cikip
     * tekrar giriyoruz (en fazla MY_ACCOUNT_MAX_ATTEMPTS kez) ve bug'i logda "BUG:" ile WARN olarak gosteriyoruz.
     */
    public void waitForMyAccountLoaded() {
        logger.info("My Account verilerinin yuklenmesi bekleniyor (en fazla {} sn)...", MY_ACCOUNT_LOAD_TIMEOUT_SECONDS);
        int maxAttempts = MY_ACCOUNT_MAX_ATTEMPTS;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            if (isMyAccountDataLoaded()) {
                logger.info("My Account verileri yuklendi (deneme {}/{})", attempt, maxAttempts);
                return;
            }
            if (attempt < maxAttempts) {
                logger.warn("BUG: My Account verileri {} sn'de yuklenmedi; ozet kartlarda gecici 0 degerleri var "
                                + "(deneme {}/{}). Sayfaya yeniden giriliyor...",
                        MY_ACCOUNT_LOAD_TIMEOUT_SECONDS, attempt, maxAttempts);
                driver.navigate().back();
                clickByAccessibility("My Account");
            }
        }

        throw new RuntimeException("BUG: My Account verileri " + maxAttempts + " denemede yuklenmedi: "
                + "Wallet Balance '$' formatina gecmedi (sayfa gecici degerlerde kaldi)");
    }

    private boolean isMyAccountDataLoaded() {
        try {
            myAccountWait.until(d -> {
                Rectangle walletLabel = d.findElement(AppiumBy.accessibilityId("Wallet Balance")).getRect();
                String wallet = findValueAbove(walletLabel);
                return wallet != null && wallet.startsWith("$");
            });
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Ozet kartindaki degeri bulur.
     * Deger, etiketle ayni X hizasinda ve etiketin hemen ustunde durur.
     * Ornek: "Total Orders" (x=94, y=1079) -> ustundeki "2" (x=94, y=945)
     * Deger gec yuklenebilecegi icin 20 saniyeye kadar tekrar tekrar aranir.
     * Etiketten MAX_VALUE_DISTANCE pikselden uzaktaki ogeler kabul edilmez,
     * boylece baska bir kartin degeri yanlislikla okunamaz.
     */
    public String getSummaryValue(String label) {
        try {
            String value = wait.until(d -> {
                Rectangle labelRect = d.findElement(AppiumBy.accessibilityId(label)).getRect();
                return findValueAbove(labelRect);
            });
            logger.info("'{}' degeri: {}", label, value);
            return value;
        } catch (TimeoutException e) {
            throw new RuntimeException("'" + label + "' kartinin degeri etiketin hemen ustunde bulunamadi!");
        }
    }

    private String findValueAbove(Rectangle labelRect) {
        String value = null;
        int bestDistance = Integer.MAX_VALUE;

        for (WebElement el : driver.findElements(myAccountTexts)) {
            Rectangle r = el.getRect();
            boolean sameColumn = Math.abs(r.getX() - labelRect.getX()) <= 10;
            int distance = labelRect.getY() - (r.getY() + r.getHeight());

            if (sameColumn && distance >= 0 && distance <= MAX_VALUE_DISTANCE && distance < bestDistance) {
                bestDistance = distance;
                value = el.getAttribute("content-desc");
            }
        }
        return value; // null ise wait tekrar dener
    }

    /**
     * Ekrandaki ilk (en yeni) siparis kartinin metnini dondurur.
     * Ornek:
     * #1410261873
     * 06:42 AM, 14-10-2026
     * Info:
     * 1 Product
     * Delivery Status:
     * Pending
     * Payment Status:
     * Unpaid
     * Total:
     * $370.00
     */
    public String getFirstOrderCardText() {
        try {
            String text = wait.until(d -> d.findElement(orderCards).getAttribute("content-desc"));
            logger.info("Ilk siparis karti:\n{}", text);
            return text;
        } catch (TimeoutException e) {
            throw new RuntimeException("Siparis gecmisinde hic siparis karti bulunamadi! "
                    + "Test hesabinda en az 1 siparis olmali.");
        }
    }

    /**
     * Sayfayi asagi kaydirarak tum siparis kartlarini siparis numarasina gore toplar.
     * Flutter ekran disindaki kartlari XML'e koymayabildigi icin kaydirmadan saymak guvenilir degil.
     * Yeni kart cikmayinca durur.
     */
    public List<String> collectAllOrderNumbers() {
        Map<String, String> orders = new LinkedHashMap<>();
        Dimension screen = driver.manage().window().getSize();
        int x = screen.getWidth() / 2;
        int startY = (int) (screen.getHeight() * 0.75);
        int endY = (int) (screen.getHeight() * 0.35);
        int maxSwipes = 15;

        wait.until(ExpectedConditions.presenceOfElementLocated(orderCards));

        for (int i = 0; i <= maxSwipes; i++) {
            int before = orders.size();
            for (WebElement card : driver.findElements(orderCards)) {
                try {
                    String desc = card.getAttribute("content-desc");
                    if (desc == null || desc.isBlank()) continue;
                    String orderNo = desc.split("\n")[0].trim();
                    orders.putIfAbsent(orderNo, desc);
                } catch (StaleElementReferenceException ignored) {
                    // Kart kaydirma sirasinda yeniden cizildi; bir sonraki turda tekrar okunur
                }
            }
            // Ilk turdan sonra yeni kart gelmediyse listenin sonuna ulastik
            if (i > 0 && orders.size() == before) {
                break;
            }
            swipe(x, startY, x, endY);
            ReusableMethods.wait(1);
        }

        List<String> orderNumbers = new ArrayList<>(orders.keySet());
        logger.info("Toplanan siparisler ({} adet): {}", orderNumbers.size(), orderNumbers);
        return orderNumbers;
    }

    // =====================================================================
    // ===== US018 - ORDER HISTORY =====
    // =====================================================================

    // Dashboard menusundeki "Order History" satiri bir ImageView.
    // Order History sayfasinin basligi ise bir View. Ayni content-desc'i tasidiklari icin sinif adiyla ayiriyoruz.
    private final By sidebarOrderHistory = By.xpath("//android.widget.ImageView[@content-desc='Order History']");
    private final By orderHistoryTitle = By.xpath("//android.view.View[@content-desc='Order History']");

    // Siparis listesi cok uzun olunca bir ScrollView icine giriyor (az siparis varken ScrollView olmayabilir)
    private final By scrollViews = By.className("android.widget.ScrollView");

    // Order History / Order Details sayfalarinin sol ustundeki geri oku: content-desc'i olmayan ilk tiklanabilir ImageView
    private final By backArrow = By.xpath(
            "(//android.widget.ImageView[@clickable='true' and (not(@content-desc) or @content-desc='')])[1]");

    // Iptalden sonra cikan yesil bildirim: "Success / Order Canceled Successfully!"
    private final By cancelSuccessMessage = By.xpath("//*[contains(@content-desc,'Order Canceled Successfully')]");

    // Fatura ekranindaki siparis no: content-desc = "Order#1510261882"
    private final By receiptOrderNumber = By.xpath("//android.view.View[starts-with(@content-desc,'Order#')]");

    // Goz ikonu XML'de ayri bir eleman degil, kartin icine cizilmis.
    // Yeri Inspector bounds'u + ekran goruntusu ile olculdu: kart [64,586][1376,1372] -> ikon merkezi ~(1246, 696).
    // Yani kartin sol kenarindan genisligin %90'i kadar saga, ust kenarindan yuksekligin %14'u kadar asagida.
    private static final double EYE_X_RATIO = 0.90;
    private static final double EYE_Y_RATIO = 0.14;

    // Tam bir kartin yuksekligi ~786 px. Bundan kisa gorunen kart ekranin kenarinda kesilmis demektir.
    private static final int MIN_FULL_CARD_HEIGHT = 700;

    // Ikonun yarim boyu ~58 px; ikonun tamami gorunur alanda olsun diye bu kadar pay birakiyoruz
    private static final int EYE_MARGIN = 60;

    // Liste en fazla kac kez kaydirilarak aranacak
    private static final int MAX_ORDER_SWIPES = 15;

    // Order History sayfasinin ve siparis kartlarinin gelmesi icin her denemede beklenen sure ve deneme sayisi
    private static final int ORDER_HISTORY_LOAD_TIMEOUT_SECONDS = 10;
    private static final int ORDER_HISTORY_MAX_ATTEMPTS = 5;

    // Kartlar gorundukten sonra listenin gri ekrana donmedigini anlamak icin beklenen sure
    private static final int ORDER_HISTORY_STABLE_SECONDS = 3;

    // Order History ard arda cok kisa aralikla acilinca (ornek: TC01'den hemen sonra TC02) liste gri ekrana dusuyor.
    // TC02 tek basina calistirilinca sorun cikmadi. Bu yuzden bir onceki acilistan en az bu kadar sure gecmesini bekliyoruz.
    private static final int ORDER_HISTORY_COOLDOWN_SECONDS = 60;

    // Order History listesinin en son basariyla yuklendigi an (senaryolar arasinda korunsun diye static)
    private static long lastOrderHistoryLoadMillis = 0;

    // ---------- Kriter 1: Dashboard sideBar'daki Order History ----------

    public boolean isSidebarOrderHistoryActive() {
        WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(sidebarOrderHistory));
        boolean displayed = item.isDisplayed();
        boolean enabled = item.isEnabled();
        boolean clickable = "true".equals(item.getAttribute("clickable"));
        boolean fullyOnScreen = isFullyOnScreen(item.getRect());
        logger.info("Dashboard menusundeki 'Order History' -> gorunur: {}, tam ekranda: {}, enabled: {}, tiklanabilir: {}",
                displayed, fullyOnScreen, enabled, clickable);
        return displayed && fullyOnScreen && enabled && clickable;
    }

    /**
     * Dashboard menusunden Order History sayfasini acar.
     * Bir onceki acilistan ORDER_HISTORY_COOLDOWN_SECONDS sn gecmediyse once kalan sureyi bekler.
     */
    public void openOrderHistoryFromMenu() {
        if (lastOrderHistoryLoadMillis > 0) {
            long passed = (System.currentTimeMillis() - lastOrderHistoryLoadMillis) / 1000;
            long remaining = ORDER_HISTORY_COOLDOWN_SECONDS - passed;
            if (remaining > 0) {
                logger.info("Order History {} sn once acilmisti; liste gri ekrana dusmesin diye {} sn bekleniyor",
                        passed, remaining);
                ReusableMethods.wait((int) remaining);
            }
        }
        logger.info("Dashboard menusunden 'Order History' aciliyor");
        wait.until(ExpectedConditions.elementToBeClickable(sidebarOrderHistory)).click();
    }

    /**
     * Order History sayfasi gercekten acildi mi?
     * - Sayfa basligi (View) gorunur olmali
     * - En az bir siparis karti olmali
     * - Dashboard menusundeki Order History satiri artik ekranda olmamali (yani dashboard'da degiliz)
     */
    public boolean isOrderHistoryPageOpened() {
        for (int attempt = 1; attempt <= ORDER_HISTORY_MAX_ATTEMPTS; attempt++) {

            // 1) Sayfa basligi geldi mi? Gelmediyse tiklama sayfayi acmamis olabilir
            if (!waitShort(orderHistoryTitle)) {
                String screen = describeScreen();
                if (attempt < ORDER_HISTORY_MAX_ATTEMPTS && !driver.findElements(sidebarOrderHistory).isEmpty()) {
                    logger.warn("'Order History'ye tiklandi ama sayfa {} sn'de acilmadi (deneme {}/{}). "
                                    + "Ekrandaki ogeler: {}. Menuye tekrar tiklaniyor...",
                            ORDER_HISTORY_LOAD_TIMEOUT_SECONDS, attempt, ORDER_HISTORY_MAX_ATTEMPTS, screen);
                    wait.until(ExpectedConditions.elementToBeClickable(sidebarOrderHistory)).click();
                    continue;
                }
                logger.error("Order History sayfasi acilmadi (deneme {}/{}). Ekrandaki ogeler: {}",
                        attempt, ORDER_HISTORY_MAX_ATTEMPTS, screen);
                return false;
            }

            // 2) Baslik var; siparis kartlari geldi mi?
            if (waitShort(orderCards)) {
                // Liste otomasyonda bazen once normal gorunup birkac saniye sonra gri ekrana donuyor (elle olmuyor).
                // Bu yuzden kartlar geldikten sonra biraz bekleyip hala yerinde mi diye tekrar bakiyoruz.
                ReusableMethods.wait(ORDER_HISTORY_STABLE_SECONDS);
                int visibleCards = driver.findElements(orderCards).size();
                if (visibleCards > 0) {
                    lastOrderHistoryLoadMillis = System.currentTimeMillis();
                    boolean dashboardGone = driver.findElements(sidebarOrderHistory).isEmpty();
                    logger.info("Order History sayfasi -> baslik: var, ekrandaki kart sayisi: {}, dashboard menusu kapandi: {} (deneme {}/{})",
                            visibleCards, dashboardGone, attempt, ORDER_HISTORY_MAX_ATTEMPTS);
                    return dashboardGone;
                }
                logger.warn("UYARI: Order History listesi once gorundu, {} sn sonra kayboldu (gri ekran) (deneme {}/{})",
                        ORDER_HISTORY_STABLE_SECONDS, attempt, ORDER_HISTORY_MAX_ATTEMPTS);
            }

            // 3) Sayfa acik ama liste yok (gri ekran): sayfadan cikip tekrar giriyoruz
            String screen = describeScreen();
            if (attempt < ORDER_HISTORY_MAX_ATTEMPTS) {
                logger.warn("UYARI: Order History acildi ama siparis listesi yok (gri ekran) (deneme {}/{}). "
                                + "Ekrandaki ogeler: {}. Sayfaya yeniden giriliyor...",
                        attempt, ORDER_HISTORY_MAX_ATTEMPTS, screen);
                clickBackArrow();
                wait.until(ExpectedConditions.elementToBeClickable(sidebarOrderHistory)).click();
            } else {
                logger.error("Order History'de siparis listesi {} denemede yuklenmedi (gri ekran). Ekrandaki ogeler: {}",
                        ORDER_HISTORY_MAX_ATTEMPTS, screen);
            }
        }
        return false;
    }

    // Oge en fazla ORDER_HISTORY_LOAD_TIMEOUT_SECONDS sn icinde ekranda belirdi mi?
    private boolean waitShort(By locator) {
        try {
            orderHistoryWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Hata ayiklamak icin: ekrandaki yazili ogelerin ilk 15'i (hangi sayfada oldugumuzu logda gormek icin)
    private String describeScreen() {
        List<String> texts = new ArrayList<>();
        try {
            for (WebElement el : driver.findElements(By.xpath("//*[@content-desc!='']"))) {
                String desc = el.getAttribute("content-desc");
                if (desc == null || desc.isBlank()) continue;
                desc = desc.replace("\n", " / ");
                texts.add(desc.length() > 40 ? desc.substring(0, 40) + "..." : desc);
                if (texts.size() == 15) break;
            }
        } catch (Exception e) {
            texts.add("(ekran okunamadi: " + e.getClass().getSimpleName() + ")");
        }
        return texts.toString();
    }

    // ---------- Kriter 2: Goruntuleme (goz) ikonu ----------

    /**
     * Listede goz ikonu tamamen gorunen ilk siparisi bulur ve siparis numarasini dondurur.
     * requiredStatus null ise durum fark etmez; "Pending" verilirse sadece Delivery Status'u Pending olanlar secilir.
     * Gerekirse listeyi asagi kaydirir.
     */
    public String findOrderWithVisibleEye(String requiredStatus) {
        wait.until(ExpectedConditions.presenceOfElementLocated(orderCards));
        Set<String> seen = new HashSet<>();

        for (int i = 0; i <= MAX_ORDER_SWIPES; i++) {
            int before = seen.size();
            int[] area = getListVisibleArea();

            for (WebElement card : driver.findElements(orderCards)) {
                try {
                    String desc = card.getAttribute("content-desc");
                    if (desc == null || desc.isBlank()) continue;
                    String orderNo = getOrderNo(desc);
                    seen.add(orderNo);

                    String status = getCardField(desc, "Delivery Status:");
                    if (requiredStatus != null && !requiredStatus.equalsIgnoreCase(status)) continue;

                    if (isEyeFullyVisible(card.getRect(), area)) {
                        logger.info("Secilen siparis: {} (Delivery Status: {}, Total: {})",
                                orderNo, status, getCardField(desc, "Total:"));
                        return orderNo;
                    }
                } catch (StaleElementReferenceException ignored) {
                    // Kart o anda yeniden cizildi; bir sonraki turda tekrar okunur
                }
            }

            // Ilk turdan sonra yeni kart gelmediyse listenin sonuna ulastik
            if (i > 0 && seen.size() == before) {
                break;
            }
            scrollOrderListDown();
        }

        if (requiredStatus == null) {
            throw new RuntimeException("Order History'de goz ikonu ekranda tam gorunen bir siparis bulunamadi!");
        }
        throw new RuntimeException("Order History'de Delivery Status'u '" + requiredStatus + "' olan siparis kalmadi! "
                + "Bu test her calistiginda bir siparisi iptal ediyor. Uygulamadan Cash On Delivery ile yeni bir siparis verin.");
    }

    /**
     * Ekranda gorunen kartlar icinden siparis numarasi verilen kartin metnini dondurur (yoksa null).
     */
    public String getVisibleOrderCardText(String orderNo) {
        WebElement card = findVisibleOrderCard(orderNo);
        return card == null ? null : card.getAttribute("content-desc");
    }

    /**
     * Goz ikonu aktif mi? Kart gorunur, enabled ve tiklanabilir olmali; ikonun tamami ekranin gorunur alaninda olmali.
     */
    public boolean isOrderEyeIconActive(String orderNo) {
        WebElement card = findVisibleOrderCard(orderNo);
        if (card == null) {
            logger.error("{} numarali siparisin karti ekranda bulunamadi", orderNo);
            return false;
        }
        Rectangle r = card.getRect();
        Point eye = getEyePoint(r);
        boolean displayed = card.isDisplayed();
        boolean enabled = card.isEnabled();
        boolean clickable = "true".equals(card.getAttribute("clickable"));
        boolean eyeVisible = isEyeFullyVisible(r, getListVisibleArea());
        logger.info("{} goz ikonu -> konum: ({}, {}), gorunur: {}, ikon tam ekranda: {}, enabled: {}, tiklanabilir: {}",
                orderNo, eye.getX(), eye.getY(), displayed, eyeVisible, enabled, clickable);
        return displayed && eyeVisible && enabled && clickable;
    }

    /**
     * Kartin tamamina degil, tam olarak goz ikonunun durdugu noktaya dokunur.
     */
    public void tapOrderEyeIcon(String orderNo) {
        WebElement card = findVisibleOrderCard(orderNo);
        if (card == null) {
            throw new RuntimeException(orderNo + " numarali siparisin karti ekranda bulunamadi!");
        }
        Rectangle r = card.getRect();
        if (!isEyeFullyVisible(r, getListVisibleArea())) {
            throw new RuntimeException(orderNo + " numarali siparisin goz ikonu ekranda tam gorunmuyor!");
        }
        Point eye = getEyePoint(r);
        logger.info("{} siparisinin goz ikonuna dokunuluyor -> ({}, {})", orderNo, eye.getX(), eye.getY());
        tapAt(eye.getX(), eye.getY());
    }

    // ---------- Order Details ----------

    public boolean isOrderDetailsPageOpened() {
        boolean title = isVisibleWithWait("Order Details");
        boolean orderHistoryGone = driver.findElements(orderHistoryTitle).isEmpty();
        logger.info("Order Details sayfasi -> baslik: {}, Order History listesi kapandi: {}", title, orderHistoryGone);
        return title && orderHistoryGone;
    }

    /**
     * Ayni satirda, etiketin saginda duran '$' ile baslayan degeri dondurur.
     * Ornek: "Total" (x=132, y=2568) -> "$45.70" (x=1107, y=2568)
     * Order Details ve fatura ekraninda ayni sekilde calisir.
     */
    public String getRowValue(String label) {
        scrollDetailsUntilVisible(label);
        try {
            String value = wait.until(d -> {
                Rectangle labelRect = d.findElement(AppiumBy.accessibilityId(label)).getRect();
                for (WebElement el : d.findElements(myAccountTexts)) {
                    String desc = el.getAttribute("content-desc");
                    if (desc == null || !desc.startsWith("$")) continue;
                    Rectangle r = el.getRect();
                    boolean sameRow = Math.abs(r.getY() - labelRect.getY()) <= 15;
                    boolean onRight = r.getX() > labelRect.getX() + labelRect.getWidth();
                    if (sameRow && onRight) {
                        return desc;
                    }
                }
                return null; // null ise wait tekrar dener
            });
            logger.info("'{}' satirindaki deger: {}", label, value);
            return value;
        } catch (TimeoutException e) {
            throw new RuntimeException("'" + label + "' satirinin degeri bulunamadi!");
        }
    }

    public boolean isButtonActive(String description) {
        if (!scrollDetailsUntilVisible(description)) {
            logger.error("'{}' butonu sayfada bulunamadi", description);
            return false;
        }
        WebElement button = driver.findElement(AppiumBy.accessibilityId(description));
        boolean displayed = button.isDisplayed();
        boolean enabled = button.isEnabled();
        boolean clickable = "true".equals(button.getAttribute("clickable"));
        logger.info("'{}' butonu -> gorunur: {}, enabled: {}, tiklanabilir: {}", description, displayed, enabled, clickable);
        return displayed && enabled && clickable;
    }

    public void clickDetailsButton(String description) {
        if (!scrollDetailsUntilVisible(description)) {
            throw new RuntimeException("'" + description + "' butonu sayfada bulunamadi!");
        }
        clickByAccessibility(description);
    }

    /**
     * Order Details'in ust kismindaki bilgi kartinda etiketin sagindaki degeri dondurur.
     * Ornek: "Order ID:" -> "#1510261882", "Order Status:" -> "Canceled"
     * Sayfa asagi kaymis acildigi icin once yukari kaydirir.
     * Etiket ile deger ayni satirda ama birkac piksel kayik olabiliyor (ornek: 1565 / 1547); bu yuzden
     * satir ortalarini 30 px toleransla karsilastiriyoruz.
     */
    public String getDetailsFieldValue(String label) {
        if (!scrollDetailsUpUntilVisible(label)) {
            throw new RuntimeException("Order Details'te '" + label + "' etiketi bulunamadi!");
        }
        try {
            String value = wait.until(d -> {
                Rectangle l = d.findElement(AppiumBy.accessibilityId(label)).getRect();
                int labelCenter = l.getY() + l.getHeight() / 2;
                String best = null;
                int bestX = Integer.MAX_VALUE;
                for (WebElement el : d.findElements(myAccountTexts)) {
                    String desc = el.getAttribute("content-desc");
                    if (desc == null || desc.isBlank() || desc.equals(label)) continue;
                    Rectangle r = el.getRect();
                    int center = r.getY() + r.getHeight() / 2;
                    boolean sameRow = Math.abs(center - labelCenter) <= 30;
                    boolean onRight = r.getX() > l.getX() + l.getWidth();
                    if (sameRow && onRight && r.getX() < bestX) {
                        bestX = r.getX();
                        best = desc.trim();
                    }
                }
                return best; // null ise wait tekrar dener
            });
            logger.info("Order Details -> '{}' {}", label, value);
            return value;
        } catch (TimeoutException e) {
            throw new RuntimeException("Order Details'te '" + label + "' degeri bulunamadi!");
        }
    }

    // Iptalden sonra Order Details'in ustunde kirmizi "Order Canceled" kutusu cikiyor
    public boolean isOrderCanceledBannerShown() {
        boolean shown = scrollDetailsUpUntilVisible("Order Canceled");
        logger.info("Order Details -> 'Order Canceled' kutusu gorunur: {}", shown);
        return shown;
    }

    // ---------- Kriter 3: Fatura (Download Receipt) ----------

    public boolean isReceiptOpened() {
        boolean printInvoice = isVisibleWithWait("Print Invoice");
        logger.info("Fatura ekrani -> Print Invoice butonu: {}", printInvoice);
        return printInvoice;
    }

    // Fatura ekranindaki siparis no: "Order#1510261882" -> "#1510261882"
    public String getReceiptOrderNo() {
        try {
            String desc = wait.until(ExpectedConditions.visibilityOfElementLocated(receiptOrderNumber))
                    .getAttribute("content-desc");
            String orderNo = desc.substring("Order".length()).trim();
            logger.info("Faturadaki siparis no: {}", orderNo);
            return orderNo;
        } catch (TimeoutException e) {
            throw new RuntimeException("Fatura ekraninda siparis numarasi (Order#...) bulunamadi!");
        }
    }

    // ---------- Kriter 4: Siparis iptali (Cancel Order) ----------

    /**
     * Iptal bildirimi birkac saniye sonra kayboluyor; bu yuzden tiklamadan hemen sonra cagrilmali.
     */
    public boolean isCancelSuccessMessageShown() {
        try {
            String message = wait.until(ExpectedConditions.presenceOfElementLocated(cancelSuccessMessage))
                    .getAttribute("content-desc");
            logger.info("Iptal bildirimi: {}", message.replace("\n", " / "));
            return true;
        } catch (TimeoutException e) {
            logger.error("'Order Canceled Successfully' bildirimi 20 sn icinde gorunmedi");
            return false;
        }
    }

    public boolean isCancelOrderButtonGone() {
        boolean gone = isNotVisibleWithWait("Cancel Order");
        logger.info("Cancel Order butonu kayboldu mu: {}", gone);
        return gone;
    }

    /**
     * Sol ustteki geri okuna basar.
     * Iptalden sonra cikan yesil bildirim ekranin ust kismini (geri okunun uzerini) kapliyor;
     * bildirim kaybolmadan basilirsa dokunus bildirime gidiyor ve sayfa degismiyor.
     * Bu yuzden once bildirimin kaybolmasini bekliyoruz. Order Details yine de kapanmazsa
     * Android'in geri tusunu kullaniyoruz.
     */
    public void clickBackArrow() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(cancelSuccessMessage));
        } catch (TimeoutException e) {
            logger.warn("Iptal bildirimi 20 sn'de kaybolmadi; yine de geri okuna basiliyor");
        }

        boolean onOrderDetails = !driver.findElements(AppiumBy.accessibilityId("Order Details")).isEmpty();
        logger.info("Sol ustteki geri okuna basiliyor");
        wait.until(ExpectedConditions.elementToBeClickable(backArrow)).click();

        if (onOrderDetails && !isGoneShort(AppiumBy.accessibilityId("Order Details"))) {
            logger.warn("Geri okuna basildi ama Order Details {} sn'de kapanmadi; Android geri tusu kullaniliyor",
                    ORDER_HISTORY_LOAD_TIMEOUT_SECONDS);
            driver.navigate().back();
        }
    }

    // Oge en fazla ORDER_HISTORY_LOAD_TIMEOUT_SECONDS sn icinde ekrandan kayboldu mu?
    private boolean isGoneShort(By locator) {
        try {
            return orderHistoryWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Siparisin Order History listesindeki Delivery Status degerini dondurur.
     * Once ekranda ve asagisinda arar; bulamazsa ya da durum henuz guncellenmediyse
     * sayfayi bastan acip (dashboard -> Order History) tekrar arar.
     */
    public String getOrderStatusInList(String orderNo, String expectedStatus) {
        String desc = scanOrderListFor(orderNo);
        String status = desc == null ? null : getCardField(desc, "Delivery Status:");

        if (status == null || !status.equalsIgnoreCase(expectedStatus)) {
            logger.info("{} icin durum '{}' okundu; liste yenilensin diye Order History sayfasi yeniden aciliyor",
                    orderNo, status);
            reopenOrderHistory();
            desc = scanOrderListFor(orderNo);
            status = desc == null ? null : getCardField(desc, "Delivery Status:");
        }

        if (desc == null) {
            throw new RuntimeException(orderNo + " numarali siparis Order History listesinde bulunamadi!");
        }
        logger.info("{} Order History'deki karti:\n{}", orderNo, desc);
        return status;
    }

    // ---------- US018 yardimci metotlari ----------

    // Kart metninin ilk satiri siparis numarasi: "#1510261882"
    public static String getOrderNo(String cardText) {
        return cardText.split("\n")[0].trim();
    }

    /**
     * Kart metninde etiketin bir alt satirindaki degeri dondurur.
     * Ornek: getCardField(kart, "Delivery Status:") -> "Pending"
     */
    public static String getCardField(String cardText, String label) {
        String[] lines = cardText.split("\n");
        for (int i = 0; i < lines.length - 1; i++) {
            if (lines[i].trim().equalsIgnoreCase(label)) {
                return lines[i + 1].trim();
            }
        }
        return null;
    }

    private WebElement findVisibleOrderCard(String orderNo) {
        for (WebElement card : driver.findElements(orderCards)) {
            try {
                String desc = card.getAttribute("content-desc");
                if (desc != null && getOrderNo(desc).equals(orderNo)) {
                    return card;
                }
            } catch (StaleElementReferenceException ignored) {
                // yeniden cizildi; atla
            }
        }
        return null;
    }

    /**
     * Listeyi asagi kaydirarak siparisi arar, kart metnini dondurur (bulunamazsa null).
     */
    private String scanOrderListFor(String orderNo) {
        wait.until(ExpectedConditions.presenceOfElementLocated(orderCards));
        Set<String> seen = new HashSet<>();

        for (int i = 0; i <= MAX_ORDER_SWIPES; i++) {
            int before = seen.size();
            for (WebElement card : driver.findElements(orderCards)) {
                try {
                    String desc = card.getAttribute("content-desc");
                    if (desc == null || desc.isBlank()) continue;
                    String no = getOrderNo(desc);
                    seen.add(no);
                    if (no.equals(orderNo)) {
                        return desc;
                    }
                } catch (StaleElementReferenceException ignored) {
                    // bir sonraki turda tekrar okunur
                }
            }
            if (i > 0 && seen.size() == before) {
                break;
            }
            scrollOrderListDown();
        }
        return null;
    }

    // Order History -> (geri) dashboard -> Order History: liste en ustten ve guncel verilerle acilir
    private void reopenOrderHistory() {
        clickBackArrow();
        wait.until(ExpectedConditions.elementToBeClickable(sidebarOrderHistory)).click();
        if (!isOrderHistoryPageOpened()) {
            throw new RuntimeException("Order History sayfasi yeniden acilamadi!");
        }
    }

    // Siparis listesinin ekranda gorunen dikey alani: {ust, alt}
    private int[] getListVisibleArea() {
        int top = driver.findElement(orderHistoryTitle).getRect().getY()
                + driver.findElement(orderHistoryTitle).getRect().getHeight();
        List<WebElement> scrolls = driver.findElements(scrollViews);
        int bottom;
        if (scrolls.isEmpty()) {
            // Az siparis varken liste kaydirilamaz; tum kartlar ekrandadir
            bottom = Integer.MAX_VALUE;
        } else {
            Rectangle s = scrolls.get(0).getRect();
            bottom = s.getY() + s.getHeight();
        }
        return new int[]{top, bottom};
    }

    private Point getEyePoint(Rectangle card) {
        return new Point(card.getX() + (int) (card.getWidth() * EYE_X_RATIO),
                card.getY() + (int) (card.getHeight() * EYE_Y_RATIO));
    }

    // Kart kesilmeden gorunuyor ve goz ikonunun tamami listenin gorunur alaninda mi?
    private boolean isEyeFullyVisible(Rectangle card, int[] area) {
        if (card.getHeight() < MIN_FULL_CARD_HEIGHT) {
            return false; // kart ekranin kenarinda kesilmis; ikonun yeri hesaplanamaz
        }
        Point eye = getEyePoint(card);
        return eye.getY() - EYE_MARGIN >= area[0] && eye.getY() + EYE_MARGIN <= area[1];
    }

    private void scrollOrderListDown() {
        Dimension screen = driver.manage().window().getSize();
        int x = screen.getWidth() / 2;
        swipe(x, (int) (screen.getHeight() * 0.70), x, (int) (screen.getHeight() * 0.45));
        ReusableMethods.wait(1);
    }

    /**
     * Order Details sayfasinda oge ekranda tam gorunene kadar asagi kaydirir (en fazla 5 kez).
     * Cok urunlu siparislerde butonlar ekranin altinda kalabiliyor.
     */
    private boolean scrollDetailsUntilVisible(String description) {
        Dimension screen = driver.manage().window().getSize();
        int x = screen.getWidth() / 2;
        int maxSwipes = 5;

        for (int i = 0; i <= maxSwipes; i++) {
            List<WebElement> found = driver.findElements(AppiumBy.accessibilityId(description));
            try {
                if (!found.isEmpty() && found.get(0).isDisplayed() && isFullyOnScreen(found.get(0).getRect())) {
                    return true;
                }
            } catch (StaleElementReferenceException ignored) {
                // tekrar denenecek
            }
            if (i < maxSwipes) {
                swipe(x, (int) (screen.getHeight() * 0.70), x, (int) (screen.getHeight() * 0.40));
                ReusableMethods.wait(1);
            }
        }
        return false;
    }

    // Order Details sayfasinda oge ekranda tam gorunene kadar YUKARI kaydirir (en fazla 5 kez).
    // Sayfa asagi kaymis acildigi icin ustteki Order ID / Order Status bilgilerine ulasmak icin gerekli.
    private boolean scrollDetailsUpUntilVisible(String description) {
        Dimension screen = driver.manage().window().getSize();
        int x = screen.getWidth() / 2;
        int maxSwipes = 5;

        for (int i = 0; i <= maxSwipes; i++) {
            List<WebElement> found = driver.findElements(AppiumBy.accessibilityId(description));
            try {
                if (!found.isEmpty() && found.get(0).isDisplayed() && isFullyOnScreen(found.get(0).getRect())) {
                    return true;
                }
            } catch (StaleElementReferenceException ignored) {
                // tekrar denenecek
            }
            if (i < maxSwipes) {
                swipe(x, (int) (screen.getHeight() * 0.40), x, (int) (screen.getHeight() * 0.75));
                ReusableMethods.wait(1);
            }
        }
        return false;
    }

    /**
     * Ekranda verilen noktaya dokunur.
     * W3C Actions ile yapilan hizli dokunus bu Flutter uygulamasinda Order History listesini gri ekrana
     * dusurebiliyordu; bu yuzden UiAutomator'in kendi tiklamasini (mobile: clickGesture) kullaniyoruz.
     * Bu bir Appium komutudur, uygulamada JavaScript calistirmaz.
     * Dokunmadan once listenin oturmasi icin 1 sn bekliyoruz (elle kullanimdaki gibi).
     */
    private void tapAt(int x, int y) {
        ReusableMethods.wait(1);
        driver.executeScript("mobile: clickGesture", Map.of("x", x, "y", y));
    }

    // =====================================================================
    // ===== ORTAK YARDIMCI METOTLAR =====
    // =====================================================================

    public WebElement waitForAccessibility(String description) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId(description)));
    }

    public boolean isVisibleWithWait(String description) {
        try {
            waitForAccessibility(description);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Oge ekranda yoksa ya da kaybolursa true doner (en fazla 20 sn bekler)
    public boolean isNotVisibleWithWait(String description) {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(AppiumBy.accessibilityId(description)));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickByAccessibility(String description) {
        logger.info("'{}' ogesine tiklaniyor", description);
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId(description))).click();
    }

    private boolean isLoginScreenClosed() {
        return driver.findElements(loginScreenMarker).isEmpty();
    }

    private boolean isLoginErrorShown() {
        return !driver.findElements(loginErrorBox).isEmpty();
    }

    private String getLoginErrorText() {
        try {
            List<WebElement> errors = driver.findElements(loginErrorBox);
            return errors.isEmpty() ? "(hata metni okunamadi)" : errors.get(0).getAttribute("content-desc");
        } catch (Exception e) {
            return "(hata metni okunamadi)";
        }
    }

    private void hideKeyboardIfOpen() {
        try {
            driver.hideKeyboard();
        } catch (Exception ignored) {
            // Klavye zaten kapaliysa hata verir, onemsiz
        }
    }

    private void swipe(int startX, int startY, int endX, int endY) {
        TouchMethods.swipe(startX, startY, endX, endY);
    }
}