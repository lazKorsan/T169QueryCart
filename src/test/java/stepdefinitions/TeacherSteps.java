package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import page.RegisterPage;
import page.TeacherPage;
import utilities.ConfigReader;

import java.util.List;

import static utilities.Driver.getAppiumDriver;

public class TeacherSteps {

    private static final Logger logger = LogManager.getLogger(TeacherSteps.class);
    private final TeacherPage teacherPage = new TeacherPage();
    private final RegisterPage registerPage = new RegisterPage();

    @Given("Teacher kullanicisi uygulamayi acar")
    public void launchApp() {
        getAppiumDriver();
        logger.info("Uygulama acildi");
    }

    @Given("Teacher kullanicisi gecerli telefon numarasi ve sifre ile giris yapar")
    public void loginWithValidCredentials() {
        registerPage.loginWithPhoneNumber(
                ConfigReader.getProperty("phoneNumber"),
                ConfigReader.getProperty("password"));
        logger.info("Teacher kullanicisi giris yapti");
    }

    @Then("Teacher kullanicisi giris yapildigini dogrular")
    public void verifyUserIsLoggedIn() {
        Assert.assertTrue("Giris yapilamadi: anasayfada Categories basligi gorunmuyor",
                teacherPage.isHeadingDisplayed("Categories"));
    }

    @When("Teacher kullanicisi {string} sayfasina gider")
    public void goToBottomTab(String tabName) {
        teacherPage.clickBottomTab(tabName);
    }

    @Then("Teacher kullanicisi {string} menusunun gorunur ve aktif oldugunu dogrular")
    public void verifyProfileMenuIsActive(String menuName) {
        Assert.assertTrue(menuName + " menusu gorunur ve aktif degil",
                teacherPage.isElementDisplayedAndEnabled(menuName));
    }

    @Then("Teacher kullanicisi {string} butonunun gorunur ve aktif oldugunu dogrular")
    public void verifyButtonIsActive(String buttonName) {
        Assert.assertTrue(buttonName + " butonu gorunur ve aktif degil",
                teacherPage.isElementDisplayedAndEnabled(buttonName));
    }

    @Then("Teacher kullanicisi anasayfada {string} basliginin gorunur oldugunu dogrular")
    public void verifyHeadingIsVisible(String heading) {
        Assert.assertTrue(heading + " basligi gorunmuyor", teacherPage.isHeadingDisplayed(heading));
    }

    @Then("Teacher kullanicisi Categories basligi altinda asagidaki kategorilerin gorunur oldugunu dogrular")
    public void verifyCategoriesAreVisible(List<String> expectedCategories) {
        List<String> actualCategories = teacherPage.getAllCategoryNames();
        logger.info("Ekranda gorunen kategoriler: {}", actualCategories);
        Assert.assertEquals("Kategori listesi beklenenle eslesmiyor", expectedCategories, actualCategories);
    }

    @When("Teacher kullanicisi {string} kategorisini secer")
    public void selectCategory(String categoryName) {
        teacherPage.selectCategory(categoryName);
    }

    @Then("Teacher kullanicisi {string} kategorisine ait urunlerin listelendigini dogrular")
    public void verifyProductsAreListed(String categoryName) {
        Assert.assertTrue(categoryName + " sayfa basligi gorunmuyor", teacherPage.isHeadingDisplayed(categoryName));
        Assert.assertTrue("Urun listesi gorunmuyor", teacherPage.isProductListDisplayed());
    }

    @When("Teacher kullanicisi {string} urununu secer")
    public void selectProduct(String productName) {
        teacherPage.selectProduct(productName);
    }

    @Then("Teacher kullanicisi {string} urununun detay sayfasinin acildigini dogrular")
    public void verifyProductDetailPageIsOpened(String productName) {
        Assert.assertTrue(productName + " detay sayfasi acilmadi", teacherPage.isHeadingDisplayed(productName));
    }

    @When("Teacher kullanicisi {string} menusune tiklar")
    public void clickProfileMenuItem(String menuName) {
        teacherPage.clickProfileMenuItem(menuName);
    }
}
