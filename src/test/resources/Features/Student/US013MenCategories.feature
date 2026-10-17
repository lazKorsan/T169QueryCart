@US013ManCategorirs
Feature: Kayıtlı bir kullanici olarak ana sayfada Men menusune ait kategorileri ve urunleri gorebilmek istiyorum.

  @US013ManCategorirsTC01
  Scenario: preCondition Men kategori sayfasındaki urunleri listeler
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Category" butona tiklar
    * Student kullanicisi "Men" butona tiklar
    * Student kullanicisi "Men" sayfasinda oldugunu dogrular
    * Student kullanicisi "Men" urunlerini listeler


  @US013ManCategorirsTC02
  Scenario: preCondition Men kategori sayfasında summaryCount alanındaki urun sayisini konsola yazdirir
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Category" butona tiklar
    * Student kullanicisi "Men" butona tiklar
    * Student kullanicisi "Men" sayfasinda oldugunu dogrular
    * Student kullanicisi summary kısmındaki urun sayisini konsola yazdirir "({actualCount} Products Found)"


  @US013ManCategorirsTC03
  Scenario: Men kategori sayfasında urun sayisini dogrular
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Category" butona tiklar
    * Student kullanicisi "Men" butona tiklar
    * Student kullanicisi "Men" sayfasinda oldugunu dogrular
    * Student kullanicisi "Men" urunlerini listeler ve "({actualCount} Products Found)" yazi ile urun sayisini dogrular

  @US013ManCategorirsTC04
  Scenario: preCondition navigateToHomePage
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Category" butona tiklar
    * Student kullanicisi "Men" butona tiklar
    * Student kullanicisi "Men" sayfasinda oldugunu dogrular
    * Student kullanicisi anaSayfa ya doner


  @US013ManCategorirsTC05
  Scenario: Men kategori sayfasındaki urunler uzerinde islemler (sepete ekleme, begenilenler listesine ekleme ve goruntuleme) yapilabilmeli.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Category" butona tiklar
    * Student kullanicisi "Men" butona tiklar
    * Student kullanicisi "Men" sayfasinda oldugunu dogrular
    * Student kullanicisi "Adidas 3-Stripes Cushioned Crew Socks" isimli urunu sepete ekler
    * ReusableMethods bekle 5
    * Student kullanicisi "Back" butona tiklar
    * Student kullanicisi "Home" butona tiklar
    * Student kullanicisi "Wishlist" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi Wishlist sayfasinda oldugunu dogrular
    * Student kullanicisi urun isminin "Adidas 3-Stripes Cushioned Crew Socks\n0 (0  Reviews)\n$15.00" oldugunu dogrular

  @US013ManCategorirsTC06 @platform16
  Scenario: Filtreleme ikonları gorunur ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Category" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Men" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Filitre" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Sort By" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Brands" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "size" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "color" butonun gorunur ve aktif oldugunu dogrular

  @US013ManCategorirsTC07 @platform16
  Scenario: Bir kullanici olarak urunleri dusuk fiyattan yuksek fiyata dogru sıralayabilmeliyim
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Category" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Men" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Filitre" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Sort By" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Price Low To High" butona tiklar
    * ReusableMethods bekle 7
    * Student kullanicisi "Men" urunlerinin dusuk fiyattan yuksek fiyata siralandigini dogrular






