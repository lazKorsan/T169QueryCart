@US015WomanCategories
Feature: Kayıtlı bir  kullanici olarak ana sayfada Women menusune ait kategorileri ve urunleri gorebilmek istiyorum.

  @US015WomanCategoriesTC01
  Scenario: Ana sayfa body bolumunde Women kategori penceresi ve alt kategorileri  goruntulenmeli.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Category" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Women" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Women" sayfasinda oldugunu dogrular
    * ReusableMethods bekle 5
    * Student kullanicisi "Women" urunlerini listeler
    * ReusableMethods bekle 5
    * Student kullanicisi summary kısmındaki urun sayisini konsola yazdirir "({actualCount} Products Found)"

  @US015WomanCategoriesTC02
  Scenario: Ana sayfa body bolumunde Women kategori penceresi ve alt kategorileri  goruntulenmeli.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Category" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Women" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Women" sayfasinda oldugunu dogrular
    * ReusableMethods bekle 5
    * Student kullanicisi "Women" urunlerini listeler ve "({actualCount} Products Found)" yazi ile urun sayisini dogrular

  @US015WomanCategoriesTC03
  Scenario: Women kategori sayfasındaki urunler uzerinde islemler (sepete ekleme, begenilenler listesine ekleme ve goruntuleme) yapilabilmeli.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Category" butona tiklar
    * Student kullanicisi "Women" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Women" sayfasinda oldugunu dogrular
    * ReusableMethods bekle 5
    * Student kullanicisi "(18, Wine Red) Womens Short Sleeve Tie Dye Blosue T-Shirt" isimli urunu sepete ekler
    * ReusableMethods bekle 5
    * Student kullanicisi "Back" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Home" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Wishlist" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi Wishlist sayfasinda oldugunu dogrular
    * Student kullanicisi urun isminin "(18, Wine Red) Womens Short Sleeve Tie Dye Blosue T-Shirt\n0 (0  Reviews)\n$21.00" oldugunu dogrular

  @US015WomanCategoriesTC04
  Scenario: Filtreleme ikonları gorunur ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Category" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Women" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Filitre" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Sort By" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Brands" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "size" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "color" butonun gorunur ve aktif oldugunu dogrular


