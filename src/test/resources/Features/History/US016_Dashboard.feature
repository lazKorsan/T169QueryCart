Feature: US016 - Kullaniciya ozel Dashboard sayfasi

  @US016 @US016TC01
  Scenario: POSITIVE-->HOME [PROFILE_ICON=VISIBLE_AND_ACTIVE]
  Ana sayfada Profile ikonu gorunur ve aktif olmali.

    * History kullanicisi uygulamayi acar
    * History kullanicisi ana sayfada Profile ikonunun gorunur ve aktif oldugunu dogrular

  @US016 @US016TC02
  Scenario: POSITIVE-->PROFILE [DASHBOARD=OPENED]
  Giris yapmis kullanici ana sayfadayken Profile ikonuna tikladiginda dashboard sayfasina gecmeli.

    * History kullanicisi uygulamayi acar
    * History kullanicisi e-posta ile giris yapar
    * History kullanicisi "Home" menusune tiklar
    * History kullanicisi "My Account" basliginin gorunmedigini dogrular
    * History kullanicisi Profile ikonuna tiklar
    * History kullanicisi dashboardda giris yapan kullanicinin e-postasini gorur
    * History kullanicisi dashboard menusunde su basliklarin gorunur oldugunu dogrular
      | My Account      |
      | Order History   |
      | Return Orders   |
      | Edit Profile    |
      | Change Password |
      | Address         |
      | App Download    |
      | Logout          |

  @US016 @US016TC03
  Scenario: POSITIVE-->MY_ACCOUNT [SUMMARY_CARDS=VISIBLE]
  My Account sayfasinda Total Orders, Total Completed, Total Returnd ve Wallet Balance gorunur olmali.

    * History kullanicisi uygulamayi acar
    * History kullanicisi e-posta ile giris yapar
    * History kullanicisi Profile ikonuna tiklar
    * History kullanicisi "My Account" menusune tiklar
    * History kullanicisi My Account verilerinin yuklenmesini bekler
    * History kullanicisi "Total Orders" ozet kartinin ve degerinin gorunur oldugunu dogrular
    * History kullanicisi "Total Completed" ozet kartinin ve degerinin gorunur oldugunu dogrular
    * History kullanicisi "Total Returnd" ozet kartinin ve degerinin gorunur oldugunu dogrular
    * History kullanicisi "Wallet Balance" ozet kartinin ve degerinin gorunur oldugunu dogrular
    * History kullanicisi "Wallet Balance" ozet kartinin degerinin para formatinda oldugunu dogrular

  # On kosul: Test hesabinda en az 1 siparis olmali
  @US016 @US016TC04
  Scenario: POSITIVE-->MY_ACCOUNT [ORDER_HISTORY=LISTED]
  My Account sayfasinda kullanicinin siparis gecmisi goruntulenmeli.

    * History kullanicisi uygulamayi acar
    * History kullanicisi e-posta ile giris yapar
    * History kullanicisi Profile ikonuna tiklar
    * History kullanicisi "My Account" menusune tiklar
    * History kullanicisi My Account verilerinin yuklenmesini bekler
    * History kullanicisi "Order History" basliginin gorunur oldugunu dogrular
    * History kullanicisi siparis gecmisindeki ilk sipariste siparis no, durum ve toplam bilgilerinin oldugunu dogrular
    * History kullanicisi "Total Orders" degerinin siparis gecmisindeki siparis sayisina esit oldugunu dogrular