import java.util.Date;
import java.util.Scanner;

public class VadesizHesap extends BankaHesap
{
    private String hesapTuru;
    private double vadesizBakiye;

    public VadesizHesap(long iban, double toplamBakiye, String hesapBilgisi, String hesapTuru, double vadesizBakiye)
    {
        super(iban, toplamBakiye, hesapBilgisi);
        this.hesapTuru = hesapTuru;
        this.vadesizBakiye = vadesizBakiye;

        // Hesabın açılış tarihini şu an olarak ayarlar.
        setHesapAcilisTarihi(new Date());
    }

    public void paraTransferi(Musteri musteri)
    {
        Scanner scanner = new Scanner(System.in);
        // Müşteriye hangi hesaba para transfer etmek istediği sorulur.
        System.out.println("Parayı hangi hesaba transfer etmek istiyorsunuz : \t (Vadeli/Yatırım)");
        String secim = scanner.next();
        // Müşteriye ne kadar para transfer etmek istediği sorulur.
        System.out.println("Transfer edilecek tutar : ");
        int tutar = scanner.nextInt();

        // Eğer müşterinin vadesiz hesabında transfer etmek istediği kadar bakiye varsa çalışır.
        if(tutar<((VadesizHesap)musteri.hesaplar.get(0)).getVadesizBakiye())
        {
            for(int i=0;i<musteri.hesaplar.size();i++)
            {
                if(secim.equals(musteri.hesaplar.get(i).getHesapTuru()))
                {
                    // Eğer para transferi yapılmak istenen hesap bir vadeli hesap ise çalışır.
                    if(musteri.hesaplar.get(i) instanceof VadeliHesap)
                    {
                        // Eğer vadesiz hesap bir maaş hesabı ise çalışır.
                        if(((VadesizHesap)musteri.hesaplar.get(0)).getHesapBilgisi().equals("1"))
                        {
                            // Vadesiz hesap bakiyesinden transfer edilmek istenen para ve transfer ücreti olan 8TL kesilir.
                            // Vadeli hesap bakiyesine ise transfer edilmek istenen para aktarılır.
                            System.out.println("Para transferi başarılı.");
                            ((VadesizHesap) musteri.hesaplar.get(0)).setVadesizBakiye(((VadesizHesap)musteri.hesaplar.get(0)).getVadesizBakiye()-tutar-8);
                            ((VadeliHesap) musteri.hesaplar.get(i)).setVadeliBakiye(((VadeliHesap)musteri.hesaplar.get(i)).getVadeliBakiye()+tutar);
                            System.out.println("Güncel Vadesiz Bakiye : " + ((VadesizHesap)musteri.hesaplar.get(0)).getVadesizBakiye() +"\t Transfer Ücreti : " + 8);
                            System.out.println("Güncel Vadeli Bakiye : " + ((VadeliHesap)musteri.hesaplar.get(i)).getVadeliBakiye());
                        }

                        // Eğer vadesiz hesap normak bir hesap ise çalışır.
                        else if(((VadesizHesap)musteri.hesaplar.get(0)).getHesapBilgisi().equals("2"))
                        {
                            // Vadesiz hesap bakiyesinden transfer edilmek istenen para kesilir.
                            // Transfer ücreti yoktur.
                            // Vadeli hesap bakiyesine ise transfer edilmek istenen para aktarılır.
                            System.out.println("Para transferi başarılı.");
                            ((VadesizHesap) musteri.hesaplar.get(0)).setVadesizBakiye(((VadesizHesap)musteri.hesaplar.get(0)).getVadesizBakiye()-tutar);
                            ((VadeliHesap) musteri.hesaplar.get(i)).setVadeliBakiye(((VadeliHesap)musteri.hesaplar.get(i)).getVadeliBakiye()+tutar);
                            System.out.println("Güncel Vadesiz Bakiye : " + ((VadesizHesap)musteri.hesaplar.get(0)).getVadesizBakiye() +"\t Transfer Ücreti : " + 0);
                            System.out.println("Güncel Vadeli Bakiye : " + ((VadeliHesap)musteri.hesaplar.get(i)).getVadeliBakiye());

                        }
                    }
                    // Eğer para transferi yapılmak istenen hesap bir yatırım hesabı ise çalışır.
                    else if(musteri.hesaplar.get(i) instanceof YatirimHesap)
                    {
                        // Eğer vadesiz hesap bir maaş hesabı ise çalışır.
                        if(((VadesizHesap)musteri.hesaplar.get(0)).getHesapBilgisi().equals("1"))
                        {
                            // Vadesiz hesap bakiyesinden transfer edilmek istenen para ve transfer ücreti olan 8TL kesilir.
                            // Yatırım hesap bakiyesine ise transfer edilmek istenen para kura göre hesaplanarak aktarılır.
                            System.out.println("Para transferi başarılı.");
                            ((VadesizHesap) musteri.hesaplar.get(0)).setVadesizBakiye(((VadesizHesap)musteri.hesaplar.get(0)).getVadesizBakiye()-tutar-8);
                            ((YatirimHesap) musteri.hesaplar.get(i)).setYatirimBakiye(((YatirimHesap)musteri.hesaplar.get(i)).getYatirimBakiye()+(tutar/19));
                            System.out.println("Güncel Vadesiz Bakiye : " + ((VadesizHesap)musteri.hesaplar.get(0)).getVadesizBakiye() +"\t Transfer Ücreti : " + 8);
                            System.out.println("Güncel Vadeli Bakiye : " + ((VadeliHesap)musteri.hesaplar.get(i)).getVadeliBakiye());
                        }

                        // Eğer vadesiz hesap normak bir hesap ise çalışır.
                        else if(((VadesizHesap)musteri.hesaplar.get(0)).getHesapBilgisi().equals("2"))
                        {
                            // Vadesiz hesap bakiyesinden transfer edilmek istenen para kesilir.
                            // Transfer ücreti yoktur.
                            // Yatırım hesap bakiyesine ise transfer edilmek istenen para kura göre hesaplanarak aktarılır.
                            System.out.println("Para transferi başarılı.");
                            ((VadesizHesap) musteri.hesaplar.get(0)).setVadesizBakiye(((VadesizHesap)musteri.hesaplar.get(0)).getVadesizBakiye()-tutar);
                            ((YatirimHesap) musteri.hesaplar.get(i)).setYatirimBakiye(((YatirimHesap)musteri.hesaplar.get(i)).getYatirimBakiye()+(tutar/19));
                            System.out.println("Güncel Vadesiz Bakiye : " + ((VadesizHesap)musteri.hesaplar.get(0)).getVadesizBakiye() +"\t Transfer Ücreti : " + 0);
                            System.out.println("Güncel Yatırım Bakiye : " + ((YatirimHesap)musteri.hesaplar.get(i)).getYatirimBakiye());

                        }
                    }
                }
            }
        }
        // Eğer vadesiz hesap bakiyesi transfer edilmek istenen paradan daha küçükse ekrana bir uyarı yazdırılır.
        else
            System.out.println("Bakiye yetersiz");

    }

    public void krediKartiBorcOdeme(Musteri musteri,double vadesizBakiye, double guncelBorc)
    {
        Scanner scanner= new Scanner(System.in);
        vadesizBakiye = ((VadesizHesap)musteri.hesaplar.get(0)).getVadesizBakiye();

        // Ekrana kredi kartları numaraları ile birlikte yazdırılır.
        System.out.println("Kredi Kartları :");
        for(int i=0;i<musteri.krediKartlari.size();i++)
        {
            System.out.println("Kart No : " +musteri.krediKartlari.get(i).getKartNumarasi());
        }

        // Kullanıcıdan ödeme yapmak istediği kredi kartı numarasını girmesi istenir.
        System.out.println("Ödeme yapmak istediğiniz kredi kartı numarasını giriniz");
        int kartNo = scanner.nextInt();

        int kontrol=0;
        for(int i=0;i<musteri.krediKartlari.size();i++)
        {
            guncelBorc =  musteri.krediKartlari.get(i).getGuncelBorc();

            // Eğer kullanıcının girdiği kredi kartı numarası müşterinin sahip olduğu herhangi bir kredi kartının numarasına eşit ise çalışır.
            if(kartNo==musteri.krediKartlari.get(i).getKartNumarasi())
            {
                // Ekrana vadesiz hesap bakiyesi ve kredi kartı borcu yazdırılır.
                System.out.println("Bakiye : " + vadesizBakiye + " \t " + "Borç : " + guncelBorc);
                // Kullanıcıya borcun ne kadarını ödemek istediği sorulur.
                System.out.println("Ödeme yapmak istedğiniz miktar : ");
                int odeme = scanner.nextInt();

                // Eğer kullanıcının ödemek istediği tutar vadesiz bakiyeden ve güncel borçdan küçük ise çalışır.
                if((vadesizBakiye>=odeme)&& (guncelBorc>=odeme) && (odeme>0))
                {
                    // Vadesiz hesap bakiyesinden ödenmek istenen tutar düşülür.
                    // Kredi kartının borcundan ödenmek istenen tutar düşülür.
                    ((VadesizHesap)musteri.hesaplar.get(0)).setVadesizBakiye(getVadesizBakiye()-odeme);
                    musteri.krediKartlari.get(i).setGuncelBorc(guncelBorc-odeme);
                    System.out.println("Kredi ödemesi başarıyla gerçekleşmiştir.");
                    System.out.println("Güncel Borç : " + musteri.krediKartlari.get(i).getGuncelBorc() + "TL");
                    break;
                }
                // Eğer vadesiz hesapda ödenmek istendiği kadar para yoksa ya da ödenmek istenen tutar güncel borçdan daha fazla ise ekrana uyarı yazdırılır.
                else
                    System.out.println("Ödeme yapılamadı");
            }
            else
                kontrol++;

            // Eğer kullanıcının girdiği kart numarası müşterinin sahip olduğu kredi kartlarından hiçbirinin numarası ile eşleşmez ise ekrana uyarı yazdırılır.
            if(kontrol==musteri.krediKartlari.size())
                System.out.println("Kredi kartı bulunamadı");

        }
    }

    public void krediOdeme(Musteri musteri)
    {
        double krediMiktari = musteri.getKrediler().getKrediMiktari();
        double bakiye = ((VadesizHesap)musteri.hesaplar.get(0)).vadesizBakiye;

        // Müşteriye kredinin ne kadarını ödemek istediği sorulur.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ödenecek tutarı giriniz : ");
        double odeme = scanner.nextDouble();

        // Eğer ödenmek istenen tutar müşterinin vadesiz hesap bakiyesinden ve kredi miktarından küçük ise çalışır.
        if((odeme>0) && (odeme<krediMiktari) && (odeme<bakiye))
        {
            // Vadesiz hesap bakiyesinden ödenmek istenen tutar düşülür.
            // Kredi miktarından ödenmek istenen tutar düşülür.
            musteri.getKrediler().setKrediMiktari(krediMiktari-odeme);
            ((VadesizHesap)musteri.hesaplar.get(0)).setVadesizBakiye(getVadesizBakiye()-odeme);
            System.out.println("Kalan kredi : " + musteri.getKrediler().getKrediMiktari());
        }
        // Eğer vadesiz hesapda ödenmek istendiği kadar para yoksa ya da ödenmek istenen tutar kredi miktarından daha fazla ise ekrana uyarı yazdırılır
        else
            System.out.println("Yetersiz Bakiye");
    }


    @Override
    public String toString() {
        return "IBAN : " + getIban() +
                "\t Açılış Tarihi : " + getHesapAcilisTarihi() +
                "\t Hesap Bilgisi : " + getHesapBilgisi() +
                "\t Hesap Türü : " + getHesapTuru() +
                "\t Vadesiz Bakiye : " + getVadesizBakiye()+"TL";
    }
    @Override
    public String getHesapTuru() {return hesapTuru;}

    @Override
    public void setHesapTuru(String hesapTuru) {this.hesapTuru = hesapTuru;}

    public double getVadesizBakiye() {return vadesizBakiye;}

    public void setVadesizBakiye(double vadesizBakiye) {this.vadesizBakiye = vadesizBakiye;}
}
