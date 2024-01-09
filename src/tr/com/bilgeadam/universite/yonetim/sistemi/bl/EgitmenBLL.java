package tr.com.bilgeadam.universite.yonetim.sistemi.bl;

import java.util.List;

import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Readable;
import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Updatable;
import tr.com.bilgeadam.universite.yonetim.sistemi.dal.EgitmenVeriYonetimi;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Egitmen;

public class EgitmenBLL implements Readable<Egitmen>, Updatable<Egitmen>{
	Readable<Egitmen> dal = EgitmenVeriYonetimi.getInstance();
	Updatable<Egitmen> update = EgitmenVeriYonetimi.getInstance();
	
	
	@Override
	public void Ekle(Egitmen egitmen) {
		update.Ekle(egitmen);
	}

	@Override
	public void guncelle(Egitmen egitmen) {
		update.guncelle(egitmen);
		
	}

	@Override
	public void tcKimlikNoSil(long tcKimlikNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sil(int id) {
		update.sil(id);
		
	}

	@Override
	public List<Egitmen> hepsiniOku() {
		return dal.hepsiniOku();
		
	}

	@Override
	public Egitmen emailIleBul(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Egitmen idIleBul(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
