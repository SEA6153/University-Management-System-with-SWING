package tr.com.bilgeadam.universite.yonetim.sistemi.bl;

import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Readable;
import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Updatable;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Ders;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Egitmen;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Kullanici;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Ogrenci;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.SinavNotu;

public class bllFactory {

	public static Readable<Kullanici> getKullaniciBul() {
		return new KullaniciBul();
	}

	public static Readable<Ogrenci> getOgrenciBLL() {
		return new OgrenciBLL();
	}

	public static Updatable<Ogrenci> getOgrenciBLLUpdatable() {
		return new OgrenciBLL();
	}

	public static Readable<Egitmen> getEgitmenBLL() {
		return new EgitmenBLL();
	}

	public static Updatable<Egitmen> getEgitmenUpdatable() {
		return new EgitmenBLL();
	}

	public static Readable<Ders> getDersBLL() {
		return new DersBLL();
	}

	public static Updatable<Ders> getDersUpdatable() {
		return new DersBLL();
	}
	
	public static Readable<SinavNotu> getSinavNotuBLL() {
		return new SinavNotuBLL();
	}
	
	public static Updatable<SinavNotu> getSinavNotuUpdate(){
		return new SinavNotuBLL();
	}

}
