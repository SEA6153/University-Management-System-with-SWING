package tr.com.bilgeadam.universite.yonetim.sistemi.bl;


import java.util.List;

import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Readable;
import tr.com.bilgeadam.universite.yonetim.sistemi.dal.KullaniciVeriBul;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Kullanici;

public class KullaniciBul implements Readable<Kullanici>{

	Readable veriBul = KullaniciVeriBul.getInstance();
	
	
	
	
	@Override
	public List<Kullanici> hepsiniOku() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kullanici emailIleBul(String email) {
		Kullanici kullanici = (Kullanici)veriBul.emailIleBul(email);
		return kullanici; 
	}

	@Override
	public Kullanici idIleBul(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
}
