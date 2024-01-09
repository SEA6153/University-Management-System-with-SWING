package tr.com.bilgeadam.universite.yonetim.sistemi.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Readable;
import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Updatable;
import tr.com.bilgeadam.universite.yonetim.sistemi.bl.bllFactory;
import tr.com.bilgeadam.universite.yonetim.sistemi.helper.DateTimeHelper;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Cinsiyet;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Ogrenci;

public class OgrenciIsleri extends JFrame {
	private Readable<Ogrenci> bll = bllFactory.getOgrenciBLL();

	private Updatable<Ogrenci> updbl = bllFactory.getOgrenciBLLUpdatable();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable ogrenciTablosu;
	private Menu menu;
	private JTextField txtAd;
	private JTextField txtSoyad;
	private JTextField txtTCKNO;
	private JTextField txtEposta;
	private JTextField txtOkulno;
	private JButton btnSil;
	private JLabel lblId;
	private List<Ogrenci> ogrenciler;
	private JDateChooser dtcDogumTarihi;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnERKEK;
	private JRadioButton rdbtnKIZ;
	private JLabel lblHobiler;

	/**
	 * Create the frame.
	 */
	public OgrenciIsleri(Menu menu) {

		this.menu = menu;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				menuGoster();
			}
		});
		setTitle("Öğrenci İşleri");
		setBounds(100, 100, 979, 574);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 253, 858, 269);
		contentPane.add(scrollPane);

		lblId = new JLabel();
		ogrenciTablosu = new JTable();
		ogrenciTablosu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int secilenIndex = ogrenciTablosu.getSelectedRow();
				Ogrenci secilenOgrenci = ogrenciler.get(secilenIndex);
				lblId.setText("" + secilenOgrenci.getId());
				txtAd.setText(secilenOgrenci.getAd());
				txtSoyad.setText(secilenOgrenci.getSoyad());
				txtOkulno.setText(secilenOgrenci.getOkulNo().toString());
				txtEposta.setText(secilenOgrenci.getEmail());
				txtTCKNO.setText(String.valueOf(secilenOgrenci.getTcKimlikNo()));
				if (secilenOgrenci.getDogumTarihi() != null) {
					dtcDogumTarihi.setDate(java.util.Date.from(
							secilenOgrenci.getDogumTarihi().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
					
				}

			}
		});
		scrollPane.setViewportView(ogrenciTablosu);

		JLabel lblAD = new JLabel("AD:");
		lblAD.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblAD.setForeground(new Color(255, 0, 0));
		lblAD.setBounds(62, 50, 45, 13);
		contentPane.add(lblAD);

		txtAd = new JTextField();
		txtAd.setBounds(153, 47, 96, 19);
		contentPane.add(txtAd);
		txtAd.setColumns(10);

		JLabel lblSoyad = new JLabel("SOYAD:");
		lblSoyad.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblSoyad.setForeground(new Color(255, 0, 0));
		lblSoyad.setBounds(62, 101, 45, 13);
		contentPane.add(lblSoyad);

		JLabel lblTcKimlikNo = new JLabel("TC KİMLİK NO:");
		lblTcKimlikNo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblTcKimlikNo.setForeground(new Color(255, 0, 0));
		lblTcKimlikNo.setBounds(298, 50, 124, 13);
		contentPane.add(lblTcKimlikNo);

		JLabel lblEposta = new JLabel("E-POSTA:");
		lblEposta.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblEposta.setForeground(new Color(255, 0, 0));
		lblEposta.setBounds(298, 101, 76, 13);
		contentPane.add(lblEposta);

		JLabel lblOkulNo = new JLabel("OKUL NO:");
		lblOkulNo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblOkulNo.setForeground(new Color(255, 0, 0));
		lblOkulNo.setBounds(570, 50, 76, 13);
		contentPane.add(lblOkulNo);

		txtSoyad = new JTextField();
		txtSoyad.setColumns(10);
		txtSoyad.setBounds(153, 98, 96, 19);
		contentPane.add(txtSoyad);

		txtTCKNO = new JTextField();
		txtTCKNO.setColumns(10);
		txtTCKNO.setBounds(405, 47, 96, 19);
		contentPane.add(txtTCKNO);

		txtEposta = new JTextField();
		txtEposta.setColumns(10);
		txtEposta.setBounds(405, 98, 96, 19);
		contentPane.add(txtEposta);

		txtOkulno = new JTextField();
		txtOkulno.setColumns(10);
		txtOkulno.setBounds(670, 47, 96, 19);
		contentPane.add(txtOkulno);
		lblHobiler = new JLabel("HOBİLER:");
		lblHobiler.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
		lblHobiler.setForeground(new Color(255, 0, 0));
		lblHobiler.setBounds(405, 132, 76, 31);
		contentPane.add(lblHobiler);

		JCheckBox chckbxFutbol = new JCheckBox("FUTBOL");
		chckbxFutbol.setFont(new Font("Times New Roman", Font.BOLD, 10));
		chckbxFutbol.setBackground(new Color(255, 0, 0));
		chckbxFutbol.setForeground(new Color(0, 0, 0));
		chckbxFutbol.setBounds(487, 135, 93, 21);
		contentPane.add(chckbxFutbol);

		JCheckBox chckbxSinema = new JCheckBox("SİNEMA");
		chckbxSinema.setFont(new Font("Times New Roman", Font.BOLD, 10));
		chckbxSinema.setBackground(new Color(255, 0, 0));
		chckbxSinema.setBounds(601, 135, 93, 21);
		contentPane.add(chckbxSinema);

		JCheckBox chckbxOkumak = new JCheckBox("OKUMAK");
		chckbxOkumak.setFont(new Font("Times New Roman", Font.BOLD, 10));
		chckbxOkumak.setBackground(new Color(255, 0, 0));
		chckbxOkumak.setBounds(713, 135, 93, 21);
		contentPane.add(chckbxOkumak);

		JCheckBox chckbxYuzmek = new JCheckBox("YÜZMEK");
		chckbxYuzmek.setFont(new Font("Times New Roman", Font.BOLD, 10));
		chckbxYuzmek.setBackground(new Color(255, 0, 0));
		chckbxYuzmek.setBounds(827, 135, 93, 21);
		contentPane.add(chckbxYuzmek);

		JButton btnNewButton = new JButton("KAYDET");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ogrenci yeniOgrenci = new Ogrenci();

				yeniOgrenci.setAd(txtAd.getText());
				yeniOgrenci.setSoyad(txtSoyad.getText());
				yeniOgrenci.setTcKimlikNo(Long.valueOf(txtTCKNO.getText()));
				yeniOgrenci.setEmail(txtEposta.getText());
				yeniOgrenci.setOkulNo(Integer.valueOf(txtOkulno.getText()));
				yeniOgrenci.setDogumTarihi(
						dtcDogumTarihi.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				if (rdbtnERKEK.isSelected()) {
					yeniOgrenci.setCinsiyet(Cinsiyet.ERKEK);
				} else {
					yeniOgrenci.setCinsiyet(Cinsiyet.KIZ);
				}

				List<String> hobiler = new ArrayList<>();

				Component[] components = contentPane.getComponents();

				for (Component component : components) {
					if (component instanceof JCheckBox) {
						JCheckBox chb = (JCheckBox) component;
						if (chb.isSelected()) {
							hobiler.add(chb.getText());
						}
					}
				}
				yeniOgrenci.setHobiler(hobiler);

				updbl.Ekle(yeniOgrenci);
				veriGoster();

				formuTemizle();
//
//				if (chckbxFutbol.isSelected()) {
//					hobiler.add("Futbol");
//				}
//
//				if (chckbxSinema.isSelected()) {
//					hobiler.add("Sinema");
//				}
//				if (chckbxOkumak.isSelected()) {
//					hobiler.add("Okumak");
//				}
//				if (chckbxYuzmek.isSelected()) {
//					hobiler.add("Yüzmek");
//				}

			}

		});

		btnNewButton.setBounds(153, 185, 161, 33);
		contentPane.add(btnNewButton);

		btnSil = new JButton("SİL");
		btnSil.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSil.setBackground(new Color(255, 0, 0));
		btnSil.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int secilenIndex = ogrenciTablosu.getSelectedRow();
				if (secilenIndex == -1) {
					JOptionPane.showMessageDialog(btnSil, "Silme işlemi için satır seçilmelidir!");
					return;
				}

				int secilenOgrenciId = ogrenciler.get(secilenIndex).getId();
				updbl.sil(secilenOgrenciId);
				veriGoster();
				// satır seçildiyse silme işlemine devam edeceğiz.
			}
		});
		btnSil.setBounds(385, 185, 161, 33);
		contentPane.add(btnSil);

		JButton btnGuncelle = new JButton("GÜNCELLE");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int secilenIndex = ogrenciTablosu.getSelectedRow();
				if (secilenIndex == -1) {
					JOptionPane.showMessageDialog(btnSil, "Güncellemek için satır seçilmelidir!");
					return;
				}

				Ogrenci guncellenecekOgrenci = ogrenciler.get(secilenIndex);

				guncellenecekOgrenci.setAd(txtAd.getText());
				guncellenecekOgrenci.setEmail(txtEposta.getText());
				guncellenecekOgrenci.setOkulNo(Integer.valueOf(txtOkulno.getText()));
				guncellenecekOgrenci.setSoyad(txtSoyad.getText());
				guncellenecekOgrenci.setTcKimlikNo(Long.valueOf(txtTCKNO.getText()));
				guncellenecekOgrenci.setDogumTarihi(
						dtcDogumTarihi.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				if (rdbtnERKEK.isSelected()) {
					guncellenecekOgrenci.setCinsiyet(Cinsiyet.ERKEK);
				} else {
					guncellenecekOgrenci.setCinsiyet(Cinsiyet.KIZ);
				}
				updbl.guncelle(guncellenecekOgrenci);
				veriGoster();
			}
		});
		btnGuncelle.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnGuncelle.setBackground(new Color(255, 0, 0));
		btnGuncelle.setBounds(612, 185, 154, 33);
		contentPane.add(btnGuncelle);

		JLabel lblID = new JLabel("New label");
		lblID.setBounds(10, 10, 45, 13);
		contentPane.add(lblID);

		JLabel lblDogumTarihi = new JLabel("DOĞUM TARİHİ:");
		lblDogumTarihi.setForeground(Color.RED);
		lblDogumTarihi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblDogumTarihi.setBounds(542, 101, 118, 13);
		contentPane.add(lblDogumTarihi);

		dtcDogumTarihi = new JDateChooser();
		dtcDogumTarihi.setDateFormatString("dd.MM.yyyy");
		dtcDogumTarihi.setBounds(670, 95, 106, 19);
		contentPane.add(dtcDogumTarihi);

		JLabel lblNewLabel = new JLabel("CİNSİYET:");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
		lblNewLabel.setBounds(62, 144, 106, 19);
		contentPane.add(lblNewLabel);

		rdbtnKIZ = new JRadioButton("KADIN");
		rdbtnKIZ.setBackground(new Color(255, 0, 0));
		rdbtnKIZ.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnKIZ.setFont(new Font("Times New Roman", Font.BOLD, 12));
		buttonGroup.add(rdbtnKIZ);
		rdbtnKIZ.setBounds(153, 141, 103, 21);
		contentPane.add(rdbtnKIZ);

		rdbtnERKEK = new JRadioButton("ERKEK");
		rdbtnERKEK.setBackground(new Color(255, 0, 0));
		rdbtnERKEK.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnERKEK.setFont(new Font("Times New Roman", Font.BOLD, 12));
		buttonGroup.add(rdbtnERKEK);
		rdbtnERKEK.setBounds(271, 141, 103, 21);
		contentPane.add(rdbtnERKEK);

		veriGoster();
	}

	private void formuTemizle() {
//		txtAd.setText("");
//		txtSoyad.setText("");
//		txtTCKNO.setText("");
//		txtEposta.setText("");
//		txtOkulno.setText("");

		// contentPane alanına eklenen bütün componentleri aldım(abstraction)
		textFieldDegistir(contentPane.getComponents(), "");
	}

	private void textFieldDegistir(Component[] compoenents, String yeniDeger) {
		// aldığım componentler içinde dönerken bunlardan textfield olanların içindeki
		// textleri siliyorum.
		for (Component component : compoenents) {
			if (component instanceof JTextField) {// polymorphism
				((JTextField) component).setText(yeniDeger);
			}
		}
	}

	private void veriGoster() {
		ogrenciler = bll.hepsiniOku();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("T.C KİMLİK NO");
		model.addColumn("AD");
		model.addColumn("SOYAD");
		model.addColumn("E-POSTA");
		model.addColumn("OKUL NO");
		model.addColumn("DOĞUM TARİHİ");
		model.addColumn("CİNSİYET");

		Object[] row = new Object[model.getColumnCount()];

		int rowCount = ogrenciler.size();

		for (Ogrenci ogrenci : ogrenciler) {

			row[0] = ogrenci.getId();
			row[1] = ogrenci.getTcKimlikNo();
			row[2] = ogrenci.getAd();
			row[3] = ogrenci.getSoyad();
			row[4] = ogrenci.getEmail();
			row[5] = ogrenci.getOkulNo();
			if (ogrenci.getDogumTarihi() != null) {
				row[6] = DateTimeHelper.tarihBicimlendir(ogrenci.getDogumTarihi(),
						DateTimeHelper.EGIK_CIZGILI_TARIH_FORMATI);
			}
			if (rdbtnERKEK != null || rdbtnKIZ != null) {
				row[7] = ogrenci.getCinsiyet();
			}
			model.addRow(row);
		}
		ogrenciTablosu.setModel(model);
	}

	public void menuGoster() {
		this.menu.setVisible(true);
	}
}