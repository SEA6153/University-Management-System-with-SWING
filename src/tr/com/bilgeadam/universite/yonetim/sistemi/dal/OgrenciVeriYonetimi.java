package tr.com.bilgeadam.universite.yonetim.sistemi.dal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Readable;
import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Updatable;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Cinsiyet;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Ogrenci;

public class OgrenciVeriYonetimi implements Readable<Ogrenci>, Updatable<Ogrenci> {
	public static final String OGRENCILER_DOSYA_ADI = "ogrenciler.csv";
	// private static int idCounter = 2;
	/// singleton design pattern - begin
	private static OgrenciVeriYonetimi dal;

	private OgrenciVeriYonetimi() {}

	public static OgrenciVeriYonetimi getInstance() {
		if (dal == null) {
			dal = new OgrenciVeriYonetimi();

		}
		return dal;
	}

	/// singleton design pattern - end
	@Override
	public List<Ogrenci> hepsiniOku() {
		File ogrencilerDosyasi = new File(OGRENCILER_DOSYA_ADI);
		List<Ogrenci> liste = new ArrayList<>();

		try {
			Scanner reader = new Scanner(ogrencilerDosyasi);

			while (reader.hasNextLine()) {
				String satir = reader.nextLine();

				String[] veriler = satir.split(";");

				Ogrenci ogrenci = new Ogrenci();
				ogrenci.setId(Integer.valueOf(veriler[0]));
				ogrenci.setTcKimlikNo(Long.valueOf(veriler[1]));
				ogrenci.setAd(veriler[2]);
				ogrenci.setSoyad(veriler[3]);
				ogrenci.setEmail(veriler[4]);
				ogrenci.setOkulNo(Integer.valueOf(veriler[5]));
				if(veriler.length == 8 && !"null".equals(veriler[6])) {ogrenci.setDogumTarihi(LocalDate.parse(veriler[6]));}
				if(veriler.length == 8 && !"null".equals(veriler[7])) {
					ogrenci.setCinsiyet(Cinsiyet.valueOf(veriler[7]));
				}
				
				
				liste.add(ogrenci);

			}
			reader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liste;
	}

	@Override
	public Ogrenci emailIleBul(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ogrenci idIleBul(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Ekle(Ogrenci ogrenci) {
		SayacYonetim sayac = SayacYonetim.getInstance();
		try {

			FileWriter fileWriter = new FileWriter(OGRENCILER_DOSYA_ADI, true);

			String csvOgrenci = sayac.sonIdVer(SayacTuru.OGRENCI) + ";" + ogrenci.getTcKimlikNo() + ";"
					+ ogrenci.getAd() + ";" + ogrenci.getSoyad() + ";" + ogrenci.getEmail() + ";" + ogrenci.getOkulNo()+ ";" 
					+ ogrenci.getDogumTarihi() + ";" + ogrenci.getCinsiyet() 
					+ ";" +Arrays.toString(ogrenci.getHobiler().toArray()).replace("[", "").replace("]", "");

			fileWriter.write(csvOgrenci +"\n");
			fileWriter.close();
			System.out.println("Yeni Personel Başarılı Bir Şekilde Eklendi!");

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	@Override
	public void guncelle(Ogrenci guncellenecekOgrenci) {

		List<Ogrenci> liste = hepsiniOku();

		for (Ogrenci ogrenci : liste) {

			if (ogrenci.getId() == guncellenecekOgrenci.getId()) {

				ogrenci.setAd(guncellenecekOgrenci.getAd());
				ogrenci.setSoyad(guncellenecekOgrenci.getSoyad());
				ogrenci.setEmail(guncellenecekOgrenci.getEmail());
				ogrenci.setOkulNo(guncellenecekOgrenci.getOkulNo());
				ogrenci.setTcKimlikNo(guncellenecekOgrenci.getTcKimlikNo());
				if(guncellenecekOgrenci.getDogumTarihi() !=null) {
					ogrenci.setDogumTarihi(guncellenecekOgrenci.getDogumTarihi());
				}
				break;
			}

		}

		try {
			FileWriter fileWriter = new FileWriter(OGRENCILER_DOSYA_ADI);

			for (Ogrenci ogrenci : liste) {
				String csvOgrenci = ogrenci.getId() + ";" + ogrenci.getTcKimlikNo() + ";" + ogrenci.getAd() + ";"
						+ ogrenci.getSoyad() + ";" + ogrenci.getEmail() + ";" + ogrenci.getOkulNo() + ";" +  ogrenci.getDogumTarihi();

				fileWriter.write(csvOgrenci);
				fileWriter.write("\n");
			}

			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void sil(int id) {

		List<Ogrenci> liste = hepsiniOku();

		for (Ogrenci ogrenci : liste) {

			if (ogrenci.getId() == id) {
				liste.remove(ogrenci);
				break;
			}

		}

		try {
			FileWriter fileWriter = new FileWriter(OGRENCILER_DOSYA_ADI);

			for (Ogrenci ogrenci : liste) {
				String csvOgrenci = ogrenci.getId() + ";" + ogrenci.getTcKimlikNo() + ";" + ogrenci.getAd() + ";"
						+ ogrenci.getSoyad() + ";" + ogrenci.getEmail() + ";" + ogrenci.getOkulNo();

				fileWriter.write(csvOgrenci);
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
		List<Ogrenci> liste = hepsiniOku();
		Ogrenci silinecekKisi = null;

		for (Ogrenci ogrenci : liste) {
			if (ogrenci.getTcKimlikNo() == tcKimlikNo) {
				silinecekKisi = ogrenci;
				break;
			}
		}

		if (silinecekKisi != null) {
			liste.remove(silinecekKisi);
			try {

				FileWriter fileWriter = new FileWriter(OGRENCILER_DOSYA_ADI, false);
				for (Ogrenci ogrenci : liste) {

					String csvOgrenci = ";" + ogrenci.getTcKimlikNo() + ";" + ogrenci.getAd() + ";" + ogrenci.getSoyad()
							+ ";" + ogrenci.getEmail() + ";" + ogrenci.getOkulNo();
					fileWriter.write(csvOgrenci);
				}
				fileWriter.close();
				System.out.println("Personel başarılı bir şekilde silindi!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
