@WhilePaymentSt
Feature: Kayıtlı bir kullanici olarak urun bedeli odeme islemlerini gerçekleştirmek istiyorum

  @StAddToAdress
  Scenario: Test amacli degil. Sayfa yuklenme problemleri icin gecerli
    * Badged kullanicisi uyugulamayi acar
    * Badged kullanicisi "ahmet" , "5671{timeStamp}" ve "Query.2026!" degerleri ile kayit olur ve giris yapar
    * Badged kullanicisi "Add New Adress" formunu "Ahmet" ismi ile doldurur
    * Badges kullanicisi eklenen adress sahibinin "ahmet" oldugunu dogrular

  @StPayment
  Scenario: Test amacli degil. Sayfa yuklenme problemleri icin gecerli
    * Badged kullanicisi uyugulamayi acar
    * Badged kullanicisi "ahmet" , "5671{timeStamp}" ve "Query.2026!" degerleri ile kayit olur ve giris yapar
    * Badged kullanicisi "Add New Adress" formunu "ahmet" ismi ile doldurur
    * Badges kullanicisi eklenen adress sahibinin "ahmet" oldugunu dogrular
    * Badges kullanicisi "Men Shorts" kategorisinden "Classic Cargo Shorts\n0 (0  Reviews)\n$40.00" urununu satin alir
    * Badged kullanicisi siparis olusumunu "Go to order details" butonunun gorunurlugu ile dogrular

    @shortWAT
  Scenario: Test amacli degil. Sayfa yuklenme problemleri icin gecerli
    * Badged kullanicisi uyugulamayi acar
    * Badges kullanicisi "Men Shorts" kategorisinden "Classic Cargo Shorts\n0 (0  Reviews)\n$40.00" urununu satin alir


