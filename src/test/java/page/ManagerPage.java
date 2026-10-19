package page;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.AndroidClickUtils;
import utilities.ConfigReader;


import java.time.Duration;

import static utilities.AndroidClickUtils.smartClick;

public class ManagerPage extends QueryCardPage {

    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    public AndroidDriver getDriver() {
        return this.driver;
    }

    public ManagerPage() {
        super();
    }

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Home\"]")
    public WebElement homeButon;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Category\"]")
    public WebElement categoryButon;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Wishlist\"]")
    public WebElement wishlistButon;

    @AndroidFindBy(xpath = "(//android.widget.ImageView)[11]")
    public WebElement cartButon;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Profile\"]")
    public WebElement profileGirisButon;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Sign In\"]")
    public WebElement signInGirisButon;

    @AndroidFindBy(xpath = "//android.widget.EditText[1]")
    public WebElement email_phoneGirisBox;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"*Use Email Instead\"]")
    public WebElement emailInsteadButon;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"*Use Phone Instead\"]")
    public WebElement phoneInsteadButon;

    @AndroidFindBy(xpath = "(//android.widget.EditText)[2]")
    public WebElement passwordGirisBox;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Forgot Password\"]")
    public WebElement forgotPasswordButon;

    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"Sign In\"])[2]")
    public WebElement signInOnayButon;

    @AndroidFindBy(xpath = "(//android.widget.EditText)[1]")
    public WebElement forgotEmail_phoneBox;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Get OTP\"]")
    public WebElement getOTPButon;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Back to sign in\"]")
    public WebElement backToSignInButon;

    @AndroidFindBy(xpath = "(//android.widget.EditText)[1]")
    public WebElement resetNewPasswordBox;

    @AndroidFindBy(xpath = "(//android.widget.EditText)[2]")
    public WebElement resetConfirmPasswordBox;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Submit\"]")
    public WebElement resetSubmitButon;

    @AndroidFindBy(xpath = "//android.widget.Toast")
    public WebElement toastSuccessMessage;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Change Password\"]")
    public WebElement dashboardChangePasswordButon;

    @AndroidFindBy(xpath = "(//android.widget.EditText)[1]")
    public WebElement oldPasswordChangePasswordBox;

    @AndroidFindBy(xpath = "(//android.widget.EditText)[2]")
    public WebElement newPasswordChangePasswordBox;

    @AndroidFindBy(xpath = "(//android.widget.EditText)[2]")
    public WebElement confirmPasswordChangePasswordBox;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Save Changes\"]")
    public WebElement saveChangesChangePasswordButon;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Logout\"]")
    public WebElement logoutButon;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Most Popular\"]")
    public WebElement mostPopularYaziElementi;

    @AndroidFindBy(xpath = "(//android.view.View)[6]")
    public WebElement grupMenusuDogrulama;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Sing In To See Your Info\"]")
    public WebElement profileSayfasiDogrulamaYazisi;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Sign in to continue shopping\"]")
    public WebElement wishlistSayfasiDogrulamaYazisi;

    @AndroidFindBy(xpath = "(//android.widget.ImageView)[2]")
    public WebElement buyutecSimgesi;

    @AndroidFindBy(xpath = "android.widget.EditText")
    public WebElement searchbox;

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'Products Found')]")
    public WebElement aramaSonucYazisi;


    public void altBarIkonuGoruntulemeVeTiklama(String ikonAdi, boolean tiklamaYapilsinMi) {
        String nihaiXpath;

        if (ikonAdi.equalsIgnoreCase("Cart")) {
            // En geniş katmanı veya mor butonun durduğu alanı hedeflesin
            nihaiXpath = "//android.widget.ImageView[@content-desc='Category']/../preceding-sibling::android.view.View";
        } else {
            nihaiXpath = "//*[@content-desc='" + ikonAdi + "']";
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement ikon = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath(nihaiXpath)));

        Assert.assertTrue(ikonAdi + " ikonu barda görünmüyor!", ikon.isDisplayed());

        if (tiklamaYapilsinMi) {
            if (ikonAdi.equalsIgnoreCase("Cart")) {

                // 🎯 KESİN ÇÖZÜM: Inspector'dan aldığımız bounds değerini buraya tam olarak yazıyoruz
                String sepetBounds = "[614,2699][826,2912]";

                // clickOnCenterOfBounds metodunun projenizde hangi class'ın içindeyse onun adını yazın
                // (Örnek olarak 'ReusableMethods' yazdım, kendi sınıf adınızla değiştirin)
                AndroidClickUtils.clickOnCenterOfBounds(driver, sepetBounds);

                System.out.println("🚀 Cart butonuna fiziksel merkez koordinatından dokunuş yapıldı!");
            } else {
                // Standart ve sorunsuz diğer butonlar (Home, Wishlist vb.) için normal tıklama
                ikon.click();
            }
            System.out.println("✅ " + ikonAdi + " ikonuna başarıyla tıklandı.");
        }
    }



    public void sayfaYonlendirmesiniDogrula(String sayfaAdi) {

        switch (sayfaAdi.toLowerCase()) {
            case "home":
                Assert.assertTrue("Most Popular yazısı ekranda görünmüyor!",
                        mostPopularYaziElementi.isDisplayed());
                break;

            case "category":
                Assert.assertTrue("Kategori sayfasina gecis yapilamiyor",
                        grupMenusuDogrulama.isEnabled());
                break;

            case "profile":
                Assert.assertTrue("Profile sayfasina yonlendirilmedi",
                        profileSayfasiDogrulamaYazisi.isDisplayed());
                break;

            case "wishlist":
                Assert.assertTrue("Wishlist sayfasina yonlendirilmedi!",
                        wishlistSayfasiDogrulamaYazisi.isDisplayed());
                break;

            case "cart":
                WebElement sepetElementi = wait.until(ExpectedConditions.presenceOfElementLocated(
                        AppiumBy.xpath("//*[contains(@content-desc, 'Cart') or contains(@text, 'Cart') or contains(@content-desc, 'Shopping') or contains(@content-desc, 'Checkout') or contains(@text, 'Bag')]")
                ));

                System.out.println("🛒 Sepet ekranında başarıyla yakalanan element açıklaması: " + sepetElementi.getAttribute("content-desc"));
                Assert.assertTrue("Shopping cart sayfasına yönlendirilemedi!", sepetElementi.isDisplayed());
                break;
            default:

                WebElement dinamikElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                        AppiumBy.xpath("//*[@text='" + sayfaAdi + "' or @content-desc='" + sayfaAdi + "']")
                ));
                Assert.assertTrue(sayfaAdi + " sayfasına yönlendirme başarısız oldu!", dinamikElement.isDisplayed());
                break;
        }
    }

    public void klavyedenAraButonunaBas() {
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public void aramaSonuclariListelendiMi() {

        wait.until(ExpectedConditions.visibilityOf(aramaSonucYazisi));

        String bulunanUrunMetni = aramaSonucYazisi.getAttribute("content-desc");

        String sadeceRakamlar = bulunanUrunMetni.replaceAll("[^0-9]", "");

        int urunSayisi = Integer.parseInt(sadeceRakamlar);

        Assert.assertTrue(
                "HATA: Arama sonucunda hiç ürün bulunamadı! Ekranda yazan: " + bulunanUrunMetni,
                urunSayisi > 0
        );
    }
}
