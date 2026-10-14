package stepdefinitions;

import io.cucumber.java.en.*;
import utilities.Driver;

public class ManagerSteps {

    @Given("Manager kullanicisi uygulamayi acar")
    public void manager_kullanicisi_uygulamayi_acar() {
        Driver.getAppiumDriver();
    }
    @Given("Manager kullanicisi sayfa alt barinda {string} ikonunun gorunur oldugunu dogrular")
    public void manager_kullanicisi_sayfa_alt_barinda_ikonunun_gorunur_oldugunu_dogrular(String string) {

    }
    @Given("Manager kullanicisi alt bardaki {string} ikonuna tiklar")
    public void manager_kullanicisi_alt_bardaki_ikonuna_tiklar(String string) {

    }
    @Given("Manager kullanicisi ilgili {string} basligina yonlendirildigini dogrular")
    public void manager_kullanicisi_ilgili_basligina_yonlendirildigini_dogrular(String string) {

    }
    @Given("Manager kullanicisi ana sayfada {string} arama kutusunu goruntuler")
    public void manager_kullanicisi_ana_sayfada_arama_kutusunu_goruntuler(String string) {

    }
    @Given("Manager kullanicisi arama kutusuna test etmek istedigi bir urun adini yazar")
    public void manager_kullanicisi_arama_kutusuna_test_etmek_istedigi_bir_urun_adini_yazar() {

    }
    @Given("Manager kullanicisi klavyeden arama butonuna tiklar")
    public void manager_kullanicisi_klavyeden_arama_butonuna_tiklar() {

    }
    @Given("Manager kullanicisi arama sonuclarinin ekranda listelendigini dogrular")
    public void manager_kullanicisi_arama_sonuclarinin_ekranda_listelendigini_dogrular() {

    }

}
