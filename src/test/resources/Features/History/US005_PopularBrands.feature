Feature: US005 - Ana sayfada Popular Brands bolumu

  @US005 @US005TC01
  Scenario: POSITIVE-->HOME [POPULAR_BRANDS_TITLE=VISIBLE]
  Ana sayfada Popular Brands basligi gorunur olmali.

    * History kullanicisi uygulamayi acar
    * History kullanicisi ana sayfada "Popular Brands" basligina kadar kaydirir
    * History kullanicisi "Popular Brands" basliginin gorunur oldugunu dogrular

  @US005 @US005TC02
  Scenario: POSITIVE-->HOME [BRANDS=VISIBLE]
  Ana sayfada Popular Brands basligi altinda brandler gorunur olmali.

    * History kullanicisi uygulamayi acar
    * History kullanicisi ana sayfada "Popular Brands" basligina kadar kaydirir
    * History kullanicisi Popular Brands altinda en az 1 markanin gorunur oldugunu dogrular
    * History kullanicisi Popular Brands altinda "Nike" markasinin gorunur oldugunu dogrular

  @US005 @US005TC03
  Scenario: POSITIVE-->HOME [BRANDS=HORIZONTAL_SCROLL]
  Popular Brands listesi yana kaydirilabilmeli ve listenin sonundaki markalar goruntulenebilmeli.

    * History kullanicisi uygulamayi acar
    * History kullanicisi ana sayfada "Popular Brands" basligina kadar kaydirir
    * History kullanicisi Popular Brands listesini "Urban Casuals" markasi gorunene kadar yana kaydirir
    * History kullanicisi Popular Brands altinda "Urban Casuals" markasinin gorunur oldugunu dogrular