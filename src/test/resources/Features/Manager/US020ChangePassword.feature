@US020
Feature: Kayıtlı bir kullanıcı olarak dashboard sayfamda sifre degistirme islemlerimi gerceklestirmek istiyorum


    @US020_TC01
  Scenario Outline: Kayitli kullanici olarak dashboard sayfasinda change password linkinin gorunur
                    ve aktif oldugunu dogrulama islemi

     * Manager kullanicisi uygulamayi acar
     * Manager kullanicisi "Profile" butonuna tiklar
     * Manager kullanicisi "Sign In" butonuna tiklar
     * Manager kullanicisi dogrulama yontemi olarak "<yontem>" secenegini secer
     * Manager kullanicisi ilgili alana "<iletisim_bilgisi>" bilgisini girer
     * Manager kullanicisi password kutusuna sifre bilgisini girer
     * Manager kullanicisi "Sign In" butonuna tiklar
     * Manager kullanicisi "Profile" butonuna tiklar
     * Manager kullanicisi acilan dashboardda "Change Password" linkini gorunur ve tiklanabilir oldugunu dogrular

     Examples:

      | yontem     | iletisim_bilgisi |
      | E-posta    | managerMail |
      | Telefon    | phoneNumber |

      @US020_TC02
     Scenario Outline: Change Password sayfasında Old password,New password,Confirm password text boxları gorunur ve aktif olmali

     * Manager kullanicisi uygulamayi acar
     * Manager kullanicisi "Profile" butonuna tiklar
     * Manager kullanicisi "Sign In" butonuna tiklar
     * Manager kullanicisi dogrulama yontemi olarak "<yontem>" secenegini secer
     * Manager kullanicisi ilgili alana "<iletisim_bilgisi>" bilgisini girer
     * Manager kullanicisi password kutusuna sifre bilgisini girer
     * Manager kullanicisi "Sign In" butonuna tiklar
     * Manager kullanicisi "Profile" butonuna tiklar
     * Manager kullanicisi acilan dashboardda "Change Password" linkini goruntuler ve tiklar
     * Manager kullanicisi old password alanina "<eski_sifre>", new password alanina "<yeni_sifre>" ve confirm alanina "<onay_sifre>" yazar


      Examples:

        | yontem     | iletisim_bilgisi | eski_sifre  | yeni_sifre  | onay_sifre      |
        | E-posta    | managerMail      | oldPassword | newPassword | confirmPassword |
        | Telefon    | phoneNumber      | oldPassword | newPassword | confirmPassword |


      @US020_TC03
      Scenario Outline: Save Changes butonu gorunur ve aktif olmalı

      * Manager kullanicisi uygulamayi acar
      * Manager kullanicisi "Profile" butonuna tiklar
      * Manager kullanicisi "Sign In" butonuna tiklar
      * Manager kullanicisi dogrulama yontemi olarak "<yontem>" secenegini secer
      * Manager kullanicisi ilgili alana "<iletisim_bilgisi>" bilgisini girer
      * Manager kullanicisi password kutusuna sifre bilgisini girer
      * Manager kullanicisi "Sign In" butonuna tiklar
      * Manager kullanicisi "Profile" butonuna tiklar
      * Manager kullanicisi acilan dashboardda "Change Password" linkini goruntuler ve tiklar
      * Manager kullanicisi old password alanina "<eski_sifre>", new password alanina "<yeni_sifre>" ve confirm alanina "<onay_sifre>" yazar
      * Manager kullanicisi "Save Changes" butonunu goruntuler ve tiklar

        Examples:

          | yontem     | iletisim_bilgisi | eski_sifre  | yeni_sifre  | onay_sifre      |
          | E-posta    | managerMail      | oldPassword | newPassword | confirmPassword |
          | Telefon    | phoneNumber      | oldPassword | newPassword | confirmPassword |


        @US020_TC04
        Scenario Outline: Yapilan degisiklikler basariyla kaydedilmeli

        * Manager kullanicisi uygulamayi acar
        * Manager kullanicisi "Profile" butonuna tiklar
        * Manager kullanicisi "Sign In" butonuna tiklar
        * Manager kullanicisi dogrulama yontemi olarak "<yontem>" secenegini secer
        * Manager kullanicisi ilgili alana "<iletisim_bilgisi>" bilgisini girer
        * Manager kullanicisi password kutusuna sifre bilgisini girer
        * Manager kullanicisi "Sign In" butonuna tiklar
        * Manager kullanicisi "Profile" butonuna tiklar
        * Manager kullanicisi acilan dashboardda "Change Password" linkini goruntuler ve tiklar
        * Manager kullanicisi old password alanina "<eski_sifre>", new password alanina "<yeni_sifre>" ve confirm alanina "<onay_sifre>" yazar
        * Manager kullanicisi "Save Changes" butonunu goruntuler ve tiklar
        * Manager kullanicisi "Change Password" yazisini goruntuler ve islemin basariyla gerceklestigini dogrular

        Examples:

        | yontem     | iletisim_bilgisi | eski_sifre  | yeni_sifre  | onay_sifre      |
        | E-posta    | managerMail      | oldPassword | newPassword | confirmPassword |
        | Telefon    | phoneNumber      | oldPassword | newPassword | confirmPassword |
