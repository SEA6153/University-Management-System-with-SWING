package tr.com.bilgeadam.universite.yonetim.sistemi.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Readable;
import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Updatable;
import tr.com.bilgeadam.universite.yonetim.sistemi.bl.bllFactory;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Kullanici;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Ogrenci;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Rol;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DersKayit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Menu menu;
	private Kullanici kullanici;
	
	
	public DersKayit(Menu menu , Kullanici kullanici) {
		this.menu = menu;
		this.kullanici = kullanici;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				menuGoster();
			}
		});
		setForeground(new Color(0, 0, 0));
		setBackground(new Color(0, 0, 0));
		setBounds(100, 100, 951, 499);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// DERS EKLE/SİL MEMUR
		JButton btnDersKayit = new JButton("DERS EKLE/SİL/GÜNCELLE");
		btnDersKayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dersEkleSilPenceresi();
				MenuClose();

			}
		});

		List<Rol> kullaniciRolleri = kullanici.getRol();

		if (kullaniciRolleri.contains(Rol.MEMUR) || kullaniciRolleri.contains(Rol.EGITMEN)) {
			btnDersKayit.setVisible(true);

		}

		btnDersKayit.setBackground(new Color(255, 0, 0));
		btnDersKayit.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		btnDersKayit.setBounds(250, 75, 398, 127);
		contentPane.add(btnDersKayit);

		JButton btnSnavNotuGncelle = new JButton("SINAV NOTU GÜNCELLE");
		btnSnavNotuGncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSnavNotuGncelle.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		btnSnavNotuGncelle.setBackground(Color.RED);
		btnSnavNotuGncelle.setBounds(250, 266, 398, 122);
		contentPane.add(btnSnavNotuGncelle);
		
		JLabel lblNewLabel = new JLabel("DERS İŞLEMLERİ MENÜSÜ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setBounds(250, 21, 416, 44);
		contentPane.add(lblNewLabel);
	}

	private void dersEkleSilPenceresi() {
		DersEkleSilMemur ekleSilMemur = new DersEkleSilMemur(this, kullanici);
		ekleSilMemur.setVisible(true);

	}

	private void MenuClose() {

		this.setVisible(false);

	}
	public void menuGoster() {
		this.menu.setVisible(true);
	}
}
