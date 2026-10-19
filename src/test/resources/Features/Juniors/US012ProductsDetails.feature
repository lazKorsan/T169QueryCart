@Junior
Feature: Bir kullanıcı olarak sayfada sectigim urun ile ilgili detayli bilgilere ulasabilmek istiyorum

  @US012JuniorProductsDetailsTC01
  Scenario: Anasayfadan secilen urun sayfasina gecilebilmeli
    * Junior kullanicisi uygulamayi acar
    * Junior kullanicisi Most Popular basliginin yanindaki "See All" butonuna tiklar
    * Junior kullanicisi acilan listeden "Flower Print Foil T-shirt" urunune tiklar

  @US012JuniorProductsDetailsTC02
  Scenario: Urun sayfasina gecildiginde Details,Videos,Review,Shipping&Return basliklari gorunur ve aktif olmali
    * Junior kullanicisi uygulamayi acar
    * Junior kullanicisi Most Popular basliginin yanindaki "See All" butonuna tiklar
    * Junior kullanicisi acilan listeden "Flower Print Foil T-shirt" urunune tiklar
    * Junior kullanicisi "Details" butonunun gorunur ve aktif oldugunu dogrular
    * Junior kullanicisi "Videos" butonunun gorunur ve aktif oldugunu dogrular
    * Junior kullanicisi "Review" butonunun gorunur ve aktif oldugunu dogrular
    * Junior kullanicisi "Shipping & Return" butonunun gorunur ve aktif oldugunu dogrular

  @US012JuniorProductsDetailsTC03
  Scenario: Details butonuna tiklandiginda Product Details basligi ve urune ait bilgiler goruntulenmeli
    * Junior kullanicisi uygulamayi acar
    * Junior kullanicisi Most Popular basliginin yanindaki "See All" butonuna tiklar
    * Junior kullanicisi acilan listeden "Flower Print Foil T-shirt" urunune tiklar
    * Junior kullanicisi "Product Details" bilgilerin gorunur oldugunu dogrular
    * Junior kullanicisi "Flower Print Foil T-shirt" bilgilerin gorunur oldugunu dogrular

  @US012JuniorProductsDetailsTC04
  Scenario: Videos butonuna tiklandiginda Product Videos basligi ve urune ait videolar goruntulenmeli
    * Junior kullanicisi uygulamayi acar
    * Junior kullanicisi Most Popular basliginin yanindaki "See All" butonuna tiklar
    * Junior kullanicisi acilan listeden "Flower Print Foil T-shirt" urunune tiklar
    * Junior kullanicisi "Videos" butonuna tiklar
    * Junior kullanicisi "Product Videos" bilgilerin gorunur oldugunu dogrular

  @US012JuniorProductsDetailsTC05
  Scenario: Review butonuna tiklandiginda Review basligi ve urune ait degerlendirmeler goruntulenmeli
    * Junior kullanicisi uygulamayi acar
    * Junior kullanicisi Most Popular basliginin yanindaki "See All" butonuna tiklar
    * Junior kullanicisi acilan listeden "Flower Print Foil T-shirt" urunune tiklar
    * Junior kullanicisi "Review" butonuna tiklar
    * Junior kullanicisi "Product Reviews" bilgilerin gorunur oldugunu dogrular
    * Junior kullanicisi "(0 Reviews)" bilgilerin gorunur oldugunu dogrular

  @US012JuniorProductsDetailsTC06
  Scenario: Shipping&Return butonuna tiklandiginda Shipping&Return basligi ve urune ait kargo ve iade kosullari goruntulenmeli
    * Junior kullanicisi uygulamayi acar
    * Junior kullanicisi Most Popular basliginin yanindaki "See All" butonuna tiklar
    * Junior kullanicisi acilan listeden "Flower Print Foil T-shirt" urunune tiklar
    * Junior kullanicisi "Shipping & Return" butonuna tiklar
    * Junior kullanicisi "Shipping & Return2" bilgilerin gorunur oldugunu dogrular

    @ignore @bug
  Scenario: Urun sayfasina gecildiginde Details, Videos, Review, Shipping&Return basliklari gorunur ve aktif olmali
    * "US012TC04 Videos butonuna tiklandiginda Product Videos basligi ve urune ait videolar goruntulenmeli"
    * "US012TC06 Shipping&Return butonuna tiklandiginda Shipping&Return basligi ve urune ait kargo ve iade kosullari goruntulenmeli"
