package tr.com.bilgeadam.universite.yonetim.sistemi.dal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Readable;
import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Updatable;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Ders;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Ogrenci;

public class DersKayitYonetimi implements Readable<Ders>, Updatable<Ders> {

	public static final String DERSLER_DOSYA_ADI = "dersler.csv";

	private static DersKayitYonetimi dersDAL;

	private DersKayitYonetimi() {

	}

	public static DersKayitYonetimi getInstance() {
		if (dersDAL == null) {

			dersDAL = new DersKayitYonetimi();

		}

		return dersDAL;

	}

	@Override
	public void Ekle(Ders ders) {
		SayacYonetim sayac = SayacYonetim.getInstance();
		try {

			FileWriter fileWriter = new FileWriter(DERSLER_DOSYA_ADI, true);

			String csvDers = sayac.sonIdVer(SayacTuru.DERS) + ";" + ders.getAd() + ";" + ders.getSinifNo() + ";"
					+ ders.getKredi() + ";" + ders.isDevamZorunluluguVarMi() + ";" + ders.getGecmeNotu();

			fileWriter.write(csvDers + "\n");
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	@Override
	public void guncelle(Ders guncellenecekDers) {

		List<Ders> liste = hepsiniOku();

		for (Ders ders : liste) {

			if (ders.getId() == guncellenecekDers.getId()) {

				ders.setAd(guncellenecekDers.getAd());
				ders.setSinifNo(guncellenecekDers.getSinifNo());
				ders.setKredi(guncellenecekDers.getKredi());
				ders.setDevamZorunluluguVarMi(guncellenecekDers.isDevamZorunluluguVarMi());
				ders.setGecmeNotu(guncellenecekDers.getGecmeNotu());
				break;
			}

		}

		try {
			FileWriter fileWriter = new FileWriter(DERSLER_DOSYA_ADI);

			for (Ders ders : liste) {
				String csvDers = ders.getAd() + ";" + ders.getSinifNo() + ";" + ders.getKredi() + ";"
						+ ders.isDevamZorunluluguVarMi() + ";" + ders.getGecmeNotu();

				fileWriter.write(csvDers);
				fileWriter.write("\n");
			}

			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void tcKimlikNoSil(long tcKimlikNo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sil(int id) {
		List<Ders> liste = hepsiniOku();

		for (Ders ders : liste) {

			if (ders.getId() == id) {
				liste.remove(ders);
				break;
			}

		}

		try {
			FileWriter fileWriter = new FileWriter(DERSLER_DOSYA_ADI);

			for (Ders ders : liste) {
				String csvDers = ders.getAd() + ";" + ders.getSinifNo() + ";" + ders.getKredi() + ";"
						+ ders.isDevamZorunluluguVarMi() + ";" + ders.getGecmeNotu();

				fileWriter.write(csvDers);
				fileWriter.write("\n");
			}

			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Ders> hepsiniOku() {
		File derslerDosyasi = new File(DERSLER_DOSYA_ADI);
		List<Ders> liste = new ArrayList<>();

		Scanner reader;
		try {
			reader = new Scanner(derslerDosyasi);
			while (reader.hasNextLine()) {
				String satir = reader.nextLine();

				String[] veriler = satir.split(";");

				Ders ders = new Ders();
				ders.setId(Integer.valueOf(veriler[0]));
				ders.setAd(veriler[1]);
				ders.setSinifNo(Byte.valueOf(veriler[2]));
				ders.setKredi(Byte.valueOf(veriler[3]));
				ders.setDevamZorunluluguVarMi(Boolean.valueOf(veriler[4]));
				ders.setGecmeNotu(Double.valueOf(veriler[5]));

				liste.add(ders);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liste;
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
