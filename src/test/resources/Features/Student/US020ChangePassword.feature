@US020ChangePassword
Feature: Kayıtlı bir kullanıcı olarak dashboard sayfamda sifre degistirme islemlerimi gerceklestirmek istiyorum

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




