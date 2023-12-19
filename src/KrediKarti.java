import java.util.Random;
import java.util.Scanner;

public class KrediKarti
{
    private int kartNumarasi;
    private double limit;
    private double guncelBorc;
    private int kullanilabilirLimit;

    Random rand = new Random();
    Scanner scanner = new Scanner(System.in);

    public KrediKarti(int kartNumarasi, double limit, double guncelBorc, int kullanilabilirLimit)
    {
        this.kartNumarasi = kartNumarasi;
        this.limit = limit;
        this.guncelBorc = guncelBorc;
        this.kullanilabilirLimit=kullanilabilirLimit;
    }

    public void krediKartiEkle(Musteri musteri)
    {
        // 100.000 - 1.000.000 arasında bir kart numarası oluşturur ve bu kart numarası ile müşterinin krediKartlari adlı arrayliste yeni bir kredi kartı ekler.
        kartNumarasi = rand.nextInt(900000)+100000;
        musteri.krediKartlari.add(new KrediKarti(kartNumarasi,6500,2500,4000));
        System.out.println("Kredi kartınız eklenmiştir.");
    }

    public void krediKartiSil(Musteri musteri)
    {
        // Ekrana müşterinin sahip olduğu tüm kredi kartlarını numaraları ile birlikte yazdırır.
        for (int i=0;i<musteri.krediKartlari.size();i++)
        {
            System.out.println("Kart " + (i+1) + " : " + musteri.krediKartlari.get(i).getKartNumarasi());
        }

        // Kullanıcıya silmek istediği kart numarasını sorar.
        System.out.println("Lütfen silmek istediğiniz kredi kartı numarasını giriniz.");
        int kartNo = scanner.nextInt();

        for(int i=0;i<musteri.krediKartlari.size();i++)
        {
            // Eğer kullanıcının girdiği kart numarası müşterinin sahip olduğu kredilerden herhangi birinin kart numarasına eşit ise çalışır.
            if(kartNo == musteri.krediKartlari.get(i).kartNumarasi)
            {
                // Eğer kredi kartının borcu varsa ekrana bir uyarı yazdırılır.
                if(musteri.krediKartlari.get(i).guncelBorc>0)
                {
                    System.out.println("Lütfen öncelikle borç ödemesi yapınız!!!");
                }
                // Eğer kredi kartının bir borcu yoksa kredi kartı silinir.
                else
                {
                    System.out.println(musteri.krediKartlari.get(i).getKartNumarasi() + " nolu kredi kartı silindi.");
                    musteri.krediKartlari.remove(i);
                }

            }
        }

    }

    public void kullanilabilirLimit(Musteri musteri)
    {
        // Müşterinin sahip oldğu tüm kredi kartları kulllanılabilir limitleri ile ekrana yazdırılır.
        System.out.println("Kredi kartlarınızın kullanılabilir limitleri : \n -----------------------------------");
        for(int i=0;i<musteri.krediKartlari.size();i++)
        {
            System.out.println("Kart " +(i+1)+" : "+musteri.krediKartlari.get(i).getKullanilabilirLimit());

        }
    }

    @Override
    public String toString() {
        return "Kart No : " + getKartNumarasi() +
                "\t Limit : " +getLimit() +
                "\t Güncel Borç : " + getGuncelBorc() +
                "\t Kullanılabilir Limit : " + getKullanilabilirLimit();
    }

    public int getKartNumarasi() {return kartNumarasi;}

    public void setKartNumarasi(int kartNumarasi) {this.kartNumarasi = kartNumarasi;}

    public double getLimit() {return limit;}

    public void setLimit(double limit) {this.limit = limit;}

    public double getGuncelBorc() {return guncelBorc;}

    public void setGuncelBorc(double guncelBorc) {this.guncelBorc = guncelBorc;}

    public int getKullanilabilirLimit() {return kullanilabilirLimit;}
    public void setKullanilabilirLimit(int kullanilabilirLimit) {this.kullanilabilirLimit = kullanilabilirLimit;}
}
