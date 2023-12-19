import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Musteri extends Kisi
{
    private int musteriNumarasi;
    private double toplamBakiye;
    private Krediler krediler;
    ArrayList<BankaHesap> hesaplar = new ArrayList<>();
    ArrayList<KrediKarti> krediKartlari = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    public Musteri(long tcKimlikNo, String ad, String soyad, String email, long telefonNumarasi)
    {
        super(tcKimlikNo, ad, soyad, email, telefonNumarasi);
    }


    public void hesapEkle()
    {
        // Kulllaniciya hangi tur hesap acmak istediği sorulur.
        System.out.println("Hangi tur hesap acmak istersiniz ? (Vadeli / Yatirim)");
        String hesapSorgu = scanner.next();

        VadeliHesap vadeliHesap = new VadeliHesap(this,IBANOlustur(),getToplamBakiye(),hesaplar.get(0).getHesapBilgisi(),"Vadeli",0,45);
        YatirimHesap yatirimHesap = new YatirimHesap(IBANOlustur(),getToplamBakiye(),hesaplar.get(0).getHesapBilgisi(),"Yatirim",10000,"usd",19);

        // Eğer kullanicidan alinan değer vadeli hesabin hesap turune esit ise calisir.
        if(hesapSorgu.equals(vadeliHesap.getHesapTuru()))
        {
            int kontrol=0;
            for(int i=0;i<hesaplar.size();i++)
            {
                // Eğer musterinin daha önceden acilmis bir vadeli hesabi varsa calisir ve ekrana bir uyari yazdirir.
                if(hesapSorgu.equals(hesaplar.get(i).getHesapTuru()))
                {
                    System.out.println("Vadeli hesabiniz bulunmaktadir.");
                    break;
                }
                else
                    kontrol++;

                // Eğer kullanicinin daha önceden acilmis bir vadeli hesabi yoksa musterinin hesaplar arraylistine bir vadeli hesap ekler.
                if(kontrol==hesaplar.size())
                {
                    hesaplar.add(vadeliHesap);
                    System.out.println("Vadeli hesabiniz eklendi");
                    break;
                }
            }
        }

        // Eğer kullanicidan alinan değer yatirim hesabin hesap turune esit ise calisir.
        else if(hesapSorgu.equals(yatirimHesap.getHesapTuru()))
        {
            int kontrol=0;
            for(int i=0;i<hesaplar.size();i++)
            {
                // Eğer musterinin daha önceden acilmis bir yatirim hesabi varsa calisir ve ekrana bir uyari yazdirir.
                if(hesapSorgu.equals(hesaplar.get(i).getHesapTuru()))
                {
                    System.out.println("Yatirim hesabiniz bulunmaktadir.");
                    break;
                }
                else
                    kontrol++;

                // Eğer kullanicinin daha önceden acilmis bir vadeli hesabi yoksa musterinin hesaplar arraylistine bir vadeli hesap ekler.
                if(kontrol==hesaplar.size())
                {
                    hesaplar.add(yatirimHesap);
                    System.out.println("Yatirim hesabiniz eklendi");
                    break;
                }
            }
        }

        // Eğer kullanicidan alinan değer herhangi bir hesabin hesap turune esit değil ise calisir ve ekrana bir uyari yazdirir.
        else
            System.out.println("Lutfen gecerli bir hesap turu giriniz");

    }




    public void hesapSil()
    {
        // Kullaniciya hangi hesabi silmek istediği sorulur.
        System.out.println("Hangi hesabi silmek istersiniz ? \t (Vadeli  / Yatirim)");
        String secim = scanner.next();

        for(int i=0;i<hesaplar.size();i++)
        {
            // Eğer kullanicinin silmek istediği hesap turunde musterinin bir hesabi varsa calisir.
            if(secim.equals(hesaplar.get(i).getHesapTuru()))
            {
                // Silinmek istenen hesap bir vadeli hesap ise calisir.
                if(hesaplar.get(i) instanceof VadeliHesap)
                {
                    // Hesabin icerisinde para olup olmadiği kontrol edilir Eğer para varsa ekrana bir uyari yazdirilir.
                    if(((VadeliHesap)hesaplar.get(i)).getVadeliBakiye()>0)
                    {
                        System.out.println("Lutfen öncelikle bakiyenizi baska bir hesaba aktariniz");
                    }
                    // Eğer hesabin icerisindeki bakiye sifira esit ise hesap silinir.
                    else
                    {
                        hesaplar.remove(i);
                        System.out.println("Vadeli hesabiniz kaldirildi");
                    }
                    break;

                }

                // Silinmek istenen hesap bir yatirim hesabi ise calisir.
                else if(hesaplar.get(i) instanceof YatirimHesap)
                {
                    // Hesabin icerisinde para olup olmadiği kontrol edilir Eğer para varsa ekrana bir uyari yazdirilir.
                    if(((YatirimHesap)hesaplar.get(i)).getYatirimBakiye()>0)
                    {
                        System.out.println("Lutfen öncelikle bakiyenizi baska bir hesaba aktariniz");
                    }
                    // Eğer hesabin icerisindeki bakiye sifira esit ise hesap silinir.
                    else
                    {
                        hesaplar.remove(i);
                        System.out.println("Yatirim hesabiniz kaldirildi");
                    }
                    break;
                }
            }
        }
    }


    public int IBANOlustur()
    {
        // 1.000.000 -10.000.000 arasinda random bir sayiyi geri döndurur.
        Random rand = new Random();
        int iban = rand.nextInt(9000000)+1000000;
        return iban;
    }




    @Override
    public String toString() {
        return "Ad Soyad   \t: " +getAd() + " " + getSoyad() +
                "\nTC No   \t: " +getTcKimlikNo() +
                "\nE-mail    \t: " + getEmail() +
                "\nTelefon No \t: 0"+getTelefonNumarasi()+
                "\nMusteri No \t: " + getMusteriNumarasi();
    }

    public int getMusteriNumarasi() {return musteriNumarasi;}
    public void setMusteriNumarasi(int musteriNumarasi) {this.musteriNumarasi = musteriNumarasi;}

    public ArrayList<BankaHesap> getHesaplar() {return hesaplar;}
    public void setHesaplar(ArrayList<BankaHesap> hesaplar) {this.hesaplar = hesaplar;}

    public ArrayList<KrediKarti> getKrediKartlari() {return krediKartlari;}
    public void setKrediKartlari(ArrayList<KrediKarti> krediKartlari) {this.krediKartlari = krediKartlari;}

    public Krediler getKrediler() {return krediler;}

    public void setKrediler(Krediler krediler) {this.krediler = krediler;}

    public double getToplamBakiye() {return toplamBakiye;}
    public void setToplamBakiye(double toplamBakiye) {this.toplamBakiye = toplamBakiye;}
}
