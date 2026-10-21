package page;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

public class BadgedPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(BadgedPage.class);
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    public BadgedPage() {
        super();
    }

    public AndroidDriver getDriver() {
        return this.driver;
    }


    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Home\")")
    public WebElement homeButton;
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Wishlist\")")
    public WebElement wishlistButton;


    public WebElement getElementByName(String elementName) {
        if (elementName == null || elementName.isBlank()) {
            return null;
        }
        String normalized = elementName.strip().toLowerCase(Locale.ROOT);

        return switch (normalized) {
            case "wishlist" -> wishlistButton;
            case "home" -> homeButton;

            default -> null;
        };
    }
}
