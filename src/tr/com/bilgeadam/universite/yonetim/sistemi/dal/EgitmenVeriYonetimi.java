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
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Brans;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Egitmen;

public class EgitmenVeriYonetimi implements Readable<Egitmen>, Updatable<Egitmen> {
	public static final String EGITMENLER_DOSYA_ADI = "egitmenler.csv";

	private static EgitmenVeriYonetimi dal;

	private EgitmenVeriYonetimi() {
	}

	public static EgitmenVeriYonetimi getInstance() {
		if (dal == null) {
			dal = new EgitmenVeriYonetimi();
		}
		return dal;
	}

	@Override
	public void Ekle(Egitmen egitmen) {
		SayacYonetim sayac = SayacYonetim.getInstance();

		try {
			FileWriter fileWriter = new FileWriter(EGITMENLER_DOSYA_ADI, true);

			String csvEgitmen = sayac.sonIdVer(SayacTuru.EGITMEN) + ";" + egitmen.getTcKimlikNo() + ";"
					+ egitmen.getAd() + ";" + egitmen.getSoyad() + ";" + egitmen.getEmail() + ";" + egitmen.getBrans();

			fileWriter.write(csvEgitmen + "\n");
			fileWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void guncelle(Egitmen guncellenecekEgitmen) {
		List<Egitmen> liste = hepsiniOku();

		for (Egitmen egitmen : liste) {

			if (egitmen.getId() == guncellenecekEgitmen.getId()) {
				egitmen.setAd(guncellenecekEgitmen.getAd().toUpperCase());
				egitmen.setSoyad(guncellenecekEgitmen.getSoyad().toUpperCase());
				egitmen.setEmail(guncellenecekEgitmen.getEmail());
				egitmen.setBrans(guncellenecekEgitmen.getBrans());
				egitmen.setTcKimlikNo(guncellenecekEgitmen.getTcKimlikNo());
				break;

			}

		}

		
		try {
			
			FileWriter fileWriter = new FileWriter(EGITMENLER_DOSYA_ADI);
			
			
			for (Egitmen egitmen : liste) {
				
				String csvEgitmen = egitmen.getId() + ";" + egitmen.getTcKimlikNo() + ";" + egitmen.getAd() + ";"
						+ egitmen.getSoyad() + ";" + egitmen.getEmail() + ";" + egitmen.getBrans();
				
				fileWriter.write(csvEgitmen + "\n");
				
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
		List<Egitmen> liste = hepsiniOku();

		for (Egitmen egitmen : liste) {

			if (egitmen.getId() == id) {
				liste.remove(egitmen);
				break;

			}

		}

		try {
			FileWriter fileWriter = new FileWriter(EGITMENLER_DOSYA_ADI);

			for (Egitmen egitmen : liste) {

				String csvEgitmen = egitmen.getId() + ";" + egitmen.getTcKimlikNo() + ";" + egitmen.getAd() + ";"
						+ egitmen.getSoyad() + ";" + egitmen.getEmail() + ";" + egitmen.getBrans();
				fileWriter.write(csvEgitmen + "\n");

			}
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Egitmen> hepsiniOku() {
		File egitmenlerDosyasi = new File(EGITMENLER_DOSYA_ADI);
		List<Egitmen> liste = new ArrayList<>();

		try {
			Scanner reader = new Scanner(egitmenlerDosyasi);
			while (reader.hasNextLine()) {
				String satir = reader.nextLine();

				String[] veriler = satir.split(";");

				Egitmen egitmen = new Egitmen();
				egitmen.setId(Integer.valueOf(veriler[0]));
				egitmen.setTcKimlikNo(Long.valueOf(veriler[1]));
				egitmen.setAd(veriler[2]);
				egitmen.setSoyad(veriler[3]);
				egitmen.setEmail(veriler[4]);
				egitmen.setBrans(Brans.valueOf(veriler[5]));
				liste.add(egitmen);

			}
			reader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liste;
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
