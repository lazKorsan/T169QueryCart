package stepdefinitions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.JuniorPage;
import utilities.AndroidClickUtils;
import utilities.ReusableMethods;
import utilities.VerifyButtons;

import java.time.Duration;

import static utilities.Driver.getAppiumDriver;


public class JuniorSteps {
    private static final Logger logger = LogManager.getLogger(JuniorSteps.class);
    private final JuniorPage juniorPage = new JuniorPage();

    private WebElement resolve(String name) {
        try {
            return juniorPage.getElementByName(name);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private void verifyTextVisible(String text) {
        try {
            AndroidDriver driver = (AndroidDriver) getAppiumDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.androidUIAutomator(
                            "new UiSelector().descriptionContains(\"" + text + "\")")));

            wait.until(ExpectedConditions.visibilityOf(element));
            logger.info("'{}' metni görünür.", text);
        } catch (TimeoutException e) {
            Assert.fail("Metin ekranda bulunamadı: " + text);
        }
    }

    @Given("Junior kullanicisi uygulamayi acar")
    public void junior_kullanicisi_uygulamayi_acar() {
        getAppiumDriver();

        AndroidClickUtils.verifyButtonByName("Profile");
    }
    @Given("Junior kullanicisi {string} butonuna tiklar")
    public void junior_kullanicisi_butonuna_tiklar(String buton) {

        juniorPage.getElementByName(buton).click();
    }
    @Given("Junior kullanicisi Most Popular basliginin yanindaki {string} butonuna tiklar")
    public void junior_kullanicisi_most_popular_basliginin_yanindaki_butonuna_tiklar(String buton) {
        ReusableMethods.wait(2);
        juniorPage.getElementByName(buton).click();

    }
    @Given("Junior kullanicisi acilan listeden {string} urunune tiklar")
    public void junior_kullanicisi_acilan_listeden_urunune_tiklar(String buton) {
        ReusableMethods.scrollWithPartialContentDesc(buton);
    }
    @Given("Junior kullanicisi {string} butonunun gorunur ve aktif oldugunu dogrular")
    public void junior_kullanicisi_butonunun_gorunur_ve_aktif_oldugunu_dogrular(String buton) {
        ReusableMethods.scrollWithUiScrollableContentDesc(buton);
        VerifyButtons.verifyButton(buton);
    }

    @Given("Junior kullanicisi {string} bilgilerin gorunur oldugunu dogrular")
    public void junior_kullanicisi_bilgilerin_gorunur_oldugunu_dogrular(String metin) {
        WebElement el = resolve(metin);
        if (el != null) {
            VerifyButtons.verifyButton(el, metin);   // "Shipping & Return2" gibi page'de tanımlılar
        } else {
            verifyTextVisible(metin);                // sınıf içindeki metot, başında VerifyButtons yok
        }
    }
    @Given("Junior kullanicisi {string} box kutusuna index {int} ile {string} yazar")
    public void junior_kullanicisi_box_kutusuna_index_ile_yazar(String boxName, Integer index, String text) {
        AndroidClickUtils.sendKeysToTextBoxByIndex(text, index);
        logger.info("Junior kullanicisi {} box kutusuna index {} ile {} yazar", boxName, index, text);
    }
    @Given("Junior kullanicisi {string} box kutusuna tiklar")
    public void junior_kullanicisi_box_kutusuna_tiklar(String boxAdi) {
        juniorPage.getElementByName(boxAdi).click();
    }
    @Given("Junior kullanicisi {string} box kutusuna {string} yazar")
    public void junior_kullanicisi_box_kutusuna_yazar(String boxAdi, String metin) {
        org.openqa.selenium.WebElement kutu = juniorPage.getElementByName(boxAdi);
        kutu.clear();
        kutu.sendKeys(metin);
        logger.info("Junior kullanicisi '{}' kutusuna '{}' metnini yazdi.", boxAdi, metin);
    }
    @Given("Junior kullanicisi {string} yazisinin goruntulendigini dogrular")
    public void junior_kullanicisi_yazisinin_goruntulendigini_dogrular(String yazi) {
        ReusableMethods.scrollWithUiScrollableContentDesc(yazi);
        VerifyButtons.verifyButton(yazi);
    }
}
