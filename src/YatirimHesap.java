import java.util.Date;
import java.util.Scanner;

public class YatirimHesap extends BankaHesap
{
    private String  hesapTuru;
    private int yatirimBakiye;
    private String yatirimTuru;
    private float kur;


    public YatirimHesap(long iban, double toplamBakiye, String hesapBilgisi, String hesapTuru, int yatirimBakiye, String yatirimTuru, float kur)
    {
        super(iban, toplamBakiye, hesapBilgisi);
        this.hesapTuru = hesapTuru;
        this.yatirimBakiye = yatirimBakiye;
        this.yatirimTuru = yatirimTuru;
        this.kur = kur;

        // Hesabın açılış tarihini şu an olarak ayarlar.
        setHesapAcilisTarihi(new Date());
    }

    public void paraEkle(Musteri musteri,int yatirimBakiye, String yatirimTuru, float kur)
    {
        Scanner scanner = new Scanner(System.in);
        int kontrol=0;
        for(int i=0;i<musteri.hesaplar.size();i++)
        {
            // Müşterinin sahip olduğu yatırım hesabına ulaşılır.
            if(musteri.hesaplar.get(i) instanceof YatirimHesap)
            {
                // Müşteriye hesaba ne kadar para yüklemek istediği sorulur.
                System.out.println("Hesabınıza ne kadar dolar yüklemek istiyorsunuz : ");
                int tutar = scanner.nextInt();

                // Eğer müşterinin vadesiz hesabında kullanıcının yüklemek istediği kadar bakiye varsa çalışır.
                if((tutar*kur<((VadesizHesap)musteri.hesaplar.get(0)).getVadesizBakiye()))
                {
                    // Yatırım hesabına aktarılmak istenen ücret aktarılır.
                    // Vadesiz hesapdan aktarılmak istenen ücret kur değeri ile hesaplanarak kesilir.
                    System.out.println("Hesabınıza " + tutar +"$ eklenmiştir.");
                    ((VadesizHesap)musteri.hesaplar.get(0)).setVadesizBakiye(((VadesizHesap)musteri.hesaplar.get(0)).getVadesizBakiye()-(tutar*kur));
                    ((YatirimHesap)musteri.hesaplar.get(i)).setYatirimBakiye(((YatirimHesap)musteri.hesaplar.get(i)).getYatirimBakiye()+tutar);
                    System.out.println("Guncel Yatırım Hesabı Bakiyesi : " + ((YatirimHesap)musteri.hesaplar.get(i)).getYatirimBakiye() + "$");
                }
                // Eğer müşterinin vadesiz hesabında kullanıcının yüklemek istediği kadar bakiye yoksa ekrana bir uyarı yazdırılır.
                else
                    System.out.println("Vadesiz hesabınızda yeterli bakiye bulunmamaktadır.");

            }
            else
                kontrol++;
        }
        // Eğer müşterinin bir yatırım hesabı yoksa ekrana bir uyarı yazdırılır.
        if(kontrol==musteri.hesaplar.size())
            System.out.println("Yatırım hesabınız bulunamadı");
    }

    public void paraBoz(Musteri musteri,int yatirimBakiye, String  yatirimTuru, float kur)
    {
        Scanner scanner = new Scanner(System.in);
        int kontrol=0;
        for(int i=0;i<musteri.hesaplar.size();i++)
        {
            // Müşterinin sahip olduğu yatırım hesabına ulaşılır.
            if(musteri.hesaplar.get(i) instanceof YatirimHesap)
            {
                // Müşteriye ne kadar para bozdurmak istediği sorulur.
                System.out.println("Ne kadar dolar bozdurmak istiyorsunuz  : ");
                int tutar = scanner.nextInt();

                // Eğer müşterinin yatırım hesabında kullanıcının bozdurmak istediği kadar bakiye varsa çalışır.
                if(tutar<((YatirimHesap)musteri.hesaplar.get(i)).getYatirimBakiye())
                {
                    // Yatırım hesabından bozdurulmak istenen ücret kerilir.
                    // Vadesiz hesaba ise bozdurulmak istenen ücret kur değeri ile hesaplanarak aktarılır.
                    System.out.println(tutar + "$ bozduruldu");
                    ((YatirimHesap)musteri.hesaplar.get(i)).setYatirimBakiye(((YatirimHesap)musteri.hesaplar.get(i)).getYatirimBakiye()-tutar);
                    ((VadesizHesap)musteri.hesaplar.get(0)).setVadesizBakiye(((VadesizHesap)musteri.hesaplar.get(0)).getVadesizBakiye()+(tutar*kur));
                    System.out.println("Güncel Yatırım Hesabı Bakiyesi : " + ((YatirimHesap)musteri.hesaplar.get(i)).getYatirimBakiye() + "$");
                }
                // Eğer müşterinin yatırım hesabında kullanıcının bozdurmak istediği kadar bakiye yoksa ekrana bir uyarı yazdırılır.
                else
                    System.out.println("Yatırım hesabınızda yeterli bakiye bulunmamaktadır.");


            }
            else
                kontrol++;
        }
        // Eğer müşterinin bir yatırım hesabı yoksa ekrana bir uyarı yazdırılır.
        if(kontrol==musteri.hesaplar.size())
            System.out.println("Yatırım hesabınız bulunamadı");
    }


    @Override
    public String getHesapTuru() {return hesapTuru;}

    @Override
    public void setHesapTuru(String hesapTuru) {this.hesapTuru = hesapTuru;}

    public int getYatirimBakiye() {return yatirimBakiye;}

    public void setYatirimBakiye(int yatirimBakiye) {this.yatirimBakiye = yatirimBakiye;}

    public String getYatirimTuru() {return yatirimTuru;}

    public void setYatirimTuru(String yatirimTuru) {this.yatirimTuru = yatirimTuru;}

    public float getKur() {return kur;}

    public void setKur(float kur) {this.kur = kur;}

    @Override
    public String toString() {
        return "IBAN : " + getIban() +
                "\t Açılış Tarihi : " + getHesapAcilisTarihi() +
                "\t Hesap Bilgisi : " + getHesapBilgisi() +
                "\t Hesap Türü : " + getHesapTuru() +
                "\t Vadesiz Bakiye : " + getYatirimBakiye()+"$" +
                "\t\t Yatırım Türü : " +getYatirimTuru() +
                "\t\t Kur : " + getKur();
    }
}
