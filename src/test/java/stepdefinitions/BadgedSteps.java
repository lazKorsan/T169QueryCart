package stepdefinitions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.BadgedPage;
import utilities.*;


import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utilities.AndroidClickUtils.*;
import static utilities.Driver.getAppiumDriver;
import static utilities.VerifyElements.verifyButton;


public class BadgedSteps {

    private static final Logger logger = LogManager.getLogger(BadgedSteps.class);
    private final BadgedPage badged = new BadgedPage();
    private final AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();

    // Driver'ı güvenle almak için yardımcı getter metodu
    private AndroidDriver getDriver() {
        return (AndroidDriver) Driver.getAppiumDriver();
    }

    @When("Badged kullanicisi uyugulamayi acar")
    public void badgedKullanicisiUyugulamayiAcar() {
        Driver.getAppiumDriver();
        verifyButton("Profile");
    }

    @When("Badged kullanicisi {string} , {string} ve {string} degerleri ile kayit olur ve giris yapar")
    public void badgedKullanicisiVeDegerleriIleKayitOlur(String name, String rawPhone, String password) {

        clickByDescription("Profile");

        // Driver ilklendirildikten sonra dinamik olarak oluşturulan wait nesnesi
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));

        // signUpButton
        WebElement signUpButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().description(\"Sign Up\")")
                ));
        clickElement(signUpButton);
        wait(1);

        // nameBox
        sendKeysToTextBoxByIndex(name, 0);

        // phoneProgress
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
        String formattedPhone = rawPhone.contains("{timeStamp}")
                ? rawPhone.replace("{timeStamp}", timestamp)
                : rawPhone;

        sendKeysToTextBoxByIndex(formattedPhone, 1);

        // password
        sendKeysToTextBoxByIndex(password, 2);
        wait(1);

        // Klavyeyi guvenle kapat
        hideKeyboard();
        wait(1);

        // todo while1 submitButton while dongusu baslangici
        // signUp Button tıklama islemleri
        // dogru veriler girildiginde Backend den hatali yaniti tolere edecek
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().description(\"Sign Up\").instance(1)")
        ));

        int maxTries = 5;
        int count = 0;

        while (count < maxTries) {
            try {
                // Element ekranda görünür ve tıklanabilir durumdaysa
                if (submitButton.isDisplayed()) {
                    logger.info("Sign Up (Submit) butonuna tıklanıyor. Deneme sayısı: {}", count + 1);
                    clickElement(submitButton);
                    wait(1);
                    count++;
                } else {
                    logger.info("Sign Up butonu ekrandan kayboldu, işlem başarılı.");
                    break;
                }
            } catch (Exception e) {
                // Sayfa değiştiğinde veya buton DOM'dan kaybolduğunda Stale/NoSuchElement hatası alırsak işlem tamamlanmıştır
                logger.info("Sign Up butonu artık erişilebilir değil (sayfa değişti), döngüden çıkılıyor.");
                break;
            }
        }
        // todo while1 submitButton dongu sonu

        // step2
        // login islemleri baslangici
        // phoneBox
        sendKeysToTextBoxByIndex(formattedPhone, 0);
        wait(1);

        // passwordBox
        sendKeysToTextBoxByIndex(password, 1);
        wait(1);

        // klavyeyi guvenle kapat
        hideKeyboard();
        wait(1);

        // SignInButton
        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().description(\"Sign In\").instance(1)")
                )
        );
        clickElement(loginButton);
        wait(1);

        // sayfa yuklenemesini garantiye almak
        verifyButton("Profile");
        wait(1);

        // profileButton
        clickByDescription("Profile");


        // todo While2 burada yine sayfa geç yuklenmesi problemleri dolayısıyla
        int maxNameTries = 12;
        int nameCount = 0;
        boolean isNameDisplayed = false;

        while (nameCount < maxNameTries) {
            try {
                WebElement nameElement = getDriver().findElement(
                        AppiumBy.androidUIAutomator("new UiSelector().description(\"" + name + "\")")
                );

                if (nameElement.isDisplayed()) {
                    logger.info("✅ Kullanıcı ismi '{}' ekranda başarıyla görüntülendi. (Deneme: {})", name, nameCount + 1);
                    isNameDisplayed = true;
                    break;
                }
            } catch (Exception e) {
                logger.warn("⏳ Kullanıcı ismi '{}' henüz yüklenmedi, bekleniyor... (Deneme: {}/{})", name, nameCount + 1, maxNameTries);
            }

            wait(1);
            nameCount++;
        }

        if (!isNameDisplayed) {
            logger.warn("⚠️ Kullanıcı ismi '{}' 12 saniye içinde ekranda görünmedi fakat test akışına devam ediliyor.", name);
        }

        // todo while2 sonu


    }

    public static void wait(int saniye) {
        try {
            Thread.sleep(saniye * 1000L);
        } catch (InterruptedException e) {
            logger.error("Bekleme sırasında hata oluştu: ", e);
            Thread.currentThread().interrupt();
        }
    }

    @When("Badged kullanicisi {string} formunu {string} ismi ile doldurur")
    public void badgedKullanicisiFormunuIsmiIleDoldurur(String formName, String name) {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));

        // ======== 5 Defalık Tekrarlı Giriş-Çıkış Döngüsü ========
        int maxTries = 5;
        int count = 0;
        boolean isPageLoaded = false;

        while (count < maxTries) {
            logger.info("Address sayfasına giriş deneniyor... Deneme: {}/{}", count + 1, maxTries);

            // 1. Address butonuna tıkla
            clickByDescription("Address");
            wait(1);

            // 2. Add New Address butonunun ekranda olup olmadığını hızlıca kontrol et (findElements exception fırlatmaz)
            var addressButtons = getDriver().findElements(
                    AppiumBy.androidUIAutomator("new UiSelector().description(\"Add New Address\")")
            );

            if (!addressButtons.isEmpty() && addressButtons.get(0).isDisplayed()) {
                logger.info("✅ 'Add New Address' butonu göründü, sayfa başarıyla yüklendi.");
                isPageLoaded = true;
                break; // Sayfa yüklendi, döngüden çık
            } else {
                logger.warn("⚠️ 'Add New Address' butonu görünmedi, Back butonuna tıklanıp tekrar deneniyor...");

                try {
                    // Back butonunu bulup tıkla
                    WebElement backButton = wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(0)")
                            )
                    );
                    clickElement(backButton);
                    wait(1);
                } catch (Exception e) {
                    logger.warn("Back butonuna tıklanırken hata oluştu veya buton bulunamadı, KeyBack deneniyor.");
                    KeyBack(); // Alternatif olarak cihazın geri tuşunu kullanır
                    wait(1);
                }
            }

            count++;
        }

        if (!isPageLoaded) {
            logger.error("❌ 'Add New Address' butonu 5 deneme sonrasında da görüntülenemedi!");
            org.junit.Assert.fail("Address sayfası 5 denemeye rağmen yüklenemedi.");
        }

        // ======== Form Doldurma Adımları Buradan Devam Edebilir ========
        WebElement addNewAddressButon = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().description(\"Add New Address\")")
                )
        );
        clickElement(addNewAddressButon);
        wait(1);

        // fullNameBox
        sendKeysToTextBoxByIndex(name, 0);

        // emailBox
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
        String formattedMail = name + ".customer." + timestamp + "@QueryCart.com";
        sendKeysToTextBoxByIndex(formattedMail, 1);

        // phoneBox
        String formattedPhone = "0543" + timestamp;
        sendKeysToTextBoxByIndex(formattedPhone, 2);

        // hideKeyboard();

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


        hideKeyboard();
        logger.info("Klavye kapatıldı");
        // streetBox elementi
        WebElement streetBOx = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[5]")
        ));
        streetBOx.click();
        streetBOx.clear();
        streetBOx.sendKeys("MaviEvler sitesi");


        hideKeyboard();


        // Scroll gerekmiyorsa doğrudan tıkla
        AndroidClickUtils.clickByDescription("Add Address");


        ReusableMethods.wait(2);
        logger.info("Student kullanicisi {} formunu başarıyla doldurdu", formName);


    }

    @When("Badged kullanicisi {string}")
    public void badgedKullanicisi(String description) {


        wait(2);
        SwingUtilities.invokeLater(() -> {
            BannerUtils.InProgress(
                    description
            );
        });
        wait(6);
    }

    @When("Badges kullanicisi eklenen adress sahibinin {string} oldugunu dogrular")
    public void badgesKullanicisiEklenenAdressSahibininOldugunuDogrular(String accountName) {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
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


    @When("Badges kullanicisi {string} kategorisinden {string} urununu satin alir")
    public void badgesKullanicisiKategorisindenUrununuSatinAlir(String productsCategories, String productName) {

        wait(3);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        // navigateDashBoardPage
        WebElement backButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(0)")
                )
        );
        clickElement(backButton);
        logger.info(turkuazwhite("backButtona tiklandi"));
        wait(1);

        //
        clickByDescription("Home");
        logger.info(turkuazwhite("Home tiklandi"));
        wait(1);


        int maxTries = 5;
        int count = 0;
        boolean isProductFound = false;

        while (count < maxTries) {
            logger.info("'{}}' kategorisine tıklanıyor ve '{}}' ürünü aranıyor... Deneme: {}/{}",
                    productsCategories, productName, count + 1, maxTries);

            // Adım 1: Kategoriye tıkla (Örn: "Men Shorts")
            clickByDescription(productsCategories);
            logger.info(turkuazwhite("Men Shorts Categories tiklandi"));
            wait(2);

            // Adım 2: Açılan sayfada ürünün varlığını kontrol et
            // Exception fırlatmaması için findElements kullanıyoruz
            var foundProducts = getDriver().findElements(
                    AppiumBy.androidUIAutomator("new UiSelector().description(\"Classic Cargo Shorts\n" +
                            "0 (0  Reviews)\n" +
                            "$40.00\")")
            );

            if (!foundProducts.isEmpty() && foundProducts.get(0).isDisplayed()) {
                logger.info("✅ Ürün ekranda başarıyla görüntülendi: {}", productName);

                // Ürüne tıklayıp bir sonraki sayfaya geç
                clickElement(foundProducts.get(0));
                logger.info(turkuazwhite("Classic Cargo Shorts  tiklandi"));
                wait(1);

                isProductFound = true;
                break; // Ürün bulundu ve tıklandı, döngüden çık
            } else {
                logger.warn("⚠️ Ürün yüklenemedi/görünmedi. Sol üstteki Back butonuna basılıp tekrar deneniyor...");

                try {
                    // Sayfa yüklenmediğinde sol üst köşedeki Back butonuna bas
                    WebElement pageBackButton = wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(0)")
                            )
                    );
                    clickElement(pageBackButton);
                    wait(1);
                } catch (Exception e) {
                    logger.warn("Back butonuna tıklanırken hata oluştu, cihazın fiziksel geri tuşu (KeyBack) kullanılıyor.");
                    KeyBack();
                    wait(1);
                }
            }

            count++;
        }

        // 5 deneme sonucunda ürün bulunamadıysa testi fail et
        if (!isProductFound) {
            logger.error("❌ Aranan ürün 5 denemeye rağmen ekranda görüntülenemedi: {}", productName);
            logger.error(redWhite("❌ Aranan ürün 5 denemeye rağmen ekranda görüntülenemedi: {}"), productName);
            Assert.fail("Kategoriye 5 defa giriş-çıkış yapılmasına rağmen " + productName + " ürünü yüklenemedi.");
        }



        String productTitleOnly = productName.split("\n")[0]; // "Classic Cargo Shorts"

        int detailTries = 5;
        int detailCount = 0;
        boolean isDetailPageLoaded = false;

        while (detailCount < detailTries) {
            logger.info("Ürün detay sayfasının yüklenmesi kontrol ediliyor ('{}')... Deneme: {}/{}",
                    productTitleOnly, detailCount + 1, detailTries);

            // Detay sayfasındaki başlık yazısının ("Classic Cargo Shorts") ekranda olup olmadığını kontrol et
            var detailTitleElements = getDriver().findElements(
                    AppiumBy.androidUIAutomator("new UiSelector().description(\"Classic Cargo Shorts\")")
            );

            if (!detailTitleElements.isEmpty() && detailTitleElements.get(0).isDisplayed()) {
                logger.info("🎯 Ürün detay sayfası başarıyla yüklendi! (Başlık göründü: '{}')", productTitleOnly);
                logger.info(turkuazwhite("Urun Basligi goruldu tiklandi"));
                isDetailPageLoaded = true;
                break; // Detay sayfası sorunsuz açıldı, döngüyü bitir.
            } else {
                logger.warn("⚠️ Ürün detay sayfası yüklenemedi. Geri dönülüp ürüne tekrar tıklanıyor...");

                // 1. Detay sayfasından liste sayfasına geri çık
                navigateBackSafe(wait);

                // 2. Liste sayfasındaki ürüne tekrar tıkla
                clickByDescription(productName);
                wait(2);
            }

            detailCount++;
        }

        if (!isDetailPageLoaded) {
            logger.error("❌ Aranan ürün detay sayfası 5 denemeye rağmen tam olarak yüklenemedi: {}", productTitleOnly);
            Assert.fail("5 deneme sonrasında " + productTitleOnly + " ürün detay sayfası açılamadı.");
        }

        wait(1);

        // M size secer
        clickByDescription("M");
        logger.info(turkuazwhite("Urun Bedeni M tiklandi"));

        // * Student kullanicisi  ekrani asagi 710, 2400 noktasindan yukari 710, 1700 noktsina kaydirir
        TouchMethods.swipe(710, 2400, 710, 1700);
        logger.info(turkuazwhite("aDD TO cART butona kaydirildi"));

        // Add to Cart butona tiklar
        clickByDescription("Add To Cart");
        logger.info(turkuazwhite("Add To Cart Button tiklandi"));
        wait(1);

        WebElement carTIcon=wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(12)")
                )
        );
        clickElement(carTIcon);
        logger.info(turkuazwhite("Cart ICon tiklandi"));
        wait(1);

        clickByDescription("Proceed to Checkout");
        logger.info(turkuazwhite("Proceed to Checkout tiklandi"));
        wait(1);

        // adres secimi sayfasi
        String className = "android.widget.ImageView";
        int index = 3;

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
        logger.info(turkuazwhite("Adress secmi yapildi"));

        // element uzerine kaydırma
        scrollWithUiScrollableContentDesc("Save & Pay");
        logger.info(turkuazwhite("Save and Pay kaydirildi"));


        int paymentTries = 5;
        int paymentCount = 0;
        boolean isStripeFound = false;

        while (paymentCount < paymentTries) {
            logger.info("'Save & Pay' butonuna tıklanıyor ve 'Stripe' seçeneği kontrol ediliyor... Deneme: {}/{}",
                    paymentCount + 1, paymentTries);

            try {
                // Adım 1: Save & Pay butonuna tekrar erişip tıkla
                WebElement saveAndPay = wait.until(ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().description(\"Save & Pay\")")
                ));
                clickElement(saveAndPay);
                wait(2);

                // Adım 2: Stripe butonunun görünüp görünmediğini kontrol et (findElements exception fırlatmaz)
                var stripeElements = getDriver().findElements(
                        AppiumBy.androidUIAutomator("new UiSelector().description(\"Stripe\")")
                );

                if (!stripeElements.isEmpty() && stripeElements.get(0).isDisplayed()) {
                    logger.info("✅ 'Stripe' ödeme seçeneği ekranda görüntülendi.");

                    // Stripe butonuna tıkla
                    clickElement(stripeElements.get(0));
                    wait(1);

                    isStripeFound = true;
                    break; // Stripe bulundu ve tıklandı, döngüden çık
                } else {
                    logger.warn("⚠️ Stripe seçeneği yüklenemedi/görünmedi. Geri butonuna basılıp tekrar deneniyor...");
                    navigateBackSafe(wait);
                }
            } catch (Exception e) {
                logger.warn("⚠️ Döngü sırasında bir hata oluştu: {}. Geri dönüp tekrar deneniyor...", e.getMessage());
                navigateBackSafe(wait);
            }

            paymentCount++;
        }

        if (!isStripeFound) {
            logger.error("❌ 'Stripe' ödeme seçeneği 5 denemeye rağmen görüntülenemedi!");
            Assert.fail("Save & Pay adımından sonra Stripe ödeme seçeneği 5 denemede de yüklenemedi.");
        }

        logger.info(turkuazwhite("Save&Pay butona tiklandi ve Stripe secenegi isaretlendi"));

        // Confirm Order
        clickByDescription("Confirm Order");
        logger.info(turkuazwhite("Confirm Order Butona tiklandi"));

        // confirmButton gorunurluk testi
        //===============================================
        // new UiSelector().resourceId("confirmBtn")
        boolean isOrderCompleted = false;
        int maxRetries = 60;

        for (int i = 1; i <= maxRetries; i++) {
            try {

                WebElement confirmButton = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"confirmBtn\")"));

                if (confirmButton.isDisplayed()) {
                    System.out.println("✅ Kart bilgileri sayfasi yuklendi! Detay sayfasına gidiliyor. (Deneme #" + i + ")");
                    //confirmButton.click();
                    isOrderCompleted = true;
                    break; // Döngüden çık
                }
            } catch (Exception e) {

                System.out.println("⏳ Sayfanin Yuklenmesi bekleniyor... Deneme #" + i + "/" + maxRetries);


                if (i == 1) {

                    System.out.println("Sipariş işleniyor, lütfen bekleyin...");
                    logger.info(redWhite("Kart bilgisi sayfasinin yuklenmesi bekleniyor, lütfen bekleyin..."));
                }
            }

            ReusableMethods.wait(5);
        }

        if (!isOrderCompleted) {
            Assert.fail("❌ Kart bilgisi doldurma sayfasi  " + (maxRetries * 5) + " saniye içinde yuklenmedi!");
        }

        // ==============================================

        // ===================================
        // cart islemleri

        String cartNumber="4242424242424242";
        String endDate="0234";
        String cvcNumber="321";
        String zipCode="12345";

        AndroidClickUtils.sendKeysToTextBoxByIndex(cartNumber, 0);
        ReusableMethods.wait(2);

        AndroidClickUtils.sendKeysToTextBoxByIndex(endDate, 1);
        ReusableMethods.wait(2);

        AndroidClickUtils.sendKeysToTextBoxByIndex(cvcNumber, 2);
        ReusableMethods.wait(2);

        AndroidClickUtils.sendKeysToTextBoxByIndex(zipCode, 3);
        ReusableMethods.wait(2);

        WebElement confirmButton2 = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"confirmBtn\")")
                ));

        confirmButton2.click();




    }

    public static String turkuazwhite(String text) {

        return "\u001B[48;2;10;170;170m" + "\u001B[3m" + "\u001B[38;2;255;255;255m" + "\u001B[1m" + " " + text + " " + "\u001B[0m";
    }

    public static String redWhite(String text) {

        return "\u001B[48;2;220;38;38m" + "\u001B[3m" + "\u001B[38;2;255;255;255m" + "\u001B[1m" + " " + text + " " + "\u001B[0m";
    }

    private void navigateBackSafe(WebDriverWait wait) {
        try {
            WebElement pageBackButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(0)")
                    )
            );
            clickElement(pageBackButton);
            wait(1);
        } catch (Exception e) {
            logger.warn("Back butonuna tıklanırken hata oluştu, cihazın fiziksel geri tuşu (KeyBack) deneniyor.");
            KeyBack();
            wait(1);
        }
    }

    @When("Badged kullanicisi siparis olusumunu {string} butonunun gorunurlugu ile dogrular")
    public void badgedKullanicisiSiparisOlusumunuButonununGorunurluguIleDogrular(String buttonName) {


        boolean isOrderCompleted = false;
        int maxRetries = 60;

        for (int i = 1; i <= maxRetries; i++) {
            try {

                WebElement goToOrderDetailsPageButton = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"Go to order details\")"));

                if (goToOrderDetailsPageButton.isDisplayed()) {
                    System.out.println("✅ Sipariş tamamlandı! Detay sayfasına gidiliyor. (Deneme #" + i + ")");
                    goToOrderDetailsPageButton.click();
                    isOrderCompleted = true;
                    break; // Döngüden çık
                }
            } catch (Exception e) {

                System.out.println("⏳ Sipariş bekleniyor... Deneme #" + i + "/" + maxRetries);


                if (i == 1) {

                    System.out.println("Sipariş işleniyor, lütfen bekleyin...");
                    logger.info(redWhite("Sipariş işleniyor, lütfen bekleyin..."));
                }
            }

            ReusableMethods.wait(5);
        }

        if (!isOrderCompleted) {
            Assert.fail("❌ Sipariş " + (maxRetries * 5) + " saniye içinde tamamlanamadı!");
        }


        System.out.println("✅ Payment.feature testi başarıyla tamamlandı!");
        logger.info(turkuazwhite("✅ feature testi başarıyla tamamlandı!"));
    }
}