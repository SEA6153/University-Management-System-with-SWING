package tr.com.bilgeadam.universite.yonetim.sistemi.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {

	public static final String NOKTALI_TARIH_FORMATI = "dd.MM.YYYY";
	public static final String EGIK_CIZGILI_TARIH_FORMATI = "dd/MM/YYYY";

	public static String tarihBicimlendir(LocalDate date, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EGIK_CIZGILI_TARIH_FORMATI);
		String formatlanmisTarih = formatter.format(date);

		return formatlanmisTarih;
	}

}
