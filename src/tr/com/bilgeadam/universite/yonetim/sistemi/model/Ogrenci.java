package tr.com.bilgeadam.universite.yonetim.sistemi.model;

import java.util.List;

public class Ogrenci extends Kisi {
	private List<String> hobiler;
	private Integer okulNo;
	private Cinsiyet cinsiyet;

	public List<String> getHobiler() {
		return hobiler;
	}

	public void setHobiler(List<String> hobiler) {
		this.hobiler = hobiler;
	}

	public Cinsiyet getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(Cinsiyet cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public Integer getOkulNo() {
		return okulNo;
	}

	public void setOkulNo(Integer okulNo) {
		this.okulNo = okulNo;
	}
	
	
}
