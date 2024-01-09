package tr.com.bilgeadam.universite.yonetim.sistemi.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Readable;
import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Updatable;
import tr.com.bilgeadam.universite.yonetim.sistemi.bl.bllFactory;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Brans;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Ders;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Kullanici;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DersEkleSilMemur extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDersAdı;
	private JTable dersKayitTablosu;
	private Readable<Ders> BLL = bllFactory.getDersBLL();
	private Updatable<Ders> UPDATE = bllFactory.getDersUpdatable();
	private List<Ders> ders;
	private DersKayit dersKayit;
	private JTextField txtSınıfNo;
	private JTextField txtKredi;
	private JTextField txtGecmeNotu;
	private JTextField txtDevamZorunluluğu;
	private Brans brans;
	private JLabel lblId;
	
	private Kullanici kullanici;

	/**
	 * Create the frame.
	 */
	public DersEkleSilMemur(DersKayit dersKayit, Kullanici kullanici) {
		this.dersKayit = dersKayit;
		this.kullanici = kullanici;
		setBounds(100, 100, 979, 518);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 0, 0));
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("DERS EKLE/SİL MENÜSÜ");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setBounds(306, -11, 363, 80);
		contentPane.add(lblNewLabel);

		JLabel lblDersAdı = new JLabel("DERS ADI:");
		lblDersAdı.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblDersAdı.setForeground(new Color(255, 0, 0));
		lblDersAdı.setBounds(68, 66, 109, 25);
		contentPane.add(lblDersAdı);

		txtDersAdı = new JTextField();
		txtDersAdı.setBounds(157, 68, 120, 19);
		contentPane.add(txtDersAdı);
		txtDersAdı.setColumns(10);
		JButton btnDersEkle = new JButton("EKLE");
		btnDersEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Ders yeniDers = new Ders();
				yeniDers.setAd(txtDersAdı.getText().toUpperCase());
				yeniDers.setSinifNo(Byte.valueOf(txtSınıfNo.getText()));
				yeniDers.setGecmeNotu(Double.valueOf(txtGecmeNotu.getText()));
				yeniDers.setKredi(Byte.valueOf(txtKredi.getText()));
				yeniDers.setDevamZorunluluguVarMi(Boolean.valueOf(txtDevamZorunluluğu.getText().toUpperCase()));
				String devamZorunluluguText = txtDevamZorunluluğu.getText().toUpperCase();
				if (devamZorunluluguText.equalsIgnoreCase("YOK")) {
					yeniDers.setDevamZorunluluguVarMi(false);
				} else if (devamZorunluluguText.equalsIgnoreCase("VAR")) {
					yeniDers.setDevamZorunluluguVarMi(true);
				} else {
					JOptionPane.showMessageDialog(btnDersEkle, "Yanlış tuşlama yaptınız!");
					formuTemizle();
				}
				if (txtDersAdı.getText().equalsIgnoreCase("Mühendislik") || txtDersAdı.getText().equalsIgnoreCase("FEN")
						|| txtDersAdı.getText().equalsIgnoreCase("İktisat")
						|| txtDersAdı.getText().equalsIgnoreCase("Edebiyat")) {
					
					

					UPDATE.Ekle(yeniDers);

					veriGoster();

					formuTemizle();
				} else {
					JOptionPane.showMessageDialog(btnDersEkle, "Yanlış ders ismi girdiniz!");
					formuTemizle();
				}

			}
		});
		btnDersEkle.setFont(new Font("Sitka Subheading", Font.BOLD, 15));
		btnDersEkle.setBounds(572, 107, 134, 34);
		contentPane.add(btnDersEkle);

		JButton btnDersSil = new JButton("SİL");
		btnDersSil.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int secilenIndex = dersKayitTablosu.getSelectedRow();
				if (secilenIndex == -1) {
					JOptionPane.showMessageDialog(btnDersSil, "Silme işlemi için satır seçmelisiniz!");
					return;
				}

				int secilenDersId = ders.get(secilenIndex).getId();
				UPDATE.sil(secilenDersId);
				veriGoster();

			}
		});
		btnDersSil.setFont(new Font("Sitka Subheading", Font.BOLD, 15));
		btnDersSil.setBounds(734, 107, 143, 34);
		contentPane.add(btnDersSil);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 221, 853, 228);
		contentPane.add(scrollPane);
		lblId = new JLabel();
		dersKayitTablosu = new JTable();
		dersKayitTablosu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int secilenIndex = dersKayitTablosu.getSelectedRow();
				
				Ders secilenDers = ders.get(secilenIndex);
				lblId.setText("" + secilenDers.getId());
				txtDersAdı.setText(secilenDers.getAd());
				txtGecmeNotu.setText("" +secilenDers.getGecmeNotu());
				txtKredi.setText("" +secilenDers.getKredi());
				txtSınıfNo.setText("" + secilenDers.getSinifNo());
				txtDevamZorunluluğu.setText("" + secilenDers.isDevamZorunluluguVarMi());
				
				
				
				
				
				
				
			}
		});
		scrollPane.setViewportView(dersKayitTablosu);

		JLabel lblSnfNo = new JLabel("SINIF NO:");
		lblSnfNo.setForeground(new Color(255, 0, 0));
		lblSnfNo.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblSnfNo.setBounds(68, 116, 109, 25);
		contentPane.add(lblSnfNo);

		txtSınıfNo = new JTextField();
		txtSınıfNo.setColumns(10);
		txtSınıfNo.setBounds(157, 118, 120, 19);
		contentPane.add(txtSınıfNo);

		JLabel lblKredi = new JLabel("KREDİ:");
		lblKredi.setForeground(Color.RED);
		lblKredi.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblKredi.setBounds(312, 65, 109, 25);
		contentPane.add(lblKredi);

		txtKredi = new JTextField();
		txtKredi.setColumns(10);
		txtKredi.setBounds(422, 68, 120, 19);
		contentPane.add(txtKredi);

		txtGecmeNotu = new JTextField();
		txtGecmeNotu.setColumns(10);
		txtGecmeNotu.setBounds(422, 113, 120, 19);
		contentPane.add(txtGecmeNotu);

		JLabel lblDevamZorunluluu = new JLabel("DEVAM ZORUNLULUĞU:");
		lblDevamZorunluluu.setForeground(Color.RED);
		lblDevamZorunluluu.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblDevamZorunluluu.setBounds(562, 66, 185, 25);
		contentPane.add(lblDevamZorunluluu);

		txtDevamZorunluluğu = new JTextField();
		txtDevamZorunluluğu.setColumns(10);
		txtDevamZorunluluğu.setBounds(757, 68, 120, 19);
		contentPane.add(txtDevamZorunluluğu);

		JLabel lblGecmeNotu = new JLabel("GEÇME NOTU:");
		lblGecmeNotu.setForeground(Color.RED);
		lblGecmeNotu.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblGecmeNotu.setBounds(306, 111, 115, 25);
		contentPane.add(lblGecmeNotu);

		veriGoster();

	}

	private void formuTemizle() {

		textFieldDegistir(contentPane.getComponents(), "");
	}

	private void textFieldDegistir(Component[] compoenents, String yeniDeger) {

		for (Component component : compoenents) {
			if (component instanceof JTextField) {
				((JTextField) component).setText(yeniDeger);
			}
		}
	}

	public void DersKayitGoster() {
		this.dersKayit.setVisible(true);
	}

	private void veriGoster() {
		ders = BLL.hepsiniOku();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("DERS ADI");
		model.addColumn("SINIF NO");
		model.addColumn("KREDİ");
		model.addColumn("DEVAM ZORUNLULUĞU");
		model.addColumn("GEÇME NOTU");

		Object[] row = new Object[6];

		int rowCount = ders.size();

		for (Ders ders : ders) {

			row[0] = ders.getId();
			row[1] = ders.getAd();
			row[2] = ders.getSinifNo();
			row[3] = ders.getKredi();
			row[4] = ders.isDevamZorunluluguVarMi();
			row[5] = ders.getGecmeNotu();
			model.addRow(row);
		}
		dersKayitTablosu.setModel(model);
	}

}
