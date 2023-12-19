import java.util.Scanner;

public class Krediler
{
    private int krediID;
    private double krediMiktari;
    private double taksitMiktari;

    public Krediler(Musteri musteri, int krediID, double krediMiktari, double taksitMiktari)
    {
        this.krediID = krediID;
        this.krediMiktari = krediMiktari;
        this.taksitMiktari = taksitMiktari;

        // Eğer müşterinin vadesiz hesabı içerisindeki hesap bilgisi 1 ise yani hesap maaş hesabı ise çalışır.
        if(musteri.hesaplar.get(0).getHesapBilgisi().equals("1"))
        {
            // Müşterinin 1 yıllık kazancı hesaplanır ve müşteriye yıllık kazancının %50 si kadar kredi miktarı verilebilir.
            double maas = 6000;
            krediMiktari =(maas*12)/2;
            setKrediMiktari(krediMiktari);

        }
        // Eğer müşterinin vadesiz hesabı içerisindeki hesap bilgisi 2 ise yani hesap normal bir hesap ise çalışır.
        else if(musteri.hesaplar.get(0).getHesapBilgisi().equals("2"))
        {
            // Müşterinin vadesiz hesap bakiyesi kullanılarak bir gelir belirlenir. Müşterinin yıllık gelirinin %50 si kadar kredi mikarı verilebilir.
            double bakiye = ((VadesizHesap)musteri.hesaplar.get(0)).getVadesizBakiye();
            krediMiktari =(bakiye*12.0)/2.0;
            setKrediMiktari(krediMiktari);
        }
    }

    public void kampanya(Musteri musteri)
    {
        // Ekrana müşterinin sahip olduğu kredi kartları yazdırılır.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kredi Kartları : \n------------------------");
        for(int i=0;i<musteri.krediKartlari.size();i++)
        {
            System.out.println("Kart " + (i+1) + " : " + musteri.krediKartlari.get(i).getKartNumarasi());
        }

        // Daha sonra kullanıcıya hangi kredi kartı için kampanya görmek istediği sorulur.
        System.out.println("Kampanyasını görmek istediğiniz kredi kartı numarasını giriniz ");
        int kartNo = scanner.nextInt();

        for(int i=0;i<musteri.krediKartlari.size();i++)
        {
            // Eğer kullanıcının girdiği kredi kart numarası müşterinin sahip olduğu herhangi bir kredi kartı numarası ile eşleşir ise çalışır.
            if(musteri.krediKartlari.get(i).getKartNumarasi()==kartNo)
            {
                KrediKarti krediKarti = musteri.krediKartlari.get(i);
                // Eğer kredi kartının limiti 5000TL den küçükse ekrana kredi kartının güncel borcu yazdırılır.
                if(krediKarti.getLimit()<5000)
                    System.out.println("Guncel Borç : " + krediKarti.getGuncelBorc());
                // Eğer kredi kartının limiti 10.000TL den büyük ise ekrana müşteriye özel bir kampanya yazdırılır.
                else if(krediKarti.getLimit()>10000)
                    System.out.println("Kampanya : 50.000TL kredi 72 ay taksit ve sadece %0.8 faiz ile");
                // Eğer kredi limiti 5000 - 10.000 arasında ise ekrana herhangi bir kampanya bulunmadığı yazdırılır.
                else
                    System.out.println("Şu anda herhangi bir kampanya bulunmamaktaddır.");
            }
        }

    }

    @Override
    public String toString()
    {
        return  "Kredi ID : " + getKrediID() +
                "\t Kredi Miktarı : " + getKrediMiktari() +
                "\t Taksit Miktarı : " + getTaksitMiktari();
    }

    public int getKrediID() {return krediID;}
    public void setKrediID(int krediID) {this.krediID = krediID;}

    public double getKrediMiktari() {return krediMiktari;}
    public void setKrediMiktari(double krediMiktari) {this.krediMiktari = krediMiktari;}

    public double getTaksitMiktari() {return taksitMiktari;}
    public void setTaksitMiktari(double taksitMiktari) {this.taksitMiktari = taksitMiktari;}
}
