package tr.com.bilgeadam.universite.yonetim.sistemi.bl;

import java.util.List;

import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Readable;
import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Updatable;
import tr.com.bilgeadam.universite.yonetim.sistemi.dal.OgrenciVeriYonetimi;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Ogrenci;

public class OgrenciBLL implements Readable<Ogrenci>, Updatable<Ogrenci> {

	Readable<Ogrenci> dal = OgrenciVeriYonetimi.getInstance();
	Updatable<Ogrenci> update = OgrenciVeriYonetimi.getInstance();
 	@Override
	public List<Ogrenci> hepsiniOku() {

		return dal.hepsiniOku();
	}

	@Override
	public Ogrenci emailIleBul(String email) {

		return null;
	}

	@Override
	public Ogrenci idIleBul(int id) {

		return null;
	}

	@Override
	public void Ekle(Ogrenci ogrenci) {
		update.Ekle(ogrenci);
	}

	@Override
	public void guncelle(Ogrenci ogrenci) {
		update.guncelle(ogrenci);
	}

	@Override
	public void sil(int id) {
		update.sil(id);
		
	}

	@Override
	public void tcKimlikNoSil(long tcKimlikNo) {
		// TODO Auto-generated method stub
		
	}

}
