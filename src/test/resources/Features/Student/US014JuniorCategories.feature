@US014JuniorCategoriesSt @platform16
Feature: Kayıtlı bir kullanici olarak ana sayfada Juniors menusune ait kategorileri ve urunleri gorebilmek istiyorum.

  @US014JuniorCategoriesTC01St
  Scenario: Ana sayfa body bolumunde Juniors kategori penceresi ve alt kategorileri goruntulenmeli.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Category" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Juniors" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Juniors" sayfasinda oldugunu dogrular
    * Student kullanicisi "Juniors" urunlerini listeler
    * Student kullanicisi summary kısmındaki urun sayisini konsola yazdirir "({actualCount} Products Found)"


  @US014JuniorCategoriesTC02St
  Scenario: Ana sayfa body bolumunde Juniors kategori penceresi ve alt kategorileri goruntulenmeli.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Category" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Juniors" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Juniors" sayfasinda oldugunu dogrular
    * ReusableMethods bekle 5
    * Student kullanicisi "Juniors" urunlerini listeler ve "({actualCount} Products Found)" yazi ile urun sayisini dogrular

  @US014JuniorCategoriesTC03St
  Scenario: Juniors kategori sayfasındaki urunler uzerinde islemler (sepete ekleme, begenilenler listesine ekleme ve goruntuleme) yapilabilmeli.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Category" butona tiklar
    * Student kullanicisi "Juniors" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Juniors" sayfasinda oldugunu dogrular
    * ReusableMethods bekle 5
    * Student kullanicisi "(5-6 Years) Disney Mickey Mouse Pyjamas Boys T-shirt Shorts" isimli urunu sepete ekler
    * ReusableMethods bekle 5
    * Student kullanicisi "Back" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Home" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Wishlist" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi Wishlist sayfasinda oldugunu dogrular
    * Student kullanicisi urun isminin "(5-6 Years) Disney Mickey Mouse Pyjamas Boys T-shirt Shorts Set\n0 (0  Reviews)\n$15.00" oldugunu dogrular

  @US014JuniorCategoriesTC04St
  Scenario: Filtreleme ikonları gorunur ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Category" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Juniors" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Filitre" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Sort By" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Brands" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "size" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "color" butonun gorunur ve aktif oldugunu dogrular
