package tr.com.bilgeadam.universite.yonetim.sistemi.model;

public class Ders {

	private int id;
	private String ad;
	private byte sinifNo;
	private byte kredi;
	private boolean devamZorunluluguVarMi;
	private double gecmeNotu;
	
	

	
	public double getGecmeNotu() {
		return gecmeNotu;
	}
	public void setGecmeNotu(double geçmeNotu) {
		this.gecmeNotu = geçmeNotu;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public byte getSinifNo() {
		return sinifNo;
	}
	public void setSinifNo(byte sinifNo) {
		this.sinifNo = sinifNo;
	}
	public byte getKredi() {
		return kredi;
	}
	public void setKredi(byte kredi) {
		this.kredi = kredi;
	}
	public boolean isDevamZorunluluguVarMi() {
		return devamZorunluluguVarMi;
	}
	public void setDevamZorunluluguVarMi(boolean devamZorunluluguVarMi) {
		this.devamZorunluluguVarMi = devamZorunluluguVarMi;
	}
	
	
}
