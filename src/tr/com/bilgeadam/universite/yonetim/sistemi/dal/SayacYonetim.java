package tr.com.bilgeadam.universite.yonetim.sistemi.dal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// mutable class
class Sayac {
	private SayacTuru sayacTuru;
	private long sayacDegeri;

	Sayac(SayacTuru sayacTuru, long sayacDegeri) {
		this.sayacTuru = sayacTuru;
		this.sayacDegeri = sayacDegeri;
	}

	public SayacTuru getSayacTuru() {
		return sayacTuru;
	}

	public long getSayacDegeri() {
		return sayacDegeri;
	}

	public void setSayacDegeri(long sayacDegeri) {
		this.sayacDegeri = sayacDegeri;
	}

}

enum SayacTuru {
	OGRENCI, EGITMEN, MEMUR, KULLANICI, DERS
}

final class SayacYonetim {
	
//	public static void main(String[] args) {
//		SayacYonetim yonetim = new SayacYonetim();
//		long id = yonetim.sonIdVer(SayacTuru.OGRENCI);
//		yonetim.idArttir(SayacTuru.OGRENCI);
//	}

	private static final String SAYAC_DOSYA_ADI = "idSayaclari.csv";

	// singleton dp - begin
	private static SayacYonetim sayac;

	private SayacYonetim() {
	}

	static SayacYonetim getInstance() {

		if (sayac == null) {
			sayac = new SayacYonetim();
		}

		return sayac;
	}
	// singleton dp - end

	long sonIdVer(SayacTuru sayacTuru) {
		long id = 0;

		File personellerDosyasi = new File(SAYAC_DOSYA_ADI);

		try {
			Scanner reader = new Scanner(personellerDosyasi);

			while (reader.hasNextLine()) {
				String satir = reader.nextLine();

				String[] veriler = satir.split(";");

				String strSayacTuru = veriler[0];
				String strId = veriler[1];

				if (SayacTuru.valueOf(strSayacTuru) == sayacTuru) {
					id = Long.valueOf(strId);
					idArttir(sayacTuru);
					break;
				}
			}
			reader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}

	private List<Sayac> sayacOku() {
		File personellerDosyasi = new File(SAYAC_DOSYA_ADI);
		List<Sayac> liste = new ArrayList<>();

		try {
			Scanner reader = new Scanner(personellerDosyasi);

			while (reader.hasNextLine()) {
				String satir = reader.nextLine();

				String[] veriler = satir.split(";");

				SayacTuru dosyaSayacTuru = SayacTuru.valueOf(veriler[0]);
				long id = Long.valueOf(veriler[1]);

				Sayac sayac = new Sayac(dosyaSayacTuru, id);

				liste.add(sayac);
			}
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return liste;
	}

	private void idArttir(SayacTuru sayacTuru) {

		List<Sayac> liste = sayacOku();

		Sayac guncellenecekSayac = null;

		for (Sayac sayac : liste) {

			if (sayacTuru == sayac.getSayacTuru()) {
				sayac.setSayacDegeri(sayac.getSayacDegeri() + 1); // sayaç değeri 1 arttırılıyor
				break;
			}

		}

		try {
			FileWriter dosyaYazici = new FileWriter(SAYAC_DOSYA_ADI);

			for (Sayac sayac : liste) {
				dosyaYazici.write(sayac.getSayacTuru() + ";" + sayac.getSayacDegeri());
				dosyaYazici.write("\n");
			}

			dosyaYazici.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
