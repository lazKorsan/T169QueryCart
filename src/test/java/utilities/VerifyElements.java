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
import java.util.function.Supplier;

public class VerifyElements {

    private static final Logger logger = LogManager.getLogger(VerifyElements.class);

    // Log Renk Kodları
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";

    private static final int DEFAULT_TIMEOUT = 10;

    // ==========================================
    // 1. TEMEL CORE DOĞRULAMA METOTLARI
    // ==========================================

    /**
     * Herhangi bir WebElement'in görünür ve aktif (enabled) olduğunu doğrular.
     *
     * @param element     Doğrulanacak WebElement
     * @param elementName Loglar ve raporlama için element adı
     * @param elementType Log başlığı için tür (BUTON, BOX, TEXT vb.)
     */
    public static void verifyElement(WebElement element, String elementName, String elementType) {
        logger.info(GREEN + "=".repeat(60) + RESET);
        logger.info(GREEN + "👁️ " + elementType.toUpperCase() + " GÖRÜNÜRLÜK VE AKTİFLİK TESTİ: " + elementName + RESET);
        logger.info(GREEN + "=".repeat(60) + RESET);

        try {
            if (element == null) {
                logger.error(RED + "❌ HATA: '{}' elementi null olarak gönderildi!" + RESET, elementName);
                Assert.fail("WebElement null olarak gönderildi: " + elementName);
                return;
            }

            AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));

            logger.info(YELLOW + "⏳ '{}' elementi için görünürlük bekleniyor..." + RESET, elementName);
            wait.until(ExpectedConditions.visibilityOf(element));

            if (element.isDisplayed() && element.isEnabled()) {
                logger.info(GREEN + "✅ '{}' elementi GÖRÜNÜR ve AKTİF" + RESET, elementName);
                logger.info(GREEN + "🎯 '{}' elementi doğrulama testini BAŞARIYLA geçti" + RESET, elementName);
                logger.info(GREEN + "=".repeat(60) + RESET);
            } else {
                logger.warn(YELLOW + "⚠️ '{}' elementi görünür: {}, aktif: {}" + RESET, elementName, element.isDisplayed(), element.isEnabled());
                Assert.fail(elementName + " elementi görünür veya aktif değil!");
            }

        } catch (NoSuchElementException e) {
            logger.error(RED + "❌ '{}' elementi UI'da BULUNAMADI" + RESET, elementName);
            Assert.fail(elementName + " elementi UI'da bulunamadı.");
        } catch (TimeoutException e) {
            logger.error(RED + "⏰ '{}' elementi BEKLENEN SÜREDE GÖRÜNÜR OLMADI" + RESET, elementName);
            Assert.fail(elementName + " elementi zaman aşımına uğradı.");
        } catch (Exception e) {
            logger.error(RED + "💥 '{}' elementi doğrulanırken HATA: {}" + RESET, elementName, e.getMessage());
            Assert.fail(elementName + " doğrulanırken hata oluştu: " + e.getMessage());
        }
    }

    /**
     * Sadece element adı verilerek UiAutomator üzerinden dinamik arama ve doğrulama yapar.
     */
    public static void verifyElementByDescriptionOrText(String elementName, String elementType) {
        try {
            AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));

            logger.info(CYAN + "📱 '{}' {} elementi UiAutomator ile dinamik aranıyor..." + RESET, elementName, elementType);

            String descStrategy = "new UiSelector().description(\"" + elementName + "\")";
            String textStrategy = "new UiSelector().text(\"" + elementName + "\")";

            WebElement element;
            try {
                element = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.androidUIAutomator(descStrategy)));
            } catch (TimeoutException e) {
                element = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.androidUIAutomator(textStrategy)));
            }

            verifyElement(element, elementName, elementType);

        } catch (Exception e) {
            logger.error(RED + "❌ '{}' isimli {} elementi UiAutomator ile bulunamadı!" + RESET, elementName, elementType);
            Assert.fail("Metin/Description üzerinden element bulunamadı: " + elementName);
        }
    }


    // ==========================================
    // 2. SMART (LAMBDA / SUPPLIER) METOTLAR
    // ==========================================

    /**
     * Akıllı Buton Doğrulama:
     * Önce Lambda/Supplier üzerinden Page class'taki elementi almayı dener.
     * Bulamazsa veya hata alırsa UiAutomator dinamik aramaya geçer.
     */
    public static void smartVerifyButton(AndroidDriver driver, String buttonName, Supplier<WebElement> elementSupplier) {
        try {
            WebElement element = elementSupplier.get();
            if (element != null) {
                logger.info("🎯 Buton Page class'ta bulundu: {}", buttonName);
                verifyElement(element, buttonName, "BUTON");
                return;
            }
        } catch (Exception e) {
            logger.info("⚠️ Page class üzerinden buton alınamadı: {}. Description/Text ile deneniyor...", e.getMessage());
        }

        logger.info("🔍 '{}' Page class'ta bulunamadı, UiAutomator ile aranıyor...", buttonName);
        verifyElementByDescriptionOrText(buttonName, "BUTON");
    }

    /**
     * Akıllı Box / Text Area Doğrulama:
     * Önce Lambda/Supplier üzerinden Page class'taki elementi almayı dener.
     * Bulamazsa veya hata alırsa UiAutomator dinamik aramaya geçer.
     */
    public static void smartVerifyBox(AndroidDriver driver, String boxName, Supplier<WebElement> elementSupplier) {
        try {
            WebElement element = elementSupplier.get();
            if (element != null) {
                logger.info("🎯 Box elementi Page class'ta bulundu: {}", boxName);
                verifyElement(element, boxName, "YAZI ALANI (BOX)");
                return;
            }
        } catch (Exception e) {
            logger.info("⚠️ Page class üzerinden box alınamadı: {}. Description/Text ile deneniyor...", e.getMessage());
        }

        logger.info("🔍 '{}' Page class'ta bulunamadı, UiAutomator ile aranıyor...", boxName);
        verifyElementByDescriptionOrText(boxName, "YAZI ALANI (BOX)");
    }


    // ==========================================
    // 3. PRATİK KULLANIM METOTLARI (OVERLOAD)
    // ==========================================

    public static void verifyButton(WebElement button, String buttonName) {
        verifyElement(button, buttonName, "BUTON");
    }

    public static void verifyBox(WebElement boxElement, String boxName) {
        verifyElement(boxElement, boxName, "YAZI ALANI (BOX)");
    }

    public static void verifyButton(String buttonName) {
        verifyElementByDescriptionOrText(buttonName, "BUTON");
    }

    public static void verifyBox(String boxName) {
        verifyElementByDescriptionOrText(boxName, "YAZI ALANI (BOX)");
    }

    /*
    AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();
StudentPage studentPage = new StudentPage();

// 1. Smart Box Doğrulama (Change Password Alanları için)
VerifyElements.smartVerifyBox(driver, "Old password", () -> studentPage.getBoxElement("Old password"));
VerifyElements.smartVerifyBox(driver, "New password", () -> studentPage.getBoxElement("New password"));
VerifyElements.smartVerifyBox(driver, "Confirm password", () -> studentPage.getBoxElement("Confirm password"));

// 2. Smart Button Doğrulama
VerifyElements.smartVerifyButton(driver, "Save Changes", () -> studentPage.getButtonByEnum("Save Changes"));

// 3. Doğrudan WebElement veya String ismi ile eski usül kullanım desteği de devam eder
VerifyElements.verifyBox(studentPage.oldPasswordInput, "Old Password Box");
VerifyElements.verifyButton("Submit");
     */

    /**
     * Toast mesajlarının hızlı kaybolma ihtimaline karşı 200 milisaniye aralıklarla
     * hızlıca arama yapan ve toplam belirtilen sürede (ör. 3 saniye) görünürlüğünü doğrulayan akıllı metot.
     */
    public static void smartVerifyToast(AndroidDriver driver, String expectedToastMessage, Supplier<WebElement> elementSupplier) {
        logger.info(GREEN + "=".repeat(60) + RESET);
        logger.info(GREEN + "🍞 TOAST MESAJI DOĞRULAMA TESTİ: " + expectedToastMessage + RESET);
        logger.info(GREEN + "=".repeat(60) + RESET);

        boolean isFound = false;
        long timeoutMillis = 3000; // Toplam bekleme süresi (3 saniye)
        long pollingIntervalMillis = 200; // Kontrol aralığı (200 ms)
        long startTime = System.currentTimeMillis();

        // 1. Önce Page Class (Supplier) veya DOM üzerinden hızlı arama döngüsü
        while ((System.currentTimeMillis() - startTime) < timeoutMillis) {
            try {
                // Page sınıfında tanımlı element varsa almayı dene
                WebElement element = (elementSupplier != null) ? elementSupplier.get() : null;

                if (element != null && element.isDisplayed()) {
                    logger.info(GREEN + "🎯 Toast mesajı Page Class üzerinden yakalandı: '{}'" + RESET, expectedToastMessage);
                    isFound = true;
                    break;
                }
            } catch (Exception ignored) {
                // Element henüz DOM'a gelmemiş veya silinmiş olabilir, polling devam eder
            }

            // Dinamik UiAutomator arama (Description veya Text kontrolü)
            try {
                String xpathQuery = "//*[contains(@text, '" + expectedToastMessage + "') or contains(@content-desc, '" + expectedToastMessage + "')]";
                var elements = driver.findElements(AppiumBy.xpath(xpathQuery));

                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    logger.info(GREEN + "🎯 Toast mesajı XPath/Text üzerinden yakalandı: '{}'" + RESET, expectedToastMessage);
                    isFound = true;
                    break;
                }
            } catch (Exception ignored) {
            }

            try {
                Thread.sleep(pollingIntervalMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (isFound) {
            logger.info(GREEN + "✅ '{}' Toast mesajı BAŞARIYLA DOĞRULANDI" + RESET, expectedToastMessage);
            logger.info(GREEN + "=".repeat(60) + RESET);
        } else {
            logger.error(RED + "❌ '{}' Toast mesajı {} ms içerisinde EKRANDA GÖRÜLMEDİ!" + RESET, expectedToastMessage, timeoutMillis);
            Assert.fail("Toast mesajı bulunamadı veya ekranda yakalanamadı: " + expectedToastMessage);
        }
    }
}