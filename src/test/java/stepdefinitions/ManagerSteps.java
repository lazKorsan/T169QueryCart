package stepdefinitions;

import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.AndroidClickUtils;
import utilities.Driver;

import static utilities.Driver.getAppiumDriver;

public class ManagerSteps {

    private static final Logger logger = LogManager.getLogger(ManagerSteps.class);

    @Given("Manager kullanicisi uygulamayi acar")
    public void manager_kullanicisi_uygulamayi_acar() {
        getAppiumDriver();

        AndroidClickUtils.verifyButtonByName("Profile");
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

    @Given("Manager kullanicisi Forgot Password sayfasini goruntulendigini dogrular")
    public void manager_kullanicisi_forgot_password_sayfasini_goruntulendigini_dogrular() {

    }

    @Given("Manager kullanicisi dogrulama yontemi olarak {string} secenegini secer")
    public void manager_kullanicisi_dogrulama_yontemi_olarak_secenegini_secer(String string) {

    }
    @Given("Manager kullanicisi ilgili alana {string} bilgisini girer")
    public void manager_kullanicisi_ilgili_alana_bilgisini_girer(String string) {

    }
    @Given("Manager kullanicisi Get OTP butonuna tiklar")
    public void manager_kullanicisi_get_otp_butonuna_tiklar() {

    }
    @Given("Manager kullanicisi {string} kutusuna yeni sifreyi yazar")
    public void manager_kullanicisi_kutusuna_yeni_sifreyi_yazar(String string) {

    }
    @Given("Manager kullanicisi {string} kutusuna yeni sifreyi tekrar yazar")
    public void manager_kullanicisi_kutusuna_yeni_sifreyi_tekrar_yazar(String string) {

    }

    @Given("Manager kullanicisi {string} yazisini goruntuler ve islemin basariyla gerceklestigini dogrular")
    public void manager_kullanicisi_yazisini_goruntuler_ve_islemin_basariyla_gerceklestigini_dogrular(String string) {

    }
    @Given("Manager kullanicisi {string} butonunun gorunur ve aktif oldugunu dogrular")
    public void manager_kullanicisi_butonunun_gorunur_ve_aktif_oldugunu_dogrular(String string) {

    }
    @Given("Manager kullanicisi {string} butonuna tiklar")
    public void manager_kullanicisi_butonuna_tiklar(String string) {

    }
    @Given("Manager kullanicisi password kutusuna {string} bilgisini girer")
    public void manager_kullanicisi_password_kutusuna_bilgisini_girer(String string) {

    }
    @Given("Manager kullanicisi acilan dashboardda {string} linkini goruntuler ve tiklar")
    public void manager_kullanicisi_acilan_dashboardda_linkini_goruntuler_ve_tiklar(String string) {

    }
    @Given("Manager kullanicisi old password alanina {string}, new password alanina {string} ve confirm alanina {string} yazar")
    public void manager_kullanicisi_old_password_alanina_new_password_alanina_ve_confirm_alanina_yazar(String string, String string2, String string3) {

    }
    @Given("Manager kullanicisi {string} butonunu goruntuler ve tiklar")
    public void manager_kullanicisi_butonunu_goruntuler_ve_tiklar(String string) {

    }

}
