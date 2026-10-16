Feature: Kayitli bir kullanici olarak sitede adres bilgilerimi yonetebilecegim bir sayfa olmasini istiyorum

  Background: Teacher kullanicisi giris yapar
    * Teacher kullanicisi uygulamayi acar
    * Teacher kullanicisi gecerli telefon numarasi ve sifre ile giris yapar
    * Teacher kullanicisi giris yapildigini dogrular

  @US017 @US017_TC01
  Scenario: Profile sayfasinda Address menu basligi gorunur ve aktif olmali
    * Teacher kullanicisi "Profile" sayfasina gider
    * Teacher kullanicisi "Address" menusunun gorunur ve aktif oldugunu dogrular

  @US017 @US017_TC02
  Scenario: Address sayfasinda Add New Address butonu gorunur ve aktif olmali
    * Teacher kullanicisi "Profile" sayfasina gider
    * Teacher kullanicisi "Address" menusune tiklar
    * Teacher kullanicisi "Add New Address" butonunun gorunur ve aktif oldugunu dogrular

  @US017 @US017_TC03
  Scenario: Yeni adres kaydi eklenebilmeli ve kayitli adresler listesinde gorunmeli
    * Teacher kullanicisi "Profile" sayfasina gider
    * Teacher kullanicisi "Address" menusune tiklar
    * Teacher kullanicisi "Add New Address" butonuna tiklar
    * Teacher kullanicisi adres formunu asagidaki bilgilerle doldurur
      | Full Name      | Omer Test        |
      | Email          | omer.test@querycart.com |
      | Phone          | 5435182912       |
      | Country        | Turkey           |
      | State          | Ankara Province  |
      | City           | Ankara           |
      | Street Address | Test Sokak No 1  |
      | Zip Code       | 06000            |
    * Teacher kullanicisi adresi kaydeder
    * Teacher kullanicisi "Test Sokak No 1" adresinin listede gorunur oldugunu dogrular
    * Teacher kullanicisi "Test Sokak No 1" adresini siler

  @US017 @US017_TC04
  Scenario: Kayitli adres duzenlenebilmeli
    * Teacher kullanicisi "Profile" sayfasina gider
    * Teacher kullanicisi "Address" menusune tiklar
    * Teacher kullanicisi "Add New Address" butonuna tiklar
    * Teacher kullanicisi adres formunu asagidaki bilgilerle doldurur
      | Full Name      | Omer Test        |
      | Email          | omer.test@querycart.com |
      | Phone          | 5435182912       |
      | Country        | Turkey           |
      | State          | Ankara Province  |
      | City           | Ankara           |
      | Street Address | Edit Sokak No 2  |
      | Zip Code       | 06000            |
    * Teacher kullanicisi adresi kaydeder
    * Teacher kullanicisi "Edit Sokak No 2" adresini "Edited Sokak No 3" olarak duzenler
    * Teacher kullanicisi "Edited Sokak No 3" adresinin listede gorunur oldugunu dogrular
    * Teacher kullanicisi "Edited Sokak No 3" adresini siler

  @US017 @US017_TC05
  Scenario: Kayitli adres silinebilmeli
    * Teacher kullanicisi "Profile" sayfasina gider
    * Teacher kullanicisi "Address" menusune tiklar
    * Teacher kullanicisi "Add New Address" butonuna tiklar
    * Teacher kullanicisi adres formunu asagidaki bilgilerle doldurur
      | Full Name      | Omer Test        |
      | Email          | omer.test@querycart.com |
      | Phone          | 5435182912       |
      | Country        | Turkey           |
      | State          | Ankara Province  |
      | City           | Ankara           |
      | Street Address | Delete Sokak No 4 |
      | Zip Code       | 06000            |
    * Teacher kullanicisi adresi kaydeder
    * Teacher kullanicisi "Delete Sokak No 4" adresini siler
    * Teacher kullanicisi "Delete Sokak No 4" adresinin listede gorunmedigini dogrular
