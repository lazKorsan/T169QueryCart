Feature: Bir kullanici olarak anasayfada Categories basligi altinda kategorileri gorup
  istedigim kategoriden urun secebilmek istiyorum

  Background: Teacher kullanicisi uygulamayi acar
    * Teacher kullanicisi uygulamayi acar

  @US004 @US004_TC01
  Scenario: Anasayfada Categories basligi altinda tum kategoriler gorunur olmali
    * Teacher kullanicisi anasayfada "Categories" basliginin gorunur oldugunu dogrular
    * Teacher kullanicisi Categories basligi altinda asagidaki kategorilerin gorunur oldugunu dogrular
      | Men                    |
      | Men Clothing           |
      | Men T-Shirt            |
      | Men Shorts             |
      | Men Coat               |
      | Men Suit               |
      | Men Shoes              |
      | Men Sneakers           |
      | Men Classic Shoes      |
      | Men Boots              |
      | Men Casual Shoes       |
      | Men Accessories        |
      | Men Bags               |
      | Men Socks              |
      | Men Watch              |
      | Women                  |
      | Women Clothing         |
      | Woman Dresses - Skirts |
      | Woman T-shirt          |
      | Woman Trousers         |
      | Woman Coat             |
      | Women Shoes            |
      | Women Casual Shoes     |
      | Women Classic Shoes    |
      | Women's Boots          |
      | Women Sneakers         |
      | Women Accessories      |
      | Women Bags             |
      | Women Watch            |
      | Women Jewelry          |
      | Juniors                |
      | Juniors Clothing       |
      | Girl Clothes           |
      | Juniors Sleepwear      |
      | Boy Clothes            |
      | Baby Clothes           |
      | Juniors Shoes          |
      | Girl Shoes             |
      | Boy Shoes              |
      | Juniors Accessories    |
      | Juniors Bags           |
      | Juniors Hat & Beres    |
      | Toys                   |

  @US004 @US004_TC02
  Scenario: Secilen kategorinin urunleri listelenmeli ve secilen urunun detay sayfasi acilmali
    * Teacher kullanicisi "Toys" kategorisini secer
    * Teacher kullanicisi "Toys" kategorisine ait urunlerin listelendigini dogrular
    * Teacher kullanicisi "45cm Anime POKEMON Pikachu Large Stuffed Dolls Soft Plush Animal Toy Gift" urununu secer
    * Teacher kullanicisi "45cm Anime POKEMON Pikachu Large Stuffed Dolls Soft Plush Animal Toy Gift" urununun detay sayfasinin acildigini dogrular
