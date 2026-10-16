package page;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebElement;
import utilities.AndroidClickUtils;
import utilities.TouchMethods;

public class ManagerPage extends QueryCardPage{

    public ManagerPage(){
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

    @AndroidFindBy(xpath = "//android.widget.EditText[2]")
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








}
