package tr.com.bilgeadam.universite.yonetim.sistemi.abstraction;

public interface Updatable<T> {

	
	void Ekle(T t);
	void guncelle(T t);
	void tcKimlikNoSil(long tcKimlikNo);
	void sil(int id);
	
	
	
}
