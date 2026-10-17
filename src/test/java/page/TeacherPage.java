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
        try {
            return wait.until(ExpectedConditions.visibilityOf(productsFoundLabel)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void selectProduct(String productName) {
        By product = AppiumBy.androidUIAutomator(
                "new UiSelector().descriptionStartsWith(\"" + productName + "\").clickable(true)");
        wait.until(ExpectedConditions.elementToBeClickable(product)).click();
    }

    // ===================== Bottom Navigation (Common) =====================

    public void clickBottomTab(String tabName) {
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId(tabName))).click();
    }

    // ===================== Profile Menu (Common) =====================

    public boolean isProfileMenuDisplayedAndEnabled(String menuName) {
        try {
            WebElement menu = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId(menuName)));
            return menu.isDisplayed() && menu.isEnabled();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
