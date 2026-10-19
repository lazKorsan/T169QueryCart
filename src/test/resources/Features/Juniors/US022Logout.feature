@Junior
Feature: Kayitli bir kullanici olarak sistemden guvenli bir sekilde cikis yapabilmek istiyorum

  @US022JuniorLogoutTC01
  Scenario: Anasayfada profil ikonu gorunur olmali
    * Junior kullanicisi uygulamayi acar
    * Junior kullanicisi "Profile" butonunun gorunur ve aktif oldugunu dogrular

  @US022JuniorLogoutTC02
  Scenario: Profil ikonuna tiklandiginda dashboard sidebar da Logout linki gorunur ve aktif olmali
    * Junior kullanicisi uygulamayi acar
    * Junior kullanicisi "Profile" butonuna tiklar
    * Junior kullanicisi "Sign In" butonuna tiklar
    * Junior kullanicisi "Use Email Instead" butonuna tiklar
    * Junior kullanicisi "Email" box kutusuna index 0 ile "aybenizermis.user@querycart.com" yazar
    * Junior kullanicisi "Password" box kutusuna index 1 ile "Learn.123" yazar
    * Junior kullanicisi "Sign In2" butonuna tiklar
    * Junior kullanicisi "Profile" butonuna tiklar
    * Junior kullanicisi "Logout" butonunun gorunur ve aktif oldugunu dogrular

  @US022JuniorLogoutTC03
  Scenario: Tiklandiginda basarili bir sekilde siteden cikis yapilabilmeli
    * Junior kullanicisi uygulamayi acar
    * Junior kullanicisi "Profile" butonuna tiklar
    * Junior kullanicisi "Sign In" butonuna tiklar
    * Junior kullanicisi "Use Email Instead" butonuna tiklar
    * Junior kullanicisi "Email" box kutusuna index 0 ile "aybenizermis.user@querycart.com" yazar
    * Junior kullanicisi "Password" box kutusuna index 1 ile "Learn.123" yazar
    * Junior kullanicisi "Sign In2" butonuna tiklar
    * Junior kullanicisi "Profile" butonuna tiklar
    * Junior kullanicisi "Logout" butonuna tiklar
    * Junior kullanicisi "Logged Out Successfully." yazisinin goruntulendigini dogrular
