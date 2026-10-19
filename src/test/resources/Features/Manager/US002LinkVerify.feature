@US002
Feature: Bir kullanici olarak site ici islemlerini kolaylikla yapabilmek
        icin ana sayfa altbarında kolay linklerin bulunmasını istiyorum

  Background: Manager kullanicisi uygulamayi acar
    * Manager kullanicisi uygulamayi acar

  @US002_TC01
  Scenario: Alt bar bolumu linkleri dogrulama
    * Manager kullanicisi sayfa alt barinda "Home" ikonunun gorunur oldugunu dogrular
    * Manager kullanicisi sayfa alt barinda "Category" ikonunun gorunur oldugunu dogrular
    * Manager kullanicisi sayfa alt barinda "Wishlist" ikonunun gorunur oldugunu dogrular
    * Manager kullanicisi sayfa alt barinda "Cart" ikonunun gorunur oldugunu dogrular
    * Manager kullanicisi sayfa alt barinda "Profile" ikonunun gorunur oldugunu dogrular

  @US002_TC02
  Scenario Outline: Alt bar linklerinin dogru sayfaya yonlendirme kontrolu
    * Manager kullanicisi alt bardaki "<buton>" ikonuna tiklar
    * Manager kullanicisi ilgili "<sayfa>" basligina yonlendirildigini dogrular

    Examples:

      | buton    | sayfa    |
      | Home     | Home     |
      | Category | Category |
      | Wishlist | Wishlist |
      | Profile  | Profile  |
      | Cart     | Cart     |

  @US002_TC03
  Scenario: Arama kutusunun gorunur ve arama yapilabildigini dorulama
    * Manager kullanicisi ana sayfada buyutec simgesine tiklar
    * Manager kullanicisi arama kutusunu goruntuler ve "Shoes" yazarak arama yapar
    * Manager kullanicisi klavyeden arama butonuna tiklar
    * Manager kullanicisi arama sonuclarinin ekranda listelendigini dogrular
