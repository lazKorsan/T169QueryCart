@US017AddToAddress @platform16
Feature: Kayıtlı bir kullanıcı olarak sitede adres bilgilerimi yönetebilecegim bir sayfa olmasını istiyorum

  @US017AddToAddressTC04
  Scenario: Add New Address butonu gorunur ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Address" butona tiklar
    * Student kullanicisi "Add New Address" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi "Add New Address" formunu "Ahmet" ismi ile doldurur
    * ReusableMethods bekle 7
    * Student kullanicisi "Address" sayfasinda oldugunu dogrular
    * Student kullanicisi eklenen adress sahibinin "Ahmet" oldugunu dogrular

  @US017AddToAddressTC01
  Scenario: Add New Address butonu gorunur ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Address" butonun gorunur ve aktif oldugunu dogrular
    * Student kullanicisi "Address" butona tiklar
    * Student kullanicisi "Add New Address" butonun gorunur ve aktif oldugunu dogrular

  @US017AddToAddressTC02
  Scenario: Add New Address butonu gorunur ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Address" butona tiklar
    * Student kullanicisi "Add New Address" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi "Add New Address" formunun gorunur oldugunu dogrular

  @US017AddToAddressTC03
  Scenario: Add New Address butonu gorunur ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Address" butona tiklar
    * Student kullanicisi "Add New Address" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi "Add New Address" formunu doldurur
    * ReusableMethods bekle 7
    * Student kullanicisi "Address" sayfasinda oldugunu dogrular

  @US017AddToAddressTC04
  Scenario: Add New Address butonu gorunur ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Address" butona tiklar
    * Student kullanicisi "Add New Address" butona tiklar
    * ReusableMethods bekle 3
    * Student kullanicisi "Add New Address" formunu "Ahmet" ismi ile doldurur
    * ReusableMethods bekle 7
    * Student kullanicisi "Address" sayfasinda oldugunu dogrular
    * Student kullanicisi eklenen adress sahibinin "Ahmet" oldugunu dogrular

  @preConditionAddToAddress
  Scenario: Add New Address butonu gorunur ve aktif olmalı
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Address" butona tiklar
    * ReusableMethods bekle 7
    * Student kullanicisi "Address" sayfasinda oldugunu dogrular
    * Student kullanicisi eklenen adress bilgilerini konsola yazdirir
    * Student kullanicisi eklenen adress sahibinin "Ahmet" oldugunu dogrular



