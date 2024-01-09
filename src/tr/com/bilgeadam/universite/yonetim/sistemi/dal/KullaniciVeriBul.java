package tr.com.bilgeadam.universite.yonetim.sistemi.dal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Readable;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Brans;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Kullanici;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Rol;

public class KullaniciVeriBul implements Readable<Kullanici> {
	public static final String KULLANICILAR_DOSYASI = "kullanicilar.csv";

	// singleton design pattern: Bir sınıfın tek bir instance veya nesnesi oluşması
	// işimi görüyorsa
	// veya bir sınıftan tek bir nesne oluşmasını mecbur kılmak istiyorsam

	// singleton 1. adım sınıfın kendi ismiyle static bir değişken oluştur.
	private static KullaniciVeriBul kullaniciVeriBul;

	// 2. adım sınııfın default constructorını private yap
	// bunu yaptıktan sonra başka bir constructor oluşturulmaması lazım.
	private KullaniciVeriBul() {

	}

	// 3. adım static olarak yazılan getInstance metoduyla bu sınıftan
	// sadece 1 adet nesne oluşturulabilmesi sağlanır.
	public static KullaniciVeriBul getInstance() {
		if (kullaniciVeriBul == null) {
			kullaniciVeriBul = new KullaniciVeriBul();
		}
		return kullaniciVeriBul;

	}

	@Override
	public List<Kullanici> hepsiniOku() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kullanici emailIleBul(String email) {
		File kullanicilarDosyasi = new File(KULLANICILAR_DOSYASI);
		Kullanici arananKullanici = null;

		Scanner reader;
		try {
			reader = new Scanner(kullanicilarDosyasi);
			while (reader.hasNextLine()) {

				String satir = reader.nextLine();

				String[] veriler = satir.split(";");
				if (email.equals(veriler[0])) {

					List<Rol> roller = new ArrayList<>();
					List<Brans> branslar = new ArrayList<>();
					String rol = veriler[2];
					roller.add(Rol.valueOf(rol));

					arananKullanici = Kullanici.getInstance(email, Long.valueOf(veriler[1]), true, null, roller, branslar);

					break;

				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arananKullanici;

	}

	@Override
	public Kullanici idIleBul(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
