package tr.com.bilgeadam.universite.yonetim.sistemi.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Readable;
import tr.com.bilgeadam.universite.yonetim.sistemi.bl.bllFactory;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Kullanici;

public class LoginScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtKullaniciAdi;
	private JPasswordField txtSifre;
	static Kullanici girisYapanKullanici; //package private

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	void close() {
		this.close();
	}
	
	public LoginScreen() {
		
		Readable<Kullanici> bul = bllFactory.getKullaniciBul();
		
		setForeground(new Color(192, 192, 192));
		setBackground(new Color(192, 192, 192));
		setTitle("Giriş Ekranı");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 430);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewGirisKullanici = new JLabel("KULLANICI GİRİŞ EKRANINA HOŞGELDİNİZ");
		lblNewGirisKullanici.setFont(new Font("Yu Gothic Medium", Font.BOLD | Font.ITALIC, 22));
		lblNewGirisKullanici.setForeground(new Color(128, 255, 255));
		lblNewGirisKullanici.setBounds(122, 26, 537, 48);
		contentPane.add(lblNewGirisKullanici);
		
		JLabel lblNewLabelKullaniciAdi = new JLabel("KULLANICI ADI:");
		lblNewLabelKullaniciAdi.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabelKullaniciAdi.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		lblNewLabelKullaniciAdi.setForeground(new Color(255, 0, 0));
		lblNewLabelKullaniciAdi.setBounds(122, 132, 173, 60);
		contentPane.add(lblNewLabelKullaniciAdi);
		
		JLabel lblNewLabelKullaniciSifre = new JLabel("KULLANICI ŞİFRE:");
		lblNewLabelKullaniciSifre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabelKullaniciSifre.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		lblNewLabelKullaniciSifre.setForeground(new Color(255, 0, 0));
		lblNewLabelKullaniciSifre.setBounds(106, 224, 235, 38);
		contentPane.add(lblNewLabelKullaniciSifre);
		
		txtKullaniciAdi = new JTextField();
		txtKullaniciAdi.setText("yereyi61@gmail.com");
		txtKullaniciAdi.setBounds(297, 149, 221, 25);
		contentPane.add(txtKullaniciAdi);
		txtKullaniciAdi.setColumns(10);
		
		JButton btnGiris = new JButton("GİRİŞ");
		btnGiris.setVerticalAlignment(SwingConstants.BOTTOM);
		btnGiris.setFont(new Font("Sitka Subheading", Font.BOLD, 17));
		btnGiris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kullaniciAdi = txtKullaniciAdi.getText();
				String sifre = txtSifre.getText();
				
				Kullanici kullanici = bul.emailIleBul(kullaniciAdi);
				
				
				if(kullanici != null && kullanici.getSifre() == Long.valueOf(sifre)) {
					JOptionPane.showMessageDialog(btnGiris, "HOŞGELDİNİZ!", "GİRİŞ BAŞARILI!", JOptionPane.INFORMATION_MESSAGE);
					//başarılı giriş olduğunda yeni bir pencere oluşturup açmaya yarıyor.
					girisYapanKullanici = kullanici;
					Menu menu = new Menu(kullanici);
					menu.setVisible(true);
					closeMainMenu();
					
				}
				else {
					
					JOptionPane.showMessageDialog(btnGiris, "Hatalı kullanıcı adı ve/veya şifre girdiniz, lütfen tekrar deneyin!",
							"HATALI GİRİŞ!", JOptionPane.WARNING_MESSAGE, null);
				}
				
			}
		});
		btnGiris.setForeground(new Color(0, 0, 0));
		btnGiris.setBackground(new Color(255, 0, 0));
		btnGiris.setBounds(279, 272, 136, 38);
		contentPane.add(btnGiris);
		
		txtSifre = new JPasswordField();
		txtSifre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 27) {
					close();
				}
			}
		});
		txtSifre.setBounds(297, 227, 221, 25);
		contentPane.add(txtSifre);
	}
	
	private void closeMainMenu() {
		this.setVisible(false);
	}
	
	
	
	
	
}
