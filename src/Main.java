import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        // While döngülererini kontrol eden değişkenler.
        boolean cikisYap;
        boolean hesapIslemCik;
        boolean krediKartiIslemCik;
        boolean krediIslemleriCik;
        boolean yatirimIslemCik;

        BankaPersonel personel = new BankaPersonel(tcOlustur(),"Lütfü","Bedel","lutfubedel02@gmail.com",telNoOlustur());
        KrediKarti krediKarti = new KrediKarti(kartNoOlustur(),200000,1000,200000);
        YatirimHesap yatirimHesap = new YatirimHesap(kartNoOlustur(),20000,"1","Yatırım",200,"$",19);
        personel.setPersonelID(personelNoOlustur());

        // Müşterilerin tanımlar.
        Musteri musteri1 = new Musteri(tcOlustur(),"Arda","Alver","ardaalver@gmail.com",telNoOlustur());
        Musteri musteri2 = new Musteri(tcOlustur(),"Ali","Bilici","alibilici@gmail.com",telNoOlustur());

        // Tanımlanan müşterileri personel nesnesinin içerisindeki musteriler arraylistine ekler.
        personel.musteriler.add(musteri1);
        personel.musteriler.add(musteri2);

        // Her müşteri için bir vadesiz hesap tanımlar.
        musteri1.hesaplar.add(new VadesizHesap(musteri1.IBANOlustur(), 1200,"1","Vadesiz",1000));
        musteri2.hesaplar.add(new VadesizHesap(musteri2.IBANOlustur(),2500,"2","Vadesiz",3000));

        // Kullanıcının giriş yapmadan önce ekrana müşteri isimlerini ve numaralarını yazdırır.
        System.out.println("Müşteriler \n----------------");
        for(int i=0;i<personel.musteriler.size();i++)
        {
            Musteri musteri = personel.musteriler.get(i);
            musteri.setMusteriNumarasi(musteriNoOlustur());
            System.out.println("Ad Soyad : " + musteri.getAd() + " " + musteri.getSoyad() + "\t" + "Müşteri No : " + musteri.getMusteriNumarasi());
        }

        // Hangi müşteri ile ilgili işlem yapmak istediğini kullanıcıya sorar.
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n"+"Lütfen işlem yapmak istediğiniz müşteri numarasını giriniz");
        int musteriNo = scanner.nextInt();


        for(int i=0;i<personel.musteriler.size();i++)
        {
            Musteri musteri = personel.musteriler.get(i);

            // Kullanıcının girdiği numara herhangi bir müşteri numarası ile eşleşirse çalışır.
            if(musteri.getMusteriNumarasi()==musteriNo)
            {
                System.out.println("Giriş Yapıldı");
                // Müşterinin kredisini oluşturur.
                musteri.setKrediler(new Krediler(musteri,krediIDOlustur(),20000,12));
                cikisYap=false;

                // Kullanıcı çıkmak isteyene kadar sürekli çalışan bir döngüdür.
                while (!cikisYap)
                {
                    System.out.println("\n          ANA MENÜ \n------------------------------");
                    System.out.println("Lütfen yapmak istediğiniz işlemi seçiniz : \n1-Müşteri Bilgileri \n2-Hesap İşlemleri \n3-Kredi Kartı İşlemleri \n4-Kredi İşlemleri \n5-Yatırım Hesabı İşlemleri \n6-Çıkış Yap");
                    int secim = scanner.nextInt();

                    // Müşterinin kişisel bilgileri, banka hesapları , kredi kartları, aldığı kredilere kadar her türlü bilgiyi toString() metodları ile ekrana yazdırır.
                    if(secim==1)
                    {
                        toplamBakiyeHesapla(musteri);
                        System.out.println("        MÜŞTERİ BİLGİLERİ \n------------------------------");
                        System.out.println("KİŞİSEL BİLGİLER \n----------------");
                        System.out.println(musteri.toString());
                        System.out.println("Toplam Bakiye : " + musteri.getToplamBakiye()+"TL");
                        System.out.println("Banka Hesabı Sayısı : " + musteri.hesaplar.size());
                        System.out.println("Kredi Kartı Sayısı  : " + musteri.krediKartlari.size());
                        System.out.println("\nBANKA HESAPLARI \n-----------------");

                        for(int j=0;j<musteri.hesaplar.size();j++)
                        {
                            if(musteri.hesaplar.get(j) instanceof VadesizHesap)
                                System.out.println(musteri.hesaplar.get(j).toString());

                            else if (musteri.hesaplar.get(j) instanceof VadeliHesap)
                                System.out.println(musteri.hesaplar.get(j).toString());

                            else if (musteri.hesaplar.get(j) instanceof YatirimHesap)
                                System.out.println(musteri.hesaplar.get(j).toString());
                        }
                        System.out.println("\nKREDİ KARTLARI \n-----------------");
                        for(int j=0;j<musteri.krediKartlari.size();j++)
                        {
                            System.out.println(musteri.krediKartlari.get(j).toString());
                        }
                        System.out.println("\nKrediler \n-----------------");
                        System.out.println(musteri.getKrediler().toString());

                    }
                    // Müşterinin hesap ile ilgili işlemleri yani hesap ekle/sil para transferi gibi işlemleri yapabileceği kısım.
                    else if(secim==2)
                    {
                        hesapIslemCik=false;
                        while (!hesapIslemCik)
                        {
                            toplamBakiyeHesapla(musteri);
                            System.out.println("\n          HESAP İŞLEMLERİ \n------------------------------");
                            System.out.println("1-Hesap Ekle \n2-Hesap Sil \n3-Para Transferi \n4-Geri Cık");
                            int secim2= scanner.nextInt();
                            if(secim2==1)
                                musteri.hesapEkle();
                            else if (secim2==2)
                                musteri.hesapSil();
                            else if(secim2==3)
                                ((VadesizHesap)musteri.hesaplar.get(0)).paraTransferi(musteri);
                            else if (secim2==4)
                            {
                                toplamBakiyeHesapla(musteri);
                                hesapIslemCik=true;
                            }
                            else
                                System.out.println("Geçersiz işlem.");
                        }
                    }

                    // Müşterinin kredi kartı işlemlerini yaptığı kısımdır.
                    else if(secim==3)
                    {
                        krediKartiIslemCik=false;
                        while (!krediKartiIslemCik)
                        {
                            toplamBakiyeHesapla(musteri);
                            System.out.println("\n          KREDİ KARTI İŞLEMLERİ \n------------------------------");
                            System.out.println("1-Kredi Kartı Ekle \n2-Kredi Kartı Sil  \n3-Kullanılabilir Limit \n4-Borç Ödeme \n5-Geri Cık");
                            int secim3= scanner.nextInt();
                            if(secim3==1)
                                krediKarti.krediKartiEkle(musteri);
                            else if(secim3==2)
                                krediKarti.krediKartiSil(musteri);
                            else if ((secim3==3))
                                krediKarti.kullanilabilirLimit(musteri);
                            else if(secim3==4)
                                ((VadesizHesap)musteri.hesaplar.get(0)).krediKartiBorcOdeme(musteri, ((VadesizHesap) musteri.hesaplar.get(0)).getVadesizBakiye(), krediKarti.getGuncelBorc());
                            else if(secim3==5)
                            {
                                toplamBakiyeHesapla(musteri);
                                krediKartiIslemCik=true;
                            }

                            else
                                System.out.println("Geçersiz işlem.");
                        }
                    }

                    // Müşterinin kredi işlemlerini yaptığı kısımdır.
                    else if(secim==4)
                    {
                        krediIslemleriCik=false;
                        while (!krediIslemleriCik)
                        {
                            toplamBakiyeHesapla(musteri);
                            System.out.println("\n          KREDİ İŞLEMLERİ \n------------------------------");
                            System.out.println("1-Kredi Bilgisi \n2-Kredi Öde \n3-Kampanyalar \n4-Geri Çık");
                            int secim4= scanner.nextInt();
                            if(secim4==1)
                            {
                                System.out.println(musteri.getKrediler().toString());
                            }
                            else if (secim4==2)
                            {
                                ((VadesizHesap)musteri.hesaplar.get(0)).krediOdeme(musteri);
                            }
                            else if (secim4==3)
                            {
                                musteri.getKrediler().kampanya(musteri);
                            }
                            else if (secim4==4)
                            {
                                toplamBakiyeHesapla(musteri);
                                krediIslemleriCik=true;
                            }

                            else
                                System.out.println("Geçersiz işlem.");
                        }
                    }

                    // Müşterinin yatırım hesabı işlemlerini yaptığı kısımdır.
                    else if(secim==5)
                    {
                        yatirimIslemCik=false;
                        while (!yatirimIslemCik)
                        {
                            toplamBakiyeHesapla(musteri);
                            System.out.println("\n          YATIRIM HESABI İŞLEMLERİ \n------------------------------");
                            System.out.println("Yapmak istediğiniz işlemi giriniz  : \n1-Para Ekle \n2-Para Boz \n3-Geri Çık");
                            int secim5 = scanner.nextInt();

                            if(secim5==1)
                                yatirimHesap.paraEkle(musteri ,yatirimHesap.getYatirimBakiye(),yatirimHesap.getYatirimTuru(),yatirimHesap.getKur());

                            else if (secim5==2)
                                yatirimHesap.paraBoz(musteri ,yatirimHesap.getYatirimBakiye(),yatirimHesap.getYatirimTuru(),yatirimHesap.getKur());

                            else if(secim5==3)
                            {
                                toplamBakiyeHesapla(musteri);
                                yatirimIslemCik=true;
                            }
                            else
                                System.out.println("Geçersiz işlem.");

                        }

                    }

                    else if(secim==6)
                    {
                        toplamBakiyeHesapla(musteri);
                        cikisYap=true;
                    }

                    else
                        System.out.println("Geçersiz işlem");
                }
            }
        }
    }


    public static void toplamBakiyeHesapla(Musteri musteri)
    {
        /*
            Vadesiz hesap varsa vadeli hesap ve yatırım hesabının içerisindeki bakiyeleri toplayıp toplam bakiyeye atar daha sonra musteri classının içerisindeki
            setToplamBakiye methodu ile toplam bakiye güncellenir.
         */

        double toplamBakiye=0;
        for(int i=0;i<musteri.hesaplar.size();i++)
        {
            if(musteri.hesaplar.get(i) instanceof VadesizHesap)
                toplamBakiye += ((VadesizHesap)musteri.hesaplar.get(i)).getVadesizBakiye();
            else if(musteri.hesaplar.get(i) instanceof VadeliHesap)
                toplamBakiye +=((VadeliHesap)musteri.hesaplar.get(i)).getVadeliBakiye();
            else if(musteri.hesaplar.get(i) instanceof YatirimHesap)
                toplamBakiye += (((YatirimHesap)musteri.hesaplar.get(i)).getYatirimBakiye()*(((YatirimHesap) musteri.hesaplar.get(i)).getKur()));
        }

        musteri.setToplamBakiye(toplamBakiye);
    }

    public static int musteriNoOlustur()
    {
        // 1000-10.000 arsında rastgele bir musteriNo geri döndürür.
        Random rand = new Random();
        int musteriNo = rand.nextInt(9000)+1000;
        return musteriNo;
    }
    public static long tcOlustur()
    {
        // TC Kimlik oluşturma algoritması kullanılarak her müşteri için rastgele tc kimlik numarası oluşturur.
        Random rand = new Random();

        int[] tcDizi = new int[11];
        int tek=0,cift=0,toplam=0;
        int onuncuBas,onbirinciBas;

        for(int i=0;i<9;i++)
        {
            tcDizi[i]= rand.nextInt(10);
            if(tcDizi[i]==0)
            {
                tcDizi[i]= rand.nextInt(9)+1;
            }
        }

        for(int i=0;i<9;i++)
        {
            if(i%2==0)
                tek += tcDizi[i];
            else
                cift += tcDizi[i];
        }

        onuncuBas = (tek*7 + cift*9)%10;
        tcDizi[9]=onuncuBas;

        for(int i=0;i<10;i++)
        {
            toplam += tcDizi[i];
        }
        onbirinciBas = toplam%10;

        tcDizi[10]=onbirinciBas;

        String birlesik="";
        for(int i=0;i<11;i++)
        {
            birlesik += Integer.toString(tcDizi[i]);
        }

        long tcKimlik = Long.parseLong(birlesik);
        return tcKimlik;
    }

    public static long telNoOlustur()
    {
        Random rand = new Random();
        int[] telDizi = new int[9];

        for(int i=0;i<9;i++)
        {
            telDizi[i]= rand.nextInt(10);
        }

        String birlesik="05";
        for(int i=0;i<9;i++)
        {
            birlesik += Integer.toString(telDizi[i]);
        }

        long telNo = Long.parseLong(birlesik);
        return telNo;
    }

    public static int kartNoOlustur()
    {
        // 10.000 - 100.000 arasında rastgele bir kartNo geri döndürür.
        Random rand = new Random();
        int kartNo = rand.nextInt(90000)+10000;
        return kartNo;
    }

    public static int krediIDOlustur()
    {
        // 10.000 -100.000 arasında rastgele bir krediID geri döndürür.
        Random rand = new Random();
        int krediID = rand.nextInt(90000)+10000;
        return krediID;
    }

    public static int personelNoOlustur()
    {
        // 10.000 - 100.000 arasında rastgele bir personelNo geri döndürür.
        Random rand = new Random();
        int personelNo = rand.nextInt(90000)+10000;
        return personelNo;
    }

}