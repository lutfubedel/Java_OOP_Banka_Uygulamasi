import java.util.Date;

public class BankaHesap
{
    private long iban;
    private Date hesapAcilisTarihi;
    private double toplamBakiye;
    private String hesapBilgisi;
    private double islemMiktari;
    private String hesapTuru;

    public BankaHesap(long iban, double toplamBakiye, String hesapBilgisi) {
        this.iban = iban;
        this.toplamBakiye = toplamBakiye;
        this.hesapBilgisi = hesapBilgisi;
    }

    public void hesapGoruntuleme(Musteri musteri)
    {
        // Müşterinin sahip olduğu tüm hesapların türleri ve bakiyelerini ekrana yazdırır.
        for(int i=0;i<musteri.hesaplar.size();i++)
        {
            if(musteri.hesaplar.get(i) instanceof VadesizHesap)
                System.out.println("Hesap Türü : " + (musteri.hesaplar.get(i)).getHesapTuru() +"\t Bakiye : " + ((VadesizHesap)musteri.hesaplar.get(i)).getVadesizBakiye());
            else if(musteri.hesaplar.get(i) instanceof VadeliHesap)
                System.out.println("Hesap Türü : " + (musteri.hesaplar.get(i)).getHesapTuru() +"\t Bakiye : " + ((VadeliHesap)musteri.hesaplar.get(i)).getVadeliBakiye());
            if(musteri.hesaplar.get(i) instanceof YatirimHesap)
                System.out.println("Hesap Türü : " + (musteri.hesaplar.get(i)).getHesapTuru() +"\t Bakiye : " + ((YatirimHesap)musteri.hesaplar.get(i)).getYatirimBakiye());
        }
    }


    @Override
    public String toString() {
        return "BankaHesap{" +
                "iban=" + iban +
                ", hesapAcilisTarihi=" + hesapAcilisTarihi +
                ", toplamBakiye=" + toplamBakiye +
                ", hesapBilgisi='" + hesapBilgisi + '\'' +
                ", islemMiktari=" + islemMiktari +
                ", hesapTuru='" + hesapTuru + '\'' +
                '}';
    }

    public long getIban() {return iban;}

    public void setIban(long iban) {this.iban = iban;}

    public Date getHesapAcilisTarihi() {return hesapAcilisTarihi;}

    public void setHesapAcilisTarihi(Date hesapAcilisTarihi) {this.hesapAcilisTarihi = hesapAcilisTarihi;}

    public double getToplamBakiye() {return toplamBakiye;}

    public void setToplamBakiye(double toplamBakiye) {this.toplamBakiye = toplamBakiye;}

    public String getHesapBilgisi() {return hesapBilgisi;}

    public void setHesapBilgisi(String hesapBilgisi) {this.hesapBilgisi = hesapBilgisi;}

    public double getIslemMiktari() {return islemMiktari;}

    public void setIslemMiktari(double islemMiktari) {this.islemMiktari = islemMiktari;}

    public String getHesapTuru() {return hesapTuru;}

    public void setHesapTuru(String hesapTuru) {this.hesapTuru = hesapTuru;}
}
