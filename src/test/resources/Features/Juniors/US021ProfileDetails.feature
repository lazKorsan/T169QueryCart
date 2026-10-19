@Junior
Feature: Kayitli bir kullanici olarak sitede kendi profil bilgilerimi duzenleyebilmek istiyorum

  @US021JuniorProfileDetailsTC01
  Scenario: Dashboard sayfasinda Edit Profile linki gorunur ve aktif olmali
    * Junior kullanicisi uygulamayi acar
    * Junior kullanicisi "Profile" butonuna tiklar
    * Junior kullanicisi "Sign In" butonuna tiklar
    * Junior kullanicisi "Use Email Instead" butonuna tiklar
    * Junior kullanicisi "Email" box kutusuna index 0 ile "aybenizermis.user@querycart.com" yazar
    * Junior kullanicisi "Password" box kutusuna index 1 ile "Learn.123" yazar
    * Junior kullanicisi "Sign In2" butonuna tiklar
    * Junior kullanicisi "Profile" butonuna tiklar
    * Junior kullanicisi "Edit Profile" butonunun gorunur ve aktif oldugunu dogrular

  @US021JuniorProfileDetailsTC02
  Scenario: Tiklandiginda edit profile sayfasinda Full Name,Email,Phone bilgileri duzenlenebilmeli
  enario: Dashboard sayfasinda Edit Profile linki gorunur ve aktif olmali
    * Junior kullanicisi uygulamayi acar
    * Junior kullanicisi "Profile" butonuna tiklar
    * Junior kullanicisi "Sign In" butonuna tiklar
    * Junior kullanicisi "Use Email Instead" butonuna tiklar
    * Junior kullanicisi "Email" box kutusuna index 0 ile "aybenizermis.user@querycart.com" yazar
    * Junior kullanicisi "Password" box kutusuna index 1 ile "Learn.123" yazar
    * Junior kullanicisi "Sign In2" butonuna tiklar
    * Junior kullanicisi "Profile" butonuna tiklar
    * Junior kullanicisi "Edit Profile" butonuna tiklar
    * Junior kullanicisi "Full Name" box kutusuna tiklar
    * Junior kullanicisi "Full Name" box kutusuna "Aybeniz" yazar
    * Junior kullanicisi "Email" box kutusuna tiklar
    * Junior kullanicisi "Email" box kutusuna "aybeniz.user@querycart.com" yazar
    * Junior kullanicisi "Phone" box kutusuna tiklar
    * Junior kullanicisi "Phone" box kutusuna "0909090" yazar


  @US021JuniorProfileDetailsTC03
  Scenario: Save Changes butonu gorunur ve aktif olmali
    * Junior kullanicisi uygulamayi acar
    * Junior kullanicisi "Profile" butonuna tiklar
    * Junior kullanicisi "Sign In" butonuna tiklar
    * Junior kullanicisi "Use Email Instead" butonuna tiklar
    * Junior kullanicisi "Email" box kutusuna index 0 ile "aybenizermis.user@querycart.com" yazar
    * Junior kullanicisi "Password" box kutusuna index 1 ile "Learn.123" yazar
    * Junior kullanicisi "Sign In2" butonuna tiklar
    * Junior kullanicisi "Profile" butonuna tiklar
    * Junior kullanicisi "Edit Profile" butonuna tiklar
    * Junior kullanicisi "Phone" box kutusuna tiklar
    * Junior kullanicisi "Phone" box kutusuna "0909095" yazar
    * Junior kullanicisi "Save Changes" butonunun gorunur ve aktif oldugunu dogrular


  @US021JuniorProfileDetailsTC04
  Scenario: Yapilan degisiklikler basariyla kaydedilmeli
    * Junior kullanicisi uygulamayi acar
    * Junior kullanicisi "Profile" butonuna tiklar
    * Junior kullanicisi "Sign In" butonuna tiklar
    * Junior kullanicisi "Use Email Instead" butonuna tiklar
    * Junior kullanicisi "Email" box kutusuna index 0 ile "aybenizermis.user@querycart.com" yazar
    * Junior kullanicisi "Password" box kutusuna index 1 ile "Learn.123" yazar
    * Junior kullanicisi "Sign In2" butonuna tiklar
    * Junior kullanicisi "Profile" butonuna tiklar
    * Junior kullanicisi "Edit Profile" butonuna tiklar
    * Junior kullanicisi "Phone" box kutusuna tiklar
    * Junior kullanicisi "Phone" box kutusuna "0909093" yazar
    * Junior kullanicisi "Save Changes" butonuna tiklar
    * Junior kullanicisi "Profile Updated Successfully." yazisinin goruntulendigini dogrular
