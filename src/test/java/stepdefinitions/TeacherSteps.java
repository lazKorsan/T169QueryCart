package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import page.TeacherPage;

import java.util.List;

import static utilities.Driver.getAppiumDriver;

public class TeacherSteps {

    private static final Logger logger = LogManager.getLogger(TeacherSteps.class);
    private final TeacherPage teacherPage = new TeacherPage();

    @Given("Teacher kullanicisi uygulamayi acar")
    public void launchApp() {
        getAppiumDriver();
        logger.info("Uygulama acildi");
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
}
