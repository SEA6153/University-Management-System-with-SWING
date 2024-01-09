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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Readable;
import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Updatable;
import tr.com.bilgeadam.universite.yonetim.sistemi.bl.bllFactory;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Brans;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.Egitmen;

public class EgitmenIslemleri extends JFrame {
	private Readable<Egitmen> egitmenBLL = bllFactory.getEgitmenBLL();
	private Updatable<Egitmen> egitmenUpdate = bllFactory.getEgitmenUpdatable();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtAD;
	private JTextField txtSOYAD;
	private JTextField txtTCKNO;
	private JTextField txtEPOSTA;
	private JTextField txtBRANS;
	private JLabel lblId;
	private List<Egitmen> egitmenler;
	private Menu menu;
	private JTable egitmenTablosu;

	public EgitmenIslemleri(Menu menu) {
		this.menu = menu;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				menuGoster();
			}
		});
		setTitle("Eğitmen İşlemleri");
		setBackground(new Color(0, 0, 0));
		setForeground(new Color(0, 0, 0));
		setBounds(100, 100, 953, 531);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 178, 836, 306);
		contentPane.add(scrollPane);
		
		lblId = new JLabel();
		egitmenTablosu = new JTable();
		veriGoster();
		egitmenTablosu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int secilenIndex = egitmenTablosu.getSelectedRow();
				Egitmen secilenEgitmen = egitmenler.get(secilenIndex);
				lblId.setText("" + secilenEgitmen.getId());
				txtAD.setText(secilenEgitmen.getAd());
				txtSOYAD.setText(secilenEgitmen.getSoyad());
				txtEPOSTA.setText(secilenEgitmen.getEmail().toString());
				txtBRANS.setText("" + secilenEgitmen.getBrans());
				txtTCKNO.setText(String.valueOf(secilenEgitmen.getTcKimlikNo()));

			}
		});
		scrollPane.setViewportView(egitmenTablosu);

		JLabel lblegitmenAd = new JLabel("AD:");
		lblegitmenAd.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblegitmenAd.setForeground(new Color(255, 0, 0));
		lblegitmenAd.setBounds(66, 55, 97, 27);
		contentPane.add(lblegitmenAd);

		JLabel lblSOYAD = new JLabel("SOYAD:");
		lblSOYAD.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSOYAD.setForeground(Color.RED);
		lblSOYAD.setBounds(66, 112, 97, 27);
		contentPane.add(lblSOYAD);

		JLabel lblTcKimlikNo = new JLabel("TC KİMLİK NO:");
		lblTcKimlikNo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblTcKimlikNo.setForeground(Color.RED);
		lblTcKimlikNo.setBounds(328, 55, 157, 27);
		contentPane.add(lblTcKimlikNo);

		JLabel lblEposta = new JLabel("E-POSTA:");
		lblEposta.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblEposta.setForeground(Color.RED);
		lblEposta.setBounds(328, 112, 97, 27);
		contentPane.add(lblEposta);

		JLabel lblBrans = new JLabel("BRANŞ:");
		lblBrans.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblBrans.setForeground(Color.RED);
		lblBrans.setBounds(587, 54, 129, 28);
		contentPane.add(lblBrans);

		txtAD = new JTextField();
		txtAD.setBounds(141, 60, 96, 19);
		contentPane.add(txtAD);
		txtAD.setColumns(10);

		txtSOYAD = new JTextField();
		txtSOYAD.setColumns(10);
		txtSOYAD.setBounds(141, 117, 96, 19);
		contentPane.add(txtSOYAD);

		txtTCKNO = new JTextField();
		txtTCKNO.setColumns(10);
		txtTCKNO.setBounds(455, 55, 96, 19);
		contentPane.add(txtTCKNO);

		txtEPOSTA = new JTextField();
		txtEPOSTA.setColumns(10);
		txtEPOSTA.setBounds(455, 117, 96, 19);
		contentPane.add(txtEPOSTA);

		txtBRANS = new JTextField();
		txtBRANS.setColumns(10);
		txtBRANS.setBounds(663, 55, 96, 19);
		contentPane.add(txtBRANS);

		JButton btnKAYDET = new JButton("KAYDET");
		btnKAYDET.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		btnKAYDET.setBackground(new Color(255, 0, 0));
		btnKAYDET.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Egitmen egitmen = new Egitmen();

				egitmen.setAd(txtAD.getText().toUpperCase());
				egitmen.setSoyad(txtSOYAD.getText().toUpperCase());
				egitmen.setTcKimlikNo(Long.valueOf(txtTCKNO.getText()));
				egitmen.setEmail(txtEPOSTA.getText());
				egitmen.setBrans(Brans.valueOf(txtBRANS.getText().toUpperCase()));

				egitmenUpdate.Ekle(egitmen);
				veriGoster();
				formuTemizle();

			}
		});
		btnKAYDET.setBounds(587, 103, 97, 36);
		contentPane.add(btnKAYDET);

		JButton btnSİL = new JButton("SİL");
		btnSİL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int secilenIndex = egitmenTablosu.getSelectedRow();

				if (secilenIndex == -1) {
					JOptionPane.showMessageDialog(btnSİL, "Silme işlemi içi satır seçmelisiniz!");
					return;
				}
				int secilenEgitmenId = egitmenler.get(secilenIndex).getId();

				egitmenUpdate.sil(secilenEgitmenId);

				veriGoster();

			}
		});
		btnSİL.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnSİL.setBackground(new Color(255, 0, 0));
		btnSİL.setBounds(694, 103, 97, 36);
		contentPane.add(btnSİL);

		JButton btnGUNCELLE = new JButton("GÜNCELLE");
		btnGUNCELLE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int secilenIndex = egitmenTablosu.getSelectedRow();
				
				if(secilenIndex == -1) {
					JOptionPane.showMessageDialog(btnGUNCELLE, "Güncelleme yapmak için satır seçmelisiniz!");
					return;
				}
				
				
				Egitmen guncellenecekEgitmen = egitmenler.get(secilenIndex);
				
				guncellenecekEgitmen.setAd(txtAD.getText());
				guncellenecekEgitmen.setSoyad(txtSOYAD.getText());
				guncellenecekEgitmen.setTcKimlikNo(Long.valueOf(txtTCKNO.getText()));
				guncellenecekEgitmen.setEmail(txtEPOSTA.getText());
				guncellenecekEgitmen.setBrans(Brans.valueOf(txtBRANS.getText()));
				egitmenUpdate.guncelle(guncellenecekEgitmen);
				veriGoster();
				
				
				
			}
		});
		btnGUNCELLE.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnGUNCELLE.setBackground(new Color(255, 0, 0));
		btnGUNCELLE.setBounds(801, 103, 128, 36);
		contentPane.add(btnGUNCELLE);
		veriGoster();

	}

	private void veriGoster() {
		egitmenler = egitmenBLL.hepsiniOku();

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("T.C KİMLİK NO");
		model.addColumn("AD");
		model.addColumn("SOYAD");
		model.addColumn("E-POSTA");
		model.addColumn("BRANŞ");

		Object[] row = new Object[6];

		int rowCount = egitmenler.size();

		for (Egitmen egitmen : egitmenler) {

			row[0] = egitmen.getId();
			row[1] = egitmen.getTcKimlikNo();
			row[2] = egitmen.getAd();
			row[3] = egitmen.getSoyad();
			row[4] = egitmen.getEmail();
			row[5] = egitmen.getBrans();
			model.addRow(row);

		}
		egitmenTablosu.setModel(model);
	}

	private void formuTemizle() {

		textFieldDegistir(contentPane.getComponents(), "");

	}

	private void textFieldDegistir(Component[] components, String yeniDeger) {

		for (Component component : components) {
			if (component instanceof JTextField) {
				((JTextField) component).setText(yeniDeger);
			}

		}

	}

	public void menuGoster() {
		this.menu.setVisible(true);
	}
}
