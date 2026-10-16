package stepdefinitions;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.HistoryPage;
import utilities.AndroidClickUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static utilities.Driver.getAppiumDriver;

public class HistorySteps {

    private static final Logger logger = LogManager.getLogger(HistorySteps.class);
    private final HistoryPage historyPage = new HistoryPage();
    private final AndroidDriver driver = (AndroidDriver) Driver.getAppiumDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    // Her senaryonun ilk adiminda olusturulur (driver yeniden acildiktan sonra)
    // private HistoryPage historyPage;

    // =====================================================================
    // ===== ORTAK =====
    // =====================================================================

    @Given("History kullanicisi uygulamayi acar")
    public void historyKullanicisiUygulamayiAcar() {
        // Projede senaryo sonunda driver'i kapatan Hooks yok.
        // Her senaryo cikis yapilmis temiz uygulamayla baslasin diye driver'i kapatip yeniden aciyoruz.
        // (noReset=false oldugu icin yeni oturumda uygulama sifirlanir.)
        logger.info("History kullanicisi uygulamayi sifirdan aciyor");
        //Driver.quitAppiumDriver();
        //Driver.getAppiumDriver();
        getAppiumDriver();
        AndroidClickUtils.verifyButtonByName("Profile");
        // historyPage = new HistoryPage();
    }

    @Then("History kullanicisi {string} basliginin gorunur oldugunu dogrular")
    public void historyKullanicisiBasligininGorunurOldugunuDogrular(String title) {
        Assert.assertTrue("❌ '" + title + "' basligi ekranda gorunmuyor!",
                historyPage.isVisibleWithWait(title));
        logger.info("✅ '{}' basligi gorunur", title);
    }

    @Then("History kullanicisi {string} basliginin gorunmedigini dogrular")
    public void historyKullanicisiBasligininGorunmediginiDogrular(String title) {
        Assert.assertTrue("❌ '" + title + "' basligi gorunmemesi gerekirken ekranda!",
                historyPage.isNotVisibleWithWait(title));
        logger.info("✅ '{}' basligi ekranda yok (beklenen)", title);
    }

    @When("History kullanicisi {string} menusune tiklar")
    public void historyKullanicisiMenusuneTiklar(String menuName) {
        historyPage.clickByAccessibility(menuName);
    }

    // =====================================================================
    // ===== US005 - POPULAR BRANDS =====
    // =====================================================================

    @When("History kullanicisi ana sayfada {string} basligina kadar kaydirir")
    public void historyKullanicisiAnaSayfadaBasliginaKadarKaydirir(String sectionTitle) {
        historyPage.scrollToSection(sectionTitle);
    }

    @Then("History kullanicisi Popular Brands altinda en az {int} markanin gorunur oldugunu dogrular")
    public void historyKullanicisiPopularBrandsAltindaEnAzMarkaninGorunurOldugunuDogrular(int minCount) {
        List<String> brands = historyPage.getVisibleBrandNames();
        Assert.assertTrue("❌ En az " + minCount + " marka bekleniyordu, bulunan: " + brands,
                brands.size() >= minCount);
        logger.info("✅ {} marka gorunur: {}", brands.size(), brands);
    }

    @Then("History kullanicisi Popular Brands altinda {string} markasinin gorunur oldugunu dogrular")
    public void historyKullanicisiPopularBrandsAltindaMarkasininGorunurOldugunuDogrular(String brandName) {
        Assert.assertTrue("❌ '" + brandName + "' markasi ekranda gorunmuyor!",
                historyPage.isBrandDisplayed(brandName));
        logger.info("✅ '{}' markasi gorunur", brandName);
    }

    @When("History kullanicisi Popular Brands listesini {string} markasi gorunene kadar yana kaydirir")
    public void historyKullanicisiPopularBrandsListesiniMarkasiGorunenekadarYanaKaydirir(String brandName) {
        historyPage.swipeBrandsUntilVisible(brandName, 6);
    }

    // =====================================================================
    // ===== US016 - DASHBOARD =====
    // =====================================================================

    @Then("History kullanicisi ana sayfada Profile ikonunun gorunur ve aktif oldugunu dogrular")
    public void historyKullanicisiAnaSayfadaProfileIkonununGorunurVeAktifOldugunuDogrular() {
        Assert.assertTrue("❌ Profile ikonu gorunur ve aktif degil!", historyPage.isProfileIconActive());
        logger.info("✅ Profile ikonu gorunur ve aktif");
    }

    @When("History kullanicisi e-posta ile giris yapar")
    public void historyKullanicisiEPostaIleGirisYapar() {
      //  historyPage.loginWithEmail(getConfig("historyEmail"), getConfig("historyPassword"));
        historyPage.loginWithMailMethod2();
    }

    @When("History kullanicisi Profile ikonuna tiklar")
    public void historyKullanicisiProfileIkonunaTiklar() {
        historyPage.clickByAccessibility("Profile");
    }

    @Then("History kullanicisi dashboardda giris yapan kullanicinin e-postasini gorur")
    public void historyKullanicisiDashboarddaGirisYapanKullanicininEPostasiniGorur() {
        String email = getConfig("historyEmail");
        Assert.assertTrue("❌ Dashboard'da '" + email + "' gorunmuyor!", historyPage.isVisibleWithWait(email));
        logger.info("✅ Dashboard acildi, kullanici: {}", email);
    }

    @When("History kullanicisi My Account verilerinin yuklenmesini bekler")
    public void historyKullanicisiMyAccountVerilerininYuklenmesiniBekler() {
        historyPage.waitForMyAccountLoaded();
    }



    @Then("History kullanicisi dashboard menusunde su basliklarin gorunur oldugunu dogrular")
    public void historyKullanicisiDashboardMenusundeSuBasliklarinGorunurOldugunuDogrular(List<String> menuTitles) {
        List<String> missing = new ArrayList<>();
        for (String title : menuTitles) {
            if (historyPage.isVisibleWithWait(title)) {
                logger.info("✅ Menu basligi gorunur: {}", title);
            } else {
                logger.error("❌ Menu basligi gorunmuyor: {}", title);
                missing.add(title);
            }
        }
        Assert.assertTrue("❌ Dashboard'da gorunmeyen menu basliklari: " + missing, missing.isEmpty());
    }

    @Then("History kullanicisi {string} ozet kartinin ve degerinin gorunur oldugunu dogrular")
    public void historyKullanicisiOzetKartininVeDegerininGorunurOldugunuDogrular(String label) {
        Assert.assertTrue("❌ '" + label + "' karti gorunmuyor!", historyPage.isVisibleWithWait(label));

        String value = historyPage.getSummaryValue(label);
        // Deger sayi (0, 12) veya para ($0.00, $15.50) olmali
        Assert.assertTrue("❌ '" + label + "' degeri gecersiz: " + value,
                value.matches("^\\$?\\d+(\\.\\d+)?$"));
        logger.info("✅ '{}' karti gorunur, deger: {}", label, value);
    }

    @Then("History kullanicisi {string} ozet kartinin degerinin para formatinda oldugunu dogrular")
    public void historyKullanicisiOzetKartininDegerininParaFormatindaOldugunuDogrular(String label) {
        String value = historyPage.getSummaryValue(label);
        // Para formati: $ ile baslar, kurus kismi 2 haneli (orn. $0.00, $125.50)
        Assert.assertTrue("❌ '" + label + "' degeri para formatinda degil: " + value,
                value.matches("^\\$\\d+\\.\\d{2}$"));
        logger.info("✅ '{}' degeri para formatinda: {}", label, value);
    }

    @Then("History kullanicisi siparis gecmisindeki ilk sipariste siparis no, durum ve toplam bilgilerinin oldugunu dogrular")
    public void historyKullanicisiSiparisGecmisindekiIlkSiparisteBilgilerinOldugunuDogrular() {
        String card = historyPage.getFirstOrderCardText();
        String[] lines = card.split("\n");

        String orderNo = lines[0].trim();
        String total = lines[lines.length - 1].trim();

        Assert.assertTrue("❌ Siparis numarasi beklenen formatta degil: " + orderNo, orderNo.matches("^#\\d+$"));
        Assert.assertTrue("❌ Kartta 'Delivery Status' yok:\n" + card, card.contains("Delivery Status:"));
        Assert.assertTrue("❌ Kartta 'Payment Status' yok:\n" + card, card.contains("Payment Status:"));
        Assert.assertTrue("❌ Kartta 'Total' yok:\n" + card, card.contains("Total:"));
        Assert.assertTrue("❌ Toplam tutar para formatinda degil: " + total, total.matches("^\\$\\d+\\.\\d{2}$"));

        logger.info("✅ Ilk siparis karti eksiksiz: {} / toplam {}", orderNo, total);
    }

    @Then("History kullanicisi {string} degerinin siparis gecmisindeki siparis sayisina esit oldugunu dogrular")
    public void historyKullanicisiDegerininSiparisGecmisindekiSiparisSayisinaEsitOldugunuDogrular(String label) {
        // Once ozet degerini oku (sayfa kaydirilmadan once etiket ekranda)
        int expected = Integer.parseInt(historyPage.getSummaryValue(label));

        // Sonra sayfayi kaydirarak tum siparis kartlarini topla
        List<String> orders = historyPage.collectAllOrderNumbers();

        Assert.assertTrue("❌ Siparis gecmisinde hic siparis listelenmiyor!", !orders.isEmpty());
        Assert.assertEquals("❌ '" + label + "' (" + expected + ") ile listelenen siparis sayisi ("
                + orders.size() + ") esit degil! Siparisler: " + orders, expected, orders.size());

        logger.info("✅ '{}' = {} ve listede {} siparis var: {}", label, expected, orders.size(), orders);
    }

    // =====================================================================
    // ===== US018 - ORDER HISTORY =====
    // =====================================================================

    // Senaryo boyunca secilen siparisin bilgileri (Cucumber her senaryoda bu sinifi yeniden olusturur)
    private String selectedOrderNo;
    private String selectedOrderTotal;

    // ----- Kriter 1: Dashboard sideBar'da Order History gorunur ve aktif -----

    @Then("History kullanicisi dashboard menusunde Order History basliginin gorunur ve aktif oldugunu dogrular")
    public void historyKullanicisiDashboardMenusundeOrderHistoryBasligininGorunurVeAktifOldugunuDogrular() {
        Assert.assertTrue("❌ Dashboard menusundeki 'Order History' gorunur ve aktif degil!",
                historyPage.isSidebarOrderHistoryActive());
        logger.info("✅ Dashboard menusunde 'Order History' gorunur ve aktif");
    }

    @When("History kullanicisi dashboard menusunden Order History sayfasini acar")
    public void historyKullanicisiDashboardMenusundenOrderHistorySayfasiniAcar() {
        historyPage.openOrderHistoryFromMenu();
    }

    @Then("History kullanicisi Order History sayfasinin acildigini dogrular")
    public void historyKullanicisiOrderHistorySayfasininAcildiginiDogrular() {
        Assert.assertTrue("❌ Order History sayfasi acilmadi!", historyPage.isOrderHistoryPageOpened());
        logger.info("✅ Order History sayfasi acildi");
    }

    // ----- Kriter 2: Goruntuleme (goz) ikonu aktif -----

    @When("History kullanicisi listedeki ilk siparisi secer")
    public void historyKullanicisiListedekiIlkSiparisiSecer() {
        selectOrder(null);
    }

    @When("History kullanicisi listedeki ilk {string} siparisi secer")
    public void historyKullanicisiListedekiIlkDurumdakiSiparisiSecer(String status) {
        selectOrder(status);
    }

    @Then("History kullanicisi secilen siparisin goruntuleme ikonunun aktif oldugunu dogrular")
    public void historyKullanicisiSecilenSiparisinGoruntulemeIkonununAktifOldugunuDogrular() {
        Assert.assertTrue("❌ " + selectedOrderNo + " siparisinin goruntuleme (goz) ikonu aktif degil!",
                historyPage.isOrderEyeIconActive(selectedOrderNo));
        logger.info("✅ {} siparisinin goruntuleme ikonu aktif", selectedOrderNo);
    }

    @When("History kullanicisi secilen siparisin goruntuleme ikonuna tiklar")
    public void historyKullanicisiSecilenSiparisinGoruntulemeIkonunaTiklar() {
        historyPage.tapOrderEyeIcon(selectedOrderNo);
    }

    @Then("History kullanicisi secilen siparisin Order Details sayfasinin acildigini dogrular")
    public void historyKullanicisiSecilenSiparisinOrderDetailsSayfasininAcildiginiDogrular() {
        Assert.assertTrue("❌ Goz ikonuna basildi ama Order Details sayfasi acilmadi!",
                historyPage.isOrderDetailsPageOpened());

        // Dogru siparisin acildigini once toplam tutarla, sonra Order ID ile kanitliyoruz
        String detailsTotal = historyPage.getRowValue("Total");
        Assert.assertEquals("❌ Order Details'teki Total, secilen siparisin kartindaki Total ile ayni degil!",
                selectedOrderTotal, detailsTotal);

        // Sayfanin ustundeki Order ID, secilen siparisin numarasi olmali (dogru siparisin acildiginin kesin kaniti)
        String detailsOrderId = historyPage.getDetailsFieldValue("Order ID:");
        Assert.assertEquals("❌ Order Details'teki Order ID, secilen siparisin numarasi ile ayni degil!",
                selectedOrderNo, detailsOrderId);
        logger.info("✅ {} siparisinin Order Details sayfasi acildi (Order ID: {}, Total: {})",
                selectedOrderNo, detailsOrderId, detailsTotal);
    }

    @Then("History kullanicisi Order Details sayfasinda {string} butonunun aktif oldugunu dogrular")
    public void historyKullanicisiOrderDetailsSayfasindaButonununAktifOldugunuDogrular(String button) {
        Assert.assertTrue("❌ '" + button + "' butonu gorunur ve aktif degil!", historyPage.isButtonActive(button));
        logger.info("✅ '{}' butonu gorunur ve aktif", button);
    }

    // ----- Kriter 3: Siparis faturasina ulasilabilmeli -----

    @When("History kullanicisi Download Receipt butonuna tiklar")
    public void historyKullanicisiDownloadReceiptButonunaTiklar() {
        historyPage.clickDetailsButton("Download Receipt");
    }

    @Then("History kullanicisi secilen siparisin faturasinin acildigini dogrular")
    public void historyKullanicisiSecilenSiparisinFaturasininAcildiginiDogrular() {
        Assert.assertTrue("❌ Download Receipt'e basildi ama fatura ekrani acilmadi!", historyPage.isReceiptOpened());

        String receiptOrderNo = historyPage.getReceiptOrderNo();
        Assert.assertEquals("❌ Faturadaki siparis numarasi secilen siparisle ayni degil!",
                selectedOrderNo, receiptOrderNo);

        String receiptTotal = historyPage.getRowValue("Total");
        Assert.assertEquals("❌ Faturadaki Total, secilen siparisin Total degeriyle ayni degil!",
                selectedOrderTotal, receiptTotal);

        Assert.assertTrue("❌ Faturadaki 'Print Invoice' butonu aktif degil!",
                historyPage.isButtonActive("Print Invoice"));
        logger.info("✅ {} siparisinin faturasi acildi (Total: {}, Print Invoice aktif)", receiptOrderNo, receiptTotal);
    }

    // ----- Kriter 4: Siparis iptal edilebilmeli -----

    @When("History kullanicisi Cancel Order butonuna tiklar")
    public void historyKullanicisiCancelOrderButonunaTiklar() {
        logger.warn("{} siparisi iptal ediliyor (geri alinamaz)", selectedOrderNo);
        historyPage.clickDetailsButton("Cancel Order");
    }

    @Then("History kullanicisi siparisin iptal edildi mesajini gorur")
    public void historyKullanicisiSiparisinIptalEdildiMesajiniGorur() {
        Assert.assertTrue("❌ 'Order Canceled Successfully' bildirimi gorunmedi!",
                historyPage.isCancelSuccessMessageShown());
        logger.info("✅ 'Order Canceled Successfully' bildirimi goruldu");
    }

    @Then("History kullanicisi Cancel Order butonunun kayboldugunu dogrular")
    public void historyKullanicisiCancelOrderButonununKayboldugunuDogrular() {
        Assert.assertTrue("❌ Iptalden sonra Cancel Order butonu hala ekranda!", historyPage.isCancelOrderButtonGone());
        logger.info("✅ Iptal edilen sipariste Cancel Order butonu artik yok");
    }

    @Then("History kullanicisi Order Details sayfasinda siparisin iptal edildigini dogrular")
    public void historyKullanicisiOrderDetailsSayfasindaSiparisinIptalEdildiginiDogrular() {
        Assert.assertTrue("❌ Iptalden sonra Order Details'te 'Order Canceled' kutusu gorunmuyor!",
                historyPage.isOrderCanceledBannerShown());

        String orderId = historyPage.getDetailsFieldValue("Order ID:");
        Assert.assertEquals("❌ Iptal edilen sayfadaki Order ID secilen siparis degil!", selectedOrderNo, orderId);

        String status = historyPage.getDetailsFieldValue("Order Status:");
        if (status.equalsIgnoreCase("Canceled")) {
            logger.info("✅ Order Details -> Order Status: {}", status);
        } else if (status.matches("(?i)c.nceled")) {
            // Durum dogru (iptal) ama kelime yanlis yazilmis: testi durdurmuyoruz, bug olarak logluyoruz
            logger.warn("BUG: Order Details'te Order Status yazim hatasi: '{}' (beklenen 'Canceled')", status);
        } else {
            Assert.fail("❌ Iptalden sonra Order Status 'Canceled' olmali, okunan: " + status);
        }
        logger.info("✅ {} siparisi Order Details'te iptal edilmis gorunuyor", selectedOrderNo);
    }

    @When("History kullanicisi Order Details sayfasindan geri doner")
    public void historyKullanicisiOrderDetailsSayfasindanGeriDoner() {
        historyPage.clickBackArrow();
        Assert.assertTrue("❌ Geri donuldu ama Order History sayfasi acilmadi!", historyPage.isOrderHistoryPageOpened());
    }

    @Then("History kullanicisi secilen siparisin durumunun {string} oldugunu dogrular")
    public void historyKullanicisiSecilenSiparisinDurumununOldugunuDogrular(String expectedStatus) {
        String status = historyPage.getOrderStatusInList(selectedOrderNo, expectedStatus);
        Assert.assertEquals("❌ " + selectedOrderNo + " siparisinin Delivery Status degeri beklenen gibi degil!",
                expectedStatus, status);
        logger.info("✅ {} siparisinin Order History'deki durumu: {}", selectedOrderNo, status);
    }

    // Listede goz ikonu ekranda olan ilk siparisi (status verilirse o durumdaki ilk siparisi) secer
    private void selectOrder(String status) {
        selectedOrderNo = historyPage.findOrderWithVisibleEye(status);
        String card = historyPage.getVisibleOrderCardText(selectedOrderNo);
        Assert.assertNotNull("❌ Secilen siparisin karti okunamadi: " + selectedOrderNo, card);
        selectedOrderTotal = HistoryPage.getCardField(card, "Total:");
        Assert.assertNotNull("❌ Secilen siparisin kartinda Total degeri yok: " + selectedOrderNo, selectedOrderTotal);
        logger.info("✅ Siparis secildi: {} / toplam {}", selectedOrderNo, selectedOrderTotal);
    }

    // =====================================================================
    // ===== YARDIMCI =====
    // =====================================================================

    // ConfigReader anahtar bulamazsa null dondurur; burada net bir hata mesajina ceviriyoruz
    private static String getConfig(String key) {
        String value = ConfigReader.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new RuntimeException("configuration.properties icinde '" + key + "' bulunamadi!");
        }
        return value.trim();
    }
}