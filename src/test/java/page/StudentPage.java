package page;

import com.google.common.collect.ImmutableMap;
import enev.ConfigWriter;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.*;


import javax.sound.midi.InvalidMidiDataException;
import java.time.Duration;
import java.util.*;


public class StudentPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(StudentPage.class);
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    public StudentPage() {
        super();
    }

    public AndroidDriver getDriver() {
        return this.driver;
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Home\")")
    public WebElement homeButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Wishlist\")")
    public WebElement wishlistButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Sign Up\").instance(1)")
    public WebElement registerSubmitButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Wishlist\")")
    public WebElement wishlistTextElement;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Sign In\").instance(0)")
    public WebElement signInTextElement;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(1)")
    public WebElement serchBoxButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Flower Print Foil T-shirt\n" +
            "0 (0  Reviews)\n" +
            "$65.00\"]")
    public WebElement flowerPrintButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(8)")
    public WebElement minusButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(9)")
    public WebElement plusButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(0)")
    public WebElement backButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(2)")
    public WebElement filitreButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
    public WebElement oldPasswordBox;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(1)")
    public WebElement newPasswordBox;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(2)")
    public WebElement comfirmPasswordBox;

    public By mostPopularTitle = AppiumBy.androidUIAutomator(
            "new UiSelector().descriptionContains(\"Most Popular\")");

    public By productCards = AppiumBy.androidUIAutomator(
            "new UiSelector().className(\"android.view.View\").clickable(true)");

    public By productTitle = AppiumBy.androidUIAutomator(
            "new UiSelector().descriptionContains(\"$\")");

    public By sizeOptions = AppiumBy.androidUIAutomator(
            "new UiSelector().descriptionContains(\"Size\")");

    public By colorOptions = AppiumBy.androidUIAutomator(
            "new UiSelector().descriptionContains(\"Color\")");

    public By quantitySelector = AppiumBy.androidUIAutomator(
            "new UiSelector().descriptionContains(\"Quantity\")");

    public By addToCartButton = AppiumBy.androidUIAutomator(
            "new UiSelector().descriptionContains(\"Add to Cart\")");

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Change Password\n" +
            "Password Updated Successfully\")")
    public WebElement successChangePasswordMessage; // success change password

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Success\n" +
            "Register Successfully.\")")
    public WebElement successRegisterMessage; // success register successfully


    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Success\n" +
            "Login Successfully.\")")
    public WebElement successLoginMessage; // success login successfully

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"PROFILE_UPDATE\n" +
            "Profile Updated Successfully\")")
    public WebElement successUpdateProfileMessage; // profile updated successfully

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Success\n" +
            "Logged Out Successfully.\")")
    public WebElement successLogoutMessage; // success logged out successfully

    public WebElement getElementByName(String elementName) {
        if (elementName == null || elementName.isBlank()) {
            return null;
        }
        String normalized = elementName.strip().toLowerCase(Locale.ROOT);

        return switch (normalized) {
            case "wishlist" -> wishlistButton;
            case "home" -> homeButton;
            case "wishlist text" -> wishlistTextElement;
            case "sign in text" -> signInTextElement;
            case "search box" -> serchBoxButton;
            case "flower print" -> flowerPrintButton;
            case "flower print foil t" -> flowerPrintButton;
            case "minus" -> minusButton;
            case "plus" -> plusButton;
            case "back" -> backButton;
            case "filitre" -> filitreButton;
            case "old password" -> oldPasswordBox;
            case "new  password" -> newPasswordBox;
            case "comfirm password" -> comfirmPasswordBox;
            case "success change password" -> successChangePasswordMessage;
            case "success register successfully" -> successRegisterMessage;
            case "success login successfully" -> successLoginMessage;
            case "profile updated successfully" -> successUpdateProfileMessage;
            case "success logged out successfully" -> successLogoutMessage;
            default -> null;
        };
    }

    public void registerWithPhoneNumber(String name, String phoneNumber, String password) {
        logger.info("Student kullanıcısı kayıt oluyor - İsim: {}, Telefon: {}, Şifre: {}", name, phoneNumber, password);

        try {
            AndroidClickUtils.clickByDescription("Sign Up");
            logger.info("Sign Up butonuna tıklandı");

            AndroidClickUtils.sendKeysToTextBoxByIndex(name, 0);
            logger.info("İsim girildi: {}", name);

            AndroidClickUtils.sendKeysToTextBoxByIndex(phoneNumber, 1);
            logger.info("Telefon numarası girildi: {}", phoneNumber);

            AndroidClickUtils.sendKeysToTextBoxByIndex(password, 2);
            logger.info("Şifre girildi");

            registerSubmitButton.click();
            logger.info("Submit butonuna tıklandı");

            logger.info("✅ Kayıt işlemi başarıyla tamamlandı!");

        } catch (Exception e) {
            logger.error("❌ Kayıt işlemi sırasında hata oluştu: {}", e.getMessage());
            throw new RuntimeException("Kayıt işlemi başarısız!", e);
        }


        String suffix = phoneNumber.length() >= 6
                ? phoneNumber.substring(phoneNumber.length() - 6)
                : phoneNumber;

        ConfigWriter.setProperty("name" + suffix, name);
        ConfigWriter.setProperty("phone" + suffix, phoneNumber);
        ConfigWriter.setProperty("password" + suffix, password);

    }


    public void loginNextRegister(String registeredPhoneNumber, String password) {


        VerifyButtons.verifyButton(signInTextElement, "Sign In Text");
        signInTextElement.click();


        logger.info("Açılışta gelen Telefon: '{}' | Şifre: '{}'", registeredPhoneNumber, password);

        if (registeredPhoneNumber == null || registeredPhoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ HATA: registeredPhoneNumber metoda null veya boş geldi!");
        }
        // WebElement phoneBox = driver.findElement(
        //                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)")
        //        );
        // phoneBox.click();
        //        phoneBox.clear(); // Eğer içinde önceden kalan bir değer varsa temizlemek için
        //        phoneBox.sendKeys(registeredPhoneNumber);
        // Dynamic üretilen tel nosu gönderiliyor
        AndroidClickUtils.sendKeysToTextBoxByIndex(registeredPhoneNumber, 0);

        logger.info("Student kullanicisi phoneBox kutusuna {} degerini yazar", registeredPhoneNumber);

        AndroidClickUtils.sendKeysToTextBoxByIndex(password, 1);
        logger.info("Student kullanicisi passwordBox kutusuna ******** degerini yazar");

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
        actions.sendKeys(Keys.ENTER).perform();
        logger.info("remember me butonuna tiklandi");

        WebElement loginPageSubmitButton = driver.findElement(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Sign In\").instance(1)")
        );

        loginPageSubmitButton.click();

    }

    public void verfiyPlusButtonFunctions() {
        AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement qtyLabel = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"Quantity:\")")
        ));
        int qtyLabelBottom = qtyLabel.getRect().getY() + qtyLabel.getRect().getHeight();
        logger.info("📌 Quantity başlık alt sınırı (y): {}", qtyLabelBottom);


        List<WebElement> numbers = driver.findElements(AppiumBy.androidUIAutomator(
                "new UiSelector().descriptionMatches(\"^[0-9]+$\")"
        ));

        WebElement qtyElement = null;
        for (WebElement el : numbers) {
            if (el.getRect().getY() > qtyLabelBottom) {
                qtyElement = el;
                break;
            }
        }
        if (qtyElement == null) {
            throw new RuntimeException("❌ Eski quantity değeri bulunamadı!");
        }

        int eskiQty = Integer.parseInt(qtyElement.getAttribute("content-desc"));
        logger.info("📊 Eski quantity: {}", eskiQty);


        driver.executeScript("mobile: clickGesture", Map.of(
                "x", 385,   // (347+423)/2
                "y", 1544   // (1506+1582)/2
        ));
        logger.info("➕ Plus butonuna tıklandı");


        final int eskiQtyFinal = eskiQty;

        WebElement yeniQtyElement = wait.until(d -> {
            try {
                // Quantity başlığını yeniden al (DOM değişmiş olabilir)
                WebElement label = d.findElement(AppiumBy.androidUIAutomator(
                        "new UiSelector().descriptionContains(\"Quantity:\")"));
                int bottomY = label.getRect().getY() + label.getRect().getHeight();

                List<WebElement> nums = d.findElements(AppiumBy.androidUIAutomator(
                        "new UiSelector().descriptionMatches(\"^[0-9]+$\")"));

                for (WebElement el : nums) {
                    if (el.getRect().getY() > bottomY) {
                        int val = Integer.parseInt(el.getAttribute("content-desc"));
                        if (val > eskiQtyFinal) {   // artış bekleniyor
                            return el;
                        }
                    }
                }
                return null;
            } catch (Exception e) {
                return null;
            }
        });

        int yeniQty = Integer.parseInt(yeniQtyElement.getAttribute("content-desc"));
        logger.info("📊 Yeni quantity: {}", yeniQty);


        Assert.assertTrue(
                String.format("❌ Plus butonu ÇALIŞMADI! Eski: %d → Yeni: %d", eskiQty, yeniQty),
                yeniQty > eskiQty
        );

        Assert.assertTrue(
                String.format("❌ Quantity 1'den büyük olmalı! Gelen: %d", yeniQty),
                yeniQty > 1
        );

        logger.info("✅ ASSERT BAŞARILI: Plus butonu çalışıyor ({} → {})", eskiQty, yeniQty);
    }

    public void verifiyMinusButtonFunctions() {

        String buttonName = "Minus";

        AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


        WebElement qtyLabel = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"Quantity:\")")
        ));
        int qtyLabelBottom = qtyLabel.getRect().getY() + qtyLabel.getRect().getHeight();
        logger.info("📌 Quantity başlık alt sınırı (y): {}", qtyLabelBottom);


        List<WebElement> numbers = driver.findElements(AppiumBy.androidUIAutomator(
                "new UiSelector().descriptionMatches(\"^[0-9]+$\")"
        ));

        WebElement qtyElement = null;
        for (WebElement el : numbers) {
            if (el.getRect().getY() > qtyLabelBottom) {
                qtyElement = el;
                break;
            }
        }
        if (qtyElement == null) {
            throw new RuntimeException("❌ Eski quantity değeri bulunamadı!");
        }

        int eskiQty = Integer.parseInt(qtyElement.getAttribute("content-desc"));
        logger.info("📊 Eski quantity: {}", eskiQty);

        driver.executeScript("mobile: clickGesture", Map.of(
                "x", 139,    // (101+177)/2
                "y", 1544    // (1506+1582)/2
        ));
        logger.info("➖ '{}' butonuna tıklandı", buttonName);


        final int eskiQtyFinal = eskiQty;

        WebElement yeniQtyElement = wait.until(d -> {
            try {
                WebElement label = d.findElement(AppiumBy.androidUIAutomator(
                        "new UiSelector().descriptionContains(\"Quantity:\")"));
                int bottomY = label.getRect().getY() + label.getRect().getHeight();

                List<WebElement> nums = d.findElements(AppiumBy.androidUIAutomator(
                        "new UiSelector().descriptionMatches(\"^[0-9]+$\")"));

                for (WebElement el : nums) {
                    if (el.getRect().getY() > bottomY) {
                        int val = Integer.parseInt(el.getAttribute("content-desc"));
                        if (val < eskiQtyFinal) {   // AZALIŞ bekleniyor
                            return el;
                        }
                    }
                }
                return null;
            } catch (Exception e) {
                return null;
            }
        });

        int yeniQty = Integer.parseInt(yeniQtyElement.getAttribute("content-desc"));
        logger.info("📊 Yeni quantity: {}", yeniQty);


        Assert.assertTrue(
                String.format("❌ '%s' butonu ÇALIŞMADI! Eski: %d → Yeni: %d",
                        buttonName, eskiQty, yeniQty),
                yeniQty < eskiQty
        );

        Assert.assertTrue(
                String.format("❌ Quantity 0'dan küçük olamaz! Gelen: %d", yeniQty),
                yeniQty >= 0
        );

        logger.info("✅ ASSERT BAŞARILI: '{}' butonu çalışıyor ({} → {})",
                buttonName, eskiQty, yeniQty);

    }

    public static List<String> getAllCategories() throws InvalidMidiDataException {

        List<String> allCategories = new ArrayList<>();
        Set<String> gorulen = new HashSet<>();      // tekrar kontrolü için hızlı
        boolean isEndOfList = false;
        int maxSwipe = 15;                          // sonsuz döngü koruması
        int swipeCount = 0;

        while (!isEndOfList && swipeCount < maxSwipe) {

            // ============================================================
            // 1. Mevcut ekrandaki kategorileri topla
            // ============================================================
            List<WebElement> visibleCategories = Driver.getAppiumDriver().findElements(
                    By.xpath("(//android.view.View)[11]//android.view.View")
            );

            int ilkBoyut = allCategories.size();

            for (WebElement category : visibleCategories) {
                String categoryName = category.getAttribute("content-desc");

                if (categoryName != null
                        && !categoryName.equals("null")
                        && !categoryName.trim().isEmpty()
                        && !categoryName.equalsIgnoreCase("See All")   // ⬅️ filtre
                        && !categoryName.matches("^[0-9]+$")           // sadece sayı değil
                        && !categoryName.startsWith("$")               // fiyat değil
                        && !categoryName.contains("Reviews")) {

                    if (gorulen.add(categoryName)) {                   // yeni mi?
                        allCategories.add(categoryName);
                        logger.info("✅ Yeni kategori: {}", categoryName);
                    }
                }
            }

            // ============================================================
            // 2. Sağa kaydır (yatay)
            // ============================================================
            logger.info("➡️ Swipe #{} (şu ana kadar {} kategori)", swipeCount + 1, allCategories.size());
            OptionsMet.swipe(1188, 1162, 0, 1162);   // sağdan sola
            ReusableMethods.wait(1);
            swipeCount++;

            // ============================================================
            // 3. Son kontrol: liste değişti mi?
            // ============================================================
            List<WebElement> currentVisible = Driver.getAppiumDriver().findElements(
                    By.xpath("(//android.view.View)[11]//android.view.View")
            );

            if (currentVisible.isEmpty()) {
                logger.info("🛑 Ekranda hiç kategori kalmadı, dur");
                break;
            }

            // Son görünen kategori adını al (null-safe)
            String currentCategoryName = currentVisible.get(currentVisible.size() - 1)
                    .getAttribute("content-desc");

            // Liste boşsa zaten problem var
            if (allCategories.isEmpty()) {
                logger.warn("⚠️ Hiç kategori toplanamadı!");
                break;
            }

            String lastCategory = allCategories.get(allCategories.size() - 1);

            // Son kategori adı, topladığımız son kategoriden farklı değilse VE
            // yeni bir şey eklenmediyse → liste sonu
            boolean yeniEklendi = allCategories.size() > ilkBoyut;

            if (currentCategoryName != null
                    && currentCategoryName.equals(lastCategory)
                    && !yeniEklendi) {
                logger.info("🛑 Liste sonuna gelindi");
                isEndOfList = true;
            }
        }

        // ============================================================
        // 4. Sonuçları yazdır
        // ============================================================
        logger.info("═══════════════════════════════════════");
        logger.info("📋 TOPLAM {} KATEGORİ:", allCategories.size());
        for (int i = 0; i < allCategories.size(); i++) {
            logger.info("  [{}] {}", i, allCategories.get(i));
        }
        logger.info("═══════════════════════════════════════");

        return allCategories;   // ⬅️ return eklendi, caller kullanabilsin
    }

    public static List<String> getAllPopularBrands() throws InvalidMidiDataException {

        List<String> allBrands = new ArrayList<>();
        Set<String> gorulen = new HashSet<>();
        boolean isEndOfList = false;
        int maxSwipe = 15;
        int swipeCount = 0;

        // Popular Brands yatay container'ı içindeki marka ögelerini hedefleyen XPath
        String brandItemsXpath = "//android.view.View[@content-desc='Popular Brands']" +
                "/following-sibling::android.view.View[1]" +
                "//android.view.View[@clickable='true']";

        while (!isEndOfList && swipeCount < maxSwipe) {

            int ilkBoyut = allBrands.size();

            // 1. Mevcut ekrandaki markaları topla
            List<WebElement> visibleBrands = Driver.getAppiumDriver().findElements(By.xpath(brandItemsXpath));

            for (WebElement brand : visibleBrands) {
                String desc = brand.getAttribute("content-desc");
                String text = brand.getAttribute("text");
                String brandName = (desc != null && !desc.isEmpty()) ? desc
                        : (text != null && !text.isEmpty()) ? text : null;

                if (brandName != null
                        && !brandName.trim().isEmpty()
                        && !brandName.equalsIgnoreCase("See All")
                        && !brandName.matches("^[0-9]+$")
                        && !brandName.startsWith("$")
                        && !brandName.contains("Reviews")) {

                    if (gorulen.add(brandName)) {
                        allBrands.add(brandName);
                        logger.info("✅ Yeni marka eklendi: {}", brandName);
                    }
                }
            }

            // 2. Sağa kaydır (Yatay olarak markaların üzerinde sağdan sola sürükleme)
            logger.info("➡️ Swipe #{} (Şu ana kadar {} marka bulundu)", swipeCount + 1, allBrands.size());

            // Marka container'ının Y koordinatları hiyerarşide ~2400 seviyelerindedir.
            // Ekranda markaların olduğu hizada yatay swipe gerçekleştiriyoruz:
            OptionsMet.swipe(1188, 2400, 200, 2400);
            ReusableMethods.wait(1);
            swipeCount++;

            // 3. Son kontrol: Liste sonuna gelindi mi?
            List<WebElement> currentVisible = Driver.getAppiumDriver().findElements(By.xpath(brandItemsXpath));

            if (currentVisible.isEmpty()) {
                logger.info("🛑 Ekranda hiç marka kalmadı, durduruluyor.");
                break;
            }

            String currentFirstName = currentVisible.get(0).getAttribute("content-desc");
            boolean yeniEklendi = allBrands.size() > ilkBoyut;

            if (!yeniEklendi && swipeCount > 1) {
                logger.info("🛑 Yeni marka gelmedi, liste sonuna ulaşıldı.");
                isEndOfList = true;
            }
        }

        logger.info("═══════════════════════════════════════");
        logger.info("📋 TOPLAM {} MARKA BULUNDU:", allBrands.size());
        for (int i = 0; i < allBrands.size(); i++) {
            logger.info("  [{}] {}", i, allBrands.get(i));
        }
        logger.info("═══════════════════════════════════════");

        return allBrands;
    }

    public void menCategoriesProductsList(String categoryName) {


        Set<String> productList = new LinkedHashSet<>();

        int previousSize = 0;
        boolean hasMoreScroll = true;
        int maxScrolls = 15;
        int scrollCount = 0;

        System.out.println("--- " + categoryName + " Categories Ürün Listesi Taranıyor... ---");

        while (hasMoreScroll && scrollCount < maxScrolls) {


            List<WebElement> productElements = driver.findElements(AppiumBy.className("android.view.View"));

            for (WebElement element : productElements) {
                try {
                    String contentDesc = element.getAttribute("content-desc");

                    if (contentDesc != null && !contentDesc.trim().isEmpty() && contentDesc.contains("$")) {
                        // Urunun ilk satirdaki ismi
                        String productName = contentDesc.split("\n")[0].trim();
                        productList.add(productName);

                    }
                } catch (Exception e) {

                    // Scroll sırasında element stale olabilir, yoksayıyoruz
                }
            }

            // Eğer son kaydırmadan sonra yeni ürün eklenmediyse sayfa sonuna gelmişizdir
            if (productList.size() == previousSize) {
                // İkinci bir doğrulama için 1 kere daha scroll yapıp emin olabilirsiniz
                hasMoreScroll = false;
            } else {
                previousSize = productList.size();
                swipeDown();
                ReusableMethods.wait(1); // *** Elementlerin yüklenmesi için kısa bekleme
                scrollCount++;
            }
        }

        // --- TÜM BİRİKMİŞ ÜRÜNLERİ YAZDIRMA ---
        System.out.println("\n Toplam Bulunan Benzersiz Ürün Sayısı: " + productList.size());
        int count = 1;
        for (String product : productList) {
            System.out.println(count + ". Ürün: " + product);
            count++;
        }
    }

    /**
     * W3C Pointer Input kullanarak sayfayı aşağı kaydıran yardımcı metod.
     */
    public void swipeDown() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(swipe));
    }

    public void summaryFieldProductCount(String actualCount) {
        WebElement summaryField = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").descriptionContains(\"Products Found\")")
        ));

        String rawText = summaryField.getAttribute("content-desc");
        String productCountStr = rawText.replaceAll("\\D+", "");
        int productCount = Integer.parseInt(productCountStr);
        logger.info("Bulunan ürün adedi: " + productCount);


    }

    public int getScannedProductCount(String categoryName) {
        Set<String> productList = new LinkedHashSet<>();
        int previousSize = 0;
        boolean hasMoreScroll = true;
        int maxScrolls = 15;
        int scrollCount = 0;

        while (hasMoreScroll && scrollCount < maxScrolls) {
            List<WebElement> productElements = driver.findElements(AppiumBy.className("android.view.View"));

            for (WebElement element : productElements) {
                try {
                    String contentDesc = element.getAttribute("content-desc");
                    if (contentDesc != null && !contentDesc.trim().isEmpty() && contentDesc.contains("$")) {
                        String productName = contentDesc.split("\n")[0].trim();
                        productList.add(productName);
                    }
                } catch (Exception e) {
                    // Scroll sırasında element stale olursa yoksay
                }
            }

            if (productList.size() == previousSize) {
                hasMoreScroll = false;
            } else {
                previousSize = productList.size();
                swipeDown();
                ReusableMethods.wait(1);
                scrollCount++;
            }
        }

        logger.info("{} kategorisi için taranan benzersiz ürün sayısı: {}", categoryName, productList.size());
        return productList.size(); // Tarama sonucu bulunan sayıyı döndürür
    }

    public int getSummaryProductCount() {
        WebElement summaryField = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").descriptionContains(\"Products Found\")")
        ));

        String rawText = summaryField.getAttribute("content-desc");
        String productCountStr = rawText.replaceAll("\\D+", "");
        int productCount = Integer.parseInt(productCountStr);

        logger.info("Summary alanından okunan ürün sayısı: {}", productCount);
        return productCount; // Ekranda yazan summary sayısını döndürür
    }

    public void navigateToHome() {
        // Uygulamayı ön plana getirip ana sayfadan/ilk halinden başlatır
        driver.activateApp("com.wise.querycart");
        logger.info("Uygulama ana sayfasına dönüldü.");
    }


    public void navigateToMainActivity() {
        // Android Intent kullanarak doğrudan MainActivity'ye yönlendirir
        driver.executeScript("mobile: startActivity", ImmutableMap.of(
                "intent", "com.wise.querycart/com.wise.querycart.MainActivity"
        ));
        logger.info("MainActivity doğrudan başlatıldı.");
    }

    public void returnToHomeScreen() {
        // Uygulamayı ana ekrana resetlemeden ön plana getirir/ana sayfaya döndürür
        driver.activateApp("com.wise.querycart");
        logger.info("Ana sayfaya dönüldü.");
    }

    public boolean isWishlistButtonDisplayed() {
        // Ekranda Wishlist butonunu arar ve görünürlüğünü kontrol eder
        WebElement wishlistBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("wishlist_icon") // veya UiAutomator/XPath ile selector'ınız
        ));

        return wishlistBtn.isDisplayed();
    }

    /**
     * Sayfadaki tüm ürünlerin fiyatlarını tarayıp sırasıyla Liste olarak döndürür.
     */
    public List<Double> getScannedProductPrices(String categoryName) {
        List<Double> priceList = new ArrayList<>();
        List<String> processedProducts = new ArrayList<>(); // Tekrarları önlemek ve sırayı korumak için

        int previousSize = 0;
        boolean hasMoreScroll = true;
        int maxScrolls = 15;
        int scrollCount = 0;

        while (hasMoreScroll && scrollCount < maxScrolls) {
            List<WebElement> productElements = driver.findElements(AppiumBy.className("android.view.View"));

            for (WebElement element : productElements) {
                try {
                    String contentDesc = element.getAttribute("content-desc"); // XML'deki content-desc içeriği

                    if (contentDesc != null && contentDesc.contains("$")) {
                        // XML Örneği: "Men's Athletic Cushioned...\n0 (0 Reviews)\n$12.00"[cite: 2]
                        String[] lines = contentDesc.split("\n");
                        String productName = lines[0].trim();

                        // Aynı ürünü tekrar eklememek için kontrol
                        if (!processedProducts.contains(productName)) {
                            // Fiyat satırını bul ($ simgesi olan satır)
                            for (String line : lines) {
                                if (line.contains("$")) {
                                    // Fiyat metninden sadece sayısal kısmı alma (Örn: "$12.00" -> 12.00)
                                    String priceText = line.replaceAll("[^0-9.]", "").trim();
                                    if (!priceText.isEmpty()) {
                                        double price = Double.parseDouble(priceText);
                                        priceList.add(price);
                                        processedProducts.add(productName);
                                        logger.info("Bulunan Ürün: {} | Fiyat: ${}", productName, price);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    // Stale element hatası olursa yoksay
                }
            }

            if (processedProducts.size() == previousSize) {
                hasMoreScroll = false;
            } else {
                previousSize = processedProducts.size();
                swipeDown(); //[cite: 1]
                ReusableMethods.wait(1); //[cite: 1]
                scrollCount++;
            }
        }

        logger.info("{} kategorisi için toplanan fiyat listesi: {}", categoryName, priceList);
        return priceList;
    }


}
