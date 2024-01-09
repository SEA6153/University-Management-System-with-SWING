package tr.com.bilgeadam.universite.yonetim.sistemi.bl;

import java.util.List;

import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Readable;
import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Updatable;
import tr.com.bilgeadam.universite.yonetim.sistemi.dal.DersKayitYonetimi;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Ders;

public class DersBLL implements Readable<Ders>, Updatable<Ders> {
	Readable<Ders> dal = DersKayitYonetimi.getInstance();
	Updatable<Ders> update = DersKayitYonetimi.getInstance();

	@Override
	public void Ekle(Ders ders) {
		update.Ekle(ders);

	}

	@Override
	public void guncelle(Ders ders) {
		update.guncelle(ders);

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
	public List<Ders> hepsiniOku() {

		return dal.hepsiniOku();
	}

	@Override
	public Ders emailIleBul(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ders idIleBul(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
