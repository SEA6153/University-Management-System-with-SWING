package tr.com.bilgeadam.universite.yonetim.sistemi.abstraction;

import java.util.List;

public interface Readable<T>{

	List<T> hepsiniOku();

	T emailIleBul(String email);

	T idIleBul(int id);

	
}
