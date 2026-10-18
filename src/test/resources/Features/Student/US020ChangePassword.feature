@US020ChangePassword
Feature: Kayıtlı bir kullanıcı olarak dashboard sayfamda sifre degistirme islemlerimi gerceklestirmek istiyorum

  @ChangePasswordDynamic
  Scenario: preCondition dynamic giris
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Profile" butona tiklar
    * Register kullanicisi "Sign Up" butona tiklar
    * Student kullanicisi "ahmet" , "4335{timeStamp}" telefon numarasi ve "Query.2026!" degerleri ile kayt olur
    * Student kullanicisi "Success Register Successfully" toaster mesajinin gorunurlugunu dogrular
    * Student kullanicisi yeni bilgilerle siteye giris yapar
    * Student kullanicisi "Success Login Successfully" toaster mesajinin gorunurlugunu dogrular
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Change Password" butona tiklar
    * Student kullanicisi "Old Password" box kutusuna index 0 ile "Query.2026!" yazar
    * Student kullanicisi "New Password" box kutusuna index 1 ile "Query.2027!" yazar
    * Student kullanicisi "Confirm Password" box kutusuna index 2 ile "Query.2027!" yazar
    * Student kullanicisi "Save Changes" butona tiklar
    * Student kullanicisi "Success Change Password" toaster mesaji ile sifre degisikligini dogrular

  @US020ChangePasswordTC01
  Scenario: Dashboard sayfasında Change Password linki gorunur ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Change Password" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Change Password" butona tiklar
    * Student kullancisi "Change Password" butonun gorunurlugu ile istenen sayfaya ulasildigini dogrular
    * Student kullanicisi "Old Password" yazi alaninin gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "New Password" yazi alaninin gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Confirm Password" yazi alaninin gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Save Changes" butonun gorunur ve aktif oldugunu dogrular

  @US020ChangePasswordTC02
  Scenario: Dashboard sayfasında Change Password linki gorunur ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2026!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Change Password" butona tiklar
    * Student kullanicisi "Old Password" box kutusuna index 0 ile "Query.2026!" yazar
    * Student kullanicisi "New Password" box kutusuna index 1 ile "Query.2027!" yazar
    * Student kullanicisi "Confirm Password" box kutusuna index 2 ile "Query.2027!" yazar
    * Student kullanicisi "Save Changes" butona tiklar
    * Student kullanicisi "Success Change Password" toaster mesaji ile sifre degisikligini dogrular

  @US020ChangePasswordTC03
  Scenario: preCondition dynamic giris
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Profile" butona tiklar
    * Register kullanicisi "Sign Up" butona tiklar
    * Student kullanicisi "ahmet" , "4335{timeStamp}" telefon numarasi ve "Query.2026!" degerleri ile kayt olur
    * Student kullanicisi "Success Register Successfully" toaster mesajinin gorunurlugunu dogrular
    * Student kullanicisi yeni bilgilerle siteye giris yapar
    * Student kullanicisi "Success Login Successfully" toaster mesajinin gorunurlugunu dogrular







