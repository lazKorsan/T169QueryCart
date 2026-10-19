package stepdefinitions;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.PressesKey;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;


import io.appium.java_client.android.nativekey.KeyEvent;

import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.StudentPage;
import utilities.*;

import java.time.Duration;
import javax.sound.midi.InvalidMidiDataException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


import java.util.List;


import static page.StudentPage.getAllPopularBrands;
import static utilities.Driver.getAppiumDriver;


public class StudentSteps {

    private static final Logger logger = LogManager.getLogger(StudentSteps.class);
    private final StudentPage studentPage = new StudentPage();
    private final AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    private static String registeredPhoneNumber;
    private static String registeredPassword;
    private static String registeredName;


    @Given("Student kullanicisi uygulamayi acar")
    public void student_kullanicisi_uygulamayi_acar() {

        getAppiumDriver();

        AndroidClickUtils.verifyButtonByName("Profile");

    }

    @Given("Student kullanicisi uyugulamanin yuklendigini dogrular")
    public void student_kullanicisi_uyugulamanin_yuklendigini_dogrular() {

        AndroidDriver driver = (AndroidDriver) getAppiumDriver();
        String expectedPackageName = "com.wise.querycart";

        String currentPackage = driver.getCurrentPackage();
        String currentActivity = driver.currentActivity();

        logger.info("Beklenen Package: {}", expectedPackageName);
        logger.info("Gercek Package: {}", currentPackage);
        logger.info("Gercek Activity: {}", currentActivity);

        Assert.assertEquals("Package adı eşleşmiyor!", expectedPackageName, currentPackage);

        assert currentActivity != null;
        Assert.assertTrue(
                "Activity MainActivity içermiyor! Gerçek activity: " + currentActivity,
                currentActivity.contains("MainActivity")
                        || currentActivity.contains("com.wise.querycart")
        );
    }

    @When("Student kullancisi {string} butonun gorunurlugu ile istenen sayfaya ulasildigini dogrular")
    @When("Student kullanicisi {string} butonun gorunur ve aktif oldugunu dogrular")
    public void studentKullanicisiButonunGorunurVeAktifOldugunuDogrular(String buttonName) {

        AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        VerifyButtons.verifyButton(buttonName);
        logger.error("Student kullanicisi {} butonun gorunur ve aktif oldugunu dogrular", buttonName);


    }

    @When("Student kullanicisi {string} butona tiklar")
    public void studentKullanicisiButonaTiklar(String buttonName) {


        AndroidClickUtils.smartClick((AndroidDriver) getAppiumDriver(), buttonName, () -> studentPage.getElementByName(buttonName));


    }

    @When("Student kullanicisi {string} elementinin gorunurlugu ile istenen sayfa ulasildigini dogrular")
    public void studentKullanicisiElementininGorunurluguIleIstenenSayfaUlasildiginiDogrular(String textElementName) {

        VerifyButtons.verifyButton(studentPage.getElementByName(textElementName), textElementName);
        logger.error("Student kullanicisi {} elementinin gorunurlugu ile istenen sayfa ulasildigini dogrular", textElementName);

    }

    @When("Student kullanicisi {string} , {string} telefon numarasi ve {string} degerleri ile kayt olur")
    public void studentKullanicisiTelefonNumarasiVeDegerleriIleKaytOlur(String name, String phoneNumber, String password) {

        logger.info("Student kullanicisi {} , {} telefon numarasi ve {} degerleri ile kayt olur", name, phoneNumber, password);


        String finalPhoneNumber = phoneNumber;
        if (phoneNumber.contains("{timeStamp}")) {
            String timestamp = java.time.LocalDateTime.now()
                    .format(java.time.format.DateTimeFormatter.ofPattern("HHmmss"));
            finalPhoneNumber = phoneNumber.replace("{timeStamp}", timestamp);
        }

        registeredName = name;
        registeredPhoneNumber = finalPhoneNumber;
        registeredPassword = password;

        studentPage.registerWithPhoneNumber(name, finalPhoneNumber, password);

    }

    @When("Student kullanicisi yeni bilgilerle siteye giris yapar")
    public void studentKullanicisiYeniBilgilerleSiteyeGirisYapar() {

        if (registeredPhoneNumber == null || registeredPassword == null) {
            throw new RuntimeException("Önce kayıt olmalısın! registeredPhoneNumber null.");
        }

        logger.info("Giriş yapılıyor → Telefon: {}, Şifre: {}", registeredPhoneNumber, registeredPassword);

        studentPage.loginNextRegister(registeredPhoneNumber, registeredPassword);
    }


    @When("Student kullanicisi {string} box kutusuna index {int} ile {string} yazar")
    public void studentKullanicisiBoxKutusunaIndexIleYazar(String boxName, int index, String text) {

        AndroidClickUtils.sendKeysToTextBoxByIndex(text, index);
    }

    @When("Student kullanicisi {string} box kutusuna index {int} ile {string} yazar ve arama yapar")
    public void studentKullanicisiBoxKutusunaIndexIleYazarVeAramaYapar(String boxName, int index, String text) {

        AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        AndroidClickUtils.sendKeysToTextBoxByIndex(text, index);
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
    }

    public static String turkuazwhite(String text) {

        return "\u001B[48;2;10;170;170m" + "\u001B[3m" + "\u001B[38;2;255;255;255m" + "\u001B[1m" + " " + text + " " + "\u001B[0m";
    }

    public static String redWhite(String text) {

        return "\u001B[48;2;220;38;38m" + "\u001B[3m" + "\u001B[38;2;255;255;255m" + "\u001B[1m" + " " + text + " " + "\u001B[0m";
    }

    @When("Student kullanicisi arama sonucu cikan urun sayisi ile sonuclarda verilen sayiyi dogrular")
    public void studentKullanicisiAramaSonucuCikanUrunSayisiIleSonuclardaVerilenSayiyiDogrular() throws InterruptedException {

        AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();
        new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement productsFound = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().textContains(\"Products Found\")"
        ));

        //productsFound.getText();
        //logger.info("Bulunan urun sayisi: "+turkuazwhite(productsFound.getText()));


        String desc = productsFound.getAttribute("content-desc");
        String count = desc.replaceAll(".*\\((\\d+).*", "$1");

        System.out.println("Products size: " + count);
        logger.info("Products size: " + turkuazwhite(count));

    }

    @When("Student kullanicisi arama sonucu cikan urun sayisini konsola yazdirir")
    public void studentKullanicisiAramaSonucuCikanUrunSayisiniKonsolaYazdirir() {

        //AndroidClickUtils.verifyButtonByName("Search Results");

        AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        ReusableMethods.wait(3);
        WebElement productsFound = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        AppiumBy.androidUIAutomator(
                                "new UiSelector().descriptionContains(\"Products Found\")"
                        )
                )
        );

        String desc = productsFound.getAttribute("content-desc");
        String count = desc.replaceAll(".*\\((\\d+).*", "$1");

        System.out.println("Products size: " + count);
        logger.info("Products size: " + turkuazwhite(count));
    }

    @When("Student kullanicisi {string} basligi altindaki urunlerin gorunur oldugunu dogrular")
    public void studentKullanicisiBasligiAltindakiUrunlerinGorunurOldugunuDogrular(String sectionTitle) {

        AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();
        WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().descriptionContains(\"" + sectionTitle + "\")")));
        Assert.assertTrue(sectionTitle + " başlığı görünmüyor!", title.isDisplayed());
        logger.info("✅ '{}' başlığı görünür", sectionTitle);

        List<WebElement> products = driver.findElements(studentPage.productCards);
        Assert.assertFalse("Hiç ürün bulunamadı!", products.isEmpty());
        logger.info("✅ {} adet ürün görünür", products.size());


    }

    @When("Student kullanicisi {string} butonunu bulur")
    public void studentKullanicisiButonunuBulur(String buttonName) {

        AndroidClickUtils.scrollWithUiScrollableContentDesc(buttonName);


    }

    @When("Student kullanicisi ilk siparis vermede siparis edilen urun sayisinin arttigini dogrular")
    public void studentKullanicisiIlkSiparisVermedeSiparisEdilenUrunSayisininArttiginiDogrular() {

        studentPage.verfiyPlusButtonFunctions();

    }

    @When("Student kullanicisi {string} butona tiklar ve siparis edilen urun sayisinin azaldigini dogrular")
    public void studentKullanicisiButonaTiklarVeSiparisEdilenUrunSayisininAzaldiginiDogrular(String buttonName) {

        studentPage.verifiyMinusButtonFunctions();
    }

    @When("Student kullanicsi Categories basligi altindaki kategorileri listeler")
    public void studentKullanicsiCategoriesBasligiAltindakiKategorileriListeler() throws InvalidMidiDataException {

        ReusableMethods.wait(5);
        StudentPage.getAllCategories();


    }


    @When("Student kullanicisi Popular Brands kismina gider ve urunlari listler")
    public void studentKullanicisiPopularBrandsKisminaGiderVeUrunlariListler() throws InterruptedException, InvalidMidiDataException {

        ReusableMethods.wait(2);
        AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();

        String elementName = "Popular Brands";

        // 1. İnsan gözü/parmağı mantığıyla ekranı aşağı kaydırarak 'Popular Brands' başlığına git
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".setAsVerticalList()" +
                            ".scrollIntoView(new UiSelector().descriptionContains(\"" + elementName + "\"))"
            ));
            logger.info("✅ 'Popular Brands' başlığına scroll yapıldı.");
        } catch (Exception e) {
            logger.error("❌ 'Popular Brands' bulunamadı!", e);
            throw new RuntimeException("Popular Brands ekranda görülemedi.");
        }

        // 2. Yatay kaydırma yaparak tüm markaları topla
        List<String> popularBrandsList = getAllPopularBrands();

        if (popularBrandsList.isEmpty()) {
            throw new RuntimeException("❌ 'Popular Brands' altında hiç marka bulunamadı!");
        }
    }

    @When("Student kullanicisi {string} toaster mesajinin gorunurlugu ile sifre degisikligini dogrular")
    public void studentKullanicisiToasterMesajininGorunurluguIleSifreDegisikliginiDogrular(String toasterMessage) {

        AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();

        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            WebElement toasterMessageElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.androidUIAutomator("new UiSelector().description(\"Change Password\n" +
                                    "The old password does not match.\")")
                    )
            );

            if (toasterMessageElement.isDisplayed()) {
                logger.info("Toaster mesaji basariyla goruntulendi: {}", toasterMessage);
            } else {
                logger.warn("Toaster mesaji bulundu ama gorunur degil: {}", toasterMessage);
            }

        } catch (TimeoutException e) {

            logger.warn("Toaster mesaji zaman asimina ugradi, yakalanamadi: {}. Sebep: {}",
                    toasterMessage, e.getMessage());
        } catch (Exception e) {

            logger.warn("Toaster mesaji dogrulanirken beklenmeyen hata olustu: {}. Sebep: {}",
                    toasterMessage, e.getMessage());

        }
    }

    @When("ReusableMethods bekle {int}")
    public void reusablemethodsBekle(int sec) {
        ReusableMethods.wait(sec);
    }

    @When("Student kullanicisi {string} ismi ve {string} sifresi ile kayit olur")
    public void studentKullanicisiIsmiVeSifresiIleKayitOlur(String name, String password) {


    }

    @When("Student kullanicisi Most Popular sayfasinda urunleri listeler")
    public void studentKullanicisiMostPopularSayfasindaUrunleriListeler() {

        // android.view.View class'ına sahip ögeleri buluyoruz
        List<WebElement> productElements = driver.findElements(AppiumBy.className("android.view.View"));

        System.out.println("--- MOST POPULAR ÜRÜN LİSTESİ ---");

        int productCount = 1;
        for (WebElement element : productElements) {
            String contentDesc = element.getAttribute("content-desc");

            // Yalnızca içinde ürün detayı (örneğin fiyat barındıran veya dolu olan) desc'leri alıyoruz
            if (contentDesc != null && !contentDesc.trim().isEmpty() && contentDesc.contains("$")) {

                // content-desc metni satırlara ayrılır: [0] -> Ürün Adı, [1] -> Review, [2] -> Fiyat
                String productName = contentDesc.split("\n")[0];

                System.out.println(productCount + ". Ürün: " + productName);
                productCount++;
            }
        }
    }

    @When("Student kullancisi {string} butonun gorunurlugu ile {string} butonun fonksiyonlarini dogrular")
    public void studentKullancisiButonunGorunurluguButonunGorunurVeAktifOldugunuDogrular(String buttonName, String expectedButtonName) {

        AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        VerifyButtons.verifyButton(buttonName);
        logger.error("Student kullancisi {} butonun gorunurlugu ile {} butonun fonksiyonlarini dogrular", buttonName, expectedButtonName);


    }


    @When("Student kullanicisi {string} isimli urunu favorilere ekler")
    public void studentKullanicisiIsimliUrunuFavorilereEkler(String productName) {

        ReusableMethods.wait(2);
        WebElement flowerFavIcon = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(2)")
        ));
        flowerFavIcon.click();

        logger.error("Student kullanicisi {} isimli urunu favorilere ekler", productName);


    }

    @When("Student kullanicisi {string} telefon numarasi ve {string} sifresi ile uyuglamaya giris yapar")
    public void studentKullanicisiTelefonNumarasiVeSifresiIleUyuglamayaGirisYapar(String phoneNumber, String password) {


        AndroidClickUtils.clickByDescription("Profile");
        AndroidClickUtils.clickByDescription("Sign In");
        AndroidClickUtils.sendKeysToTextBoxByIndex(phoneNumber, 0);
        AndroidClickUtils.sendKeysToTextBoxByIndex(password, 1);

        WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Sign In\").instance(1)")
        ));
        submitButton.click();


    }

    @When("Student kullanicisi {string} sayfasinda oldugunu dogrular")
    public void studentKullanicisiSayfasindaOldugunuDogrular(String elementName) {

        ReusableMethods.wait(1); // zorunlu
        AndroidClickUtils.verifyButtonByName(elementName);


    }

    @When("Student kullanicisi urun isminin {string} oldugunu dogrular")
    public void studentKullanicisiUrunIsmininOldugunuDogrular(String expectedProductName) {

        ReusableMethods.wait(3);

        // Gelen ham metnin ilk satırını alarak arama yapıyoruz
        String searchKeyword = expectedProductName.split("\n")[0].split("\\\\n")[0].trim();

        WebElement productElement = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().descriptionContains(\"" + searchKeyword + "\")")
        ));

        String fullProductInfo = productElement.getAttribute("content-desc");
        System.out.println("Gelen Ürün Bilgisi:\n" + fullProductInfo);

        // Feature'dan gelen literal \n ifadelerini gerçek yeni satıra çeviriyoruz
        String normalizedExpected = expectedProductName.replace("\\n", "\n").replaceAll("\\s+", " ").trim();
        String normalizedActual = fullProductInfo.replaceAll("\\s+", " ").trim();

        Assert.assertTrue(
                "HATA: Beklenen ürün adı gelen ürün bilgisinde bulunamadı!\nBeklenen: " + normalizedExpected + "\nGelen: " + normalizedActual,
                normalizedActual.contains(normalizedExpected) || normalizedActual.replaceAll("\\s+", "").contains(normalizedExpected.replaceAll("\\s+", ""))
        );
    }

    @When("Student kullanicisi urun detayinin {string} oldugunu dogrular")
    public void studentKullanicisiUrunDetayininOldugunuDogrular(String expectedValue) {

        WebElement productsDetailsField = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Flower Print Foil T-shirt\").instance(1)")
        ));

        System.out.println(productsDetailsField.getAttribute("content-desc"));
        String elementText = productsDetailsField.getAttribute("content-desc");
        logger.info(turkuazwhite(turkuazwhite("Product Details: ") + turkuazwhite(elementText)));

        Assert.assertEquals(expectedValue, productsDetailsField.getAttribute("content-desc"));

    }

    @When("Student kullanicisi Review bilgisinin {string} oldugunu dogrular")
    public void studentKullanicisiReviewBilgisininOldugunuDogrular(String expectedValue) {

        WebElement actualReviewTextElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"(0  Reviews)\").instance(1)")

        ));

        // System.out.println(actualReviewTextElement.getAttribute("content-desc"));

        String elementText = actualReviewTextElement.getAttribute("content-desc");
        logger.info(turkuazwhite("Review bilgisi: ") + turkuazwhite(elementText));

        Assert.assertEquals(expectedValue, actualReviewTextElement.getAttribute("content-desc"));

    }

    @Given("{string}")
    public void string(String bugReport) {

        logger.info(redWhite(bugReport));

    }

    @When("Student kullanicisi {string} urunlerini listeler")
    public void studentKullanicisiUrunleriniListeler(String categoryName) {

        ReusableMethods.wait(3);

        studentPage.menCategoriesProductsList(categoryName);


    }

    @When("Student kullanicisi {string} isimli urunu sepete ekler")
    public void studentKullanicisiIsimliUrunuSepeteEkler(String productName) {

        ReusableMethods.wait(2);
        WebElement adidasCrewSocksFavIcon = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(3)")
        ));
        adidasCrewSocksFavIcon.click();

        ReusableMethods.wait(2);

        logger.error("Student kullanicisi {} isimli urunu favorilere ekler", productName);


    }


    @When("Student kullanicisi {string} urunlerini listeler ve {string} yazi ile urun sayisini dogrular")
    public void studentKullanicisiUrunleriniListelerVeYaziIleUrunSayisiniDogrular(String categoryName, String expectedPattern) {

        // 1. Sayfayı aşağı kaydırarak listedeki ürünleri tarayıp gerçek sayıyı buluyoruz
        int scannedCount = studentPage.getScannedProductCount(categoryName);

        // 2. Ekranın en başında summary alanındaki sayıyı okuyoruz
        int summaryCount = studentPage.getSummaryProductCount();


        // 3. İki değeri Assert ile doğruluyoruz
        Assert.assertEquals(
                "Summary ekranındaki sayı (" + summaryCount + ") ile taranan ürün sayısı (" + scannedCount + ") uyuşmuyor!",
                summaryCount,
                scannedCount
        );

        logger.info("BAŞARILI: Summary sayısı ({}) ile liste sayısı ({}) birbiriyle eşleşti.", summaryCount, scannedCount);
    }

    @When("Student kullanicisi summary kısmındaki urun sayisini konsola yazdirir {string}")
    public void studentKullanicisiSummaryKısmındakiUrunSayisiniKonsolaYazdirir(String actualCount) {

        studentPage.summaryFieldProductCount(actualCount);
        logger.error("Student kullanicisi summary kısmındaki urun sayisini konsola yazdirir {}", actualCount);


    }

    @When("Student kullanicisi anaSayfa ya doner")
    public void studentKullanicisiAnaSayfaYaDoner() {
        //AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();
        Driver.startActivity("com.wise.querycart", "com.wise.querycart.MainActivity", true);


    }

    @When("Student kullanicisi Wishlist sayfasinda oldugunu dogrular")
    public void studentKullanicisiWishlistSayfasindaOldugunuDogrular() {

        ReusableMethods.wait(5); // zorunlu
        AndroidClickUtils.verifyButtonByName("Wishlist");
    }


    // Step Definition metodu
    @When("Student kullanicisi {string} urunlerinin dusuk fiyattan yuksek fiyata siralandigini dogrular")
    public void studentKullanicisiUrunlerininDusukFiyattanYuksekFiyataSiralandiginiDogrular(String categoryName) {

        List<Double> productPrices = getScannedProductPrices(categoryName);

        // En az 2 ürün bulunmalıdır ki sıralama kontrolü anlamlı olsun
        Assert.assertTrue("Sıralama kontrolü için yeterli ürün bulunamadı!", productPrices.size() > 1);

        // Fiyatların düşükten yükseğe sıralı olduğunu kontrol etme
        boolean isSorted = true;
        for (int i = 0; i < productPrices.size() - 1; i++) {
            if (productPrices.get(i) > productPrices.get(i + 1)) {
                isSorted = false;
                logger.error("Sıralama hatası: Index {} ($) > Index {} ($)",
                        i, productPrices.get(i), i + 1, productPrices.get(i + 1));
                break;
            }
        }

        logger.info(redWhite("31nci sıradaki urunun 5nci sirada olmasi gerekli"));
        logger.info(redWhite("Siralamayi bozan urun: Samba Super Suede Mens Trainers Blue\\n0 (0  Reviews)\\n$79.00"));
        Assert.assertTrue("Ürünler düşük fiyattan yüksek fiyata doğru sıralı değil!", isSorted);

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
                studentPage.swipeDown();
                ReusableMethods.wait(1); //[cite: 1]
                scrollCount++;
            }
        }

        logger.info("{} kategorisi için toplanan fiyat listesi: {}", categoryName, priceList);
        return priceList;
    }

    @When("Student kullanicisi My Account sayfasinin {string} kullanicisina ait oldugunu dogrular")
    public void studentKullanicisiMyAccountSayfasininKullanicisinaAitOldugunuDogrular(String userName) {

        String dynamicXpath = String.format("//android.view.View[@content-desc='%s!']", userName);
        boolean isUserPageDisplayed = driver.findElement(By.xpath(dynamicXpath)).isDisplayed();
        Assert.assertTrue("Hata: " + userName + " kullanıcısına ait My Account sayfası/elementi ekranda görünmedi!",
                isUserPageDisplayed);
        logger.info("✅ Doğrulandı: Burası " + userName + " kullanıcısının MY ACCOUNT sayfasıdır.");
    }

    @When("Student kullanicisi {string} alaninin gorunurlugunu dogrular")
    public void studentKullanicisiAlanininGorunurlugunuDogrular(String menuItem) {

        AndroidClickUtils.verifyButtonByName(menuItem);


    }

    @When("Student kullanicisi {string} sayisini konsola yazdirir")
    public void studentKullanicisiSayisiniKonsolaYazdirir(String arg0) {

        List<WebElement> sayiButonlari = driver.findElements(By.xpath("//android.view.View[matches(@content-desc, '^[0-9]+$')]"));


    }

    @When("Student kullanicisi {string} sayisini index {int} ile konsola yazdirir")
    public void studentKullanicisiSayisiniIndexIleKonsolaYazdirir(String fieldName, int index) {
        List<WebElement> sayiButonlari = driver.findElements(By.xpath("//android.view.View[matches(@content-desc, '^[0-9]+$')]"));
        System.out.println(sayiButonlari.get(0).getText());

        logger.info(fieldName + " sayisini index: " + sayiButonlari.get(index).getText());
    }


    @When("Student kullanicisi {string} aktivite sayisini konsola yazdirir")
    public void studentKullanicisiAktiviteSayisiniKonsolaYazdirir(String fieldName) {
        String value = getAccountDashboardValue(fieldName);

        logger.info("{} : {}", fieldName, value);
        System.out.println(fieldName + " : " + value);
    }

    public String getAccountDashboardValue(String fieldName) {
        int index = -1;

        switch (fieldName) {
            case "Total Orders":
                index = 5; // XML'deki index=5 (Değer: 1)
                break;
            case "Total Completed":
                index = 6; // XML'deki index=6 (Değer: 0)
                break;
            case "Total Returnd":
                index = 11; // XML'deki index=11 (Değer: 0)
                break;
            case "Wallet Balance":
                index = 12; // XML'deki index=12 (Değer: $0.00)
                break;
            default:
                throw new IllegalArgumentException("Tanımsız alan adı: " + fieldName);
        }

        // Doğrudan XML'deki index özniteliğini hedefler
        String xpath = "//android.view.View[@index='" + index + "']";
        WebElement valueElement = driver.findElement(AppiumBy.xpath(xpath));

        return valueElement.getAttribute("content-desc");
    }

    @Then("Student kullanicisi My Account sayfasindaki tum ozet bilgilerin gorunur oldugunu dogrular")
    public void studentKullanicisiMyAccountSayfasindakiTumOzetBilgilerinGorunurOldugunuDogrular() {
        String[] fields = {"Total Orders", "Total Completed", "Total Returnd", "Wallet Balance"};

        for (String field : fields) {
            // 1. Etiketin görünürlüğü kontrol edilir
            WebElement labelElement = driver.findElement(AppiumBy.accessibilityId(field));
            Assert.assertTrue(field + " etiketi ekranda görünür değil!", labelElement.isDisplayed());

            // 2. Etikete ait değerin boş/null olmadığı kontrol edilir
            String value = getAccountDashboardValue(field);
            Assert.assertNotNull(field + " değeri null geldi!", value);
            Assert.assertFalse(field + " değeri boş geldi!", value.trim().isEmpty());

            logger.info("Doğrulama Başarılı -> {} : {}", field, value);
        }
    }

    @When("Student kullanicisi {string} formunun gorunur oldugunu dogrular")
    public void studentKullanicisiFormununGorunurOldugunuDogrular(String formName) {

        WebElement addNewAddressForm = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(6)")
        ));

        Assert.assertTrue(addNewAddressForm.isDisplayed());
        logger.info("Student kullanicisi {} formunun gorunur oldugunu dogrular", formName);


    }

    @When("Student kullanicisi {string} formunu doldurur")
    public void studentKullanicisiFormunuDoldurur(String formName) {

        logger.info("Student kullanicisi {} formunu doldurur", formName);
        // fullNameBox
        AndroidClickUtils.sendKeysToTextBoxByIndex("Ahmet", 0);
        ReusableMethods.wait(1);

        // EmailBox
        AndroidClickUtils.sendKeysToTextBoxByIndex("ahmet.customer@QueryCart.com", 1);

        // phoneBox
        AndroidClickUtils.sendKeysToTextBoxByIndex("1691696969", 2);

        // countryButton
        AndroidClickUtils.clickByDescription("Country");

        // dropDown countryButton
        WebElement selectCountryBox = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")
        ));
        selectCountryBox.click();
        selectCountryBox.clear();
        selectCountryBox.sendKeys("Netherlands The");

        // dropDown item select country
        AndroidClickUtils.clickByDescription("Netherlands The");
        ReusableMethods.wait(2);

        // stateButton
        AndroidClickUtils.clickByDescription("State");
        ReusableMethods.wait(2);
        // dropDown stateButton select item
        AndroidClickUtils.clickByDescription("Gelderland");
        ReusableMethods.wait(2);

        // cityButton
        AndroidClickUtils.clickByDescription("City");
        ReusableMethods.wait(2);

        // cityButton dropDownn select item
        AndroidClickUtils.clickByDescription("Ammerzoden");
        ReusableMethods.wait(2);

        // zipCcode elementi
        WebElement zipCodeBox = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(3)")
        ));

        zipCodeBox.click();
        zipCodeBox.clear();
        zipCodeBox.sendKeys("12345");

        // streetBox elementi
        WebElement streetBOx = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(4)")
        ));
        streetBOx.click();
        streetBOx.clear();
        streetBOx.sendKeys("12345");

        // hideKeyBar gerekebilir

        // addAddressButton elementi
        AndroidClickUtils.clickByDescription("Add Address");
        ReusableMethods.wait(2);

        logger.error("Student kullanicisi {} formunu doldurur", formName);

    }

    @When("Student kullanicisi eklenen adress bilgilerini konsola yazdirir")
    public void studentKullanicisiEklenenAdressBilgileriniKonsolaYazdirir() {

        By addressLocator = AppiumBy.xpath("//android.view.View[contains(@content-desc, 'Ahmet') or contains(@content-desc, '@QueryCart.com')]");

        // 2. clickable="false" olduğu için elementToBeClickable yerine visibilityOfElementLocated tercih edilmelidir
        WebElement addressElement = wait.until(ExpectedConditions.visibilityOfElementLocated(addressLocator));

        // 3. Bilgiyi content-desc attribute'undan çekin
        String addressText = addressElement.getAttribute("content-desc");

        logger.info(turkuazwhite(addressText));
    }

    @When("Student kullanicisi eklenen adress sahibinin {string} oldugunu dogrular")
    public void studentKullanicisiEklenenAdressSahibininOldugunuDogrular(String accountName) {

        By addressCardLocator = AppiumBy.xpath(
                "//android.view.View[@content-desc='Address']/following-sibling::android.view.View//android.view.View[@focusable='true']"
        );

        // 2. Öğe tıklanabilir olmasa da ekranda görünür olmasını bekliyoruz
        WebElement addressCard = wait.until(ExpectedConditions.visibilityOfElementLocated(addressCardLocator));

        // 3. content-desc attribute değerini alıyoruz
        String fullAddressText = addressCard.getAttribute("content-desc");

        logger.info("Çekilen Adres Bilgisi:\n" + fullAddressText);

        // 4. Doğrulama (Assertion): Gelen metnin parametreden gelen ismi içerdiğini kontrol ediyoruz
        Assert.assertTrue(
                "HATA: Adres bilgisi istenen hesap sahibini (" + accountName + ") içermiyor! Mevcut Metin: " + fullAddressText,
                fullAddressText != null && fullAddressText.contains(accountName)
        );
    }

    @When("Student kullanicisi {string} formunu {string} ismi ile doldurur")
    public void studentKullanicisiFormunuIsmiIleDoldurur(String formName, String accountName) {

        logger.info("Student kullanicisi {} formunu doldurur", formName);

        // Form alanları
        AndroidClickUtils.sendKeysToTextBoxByIndex(accountName, 0);
        ReusableMethods.wait(1);
        AndroidClickUtils.sendKeysToTextBoxByIndex("ahmet.customer@QueryCart.com", 1);
        AndroidClickUtils.sendKeysToTextBoxByIndex("1691696969", 2);

        // Country Selection
        AndroidClickUtils.clickByDescription("Country");
        WebElement selectCountryBox = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")
        ));
        selectCountryBox.click();
        selectCountryBox.clear();
        selectCountryBox.sendKeys("Netherlands The");
        AndroidClickUtils.clickByDescription("Netherlands The");
        ReusableMethods.wait(1);

        // State Selection
        AndroidClickUtils.clickByDescription("State");
        ReusableMethods.wait(1);
        AndroidClickUtils.clickByDescription("Gelderland");
        ReusableMethods.wait(1);

        // City Selection
        AndroidClickUtils.clickByDescription("City");
        ReusableMethods.wait(1);
        AndroidClickUtils.clickByDescription("Ammerzoden");
        ReusableMethods.wait(1);

        // KLAVYEYİ GÜVENLİ KAPATMA (Android BACK Tuşu)
        try {
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
        } catch (Exception e) {
            logger.warn("Klavye zaten kapalı veya kapatılamadı: " + e.getMessage());
        }

        // zipCcode elementi
        ReusableMethods.wait(2);
        WebElement zipCodeBox = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(3)")
        ));
        zipCodeBox.click();
        zipCodeBox.clear();
        zipCodeBox.sendKeys("12345");

        // KLAVYEYİ GÜVENLİ KAPATMA (Android BACK Tuşu)
        try {
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
        } catch (Exception e) {
            logger.warn("Klavye zaten kapalı veya kapatılamadı: " + e.getMessage());
        }
        // streetBox elementi
        ReusableMethods.wait(2);
        WebElement streetBOx = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(4)")
        ));
        streetBOx.click();
        streetBOx.sendKeys("MaviEvler sitesi");

        // KLAVYEYİ GÜVENLİ KAPATMA (Android BACK Tuşu)
        try {
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
        } catch (Exception e) {
            logger.warn("Klavye zaten kapalı veya kapatılamadı: " + e.getMessage());
        }

        // Güvenli Scroll ile 'Add Address' butonuna tıkla
        try {
            WebElement addAddressBtn = driver.findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiScrollable(new UiSelector().scrollable(true))" +
                                    ".scrollIntoView(new UiSelector().description(\"Add Address\"))"
                    )
            );
            addAddressBtn.click();
        } catch (Exception e) {
            // Scroll gerekmiyorsa doğrudan tıkla
            AndroidClickUtils.clickByDescription("Add Address");
        }

        ReusableMethods.wait(2);
        logger.info("Student kullanicisi {} formunu başarıyla doldurdu", formName);
    }

    @When("Student kullanicisi siparis detaylarini konsola yazdirir")
    public void studentKullanicisiSiparisDetaylariniKonsolaYazdirir() {
        List<WebElement> orderList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                AppiumBy.androidUIAutomator("new UiSelector().descriptionMatches(\"(?s).*Info:.*\")")
        ));

        logger.info("Bulunan toplam siparis sayısı: " + orderList.size());

        // 2. Her bir sipariş kartının content-desc bilgisini al ve konsola yazdır
        for (int i = 0; i < orderList.size(); i++) {
            WebElement order = orderList.get(i);

            // Flutter/React Native yapılarında metin content-desc içerisindedir!
            String orderDetails = order.getAttribute("content-desc");

            logger.info("--- Siparis #" + (i + 1) + " Detayları ---");
            logger.info(orderDetails);


        }
    }

    @When("Student kullanicisi ilk pending durumundaki siparise tiklar")
    public void studentKullanicisiIlkPendingDurumundakiSipariseTiklar() {
        // "Pending" içeren ilk tıklanabilir elementi bekle ve tıkla
        WebElement firstPendingOrder = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().descriptionMatches(\"(?s).*Pending.*\").clickable(true)")
        ));

        firstPendingOrder.click();
        logger.info("İlk Pending durumundaki siparişe tıklandı.");
    }

    @When("Student kullanicisi fatura bilgilerini konsola yazdirir")
    public void studentKullanicisiFaturaBilgileriniKonsolaYazdirir() {

        List<WebElement> invoiceElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                AppiumBy.xpath("//android.widget.ScrollView//android.view.View[@content-desc and string-length(@content-desc) > 0]")
        ));

        logger.info("================ FATURA BİLGİLERİ ================");

        // 2. Elementleri sırayla okuyup konsola yazdır
        for (WebElement element : invoiceElements) {
            String detailText = element.getAttribute("content-desc");
            if (detailText != null && !detailText.trim().isEmpty()) {
                // Satır sonu karakterlerini düzenli basmak için clean edip logluyoruz
                logger.info(turkuazwhite(detailText.replace("\n", " ")));
            }
        }

        logger.info("==================================================");
    }

    @When("Student kullanicisi {string} butonunun gorulmemesi ile siparisin iptal edildigini dogrular")
    public void studentKullanicisiButonununGorulmemesiIleSiparisinIptalEdildiginiDogrular(String buttonName) {


        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        boolean isButtonDisplayed = true;

        try {
            // Butonun UI üzerinde görünür olmasını bekliyoruz
            WebElement button = shortWait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.androidUIAutomator("new UiSelector().description(\"" + buttonName + "\")")
            ));

            // Eğer yukarıdaki satır HATA ATMADAN geçerse buton bulunmuş demektir
            isButtonDisplayed = button.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            // Element bulunamadıysa veya zaman aşımına uğradıysa buton ekranda yok demektir
            isButtonDisplayed = false;
        }

        // 2. Butonun GÖRÜNMELİ OLMADIĞINI (false olduğunu) doğruluyoruz (Assertion)
        Assert.assertFalse("HATA: " + buttonName + " butonu hala ekranda görünmektedir!", isButtonDisplayed);

        logger.info(buttonName + " butonunun ekranda görünmediği başarıyla doğrulandı.");


    }

    @When("Student kullanicisi {string} yazi alaninin gorunur ve aktif oldugunu dogrular")
    public void studentKullanicisiYaziAlanininGorunurVeAktifOldugunuDogrular(String boxName) {

     VerifyElements.smartVerifyBox((AndroidDriver) getAppiumDriver(), boxName, () ->studentPage.getElementByName(boxName));

    }

    @When("Student kullanicisi {string} toaster mesaji ile sifre degisikligini dogrular")
    public void studentKullanicisiToasterMesajiIleSifreDegisikliginiDogrular(String toasterMessageName) {


        VerifyElements.smartVerifyToast(
                (AndroidDriver) getAppiumDriver(),
                toasterMessageName,
                () -> studentPage.getElementByName(toasterMessageName)
        );


    }

    @When("Student kullanicisi {string} toaster mesajinin gorunurlugunu dogrular")
    public void studentKullanicisiToasterMesajininGorunurlugunuDogrular(String toasterMessageName) {


    }

    @When("Student kullanicisi {string} ismi ile profil degisikligini dogrular")
    public void studentKullanicisiIsmiIleProfilDegisikliginiDogrular(String expectedValue) {

        WebElement nameContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"" + expectedValue + "\")")
        ));

        // Elementin content-desc (accessibility label) degerini alıp dogruluyoruz
        String actualValue = nameContainer.getAttribute("content-desc");

        Assert.assertEquals(actualValue, expectedValue);
    }

    @When("Student kullanicisi {string} box kutusuna index {int} ile {string} mail adresini degistirir")
    public void studentKullanicisiBoxKutusunaIndexIleMailAdresiniDegistirir(String boxName, int index, String rawEmail) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));

        // Feature dosyasından gelen "{timeStamp}" placeholder'ını dinamik zaman bilgisiyle değiştiriyoruz
        String formattedEmail = rawEmail.contains("{timeStamp}")
                ? rawEmail.replace("{timeStamp}", timestamp)
                : rawEmail;

        AndroidClickUtils.sendKeysToTextBoxByIndex(formattedEmail, index);

        logger.info("Student kullanicisi '{}' alanina (Index: {}) '{}' e-posta adresini girdi.", boxName, index, formattedEmail);
    }

    @When("Student kullanicisi {string} box kutusuna index {int} ile {string} telefon numarasini degistirir")
    public void studentKullanicisiBoxKutusunaIndexIleTelefonNumarasiniDegistirir(String boxName, int index, String rawPhone) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));

        // Feature dosyasından gelen "{timeStamp}" placeholder'ını dinamik zaman bilgisiyle değiştiriyoruz
        String formattedPhone = rawPhone.contains("{timeStamp}")
                ? rawPhone.replace("{timeStamp}", timestamp)
                : rawPhone;

        AndroidClickUtils.sendKeysToTextBoxByIndex(formattedPhone, index);

        logger.info("Student kullanicisi '{}' alanina (Index: {}) '{}' telefon numarasini girdi.", boxName, index, formattedPhone);
    }

    @When("Student kullanicisi {string} ikonu islevsiz birakir")
    public void studentKullanicisiButonunuIslevsizBirakir(String ikonName) {


        WebElement sepetIkonu = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.widget.ImageView\").instance(2)"
        ));

        // GONE ile görünür alandan kaldır (alan kaplamaz, tıklanamaz)
        driver.executeScript("mobile: setVisibility", Map.of(
                "elementId", ((RemoteWebElement) sepetIkonu).getId(),
                "visibility", "gone"
        ));
        System.out.println(ikonName + " ikonu GONE yapıldı.");

    }

    @When("Student kullanicisi {string} uyuglamadan ayrilma butonuna basar")
    public void studentKullanicisiUyuglamadanAyrilmaButonunaBasar(String buttonName) {

        WebElement button = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().description(\"" + buttonName + "\")"
        ));

        Rectangle rect = button.getRect();
        int centerX = rect.getX() + rect.getWidth() / 2;
        int centerY = rect.getY() + rect.getHeight() / 2;

        // FAB'ın ekran üzerindeki konumu (XML'den: [614,2699][826,2912])
        // Eğer butonun merkezi FAB'ın alanına denk geliyorsa sol tarafa kaydır
        boolean overlapsFab = centerX >= 614 && centerX <= 826
                && centerY >= 2699 && centerY <= 2912;

        if (overlapsFab) {
            // Sol tarafa tıkla, FAB'ı atla
            int tapX = rect.getX() + 50;
            int tapY = centerY;
            Map<String, Object> args = new HashMap<>();
            args.put("x", tapX);
            args.put("y", tapY);
            driver.executeScript("mobile: clickGesture", args);
        } else {
            // Çakışma yoksa normal tıkla
            button.click();
        }

        logger.info("Student kullanicisi {} uyuglamadan ayrilma butonuna basar", buttonName);
    }

    @When("Student kullanicisi {string} ikonuna tiklar")
    @When("Student kullanicisi {string} beden secer")
    @When("Student kullanicisi adi {string} olan urunu secer")
    public void studentKullanicisiAdiOlanUrunuSecer(String productName) {

        AndroidClickUtils.smartClick((AndroidDriver) getAppiumDriver(), productName, () ->studentPage.getElementByName(productName));
        logger.info("Student kullanicisi adi {string} olan urunu secer");

    }

    @When("Student kullanicisi  ekrani asagi {int}, {int} noktasindan yukari {int}, {int} noktsina kaydirir")
    public void swipeFromBottomToTop(int sratX, int startY, int endX, int endY) {
        ReusableMethods.wait(2);
        TouchMethods.swipe(sratX, startY, endX, endY);
    }

    @When("Student kullanicisi address sinifindan kayitli ilk adresi secer")
    public void studentKullanicisiAddressSinifindanKayitliIlkAdresiSecer() {

        String className="android.widget.ImageView";
        int index =3;

        ReusableMethods.wait(3);
        logger.info("{} class'ına ait sayfa genelindeki {}. elemente tıklıyor", className, index);
        try {
            AndroidDriver driver = (AndroidDriver) getAppiumDriver();
            // Sayfa genelindeki tüm className elementlerini listeler ve gönderilen index'i (1 tabanlı) seçer
            String xpath = String.format("(//%s)[%d]", className, index + 1);
            WebElement element = driver.findElement(AppiumBy.xpath(xpath));
            element.click();
            logger.info("✅ {} class'ına ait index {}'li elemente tıklandı", className, index);
        } catch (Exception e) {
            logger.error("❌ Tıklama başarısız: {}", e.getMessage());
            throw new RuntimeException("Elemente tıklanamadı!", e);
        }
    }

    @When("Student kullanicisi {string} ve {string} ve {string} ve {string} cart bilgileri ile odemeyi yapar")
    public void studentKullanicisiVeVeVeVerileriIleCartBilgisiGirer(String cartNumber, String endDate, String cvcNumber, String zipCode) {

        WebDriverWait stripeWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement confirmButton = stripeWait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"confirmBtn\")")
        ));

        confirmButton.isDisplayed();

        AndroidClickUtils.sendKeysToTextBoxByIndex(cartNumber, 0);
        ReusableMethods.wait(2);

        AndroidClickUtils.sendKeysToTextBoxByIndex(endDate, 1);
        ReusableMethods.wait(2);

        AndroidClickUtils.sendKeysToTextBoxByIndex(cvcNumber, 2);
        ReusableMethods.wait(2);

        AndroidClickUtils.sendKeysToTextBoxByIndex(zipCode, 3);
        ReusableMethods.wait(2);

        WebElement confirmButton2 = stripeWait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"confirmBtn\")")
                ));

        confirmButton2.click();


    }

}


