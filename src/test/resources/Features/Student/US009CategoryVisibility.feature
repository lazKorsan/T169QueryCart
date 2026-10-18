@US009CategoryVisibilitySt
Feature: Bir kullanici olarak Kategoriler basligi altinda sitede bulunan tum kategorileri gorebilmek istiyorum.

  @US009CategoryVisibilityTC01St
  Scenario: Ana sayfa navbarinda  Category ikonu gorunur ve aktif olmali.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Category" butonun gorunur ve aktif oldugunu dogrular

  @US009CategoryVisibilityTC02St
  Scenario: Ana sayfa navbarinda  Category ikonu gorunur ve aktif olmali.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Category" butona tiklar
    * Student kullanicisi "Men" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Women" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Juniors" butonun gorunur ve aktif oldugunu dogrular

  @US009CategoryVisibilityA1_TC03St
  Scenario: Ana sayfa navbarinda  Category ikonu gorunur ve aktif olmali.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Category" butona tiklar
    * Student kullanicisi "Men" butona tiklar
    * Student kullancisi "Men" butonun gorunurlugu ile istenen sayfaya ulasildigini dogrular

  @US009CategoryVisibilityA2_TC03St
  Scenario: Ana sayfa navbarinda  Category ikonu gorunur ve aktif olmali.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Category" butona tiklar
    * Student kullanicisi "Women" butona tiklar
    * Student kullancisi "Women" butonun gorunurlugu ile istenen sayfaya ulasildigini dogrular

  @US009CategoryVisibilityA3_TC03St
  Scenario: Ana sayfa navbarinda  Category ikonu gorunur ve aktif olmali.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Category" butona tiklar
    * Student kullanicisi "Juniors" butona tiklar
    * Student kullancisi "Juniors" butonun gorunurlugu ile istenen sayfaya ulasildigini dogrular
