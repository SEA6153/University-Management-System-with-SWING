package tr.com.bilgeadam.universite.yonetim.sistemi.model;

public class SinavNotu extends Ders{
	
	private Ogrenci ogrenci;
	private Ders ders;
	private byte sınavNotu;
	private byte birinceVizeNotu;
	private byte ikinciVizeNotu;
	private byte finalNotu;
	
	public byte getBirinceVizeNotu() {
		return birinceVizeNotu;
	}
	public void setBirinceVizeNotu(byte birinceVizeNotu) {
		this.birinceVizeNotu = birinceVizeNotu;
	}
	public byte getIkinciVizeNotu() {
		return ikinciVizeNotu;
	}
	public void setIkinciVizeNotu(byte ikinciVizeNotu) {
		this.ikinciVizeNotu = ikinciVizeNotu;
	}
	public byte getFinalNotu() {
		return finalNotu;
	}
	public void setFinalNotu(byte finalNotu) {
		this.finalNotu = finalNotu;
	}
	public Ogrenci getOgrenci() {
		return ogrenci;
	}
	public void setOgrenci(Ogrenci ogrenci) {
		this.ogrenci = ogrenci;
	}
	public Ders getDers() {
		return ders;
	}
	public void setDers(Ders ders) {
		this.ders = ders;
	}
	public byte getSınavNotu() {
		return sınavNotu;
	}
	public void setSınavNotu(byte sınavNotu) {
		this.sınavNotu = sınavNotu;
	}
	
	
	
	
}
