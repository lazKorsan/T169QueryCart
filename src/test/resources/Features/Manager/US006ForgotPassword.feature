Feature: Kayıtlı bir kullanici olarak şifremi unuttugumda şifre değiştirme işlemi yapabilmek istiyorum

  Background: Manager kullanicisi uygulamayi acar
    * Manager kullanicisi uygulamayi acar
    * Manager kullanicisi "Profile" butonuna tiklar
    * Manager kullanicisi "Sign In" butonuna tiklar
    * Manager kullanicisi "Forgot Password" butonuna tiklar

  @US006ForgotPassword
  Scenario: Sisteme giris yaparken sifremi unuttum butonunun gorunur ve aktif olmasi

    * Manager kullanicisi Forgot Password sayfasini goruntulendigini dogrular


  @US006ForgotPassword1
  Scenario Outline: Sifremi unuttum butonuna tiklayarak gecerli telefon ya da mail bilgileriyle sifre yenileme islemi


    * Manager kullanicisi dogrulama yontemi olarak "<yontem>" secenegini secer
    * Manager kullanicisi ilgili alana "<iletisim_bilgisi>" bilgisini girer
    * Manager kullanicisi Get OTP butonuna tiklar
    * Manager kullanicisi "New Password" kutusuna yeni sifreyi yazar
    * Manager kullanicisi "Confirm Password" kutusuna yeni sifreyi tekrar yazar
    * Manager kullanicisi "Submit" butonuna basar
    * Manager kullanicisi "Success" yazisini goruntuler ve islemin basariyla gerceklestigini dogrular


    Examples:

      | yontem  | iletisim_bilgisi     |
      | E-posta | managerMail          |
      | Telefon | phoneNumber          |

  Scenario: Sifremi unuttum butonuna tiklayarak sifre yenileme sayfasinda "Get OTP" butonunu goruntuleme

    * Manager kullanicisi "Get OTP" butonunun gorunur ve aktif oldugunu dogrular


  Scenario: Sifremi unuttum butonuna tiklayarak sifre yenileme sayfasinda "Back to sign in" butonunu goruntuleme

    * Manager kullanicisi "Back to sign in" butonunun gorunur ve aktif oldugunu dogrular




