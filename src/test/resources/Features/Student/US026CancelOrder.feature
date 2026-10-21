@CancelOrderSt
Feature: Siparis edilen bir urunun iptal edilmesi
  @CancelOrderStTC01
  Scenario: Kayitli bir kullanici olarak urun siparisi iptal edebilmeliyim
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "3335142910" telefon numarasi ve "Query.2026!" sifresi ile uyuglamaya giris yapar
    * ReusableMethods bekle 1
    * Student kullanicisi "Profile" butona tiklar
    * ReusableMethods bekle 1
    * Student kullanicisi "Order History" butonun gorunur ve aktif oldugunu dogrular
    * ReusableMethods bekle 1
    * Student kullanicisi "Order History" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi ilk pending durumundaki siparise tiklar
    * Student kullanicisi "Order Details" sayfasinda oldugunu dogrular
    * Student kullanicisi "Cancel Order" butonunu bulur
    * Student kullanicisi "Cancel Order" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi "Cancel Order" butonunun gorulmemesi ile siparisin iptal edildigini dogrular