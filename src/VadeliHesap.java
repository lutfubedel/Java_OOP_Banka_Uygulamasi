import java.time.LocalDateTime;
import java.util.Date;

public class VadeliHesap extends BankaHesap
{
    private String hesapTuru;
    private int vadeliBakiye;
    private float faizOrani;

    public VadeliHesap(Musteri musteri,long iban, double toplamBakiye, String hesapBilgisi, String hesapTuru, int vadeliBakiye, float faizOrani)
    {
        super(iban, toplamBakiye, hesapBilgisi);
        this.hesapTuru = hesapTuru;
        this.vadeliBakiye = vadeliBakiye;
        this.faizOrani = faizOrani;

        // Hesap açılış tarihini şu an olarak ayarlar.
        setHesapAcilisTarihi(new Date());

        // Eğer müşterinin vadesiz hesabı bir maaş hesabı ise vadeli hesabının faiz oranı %20 olarak ayarlanır.
        if(musteri.hesaplar.get(0).getHesapBilgisi().equals("1"))
            setFaizOrani(20);
        // Eğer müşterinin vadesiz hesabı normal bir hesap ise vadeli hesabının faiz oranı %10 olarak ayarlanır.
        else if(musteri.hesaplar.get(0).getHesapBilgisi().equals("2"))
            setFaizOrani(10);



    }

    @Override
    public String toString() {
        return "IBAN : " +getIban() +
                "\t Açılış Tarihi : " + getHesapAcilisTarihi() +
                "\t Hesap Bilgisi : " + getHesapBilgisi() +
                "\t Hesap Türü : " + getHesapTuru() +
                "\t Vadesiz Bakiye : " + getVadeliBakiye()+"TL" +
                "\t\t Faiz Oranı : " + getFaizOrani();
    }

    @Override
    public String getHesapTuru() {return hesapTuru;}

    @Override
    public void setHesapTuru(String hesapTuru) {this.hesapTuru = hesapTuru;}

    public int getVadeliBakiye() {return vadeliBakiye;}

    public void setVadeliBakiye(int vadeliBakiye) {this.vadeliBakiye = vadeliBakiye;}

    public float getFaizOrani() {return faizOrani;}

    public void setFaizOrani(float faizOrani) {this.faizOrani = faizOrani;}
}
