@US022LogoutSt
Feature: Kayıtlı bir kullanıcı olarak sistemden guvenli bir sekilde cıkıs yapabilmek istiyorum

  @studentDynamicLogoutSt
  Scenario: Kayitli kullanici siteden guvenle cikis yapabilmeli
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "Profile" butona tiklar
    * Register kullanicisi "Sign Up" butona tiklar
    * Student kullanicisi "ahmet" , "3335{timeStamp}" telefon numarasi ve "Query.2026!" degerleri ile kayt olur
    * Student kullanicisi "Success Register Successfully" toaster mesajinin gorunurlugunu dogrular
    * Student kullanicisi yeni bilgilerle siteye giris yapar
    * Student kullanicisi "Success Login Successfully" toaster mesajinin gorunurlugunu dogrular
    * ReusableMethods bekle 3
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Logout" uyuglamadan ayrilma butonuna basar
    * Student kullanicisi "Success Logged Out Successfully" toaster mesajinin gorunurlugunu dogrular

  @StudentPreCOnditionLogoutButtonSt
  Scenario: preCondition
    * Student kullanicisi uygulamayi acar
    * Student kullanicisi "5435143814" telefon numarasi ve "Query.2027!" sifresi ile uyuglamaya giris yapar
    * Student kullanicisi "Profile" butona tiklar
    * Student kullanicisi "Logout" uyuglamadan ayrilma butonuna basar
    * Student kullanicisi "Success Logged Out Successfully" toaster mesajinin gorunurlugunu dogrular

