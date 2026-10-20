Feature: Kayitli bir kullanici olarak kontrollerini sagladigim siparislerimin odemesini gerceklestirebilecegim bir odeme sayfasi olmasini istiyorum

  Background: Teacher kullanicisi sepete urun ekleyip odeme sayfasina gelir
    * Teacher kullanicisi uygulamayi acar
    * Teacher kullanicisi gecerli telefon numarasi ve sifre ile giris yapar
    * Teacher kullanicisi giris yapildigini dogrular
    * Teacher kullanicisi "Socks" urununu arar
    * Teacher kullanicisi "Adidas 3-Stripes Cushioned Crew Socks" urununu secer
    * Teacher kullanicisi "Black" urun secenegini secer
    * Teacher kullanicisi "Add To Cart" butonuna tiklar
    * Teacher kullanicisi sepete gider
    * Teacher kullanicisi "Proceed to Checkout" butonuna tiklar
    * Teacher kullanicisi ilk kayitli teslimat adresini secer
    * Teacher kullanicisi "Save & Pay" butonuna tiklar
    * Teacher kullanicisi "Payment Information" sayfasinin acildigini dogrular

  @US024 @US024_TC01
  Scenario: Select Payment Method basligi altinda odeme yontemi secilebilmeli
    * Teacher kullanicisi "Select Payment Method" basligi altinda asagidaki odeme yontemlerinin gorunur oldugunu dogrular
      | Cash On Delivery |
      | Credit           |
      | Stripe           |
    * Teacher kullanicisi "Stripe" odeme yontemini secer
    * Teacher kullanicisi "Confirm Order" butonuna tiklar
    * Teacher kullanicisi Stripe kart formunun acildigini dogrular

  @US024 @US024_TC02
  Scenario: Stripe kart formu doldurulup odeme yapilabilmeli
    * Teacher kullanicisi "Stripe" odeme yontemini secer
    * Teacher kullanicisi "Confirm Order" butonuna tiklar
    * Teacher kullanicisi Stripe kart formunu asagidaki bilgilerle doldurur
      | Card Number | 4242424242424242 |
      | Expiry      | 1230             |
      | CVC         | 123              |
      | ZIP         | 06000            |
    * Teacher kullanicisi Stripe odemesini onaylar
    * Teacher kullanicisi "Go to order details" butonuna tiklar
    * Teacher kullanicisi son siparisi acar
    * Teacher kullanicisi siparisin odeme durumunun "Paid" oldugunu dogrular
    * Teacher kullanicisi siparisin odeme tipinin "Stripe" oldugunu dogrular

  @US024 @US024_TC03
  Scenario: Confirm Order butonu gorunur ve aktif olmali
    * Teacher kullanicisi "Stripe" odeme yontemini secer
    * Teacher kullanicisi "Confirm Order" butonunun gorunur ve aktif oldugunu dogrular

  @US024 @US024_TC04
  Scenario: Siparisin tamamlandigi mesaji goruntulenmeli
    * Teacher kullanicisi "Stripe" odeme yontemini secer
    * Teacher kullanicisi "Confirm Order" butonuna tiklar
    * Teacher kullanicisi Stripe kart formunu asagidaki bilgilerle doldurur
      | Card Number | 4242424242424242 |
      | Expiry      | 1230             |
      | CVC         | 123              |
      | ZIP         | 06000            |
    * Teacher kullanicisi Stripe odemesini onaylar
    * Teacher kullanicisi "Your order is confirmed." mesajinin gorunur oldugunu dogrular

  @US024 @US024_TC05
  Scenario: Siparis detaylari ve fatura goruntulenebilmeli
    * Teacher kullanicisi "Stripe" odeme yontemini secer
    * Teacher kullanicisi "Confirm Order" butonuna tiklar
    * Teacher kullanicisi Stripe kart formunu asagidaki bilgilerle doldurur
      | Card Number | 4242424242424242 |
      | Expiry      | 1230             |
      | CVC         | 123              |
      | ZIP         | 06000            |
    * Teacher kullanicisi Stripe odemesini onaylar
    * Teacher kullanicisi "Go to order details" butonuna tiklar
    * Teacher kullanicisi son siparisi acar
    * Teacher kullanicisi "Order Details" sayfasinin acildigini dogrular
    * Teacher kullanicisi "Download Receipt" butonuna tiklar
    * Teacher kullanicisi "Print Invoice" butonunun gorunur ve aktif oldugunu dogrular

  @US024 @US024_TC06
  Scenario: Siparis tamamlandiktan sonra alisverise devam edilebilmeli
    * Teacher kullanicisi "Stripe" odeme yontemini secer
    * Teacher kullanicisi "Confirm Order" butonuna tiklar
    * Teacher kullanicisi Stripe kart formunu asagidaki bilgilerle doldurur
      | Card Number | 4242424242424242 |
      | Expiry      | 1230             |
      | CVC         | 123              |
      | ZIP         | 06000            |
    * Teacher kullanicisi Stripe odemesini onaylar
    * Teacher kullanicisi "Go to shopping" butonuna tiklar
    * Teacher kullanicisi anasayfada "Categories" basliginin gorunur oldugunu dogrular
