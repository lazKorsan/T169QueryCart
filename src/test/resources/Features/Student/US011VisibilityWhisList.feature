@US011VisibilityWhisListSt
Feature: Kayıtlı bir kullanıcı olarak sitede favori listesi oluşturabilmek istiyorum

  @US011VisibilityWhisListTC01St
  Scenario: Anasayfa navbarda wishlist butonu gorunur ve aktif olmalıdır.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Wishlist" butonun gorunur ve aktif oldugunu dogrular

  @US011VisibilityWhisListTC02St
  Scenario: Secilen urunler wishlist /favorite butonundan wishlist listesine eklenebilmelidir.
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Flower Print Foil T" isimli urunu favorilere ekler
    * Student kullanicisi "Wishlist" butona tiklar
    * Student kullanicisi "Wishlist" sayfasinda oldugunu dogrular
    * Student kullanicisi urun isminin "Flower Print Foil T-shirt\n0 (0  Reviews)\n$65.00" oldugunu dogrular
