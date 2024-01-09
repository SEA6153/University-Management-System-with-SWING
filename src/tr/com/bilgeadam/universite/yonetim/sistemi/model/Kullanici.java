package tr.com.bilgeadam.universite.yonetim.sistemi.model;

import java.time.LocalDate;
import java.util.List;

public class Kullanici {
	private String kullaniciAdi;
	private long sifre;
	private boolean aktifMi;
	private LocalDate sonGirisZamani;
	private List<Rol> rol;
	private List<Brans> brans;

	private static Kullanici kullanici;

	private Kullanici() {

	}

	public static Kullanici getInstance(String kullaniciAdi, long sifre, boolean aktifMi, LocalDate sonGirisZamani, List<Rol> rol, List<Brans> brans)  {

		if (kullanici == null) {
			kullanici = new Kullanici();
			kullanici.aktifMi = aktifMi;
			kullanici.sifre = sifre;
			kullanici.kullaniciAdi = kullaniciAdi;
			kullanici.sonGirisZamani = sonGirisZamani;
			kullanici.rol = rol;
			kullanici.brans = brans;
		}
	
		return kullanici;

	}

	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public long getSifre() {
		return sifre;
	}

	public boolean isAktifMi() {
		return aktifMi;
	}

	public LocalDate getSonGirisZamani() {
		return sonGirisZamani;
	}

	public List<Rol> getRol() {
		return rol;
	}
	public List<Brans> getBrans(){
		return brans;
	}

}
