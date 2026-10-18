@US016MyAccountSt @platform16
Feature: Kayitli bir kullanici olarak sitedeki islemlerimi ve ayarlarimi kontrol etmek icin sitede
  bana özel bir dashboard sayfasinin olmasini istiyorum.

  @US016MyAccountTC01St
  Scenario: Dashboard sayfasinda kullanici icin My Account sayfasında  ozet bilgiler
  (Total Orders,Total Completed,Total Returnd,Wallet Balance) gorunur olmali.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "My Account" butona tiklar
    * ReusableMethods bekle 5
    * Student kullanicisi "Back" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi "My Account" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi My Account sayfasinin "ahmet" kullanicisina ait oldugunu dogrular
    * ReusableMethods bekle 5
    * Student kullanicisi "Total Orders" alaninin gorunurlugunu dogrular
    * Student kullanicisi "Total Completed" alaninin gorunurlugunu dogrular
    * Student kullanicisi "Total Returnd" alaninin gorunurlugunu dogrular
    * Student kullanicisi "Wallet Balance" alaninin gorunurlugunu dogrular

  @US016MyAccountTC02St
  Scenario: Dashboard sayfasinda kullanici icin My Account sayfasında  ozet bilgiler
  (Total Orders,Total Completed,Total Returnd,Wallet Balance) gorunur olmali.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "My Account" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi My Account sayfasinin "ahmet" kullanicisina ait oldugunu dogrular
    * ReusableMethods bekle 5
    * Student kullanicisi "Total Orders" aktivite sayisini konsola yazdirir
    * Student kullanicisi "Total Completed" aktivite sayisini konsola yazdirir
    * Student kullanicisi "Total Returnd" aktivite sayisini konsola yazdirir
    * Student kullanicisi "Wallet Balance" aktivite sayisini konsola yazdirir
    * Student kullanicisi My Account sayfasindaki tum ozet bilgilerin gorunur oldugunu dogrular
