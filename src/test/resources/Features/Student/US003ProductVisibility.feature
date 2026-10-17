@US002ProductInspect
Feature: Bir kullanici olarak sitede kayıt olmadan urunleri inceleyip urun secebilmek ve sepete ekleyebilmek istiyorum

  @US002ProductInspectTC01
  Scenario: Anasayfada urunler ve urun özellikleri gorunur olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Most Popular" basligi altindaki urunlerin gorunur oldugunu dogrular

  @US002ProductInspectTC02
  Scenario: Seçilen ürüne ait beden/renk ve adet özellikleri seçilebilir olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Flower Print" butona tiklar
    * Student kullanicisi "S" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "M" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "L" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "S" butona tiklar
    * Student kullanicisi "Add To Cart" butonunu bulur
    * Student kullanicisi "Add To Cart" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Plus" butona tiklar
    * Student kullanicisi ilk siparis vermede siparis edilen urun sayisinin arttigini dogrular
    * Student kullanicisi "Minus" butona tiklar

  @US002ProductInspectTC03
  Scenario: Seçilen ürüne ait beden/renk ve adet özellikleri seçilebilir olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Flower Print" butona tiklar
    * Student kullanicisi "S" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "M" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "L" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "S" butona tiklar
    * Student kullanicisi "Add To Cart" butonunu bulur
    * Student kullanicisi "Add To Cart" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Plus" butona tiklar
    * Student kullanicisi "Minus" butona tiklar ve siparis edilen urun sayisinin azaldigini dogrular

