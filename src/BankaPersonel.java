import java.util.ArrayList;

public class BankaPersonel extends Kisi
{
    private int personelID;
    ArrayList<Musteri> musteriler = new ArrayList<>();

    public BankaPersonel(long tcKimlikNo, String ad, String soyad, String email, long telefonNumarasi)
    {
        super(tcKimlikNo, ad, soyad, email, telefonNumarasi);
    }


    @Override
    public String toString() {
        return "BankaPersonel{" +
                "personelID=" + personelID +
                ", musteri=" + musteriler +
                '}';
    }

    public int getPersonelID() {return personelID;}
    public void setPersonelID(int personelID) {this.personelID = personelID;}

    public ArrayList<Musteri> getMusteri() {return musteriler;}
    public void setMusteri(ArrayList<Musteri> musteri) {this.musteriler = musteri;}
}
