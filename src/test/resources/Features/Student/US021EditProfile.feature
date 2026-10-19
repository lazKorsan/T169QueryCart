@US021EditProfileSt
Feature: Kayıtlı bir kullanıcı olarak sitede kendi profil bilgilerimi duzenleyebilmek istiyorum

  @EditProfileDynamicSt
  Scenario: preCondiiton editProfile dynamic
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Profile" butona tiklar
    * Register kullanicisi "Sign Up" butona tiklar
    * Student kullanicisi "ahmet" , "3335{timeStamp}" telefon numarasi ve "Query.2026!" degerleri ile kayt olur
    * Student kullanicisi "Success Register Successfully" toaster mesajinin gorunurlugunu dogrular
    * Student kullanicisi yeni bilgilerle siteye giris yapar
    * Student kullanicisi "Success Login Successfully" toaster mesajinin gorunurlugunu dogrular
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Edit Profile" butona tiklar
    * Student kullanicisi "Full Name" box kutusuna index 0 ile "melek" yazar
    * Student kullanicisi "Email" box kutusuna index 1 ile "melek.customer{timeStamp}@QueryCart.com" mail adresini degistirir
    * Student kullanicisi "Phone" box kutusuna index 2 ile "5435{timeStamp}" telefon numarasini degistirir
    * Student kullanicisi "Save Changes" butona tiklar
    * Student kullanicisi "Profile Updated Successfully" toaster mesajinin gorunurlugunu dogrular
    * Student kullanicisi "melek" ismi ile profil degisikligini dogrular

  @US021EditProfileTC01St
  Scenario: Dashboard sayfasında Edit Profile linki gorunur ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "5335150049" telefon numarasi ve "Query.2026!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Edit Profile" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Edit Profile" butona tiklar
    * Student kullancisi "Edit Profile" butonun gorunurlugu ile istenen sayfaya ulasildigini dogrular
    * Student kullanicisi "Full Name" yazi alaninin gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Email" yazi alaninin gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Phone" yazi alaninin gorunur ve aktif oldugunu dogrular

  @US021EditProfileTC02St
  Scenario: Dashboard sayfasında Edit Profile linki gorunur ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "5335150049" telefon numarasi ve "Query.2026!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Edit Profile" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Edit Profile" butona tiklar
    * Student kullanicisi "Full Name" box kutusuna index 0 ile "melek" yazar
    * Student kullanicisi "Email" box kutusuna index 1 ile "melek.customer@QueryCart.com" yazar
    * Student kullanicisi "Phone" box kutusuna index 2 ile "05435412927" yazar
    * Student kullanicisi "Save Changes" butona tiklar
    * Student kullanicisi "Profile Updated Successfully" toaster mesajinin gorunurlugunu dogrular

  @preCondiitonEditProfileDynamicSt
  Scenario: preCondiiton editProfile dynamic
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Profile" butona tiklar
    * Register kullanicisi "Sign Up" butona tiklar
    * Student kullanicisi "ahmet" , "3335{timeStamp}" telefon numarasi ve "Query.2026!" degerleri ile kayt olur
    * Student kullanicisi "Success Register Successfully" toaster mesajinin gorunurlugunu dogrular
    * Student kullanicisi yeni bilgilerle siteye giris yapar
    * Student kullanicisi "Success Login Successfully" toaster mesajinin gorunurlugunu dogrular
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Edit Profile" butona tiklar
    * Student kullanicisi "Full Name" box kutusuna index 0 ile "melek" yazar
    * Student kullanicisi "Email" box kutusuna index 1 ile "melek.customer{timeStamp}@QueryCart.com" mail adresini degistirir
    * Student kullanicisi "Phone" box kutusuna index 2 ile "5435{timeStamp}" telefon numarasini degistirir
    * Student kullanicisi "Save Changes" butona tiklar
    * Student kullanicisi "Profile Updated Successfully" toaster mesajinin gorunurlugunu dogrular
    * Student kullanicisi "melek" ismi ile profil degisikligini dogrular



