package page;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeacherPage extends BasePage {

    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    // ===================== Common =====================

    public boolean isHeadingDisplayed(String heading) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId(heading)))
                    .isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    private boolean isVisible(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isElementDisplayedAndEnabled(String accessibilityId) {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId(accessibilityId)));
            return element.isDisplayed() && element.isEnabled();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickElement(String accessibilityId) {
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId(accessibilityId))).click();
    }

    public void waitForProfileToLoad(String phoneNumber) {
        By profilePhone = AppiumBy.androidUIAutomator(
                "new UiSelector().descriptionContains(\"" + phoneNumber + "\")");
        wait.until(ExpectedConditions.visibilityOfElementLocated(profilePhone));
    }

    public void scrollAndClickElement(String accessibilityId) {
        By element = AppiumBy.accessibilityId(accessibilityId);
        if (!isPresentWithin(element, Duration.ofSeconds(3))) {
            boolean canScrollMore = true;
            while (driver.findElements(element).isEmpty() && canScrollMore) {
                canScrollMore = scrollPageDown();
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    private boolean isPresentWithin(By locator, Duration timeout) {
        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private boolean scrollPageDown() {
        WebElement page = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().scrollable(true)"));
        Object canScrollMore = driver.executeScript("mobile: scrollGesture", Map.of(
                "elementId", page,
                "direction", "down",
                "percent", 0.5));
        return Boolean.TRUE.equals(canScrollMore);
    }

    // ===================== Categories (US-004) =====================

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Categories']"
            + "/following-sibling::android.view.View[1]//android.view.View[@scrollable='true']")
    private WebElement categoryList;

    public List<String> getAllCategoryNames() {
        List<String> names = new ArrayList<>(getVisibleCategoryNames());
        boolean canScrollMore = true;
        while (canScrollMore) {
            canScrollMore = scrollCategoryListRight();
            for (String name : getVisibleCategoryNames()) {
                if (!names.contains(name)) {
                    names.add(name);
                }
            }
        }
        return names;
    }

    private List<String> getVisibleCategoryNames() {
        List<String> names = new ArrayList<>();
        for (WebElement item : categoryList.findElements(By.xpath(".//android.view.View[@clickable='true']"))) {
            names.add(item.getAttribute("content-desc"));
        }
        return names;
    }

    private boolean scrollCategoryListRight() {
        Object canScrollMore = driver.executeScript("mobile: scrollGesture", Map.of(
                "elementId", categoryList,
                "direction", "right",
                "percent", 0.8));
        return Boolean.TRUE.equals(canScrollMore);
    }

    public void selectCategory(String categoryName) {
        By category = AppiumBy.accessibilityId(categoryName);
        boolean canScrollMore = true;
        while (driver.findElements(category).isEmpty() && canScrollMore) {
            canScrollMore = scrollCategoryListRight();
        }
        wait.until(ExpectedConditions.elementToBeClickable(category)).click();
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"Products Found\")")
    private WebElement productsFoundLabel;

    public boolean isProductListDisplayed() {
        return isVisible(productsFoundLabel);
    }

    public void selectProduct(String productName) {
        By product = AppiumBy.androidUIAutomator(
                "new UiSelector().descriptionStartsWith(\"" + productName + "\").clickable(true)");
        wait.until(ExpectedConditions.elementToBeClickable(product)).click();
    }

    // ===================== Address (US-017) =====================

    public void fillAddressForm(Map<String, String> formData) {
        typeIntoField(0, formData.get("Full Name"));
        typeIntoField(1, formData.get("Email"));
        typeIntoField(2, formData.get("Phone"));
        selectDropdownOption("Country", formData.get("Country"));
        selectDropdownOption("State", formData.get("State"));
        selectDropdownOption("City", formData.get("City"));
        typeIntoField(3, formData.get("Zip Code"));
        typeIntoStreetAddress(formData.get("Street Address"));
    }

    private void typeIntoField(int index, String value) {
        By field = AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.widget.EditText\").instance(" + index + ")");
        wait.until(ExpectedConditions.elementToBeClickable(field)).click();
        typeIntoFocusedField(value);
    }

    private void selectDropdownOption(String dropdownName, String option) {
        clickElement(dropdownName);
        typeIntoField(0, option);
        clickElement(option);
    }

    private void scrollFormToBottom() {
        WebElement form = driver.findElement(
                AppiumBy.androidUIAutomator("new UiSelector().scrollable(true)"));
        driver.executeScript("mobile: scrollGesture", Map.of(
                "elementId", form,
                "direction", "down",
                "percent", 1.0));
    }

    private void typeIntoStreetAddress(String value) {
        scrollFormToBottom();

        List<WebElement> fields = driver.findElements(AppiumBy.className("android.widget.EditText"));
        fields.get(fields.size() - 1).click();
        typeIntoFocusedField(value);
    }

    private void typeIntoFocusedField(String value) {
        By focusedField = AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.widget.EditText\").focused(true)");
        wait.until(ExpectedConditions.visibilityOfElementLocated(focusedField)).sendKeys(value);
    }

    public boolean isAddressDisplayed(String streetAddress) {
        By addressCard = AppiumBy.androidUIAutomator(
                "new UiSelector().descriptionContains(\"" + streetAddress + "\")");
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(addressCard)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isAddressNotDisplayed(String streetAddress) {
        By addressCard = AppiumBy.androidUIAutomator(
                "new UiSelector().descriptionContains(\"" + streetAddress + "\")");
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(addressCard));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void deleteAddress(String streetAddress) {
        By deleteIcon = AppiumBy.androidUIAutomator(
                "new UiSelector().descriptionContains(\"" + streetAddress + "\")"
                        + ".childSelector(new UiSelector().className(\"android.widget.ImageView\").instance(1))");
        wait.until(ExpectedConditions.elementToBeClickable(deleteIcon)).click();
        clickElement("Delete");
    }

    public void editAddress(String currentStreet, String newStreet) {
        By editIcon = AppiumBy.androidUIAutomator(
                "new UiSelector().descriptionContains(\"" + currentStreet + "\")"
                        + ".childSelector(new UiSelector().className(\"android.widget.ImageView\").instance(0))");
        wait.until(ExpectedConditions.elementToBeClickable(editIcon)).click();

        By streetField = AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.widget.EditText\").text(\"" + currentStreet + "\")");
        WebElement street = wait.until(ExpectedConditions.elementToBeClickable(streetField));
        street.click();
        street.clear();
        typeIntoFocusedField(newStreet);
        scrollFormToBottom();

        By updateButton = AppiumBy.androidUIAutomator(
                "new UiSelector().description(\"Update Address\").clickable(true)");
        wait.until(ExpectedConditions.elementToBeClickable(updateButton)).click();
    }

    // ===================== Payment (US-024) =====================

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").clickable(true).instance(0)")
    private WebElement searchIcon;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@clickable='true'])[last()]")
    private WebElement cartIcon;

    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"@\").clickable(true).instance(0)")
    private WebElement firstAddressCard;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"confirmBtn\")")
    private WebElement stripeConfirmButton;

    public void searchProduct(String productName) {
        By successToast = AppiumBy.androidUIAutomator("new UiSelector().descriptionStartsWith(\"Success\")");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(successToast));
        wait.until(ExpectedConditions.elementToBeClickable(searchIcon)).click();
        typeIntoFocusedField(productName);
        driver.executeScript("mobile: performEditorAction", Map.of("action", "search"));
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    public void selectFirstAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(firstAddressCard)).click();
    }

    public boolean isStripeFormDisplayed() {
        return isVisible(stripeConfirmButton);
    }
}
