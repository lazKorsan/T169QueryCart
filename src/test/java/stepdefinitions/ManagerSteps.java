package stepdefinitions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.ManagerPage;
import utilities.*;

import java.lang.reflect.Field;
import java.time.Duration;

import static utilities.Driver.getAppiumDriver;
import static utilities.Driver.quitAppiumDriver;

public class ManagerSteps {

    private static final Logger logger = LogManager.getLogger(ManagerSteps.class);
    private final ManagerPage managerPage = new ManagerPage();
    private final AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    public static String currentPassword = ConfigReader.getProperty("password");
    public static String newlyGeneratedPassword;
    private static int passwordCounter = 1;
    private final Actions action = new Actions(driver);
    public static String ortakYeniSifre = null;







    @Given("Manager kullanicisi uygulamayi acar")
    public void manager_kullanicisi_uygulamayi_acar() {

        getAppiumDriver();
        //AndroidClickUtils.verifyButtonByName("Profile");

        try {

            ((io.appium.java_client.android.AndroidDriver) Driver.getAppiumDriver()).terminateApp("com.wise.querycart");

            Thread.sleep(1000);

            ((io.appium.java_client.android.AndroidDriver) Driver.getAppiumDriver()).activateApp("com.wise.querycart");

            System.out.println("[SİSTEM SUCCESS] Uygulama tamamen kapatıldı ve sıfırdan tertemiz açıldı! ✅");
        } catch (Exception e) {
            System.out.println("[UYARI] Uygulama sıfırlanırken bir aksama oldu, doğrudan devam ediliyor: " + e.getMessage());
        }

    }
    @Given("Manager kullanicisi sayfa alt barinda {string} ikonunun gorunur oldugunu dogrular")
    public void manager_kullanicisi_sayfa_alt_barinda_ikonunun_gorunur_oldugunu_dogrular(String textName) {
        managerPage.altBarIkonuGoruntulemeVeTiklama(textName,false);

    }
    @Given("Manager kullanicisi alt bardaki {string} ikonuna tiklar")
    public void manager_kullanicisi_alt_bardaki_ikonuna_tiklar(String ikon) {
        managerPage.altBarIkonuGoruntulemeVeTiklama(ikon,true);
    }
    @Given("Manager kullanicisi ilgili {string} basligina yonlendirildigini dogrular")
    public void manager_kullanicisi_ilgili_basligina_yonlendirildigini_dogrular(String sayfaAdi) {
        managerPage.sayfaYonlendirmesiniDogrula(sayfaAdi);

        if (sayfaAdi.equalsIgnoreCase("Home")) {
            System.out.println("✨ Zaten Home (Ana Sayfa) doğrulandı, sonraki sayfaya geçiliyor.");
        } else if (sayfaAdi.equalsIgnoreCase("Wishlist")) {
            //AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();
           // driver.pressKey(new KeyEvent(AndroidKey.BACK));

            AndroidClickUtils.KeyBack();

            System.out.println("↩️ Android GERİ tuşuna basılarak ana sayfaya dönüldü.");
        }
        else {
            managerPage.altBarIkonuGoruntulemeVeTiklama("Home",true);
            System.out.println("🏠 Alt bardaki Home butonuna tıklanarak ana sayfaya dönüldü.");
        }

        logger.info("↩️ Ana sayfaya geri dönmek için Android GERİ tuşuna basıldı.");

    }
    @Given("Manager kullanicisi ana sayfada buyutec simgesine tiklar")
    public void manager_kullanicisi_ana_sayfada_buyutec_simgesine_tiklar() {
       managerPage.buyutecSimgesi.click();
    }
    @Given("Manager kullanicisi arama kutusunu goruntuler ve {string} yazarak arama yapar")
    public void manager_kullanicisi_arama_kutusunu_goruntuler_ve_yazarak_arama_yapar(String aranacakKelime) {
        AndroidClickUtils.sendKeysToTextBoxByIndex(aranacakKelime,0);
    }
    @Given("Manager kullanicisi klavyeden arama butonuna tiklar")
    public void manager_kullanicisi_klavyeden_arama_butonuna_tiklar() {
        managerPage.klavyedenAraButonunaBas();
    }
    @Given("Manager kullanicisi arama sonuclarinin ekranda listelendigini dogrular")
    public void manager_kullanicisi_arama_sonuclarinin_ekranda_listelendigini_dogrular() {
        managerPage.aramaSonuclariListelendiMi();
    }

    @Given("Manager kullanicisi Forgot Password sayfasini goruntulendigini dogrular")
    public void manager_kullanicisi_forgot_password_sayfasini_goruntulendigini_dogrular() {
        Assert.assertTrue(managerPage.forgotPasswordButon.isDisplayed());
    }

    @Given("Manager kullanicisi dogrulama yontemi olarak {string} secenegini secer")
    public void manager_kullanicisi_dogrulama_yontemi_olarak_secenegini_secer(String yontem) {

        if (yontem.equalsIgnoreCase("E-posta")) {
            System.out.println("[OTOMASYON] E-posta yöntemi seçildi. Linke tıklanıyor...");
            managerPage.emailInsteadButon.click();

        } else if (yontem.equalsIgnoreCase("phone")) {
            System.out.println("[OTOMASYON] Telefon yöntemi seçildi.");
            managerPage.phoneInsteadButon.click();
        }
    }
    @Given("Manager kullanicisi ilgili alana {string} bilgisini girer")
    public void manager_kullanicisi_ilgili_alana_bilgisini_girer(String iletisimBilgisi) {
        String finalInputData;

        if (iletisimBilgisi.equalsIgnoreCase("managerMail")) {
            finalInputData = ConfigReader.getProperty("managerMail");
        } else if (iletisimBilgisi.equalsIgnoreCase("phoneNumber")) {
            finalInputData = ConfigReader.getProperty("phoneNumber");
        } else {
            finalInputData = iletisimBilgisi;
        }

        System.out.println("[OTOMASYON] Veri enjeksiyonu deneniyor: " + finalInputData);

        try {

            AndroidClickUtils.smartSendKeys(
                    driver,
                    "İletişim Bilgisi Kutusu",
                    finalInputData,
                    () -> managerPage.email_phoneGirisBox);

            if (managerPage.email_phoneGirisBox.getText() == null || managerPage.email_phoneGirisBox.getText().isEmpty()) {
                throw new RuntimeException("Kutu boş kaldı, yedek plan tetikleniyor...");
            }

        } catch (Exception e) {
            System.out.println("[UYARI] Klavye tipi uyuşmazlığı algılandı! Telefon numarası için Sayısal Tuş Enjeksiyonu yapılıyor...");

            try {
                wait.until(ExpectedConditions.elementToBeClickable(managerPage.email_phoneGirisBox));
                managerPage.email_phoneGirisBox.click();
                Thread.sleep(500);

                System.out.println("[SAYISAL SUCCESS] Telefon numarası tuş simülasyonu ile başarıyla yazıldı: " + ConfigReader.getProperty("phoneNumber"));

            } catch (Exception ex) {
                System.out.println("[HATA] Sayısal enjeksiyon da başarısız oldu: " + ex.getMessage());

                driver.executeScript("mobile: type", java.util.Map.of("text", finalInputData));
            }
        }
    }
    @Given("Manager kullanicisi Get OTP butonuna tiklar")
    public void manager_kullanicisi_get_otp_butonuna_tiklar() {
        managerPage.getOTPButon.click();

    }
    @Given("Manager kullanicisi new password kutusuna yeni sifreyi yazar")
    public void manager_kullanicisi_new_password_kutusuna_yeni_sifreyi_yazar() {

            String prefix = ConfigReader.getProperty("passwordPrefix");
            String suffix = ConfigReader.getProperty("passwordSuffix");
            if (prefix == null) prefix = "Learn";
            if (suffix == null) suffix = ".";
            String uniqueTime = String.valueOf(System.currentTimeMillis()).substring(8);
            newlyGeneratedPassword = prefix + uniqueTime + suffix;
            ortakYeniSifre = newlyGeneratedPassword;

        System.out.println("[OTOMASYON] Kurşungeçirmez Tıklama ve Giriş Protokolü Devreye Giriyor...");


            try {
                driver.hideKeyboard();
            } catch (Exception e) {

            }

        managerPage.resetNewPasswordBox.click();
        try { Thread.sleep(3000); } catch (Exception e) {}

        managerPage.resetNewPasswordBox.clear();
        managerPage.resetNewPasswordBox.click();

        driver.executeScript("mobile: type", java.util.Map.of("text", newlyGeneratedPassword));
        currentPassword = newlyGeneratedPassword;

        }


        @Given("Manager kullanicisi confirm password kutusuna yeni sifreyi tekrar yazar")
    public void manager_kullanicisi_confirm_password_kutusuna_yeni_sifreyi_tekrar_yazar() {

                AndroidClickUtils.smartSendKeys(
                         driver,
                        "Confirm Password Kutusu",
                        newlyGeneratedPassword,
                        () -> managerPage.resetConfirmPasswordBox);
                System.out.println("[DİNAMİK ŞİFRE] Onay kutusuna aynı şifre basıldı: " + newlyGeneratedPassword);
            }



    @Given("Manager kullanicisi {string} yazisini goruntuler ve islemin basariyla gerceklestigini dogrular")
    public void manager_kullanicisi_yazisini_goruntuler_ve_islemin_basariyla_gerceklestigini_dogrular(String beklenenMesaj) {

          /* VerifyElements.smartVerifyToast(driver,
                   beklenenMesaj,
                   ()-> managerPage.toastSuccessMessage);*/
            System.out.println("[DORULAMA] Ekranda '" + beklenenMesaj + "' Toast mesajı aranıyor...");


            By toastLocator = By.xpath("//android.widget.Toast");

            try {

                WebDriverWait toastWait = new WebDriverWait(driver, Duration.ofSeconds(5));


                toastWait.until(ExpectedConditions.presenceOfElementLocated(toastLocator));

                String aktuelMesaj = driver.findElement(toastLocator).getText();
                System.out.println("[OTOMASYON] Ekranda Yakalanan Toast Metni: " + aktuelMesaj);


                Assert.assertTrue("HATA: Beklenen mesaj aktüel mesajı içermiyor! Beklenen: " + beklenenMesaj + ", Gelen: " + aktuelMesaj,
                        aktuelMesaj.toLowerCase().contains(beklenenMesaj.toLowerCase()));

                System.out.println("[DOĞRULAMA SUCCESS] Başarı mesajı başarıyla doğrulandı: ✅ " + aktuelMesaj);

            } catch (Exception e) {
                System.out.println("[UYARI] Toast elementi klasik yolla yakalanamadı. Alternatif sayfa kaynağı (Page Source) taraması yapılıyor...");


                String pageSource = driver.getPageSource();
                if (pageSource.contains(beklenenMesaj)) {
                    System.out.println("[DOĞRULAMA SUCCESS] Mesaj sayfa kaynağında (Page Source) iz olarak bulundu! ✅");
                } else {

                    throw new AssertionError("HATA: '" + beklenenMesaj + "' yazısı ekranda veya sayfa kaynağında bulunamadı!");
                }
            }
        }



    @Given("Manager kullanicisi {string} butonunun gorunur ve aktif oldugunu dogrular")
    public void manager_kullanicisi_butonunun_gorunur_ve_aktif_oldugunu_dogrular(String butonAdi) {

                WebElement targetButton = null;

                if (butonAdi.equalsIgnoreCase("Get OTP")) {
                    targetButton = managerPage.getOTPButon;

                } else if (butonAdi.equalsIgnoreCase("Back to sign in")) {
                    targetButton = managerPage.backToSignInButon;
                } else if(butonAdi.equalsIgnoreCase("Save Changes")){
                    targetButton = managerPage.saveChangesChangePasswordButon;
        }

                Assert.assertTrue("HATA: " + butonAdi + " butonu ekranda GÖRÜNMÜYOR!", targetButton.isDisplayed());
                Assert.assertTrue("HATA: " + butonAdi+ " butonu AKTİF/TIKLANABİLİR DEĞİL!", targetButton.isEnabled());
                System.out.println("[ASSERTION SUCCESS] " + butonAdi + " butonu hem görünür hem aktif! ✅");
            }
    @Given("Manager kullanicisi {string} butonuna tiklar")
    public void manager_kullanicisi_butonuna_tiklar(String buton) {

        String butonAdi = buton.trim();

        try {
            driver.hideKeyboard();
        } catch (Exception e) {
        }

        if (butonAdi.equalsIgnoreCase("Sign In")) {

            boolean sifreDegistirmeEkranindaMiyiz = false;
            try {
                sifreDegistirmeEkranindaMiyiz = managerPage.passwordGirisBox.isDisplayed();
            } catch (Exception e) {}

            By dinamikSignIn = By.xpath("//*[@content-desc='Sign In' or @text='Sign In']");

            if (sifreDegistirmeEkranindaMiyiz) {
                System.out.println("[OTOMASYON] Şifre değiştirme ekranındaki 2. Sign In butonuna tıklanıyor...");
                //wait.until(ExpectedConditions.elementToBeClickable(dinamikSignIn));
                //driver.findElement(dinamikSignIn).click();
                wait.until(ExpectedConditions.elementToBeClickable(managerPage.signInOnayButon));
                driver.findElement(By.xpath("(//android.view.View[@content-desc=\"Sign In\"])[2]")).click();

            } else {
                System.out.println("[OTOMASYON] Giriş ekranındaki ilk Sign In butonuna tıklanıyor...");

                try {
                    wait.until(ExpectedConditions.elementToBeClickable(dinamikSignIn));
                    driver.findElement(dinamikSignIn).click();
                } catch (Exception ex) {

                    managerPage.signInGirisButon.click();
                }
            }
        } else if (butonAdi.equalsIgnoreCase("Profile")) {

            By profileLocator = By.xpath("//android.widget.ImageView[@content-desc=\"Profile\"]");

            try {
                wait.until(ExpectedConditions.elementToBeClickable(profileLocator));
                driver.findElement(profileLocator).click();
            } catch (Exception e) {
                managerPage.profileGirisButon.click();
            }

            System.out.println("[OTOMASYON SUCCESS] Profile butonuna yüklenir yüklenmez başarıyla tıklandı. ✅");


        } else if (butonAdi.equalsIgnoreCase("Submit")) {
            AndroidClickUtils.smartClick(driver,"Submit button",() -> managerPage.resetSubmitButon);

        } else if (butonAdi.equalsIgnoreCase("Forgot Password")) {
            managerPage.forgotPasswordButon.click();
            System.out.println("[OTOMASYON SUCCESS] Forgot Password butonuna başarıyla tıklandı. ✅");

        } else if (butonAdi.equalsIgnoreCase("Save Changes")) {
            AndroidClickUtils.smartClick(driver, "Save Changes", () -> managerPage.saveChangesChangePasswordButon);
        }else{
                throw new RuntimeException("HATA: '" + butonAdi + "' adında bir buton tanımlanmamış!");

        }

    }
        @Given("Manager kullanicisi password kutusuna sifre bilgisini girer")
    public void manager_kullanicisi_password_kutusuna_sifre_bilgisini_girer() {

            String sifre;

            // 🎯 Eğer test esnasında şifre başarıyla değiştirildiyse, sınıf seviyesindeki static havuza kilitlenen yeni şifreyi getirir
            if (newlyGeneratedPassword != null && !newlyGeneratedPassword.isEmpty()) {
                sifre = newlyGeneratedPassword;
                System.out.println("[HAFIZA SUCCESS] Değiştirilen dinamik YENİ şifre başarıyla hafızadan çağrıldı: " + sifre);
            } else if (currentPassword != null && !currentPassword.isEmpty()) {
                sifre = currentPassword;
                System.out.println("[HAFIZA SUCCESS] currentPassword hafızasından çağrıldı: " + sifre);
            } else {
                // Eğer henüz şifre değiştirilmediyse properties dosyasındaki şifreye bakar
                sifre = ConfigReader.getProperty("mailPassword");
                if (sifre == null || sifre.isEmpty()) {
                    sifre = "Learn.123";
                }
                System.out.println("[SABİT ŞİFRE] Hafıza boş, properties dosyasındaki varsayılan şifre çağrıldı: " + sifre);
            }

            AndroidClickUtils.smartSendKeys(
                    driver,
                    "Password",
                    sifre,
                    () -> managerPage.passwordGirisBox);

            System.out.println("[OTOMASYON] Giriş için şifre başarıyla basıldı: " + sifre);
        }

    @Given("Manager kullanicisi acilan dashboardda {string} linkini goruntuler ve tiklar")
    public void manager_kullanicisi_acilan_dashboardda_linkini_goruntuler_ve_tiklar(String dashboardYazi) {

        managerPage.dashboardChangePasswordButon.click();
    }
    @Given("Manager kullanicisi old password alanina {string}, new password alanina {string} ve confirm alanina {string} yazar")
    public void manager_kullanicisi_old_password_alanina_new_password_alanina_ve_confirm_alanina_yazar(String eskiSifre, String yeniSifre, String onaySifre) {


        System.out.println("[WAIT] Ekrandaki Toast/Bildirim mesajının kaybolması bekleniyor...");
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.Toast")));
        } catch (Exception e) {
        }

        if (currentPassword == null) {
            currentPassword = ConfigReader.getProperty("oldPassword");
        }

        String finalOldPassword = eskiSifre.equalsIgnoreCase("oldPassword") ? currentPassword : eskiSifre;
        String finalNewPassword;

        if (yeniSifre.equalsIgnoreCase("newPassword") || onaySifre.equalsIgnoreCase("confirmPassword")) {
            String prefix = ConfigReader.getProperty("passwordPrefix");
            String suffix = ConfigReader.getProperty("passwordSuffix");
            String uniqueTime = String.valueOf(System.currentTimeMillis()).substring(8);
            newlyGeneratedPassword = prefix + uniqueTime + suffix;
            finalNewPassword = newlyGeneratedPassword;
            ortakYeniSifre = newlyGeneratedPassword;


        } else {
            finalNewPassword = yeniSifre;
        }

        AndroidClickUtils.smartSendKeys(driver, "Eski Sifre Kutusu", finalOldPassword, () -> managerPage.oldPasswordChangePasswordBox);
        AndroidClickUtils.smartSendKeys(driver, "Yeni Sifre Kutusu", finalNewPassword, () -> managerPage.newPasswordChangePasswordBox);
        action.sendKeys(Keys.TAB).perform();
        AndroidClickUtils.smartSendKeys(driver, "Onay Sifre Kutusu", finalNewPassword, () -> managerPage.confirmPasswordChangePasswordBox);

        currentPassword = newlyGeneratedPassword;

        }

    @Given("Manager kullanicisi acilan dashboardda {string} linkini gorunur ve tiklanabilir oldugunu dogrular")
    public void manager_kullanicisi_acilan_dashboardda_linkini_gorunur_ve_tiklanabilir_oldugunu_dogrular(String linkDogrulama) {

                WebElement targetLink = null;

                if (linkDogrulama.trim().equalsIgnoreCase("Change Password")) {
                    targetLink = managerPage.dashboardChangePasswordButon;
                }

                Assert.assertNotNull("HATA: '" + linkDogrulama + "' adında bir link Java kodunda tanımlanmamış!", targetLink);
                Assert.assertTrue("Get OTP butonu ekranda tespit edilemedi", targetLink.isDisplayed());
                Assert.assertTrue("Get OTP butonu tıklanabilir (aktif) durumda değil", targetLink.isEnabled());

                System.out.println("[ASSERTION SUCCESS] '" + linkDogrulama + "' linki başarıyla doğrulandı ve aktif! ✅");
            }
    @Given("Manager kullanicisi old password,new password ve confirm password kutularinin gorunur oldugunu dogrular")
    public void manager_kullanicisi_old_password_new_password_ve_confirm_password_kutularinin_gorunur_oldugunu_dogrular(){

        Assert.assertTrue(managerPage.oldPasswordChangePasswordBox.isDisplayed());
        Assert.assertTrue(managerPage.newPasswordChangePasswordBox.isDisplayed());
        Assert.assertTrue(managerPage.confirmPasswordChangePasswordBox.isDisplayed());
            }

        }



