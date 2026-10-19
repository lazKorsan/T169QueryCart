@US024PaymentSt
Feature: Kayitli bir kullanici olarak kontrollerini sagladigim siparislerimin odemesini gerceklestirebilecegim bir odeme sayfasi olmasini istiyorum.


  @US024PaymentStpreCondition
  Scenario: Add New Address butonu gorunur ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Profile" butona tiklar
    * Register kullanicisi "Sign Up" butona tiklar
    * Student kullanicisi "ahmet" , "3335{timeStamp}" telefon numarasi ve "Query.2026!" degerleri ile kayt olur
    * ReusableMethods bekle 3
    * Student kullanicisi yeni bilgilerle siteye giris yapar
    * ReusableMethods bekle 3
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Address" butona tiklar
    * Student kullanicisi "Back" butona tiklar
    * ReusableMethods bekle 2
    * Student kullanicisi "Address" butona tiklar
    * Student kullanicisi "Add New Address" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi "Add New Address" formunu "Ahmet" ismi ile doldurur
    * ReusableMethods bekle 3
    * Student kullanicisi "Back" butona tiklar
    * ReusableMethods bekle 1
    * Student kullanicisi "Home" butona tiklar
    * Student kullanicisi "Men Shorts" butona tiklar
    * Student kullanicisi "Back" butona tiklar
    * Student kullanicisi "Men Shorts" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi "Men Shorts" sayfasinda oldugunu dogrular
    * Student kullanicisi adi "Classic Cargo Shorts" olan urunu secer
    * Student kullanicisi "Back" butona tiklar
    * Student kullanicisi adi "Classic Cargo Shorts" olan urunu secer
    * Student kullanicisi "M" beden secer
    * Student kullanicisi  ekrani asagi 710, 2400 noktasindan yukari 710, 1700 noktsina kaydirir
    * Student kullanicisi "Details" butonunu bulur
    * Student kullanicisi "Add To Cart" butona tiklar
    * Student kullanicisi "Alis Veris" ikonuna tiklar
    * Student kullanicisi "Proceed to Checkout" butona tiklar
    * Student kullanicisi address sinifindan kayitli ilk adresi secer
    * Student kullanicisi "Save & Pay" butonunu bulur
    * Student kullanicisi "Save & Pay" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi "Stripe" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi "Confirm Order" butona tiklar
    * ReusableMethods bekle 7
    * Student kullanicisi "4242424242424242" ve "0234" ve "321" ve "12345" cart bilgileri ile odemeyi yapar
    * Student kullanicisi "Success Your payment has been confirmed" toaster mesajinin gorunurlugunu dogrular











