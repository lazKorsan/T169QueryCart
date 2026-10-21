Feature: History kullanicisi sunum dosyasi

  @klmUS017EditProfile
  Scenario: bir kullanici olarak bilgilerimi guncellemek istiyorum
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Profile" butona tiklar
    * Register kullanicisi "Sign Up" butona tiklar
    * Student kullanicisi "maria" , "3335{timeStamp}" telefon numarasi ve "Query.2026!" degerleri ile kayt olur
    * Student kullanicisi "Success Register Successfully" toaster mesajinin gorunurlugunu dogrular
    * Student kullanicisi yeni bilgilerle siteye giris yapar
    * Student kullanicisi "Success Login Successfully" toaster mesajinin gorunurlugunu dogrular
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Edit Profile" butona tiklar
    * Student kullanicisi "Full Name" box kutusuna index 0 ile "huseyin" yazar
    * Student kullanicisi "Email" box kutusuna index 1 ile "huseyin.customer{timeStamp}@QueryCart.com" mail adresini degistirir
    * Student kullanicisi "Phone" box kutusuna index 2 ile "5435{timeStamp}" telefon numarasini degistirir
    * Student kullanicisi "Save Changes" butona tiklar
    * Student kullanicisi "Profile Updated Successfully" toaster mesajinin gorunurlugunu dogrular
    * Student kullanicisi "huseyin" ismi ile profil degisikligini dogrular

  @klmUS020ChangePassword
  Scenario: Kullanici sifresini basarili bir sekilde degistirebilmeli
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Profile" butona tiklar
    * Register kullanicisi "Sign Up" butona tiklar
    * Student kullanicisi "huseyin" , "4335{timeStamp}" telefon numarasi ve "Query.2026!" degerleri ile kayt olur
    * Student kullanicisi "Success Register Successfully" toaster mesajinin gorunurlugunu dogrular
    * Student kullanicisi yeni bilgilerle siteye giris yapar
    * Student kullanicisi "Success Login Successfully" toaster mesajinin gorunurlugunu dogrular
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Change Password" butona tiklar
    * Student kullanicisi "Old Password" box kutusuna index 0 ile "Query.2026!" yazar
    * Student kullanicisi "New Password" box kutusuna index 1 ile "Query.2027!" yazar
    * Student kullanicisi "Confirm Password" box kutusuna index 2 ile "Query.2027!" yazar
    * Student kullanicisi "Save Changes" butona tiklar
    * Student kullanicisi "Success Change Password" toaster mesaji ile sifre degisikligini dogrular


  @klmUS004CategoriesList
  Scenario: Anasayfada Categories baslıgı altında tüm kategoriler görünür olmalı.
    * Student kullanicisi uygulamayi acar
    * Student kullanicsi Categories basligi altindaki kategorileri listeler


  @klmUS018CanceledOrder
  Scenario: POSITIVE-->ORDER_DETAILS [PENDING_ORDER=CANCELED]
    * History kullanicisi uygulamayi acar
    * History kullanicisi e-posta ile giris yapar
    * History kullanicisi Profile ikonuna tiklar
    * History kullanicisi dashboard menusunde Order History basliginin gorunur ve aktif oldugunu dogrular
    * History kullanicisi dashboard menusunden Order History sayfasini acar
    * History kullanicisi Order History sayfasinin acildigini dogrular
    * History kullanicisi listedeki ilk "Pending" siparisi secer
    * History kullanicisi secilen siparisin goruntuleme ikonuna tiklar
    * History kullanicisi secilen siparisin Order Details sayfasinin acildigini dogrular
    * History kullanicisi Order Details sayfasinda "Cancel Order" butonunun aktif oldugunu dogrular
    * History kullanicisi Cancel Order butonuna tiklar
    * History kullanicisi siparisin iptal edildi mesajini gorur
    * History kullanicisi Cancel Order butonunun kayboldugunu dogrular
    * History kullanicisi Order Details sayfasinda siparisin iptal edildigini dogrular
    * History kullanicisi Order Details sayfasindan geri doner
    * History kullanicisi secilen siparisin durumunun "Canceled" oldugunu dogrular