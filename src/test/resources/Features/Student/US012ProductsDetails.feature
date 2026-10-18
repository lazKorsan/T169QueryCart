@US012ProductsDetailsSt
Feature: Bir kullanıcı olarak sayfada sectigim urun ile ilgili detaylı bilgilere ulasabilmek istiyorum

  @US012ProductsDetailsTC01St
  Scenario: Anasayfadan secilen urun sayfasına gecilebilmeli
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Flower Print Foil T" butona tiklar

  @US012ProductsDetailsTC02St
  Scenario: Urun sayfasına gecildiginde Details,Videos,Review,Shipping&Return baslıkları görünür ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Flower Print Foil T" butona tiklar
    * Student kullanicisi "Details" butonunu bulur
    * Student kullanicisi "Details" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Videos" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Review" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Shipping & Return" butonun gorunur ve aktif oldugunu dogrular

  @US012ProductsDetailsTC03St
  Scenario: Urun sayfasına gecildiginde Details,Videos,Review,Shipping&Return baslıkları görünür ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Flower Print Foil T" butona tiklar
    * Student kullanicisi "Details" butonunu bulur
    * Student kullanicisi urun detayinin "Flower Print Foil T-shirt" oldugunu dogrular

  @US012ProductsDetailsTC04St
  Scenario: Urun sayfasına gecildiginde Details,Videos,Review,Shipping&Return baslıkları görünür ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Flower Print Foil T" butona tiklar
    * Student kullanicisi "Review" butonunu bulur
    * Student kullanicisi "Review" butona tiklar
    * Student kullanicisi Review bilgisinin "(0  Reviews)" oldugunu dogrular

  @ignore @bug
  Scenario: Urun sayfasına gecildiginde Details,Videos,Review,Shipping&Return baslıkları görünür ve aktif olmalı
    * "US012TC04 Videos butonuna tıklandıgında Product Videos baslıgı ve urune ait videolar goruntulenmeli"
    * "US012TC06 Shipping&Return butonuna tıklandıgında Shipping&Return baslıgı ve urune ait kargo ve iade kosulları goruntulenmeli"
    * "US013TC01 Ana sayfa body bolumunde Men kategori penceresi ve alt kategorileri gorunur ve aktif olmalı"
    * "US013TC03 Filitreleme fonksiyonlari tam olarak calismiyor"
    * "US016TC03 My Account sayfasinda kullanicinin siparis gecmisi goruntulenmeli."
    * "relatesTo US016TC03Sayfa yukleme hatasi. Geç yuklenme yada yuklenmeme"




