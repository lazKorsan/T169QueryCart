Feature: US018_OrderHistory

  @US018 @US018TC01
  Scenario: POSITIVE-->PROFILE [ORDER_HISTORY_MENU=VISIBLE_AND_ACTIVE]
    * History kullanicisi uygulamayi acar
    * History kullanicisi e-posta ile giris yapar
    * History kullanicisi Profile ikonuna tiklar
    * History kullanicisi dashboard menusunde Order History basliginin gorunur ve aktif oldugunu dogrular
    * History kullanicisi dashboard menusunden Order History sayfasini acar
    * History kullanicisi Order History sayfasinin acildigini dogrular

  @US018 @US018TC02
  Scenario: POSITIVE-->ORDER_HISTORY [VIEW_ICON=ACTIVE]
    * History kullanicisi uygulamayi acar
    * History kullanicisi e-posta ile giris yapar
    * History kullanicisi Profile ikonuna tiklar
    * History kullanicisi dashboard menusunde Order History basliginin gorunur ve aktif oldugunu dogrular
    * History kullanicisi dashboard menusunden Order History sayfasini acar
    * History kullanicisi Order History sayfasinin acildigini dogrular
    * History kullanicisi listedeki ilk siparisi secer
    * History kullanicisi secilen siparisin goruntuleme ikonunun aktif oldugunu dogrular
    * History kullanicisi secilen siparisin goruntuleme ikonuna tiklar
    * History kullanicisi secilen siparisin Order Details sayfasinin acildigini dogrular

  @US018 @US018TC03
  Scenario: POSITIVE-->ORDER_DETAILS [RECEIPT=ACCESSIBLE]
    * History kullanicisi uygulamayi acar
    * History kullanicisi e-posta ile giris yapar
    * History kullanicisi Profile ikonuna tiklar
    * History kullanicisi dashboard menusunde Order History basliginin gorunur ve aktif oldugunu dogrular
    * History kullanicisi dashboard menusunden Order History sayfasini acar
    * History kullanicisi Order History sayfasinin acildigini dogrular
    * History kullanicisi listedeki ilk siparisi secer
    * History kullanicisi secilen siparisin goruntuleme ikonuna tiklar
    * History kullanicisi secilen siparisin Order Details sayfasinin acildigini dogrular
    * History kullanicisi Order Details sayfasinda "Download Receipt" butonunun aktif oldugunu dogrular
    * History kullanicisi Download Receipt butonuna tiklar
    * History kullanicisi secilen siparisin faturasinin acildigini dogrular

  @US018 @US018TC04
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