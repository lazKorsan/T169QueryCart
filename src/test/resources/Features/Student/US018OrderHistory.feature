@US018OrderHistory
Feature: Kayitli bir kullanici olarak Dashboard panelimde alisveris gecmisimi goruntuleyebilecegim bir sayfanin olmasini istiyorum

  @US018OrderHistoryTC01
  Scenario: Dashboard sideBar'da Order History menu basligi gorunur ve aktif olmali.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
    * ReusableMethods bekle 1
    * Student kullanicisi "Profile" butona tiklar
    * ReusableMethods bekle 1
    * Student kullanicisi "Order History" butonun gorunur ve aktif oldugunu dogrular
    * ReusableMethods bekle 1
    * Student kullanicisi "Order History" butona tiklar
    * ReusableMethods bekle 7
    * Student kullanicisi siparis detaylarini konsola yazdirir

  @US018OrderHistoryTC02
  Scenario: Order History sayfasinda alisveris gecmisi goruntuleme ikonu aktif olmali.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
    * ReusableMethods bekle 1
    * Student kullanicisi "Profile" butona tiklar
    * ReusableMethods bekle 1
    * Student kullanicisi "Order History" butonun gorunur ve aktif oldugunu dogrular
    * ReusableMethods bekle 1
    * Student kullanicisi "Order History" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi ilk pending durumundaki siparise tiklar
    * Student kullanicisi "Order Details" sayfasinda oldugunu dogrular
    * Student kullanicisi fatura bilgilerini konsola yazdirir

  @US018OrderHistoryTC03
  Scenario: Order History sayfasinda alisveris gecmisi goruntuleme ikonu aktif olmali.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
    * ReusableMethods bekle 1
    * Student kullanicisi "Profile" butona tiklar
    * ReusableMethods bekle 1
    * Student kullanicisi "Order History" butonun gorunur ve aktif oldugunu dogrular
    * ReusableMethods bekle 1
    * Student kullanicisi "Order History" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi ilk pending durumundaki siparise tiklar
    * Student kullanicisi "Order Details" sayfasinda oldugunu dogrular
    * Student kullanicisi "Download Receipt" butonunu bulur
    * Student kullanicisi "Download Receipt" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Download Receipt" butona tiklar
    * Student kullanicisi "Print Invoice" butonun gorunur ve aktif oldugunu dogrular

  @US018OrderHistoryTC04
  Scenario: Order History listesinden sipariş iptal edilebilmeli
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
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
    * Student kullanicisi "Cancel Order" butonunun gorulmemesi ile siparisin iptal edildigini dogrular



