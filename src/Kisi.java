public class Kisi
{
    private long tcKimlikNo;
    private String ad;
    private String soyad;
    private String email;
    private long telefonNumarasi;

    public Kisi(long tcKimlikNo, String ad, String soyad, String email, long telefonNumarasi)
    {
        this.tcKimlikNo = tcKimlikNo;
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.telefonNumarasi = telefonNumarasi;
    }

    @Override
    public String toString() {
        return "Kisi{" +
                "tcKimlikNo=" + tcKimlikNo +
                ", ad='" + ad + '\'' +
                ", soyad='" + soyad + '\'' +
                ", email='" + email + '\'' +
                ", telefonNumarasi=" + telefonNumarasi +
                '}';
    }

    public long getTcKimlikNo() {return tcKimlikNo;}
    public void setTcKimlikNo(long tcKimlikNo) {this.tcKimlikNo = tcKimlikNo;}

    public String getAd() {return ad;}
    public void setAd(String ad) {this.ad = ad;}

    public String getSoyad() {return soyad;}
    public void setSoyad(String soyad) {this.soyad = soyad;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public long getTelefonNumarasi() {return telefonNumarasi;}
    public void setTelefonNumarasi(long telefonNumarasi) {this.telefonNumarasi = telefonNumarasi;}

}
