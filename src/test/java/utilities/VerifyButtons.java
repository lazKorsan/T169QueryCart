package utilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VerifyButtons {

    private static final Logger logger = LogManager.getLogger(VerifyButtons.class);

    // Renk Kodları (Loglar için)
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";

    /**
     * SENARYO 1: Doğrudan WebElement nesnesi ile kontrol yapma.
     * Sayfalarında PageFactory / @AndroidFindBy kullanan arkadaşlar bu metodu çağırabilir.
     */
    public static void verifyButton(WebElement button, String buttonName) {
        logger.info(GREEN + "=".repeat(60) + RESET);
        logger.info(GREEN + "👁️ BUTON GÖRÜNÜRLÜK TESTİ: " + buttonName + RESET);
        logger.info(GREEN + "=".repeat(60) + RESET);

        try {
            if (button == null) {
                logger.error(RED + "❌ HATA: '{}' butonu Page sınıfından null döndü!" + RESET, buttonName);
                Assert.fail("WebElement null olarak gönderildi: " + buttonName);
                return;
            }

            AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver(); // Kendi Driver class'ınızın adıyla güncelleyin
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            logger.info(YELLOW + "⏳ '{}' butonu için görünürlük bekleniyor..." + RESET, buttonName);
            wait.until(ExpectedConditions.visibilityOf(button));

            if (button.isDisplayed() && button.isEnabled()) {
                logger.info(GREEN + "✅ '{}' butonu GÖRÜNÜR ve AKTİF" + RESET, buttonName);
                logger.info(GREEN + "🎯 '{}' butonu görünürlük testini BAŞARIYLA geçti" + RESET, buttonName);
                logger.info(GREEN + "=".repeat(60) + RESET);
            } else {
                logger.warn(YELLOW + "⚠️ '{}' butonu görünür: {}, aktif: {}" + RESET,
                        buttonName, button.isDisplayed(), button.isEnabled());
                Assert.fail(buttonName + " butonu görünür veya aktif değil!");
            }

        } catch (NoSuchElementException e) {
            logger.error(RED + "❌ '{}' butonu UI'da BULUNAMADI" + RESET, buttonName);
            Assert.fail(buttonName + " elementi UI'da bulunamadı.");
        } catch (TimeoutException e) {
            logger.error(RED + "⏰ '{}' butonu BEKLENEN SÜREDE GÖRÜNÜR OLMADI" + RESET, buttonName);
            Assert.fail(buttonName + " elementi zaman aşımına uğradı.");
        } catch (Exception e) {
            logger.error(RED + "💥 '{}' butonu doğrulanırken HATA: {}" + RESET, buttonName, e.getMessage());
            Assert.fail(buttonName + " doğrulanırken hata oluştu: " + e.getMessage());
        }
    }

    /**
     * SENARYO 2: Sadece Buton İsmi (String) ile kontrol yapma.
     * Page nesnesi veya enum kullanmayanlar için varsayılan olarak UiAutomator (Accessibility ID / Description) ile arar.
     */
    public static void verifyButton(String buttonName) {
        try {
            AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            logger.info(CYAN + "📱 '{}' butonu UiAutomator description ile dinamik aranıyor..." + RESET, buttonName);

            WebElement button = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            AppiumBy.androidUIAutomator("new UiSelector().description(\"" + buttonName + "\")")
                    )
            );

            // Ana doğrulama metoduna yönlendir
            verifyButton(button, buttonName);

        } catch (Exception e) {
            logger.error(RED + "❌ '{}' isimli buton UiAutomator ile bulunamadı!" + RESET, buttonName);
            Assert.fail("Metin/Description üzerinden buton bulunamadı: " + buttonName);
        }
    }

    /**
     * SENARYO 3: Esnek Page nesnesi ve getElementByName metodu ile kontrol.
     * Farklı Page sınıfları olan veya getElementByName() metoduna sahip herhangi bir Page nesnesi geçmek isteyenler için.
     */
    public static void verifyButton(Object pageObject, String buttonName) {
        try {
            // Reflection ile gelen Page sınıfında getElementByName metodunu arıyoruz
            var method = pageObject.getClass().getMethod("getElementByName", String.class);
            WebElement button = (WebElement) method.invoke(pageObject, buttonName);

            verifyButton(button, buttonName);
        } catch (NoSuchMethodException e) {
            logger.warn(YELLOW + "⚠️ Gönderilen Page sınıfında 'getElementByName' metodu bulunamadı. Dinamik aramaya geçiliyor..." + RESET);
            verifyButton(buttonName);
        } catch (Exception e) {
            logger.error(RED + "💥 Page nesnesinden element çekilirken hata oluştu!" + RESET);
            Assert.fail(buttonName + " doğrulaması sırasında Page nesnesi hatası: " + e.getMessage());
        }
    }

    /*
    1 Page Class ve WebElement Kullanan Ekip Üyesi (En Sağlıklı Yol):
    RegisterPage registerPage = new RegisterPage();
     ButtonUtils.verifyButton(registerPage.submitButton, "Submit Button");

    2 Dinamik String / No-Page / No-Enum Kullanan Ekip Üyesi:
    (Hiçbir Page objesi veya locator tanımlamadan direkt ekrandaki Accessibility ID / description ile arar)
     ButtonUtils.verifyButton("Edit Profile");

    3
     getElementByName Metodu Olan Farklı Page Class'lar (RegisterPage, ProfilePage vb.) Kullanan Ekip Üyesi:
     RegisterPage registerPage = new RegisterPage();
     ButtonUtils.verifyButton(registerPage, "save changes");
     */
}