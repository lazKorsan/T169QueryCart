@US002VerifyBottomBar
Feature: Bir kullanici olarak site ici islemlerini kolaylikla yapabilmek icin ana sayfa altbarında kolay linklerin bulunmasını istiyorum

  @US002VerifyBottomBarTC01
  Scenario: Site alt barinda kolay linkler (Home,Category,Wishlist, Profile) goruntulenmeli.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Home" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Category" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Wishlist" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Profile" butonun gorunur ve aktif oldugunu dogrular

  @US002VerifyBottomBarTC02
  Scenario:  Category linki ilgili sayfaya yonlendirme yapmalidir.
    * Student kullanicisi uygulamayi acar
    * Register kullanicisi "Profile" butona tiklar
    * Register kullanicisi "ahmet.customer.141248@QueryCart.com" mail adresi ve "Query.2026!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Category" butona tiklar
    * Student kullancisi "Men" butonun gorunurlugu ile istenen sayfaya ulasildigini dogrular

  @US002VerifyBottomBarTC03
  Scenario:  Home linki ilgili sayfaya yonlendirme yapmalidir.
    * Student kullanicisi uygulamayi acar
    * Register kullanicisi "Profile" butona tiklar
    * Register kullanicisi "ahmet.customer.141248@QueryCart.com" mail adresi ve "Query.2026!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Wishlist" butona tiklar
    * Student kullanicisi "Wishlist Text" elementinin gorunurlugu ile istenen sayfa ulasildigini dogrular

  @US002VerifyBottomBarTC04A1
  Scenario:  Profile linki ilgili sayfaya yonlendirme yapmalidir.
    * Student kullanicisi uygulamayi acar
    * Register kullanicisi "Profile" butona tiklar
    * Register kullanicisi "ahmet.customer.141248@QueryCart.com" mail adresi ve "Query.2026!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullancisi "My Account" butonun gorunurlugu ile istenen sayfaya ulasildigini dogrular

  @US002VerifyBottomBarTC04A2
  Scenario: preCondition dynamic giris
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Profile" butona tiklar
    * Register kullanicisi "Sign Up" butona tiklar
    * Student kullanicisi "ahmet" , "4335{timeStamp}" telefon numarasi ve "Query.2026!" degerleri ile kayt olur
    * Student kullanicisi yeni bilgilerle siteye giris yapar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullancisi "My Account" butonun gorunurlugu ile istenen sayfaya ulasildigini dogrular

  @DynamicTestBaslangici
  Scenario: preCondition dynamic giris
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Profile" butona tiklar
    * Register kullanicisi "Sign Up" butona tiklar
    * Student kullanicisi "ahmet" , "4335{timeStamp}" telefon numarasi ve "Query.2026!" degerleri ile kayt olur
    * Student kullanicisi yeni bilgilerle siteye giris yapar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullancisi "My Account" butonun gorunurlugu ile istenen sayfaya ulasildigini dogrular


  @US002SeacrhBox
  Scenario: Search Text Box goruntulenmeli ve arama yapilabilmeli.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Search Box" butona tiklar
    * Student kullancisi "Search" butonun gorunurlugu ile istenen sayfaya ulasildigini dogrular
    * Student kullanicisi "Search" box kutusuna index 0 ile "Junior" yazar ve arama yapar
    * Student kullanicisi arama sonucu cikan urun sayisini konsola yazdirir



