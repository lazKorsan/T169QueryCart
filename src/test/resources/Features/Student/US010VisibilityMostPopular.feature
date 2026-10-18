@US010VisibilityMostPopularSt
Feature: Bir kullanici olarak alisveris yaptigim sitede guncel urunleri takip edebilecegim bir sayfanin olmasini istiyorum.

  @US010VisibilityMostPopularTC01St
  Scenario: Ana sayfa navBar da Most Popular menu basligi ve "see All" iconu gorunur ve aktif olmali
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Most Popular" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "See All" butonun gorunur ve aktif oldugunu dogrular

  @US010VisibilityMostPopularTC02St
  Scenario: Most Popular baslıgı altında guncel urunler listenmeli
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "See All" butona tiklar
    * Student kullancisi "Most Popular" butonun gorunurlugu ile istenen sayfaya ulasildigini dogrular
    * Student kullanicisi Most Popular sayfasinda urunleri listeler
    * Student kullanicisi "Back" butona tiklar
    * Student kullancisi "Profile" butonun gorunurlugu ile "Back" butonun fonksiyonlarini dogrular
