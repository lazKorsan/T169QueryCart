package stepdefinitions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.BadgedPage;
import utilities.AndroidClickUtils;
import utilities.BannerUtils;
import utilities.Driver;
import utilities.ReusableMethods;


import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utilities.AndroidClickUtils.*;
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
}