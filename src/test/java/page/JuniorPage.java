package page;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.Locale;

public class JuniorPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(JuniorPage.class);


    public JuniorPage() {

        super();
    }
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_allow_button\")")
    public WebElement allowButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_deny_button\")")
    public WebElement dontAllowButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(1)")
    public WebElement aramaCubugu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"See All\")")
    public WebElement seeAllButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Home\")")
    public WebElement homeButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Category\")")
    public WebElement kategoriButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(17)")
    public WebElement alisverisSepetiButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Wishlist\")")
    public WebElement wishlistButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Profile\")")
    public WebElement profileButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Sign In\")")
    public WebElement signInButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"*Use Email Instead\")")
    public WebElement useEmailInsteadButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Sign In\").instance(1)")
    public WebElement signIn2Butonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Edit Profile\")")
    public WebElement editProfileButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Save Changes\")")
    public WebElement saveChangesButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Videos\")")
    public WebElement videosButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Review\")")
    public WebElement reviewButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Shipping & Return\")")
    public WebElement shippingReturnButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Logout\")")
    public WebElement logOutButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Details\")")
    public WebElement detailsButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Product Details\")")
    public WebElement productDetailsButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Product Videos\")")
    public WebElement productVideosButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Product Reviews\")")
    public WebElement productReviewsButonu;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Shipping & Return\").instance(1)")
    public WebElement shippingReturnButonu2;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
    public WebElement fullNameKutusu;
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(1)")
    public WebElement emailKutusu;
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(2)")
    public WebElement phoneKutusu;



    public WebElement getElementByName(String elementName) {
        if (elementName == null || elementName.isBlank()) {
            return null;
        }

        String normalized = elementName.strip().toLowerCase(Locale.ROOT);

        return switch (normalized) {
            case "allow" -> allowButonu;
            case "don't allow" -> dontAllowButonu;
            case "arama cubugu" -> aramaCubugu;
            case "see all" -> seeAllButonu;
            case "home"-> homeButonu;
            case "category" -> kategoriButonu;
            case "alisveris sepeti" -> alisverisSepetiButonu;
            case "wishlist" -> wishlistButonu;
            case "videos" -> videosButonu;
            case "profile" -> profileButonu;
            case "sign in" -> signInButonu;
            case "use email instead" -> useEmailInsteadButonu;
            case "sign in2" -> signIn2Butonu;
            case "edit profile" ->editProfileButonu;
            case "save changes" -> saveChangesButonu;
            case "review" -> reviewButonu;
            case "shipping & return" -> shippingReturnButonu;
            case "logout" -> logOutButonu;
            case "details" -> detailsButonu;
            case "product details" -> productDetailsButonu;
            case "product videos" -> productVideosButonu;
            case "product reviews" -> productReviewsButonu;
            case "shipping & return2" -> shippingReturnButonu2;
            case "email" -> emailKutusu;
            case "full name" -> fullNameKutusu;
            case "phone" -> phoneKutusu;
            default -> throw new IllegalArgumentException("Tanımsız element adı: " + elementName);
        };
    }

    public static void scrollAndClickByPartialDescription(String urunAdi) {
        io.appium.java_client.android.AndroidDriver driver = (io.appium.java_client.android.AndroidDriver) utilities.Driver.getAppiumDriver();

        driver.findElement(io.appium.java_client.AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().descriptionContains(\"" + urunAdi + "\"))"
        )).click();
    }}
