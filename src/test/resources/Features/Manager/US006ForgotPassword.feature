@US006
Feature: Kayıtlı bir kullanici olarak şifremi unuttugumda şifre değiştirme işlemi yapabilmek istiyorum


  @US006_TC01_ForgotPassword
  Scenario: Sisteme giris yaparken sifremi unuttum butonunun gorunur ve aktif olmasi

    * Manager kullanicisi uygulamayi acar
    * Manager kullanicisi "Profile" butonuna tiklar
    * Manager kullanicisi "Sign In" butonuna tiklar
    * Manager kullanicisi "Forgot Password" butonuna tiklar
    * Manager kullanicisi Forgot Password sayfasini goruntulendigini dogrular


    @US006_TC02_GET_OTPButonKontrol
  Scenario: Sifremi unuttum butonuna tiklayarak sifre yenileme sayfasinda "Get OTP" butonunu goruntuleme

    * Manager kullanicisi uygulamayi acar
    * Manager kullanicisi "Profile" butonuna tiklar
    * Manager kullanicisi "Sign In" butonuna tiklar
    * Manager kullanicisi "Forgot Password" butonuna tiklar
    * Manager kullanicisi "Get OTP" butonunun gorunur ve aktif oldugunu dogrular


    @US006_TC03_BackToSignInKontol
  Scenario: Sifremi unuttum butonuna tiklayarak sifre yenileme sayfasinda "Back to sign in" butonunu goruntuleme

     * Manager kullanicisi uygulamayi acar
     * Manager kullanicisi "Profile" butonuna tiklar
     * Manager kullanicisi "Sign In" butonuna tiklar
     * Manager kullanicisi "Forgot Password" butonuna tiklar
     * Manager kullanicisi "Back to sign in" butonunun gorunur ve aktif oldugunu dogrular

  @US006_TC04_ResetPassword
  Scenario Outline: Sifremi unuttum butonuna tiklayarak gecerli telefon ya da mail bilgileriyle sifre yenileme islemi

    * Manager kullanicisi uygulamayi acar
    * Manager kullanicisi "Profile" butonuna tiklar
    * Manager kullanicisi "Sign In" butonuna tiklar
    * Manager kullanicisi "Forgot Password" butonuna tiklar
    * Manager kullanicisi dogrulama yontemi olarak "<yontem>" secenegini secer
    * Manager kullanicisi ilgili alana "<iletisim_bilgisi>" bilgisini girer
    * Manager kullanicisi Get OTP butonuna tiklar
    * Manager kullanicisi "Success" yazisini goruntuler ve islemin basariyla gerceklestigini dogrular
    * Manager kullanicisi new password kutusuna yeni sifreyi yazar
    * Manager kullanicisi confirm password kutusuna yeni sifreyi tekrar yazar
    * Manager kullanicisi "Submit" butonuna tiklar
    * Manager kullanicisi "Success" yazisini goruntuler ve islemin basariyla gerceklestigini dogrular


    Examples:

      | yontem  | iletisim_bilgisi     |
      | E-posta | managerMail          |
      | Telefon | phoneNumber          |





