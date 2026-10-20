@US025ReturnRequest
Feature: Kayıtlı bir kullanici olarak iade etmek istedigim siparisler ile ilgili bir sayfa olmasını ve urun iade islemlerimi yonetebilmeyi istiyorum

  @US025ReturnRequestStTC01
  Scenario: preCondtion Return Request
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "3335134808" telefon numarasi ve "Query.2026!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Profile" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi "Order History" butona tiklar
    * ReusableMethods bekle 1
    * Student kullanicisi "Back" butona tiklar
    * ReusableMethods bekle 1
    * Student kullanicisi "Order History" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi status "Delivered" olan urune tiklar
    * ReusableMethods bekle 1
    * Student kullanicisi "Back" butona tiklar
    * Student kullanicisi status "Delivered" olan urune tiklar
    * Student kullanicisi "Order Details" sayfasinda oldugunu dogrular
    * Student kullanicisi "Return Request" butonunu bulur
    * Student kullanicisi "Return Request" butona tiklar
    * Student kullanicisi "checkbox" isaretler
    * Student kullanicisi "Return Reason" butona tiklar
    * Student kullanicisi "Sizing or fit issues" butona tiklar
    * Student kullanicisi "Return Note" box kutusuna index 0 ile "Beden uyusmazligi" yazar
    * Student kullanicisi klavyeyi guvenle kapatır
    * Student kullanicisi "Request Return" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi "Return Orders" sayfasinda oldugunu dogrular
    * Student kullanicisi urunun durumunun "Pending" oldugunu dogrular

