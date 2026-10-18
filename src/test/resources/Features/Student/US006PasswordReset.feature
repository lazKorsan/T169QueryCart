@US006PasswordResetSt
Feature: Kayıtlı bir kullanici olarak şifremi unuttugumda şifre değiştirme işlemi yapabilmek istiyorum

  @US006PasswordResetTC01St
  Scenario: SignIn sayfasında Forgot Password linki görünür ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Sign In" butona tiklar
    * Student kullanicisi "Forgot Password" butonun gorunur ve aktif oldugunu dogrular

  @US006PasswordResetTC02St
  Scenario: "Get OTP butonu" ve "Back to sign in" butonlari görünür ve aktif olmalıdır.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Sign In" butona tiklar
    * Student kullanicisi "Forgot Password" butona tiklar
    * Student kullanicisi "Get OTP" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Back to sign in" butonun gorunur ve aktif oldugunu dogrular

  @US006PasswordResetTC03St
  Scenario: Forgot password sayfasında gecerli e-mail ya da telefon numarası girilerek şifre resetleme işlemi yapılabilmelidir.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Sign In" butona tiklar
    * Student kullanicisi "Forgot Password" butona tiklar
    * Student kullanicisi "Phone" box kutusuna index 0 ile "5435143814" yazar
    * Student kullanicisi "Get OTP" butona tiklar
    * Student kullancisi "Reset Password" butonun gorunurlugu ile istenen sayfaya ulasildigini dogrular
    * Student kullanicisi "New Password" box kutusuna index 0 ile "Query.2027!" yazar
    * Student kullanicisi "Confirm Password" box kutusuna index 1 ile "Query.2027!" yazar
    * Student kullanicisi "Submit" butona tiklar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullancisi "Change Password" butonun gorunurlugu ile istenen sayfaya ulasildigini dogrular
    * Student kullanicisi "Change Password" butona tiklar
    * Student kullancisi "Change Password" butonun gorunurlugu ile istenen sayfaya ulasildigini dogrular
    * Student kullanicisi "Old Password" box kutusuna index 0 ile "Query.2026!" yazar
    * Student kullanicisi "New Password" box kutusuna index 1 ile "Query.2027!" yazar
    * Student kullanicisi "Confirm Password" box kutusuna index 2 ile "Query.2027!" yazar
    * Student kullanicisi "Save Changes" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi "The old password does not match." toaster mesajinin gorunurlugu ile sifre degisikligini dogrular