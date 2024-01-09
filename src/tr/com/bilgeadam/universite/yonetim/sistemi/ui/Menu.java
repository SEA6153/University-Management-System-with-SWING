package tr.com.bilgeadam.universite.yonetim.sistemi.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import tr.com.bilgeadam.universite.yonetim.sistemi.model.Kullanici;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Rol;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Kullanici kullanici;

	public Menu(Kullanici kullanici) {
		this.kullanici = kullanici;
		setTitle("MENÜ");
		setBackground(new Color(0, 0, 0));
		setForeground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 778, 461);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// MENÜ

		JLabel lblMenu = new JLabel("MEMUR İŞLEMLERİ");
		lblMenu.setForeground(new Color(255, 0, 0));
		lblMenu.setFont(new Font("Sitka Subheading", Font.BOLD | Font.ITALIC, 35));
		lblMenu.setBounds(207, 10, 372, 50);
		contentPane.add(lblMenu);

		// ÖĞRENCİ İŞLEMLERİ

		JButton btnOgrenciIsleri = new JButton("ÖĞRENCİ İŞLEMLERİ");
		btnOgrenciIsleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OgrenciIsleriPenceresi();
				MenuClose();

			}
		});
		btnOgrenciIsleri.setForeground(new Color(0, 0, 0));
		btnOgrenciIsleri.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		btnOgrenciIsleri.setBackground(new Color(255, 0, 0));
		btnOgrenciIsleri.setBounds(242, 70, 260, 64);
		btnOgrenciIsleri.setVisible(false);
		contentPane.add(btnOgrenciIsleri);

		List<Rol> kullaniciRolleri = kullanici.getRol();

		if (kullaniciRolleri.contains(Rol.MEMUR) || kullaniciRolleri.contains(Rol.ADMIN)) {
			btnOgrenciIsleri.setVisible(true);
		}

		// EĞİTMEN İŞLEMLERİ

		JButton btnEgıtmenIslemleri = new JButton("EĞİTMEN İŞLEMLERİ");
		btnEgıtmenIslemleri.setBackground(new Color(255, 0, 0));
		btnEgıtmenIslemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EgitmenIsleriMenusu();
				MenuClose();
			}

		});

		
		btnEgıtmenIslemleri.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		btnEgıtmenIslemleri.setBounds(242, 171, 260, 64);
		btnEgıtmenIslemleri.setVisible(false);

		contentPane.add(btnEgıtmenIslemleri);

		// DERS KAYIT İŞLEMLERİ

		JButton btnDersKayitIslemleri = new JButton("DERS İŞLEMLERİ");
		btnDersKayitIslemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dersKayitIslemleriMenusu();
				MenuClose();
			}
		});
		if (kullaniciRolleri.contains(Rol.MEMUR) || kullaniciRolleri.contains(Rol.ADMIN)
				|| kullaniciRolleri.contains(Rol.EGITMEN) || kullaniciRolleri.contains(Rol.OGRENCI)) {
			btnDersKayitIslemleri.setVisible(true);

		}
		btnDersKayitIslemleri.setBackground(new Color(255, 0, 0));
		btnDersKayitIslemleri.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		btnDersKayitIslemleri.setBounds(242, 277, 260, 64);
		contentPane.add(btnDersKayitIslemleri);
		
		if (kullaniciRolleri.contains(Rol.MEMUR) || kullaniciRolleri.contains(Rol.ADMIN)) {
			btnEgıtmenIslemleri.setVisible(true);
		}
	
	}

	// METODLAR
	private void OgrenciIsleriPenceresi() {

		OgrenciIsleri ogrenciIsleri = new OgrenciIsleri(this);
		ogrenciIsleri.setVisible(true);

	}

	private void EgitmenIsleriMenusu() {
		EgitmenIslemleri egitmenIsleri = new EgitmenIslemleri(this);
		egitmenIsleri.setVisible(true);

	}

	private void dersKayitIslemleriMenusu() {
		DersKayit dersKayit = new DersKayit(this, kullanici);
		dersKayit.setVisible(true);

	}

	private void MenuClose() {

		this.setVisible(false);

	}
}
