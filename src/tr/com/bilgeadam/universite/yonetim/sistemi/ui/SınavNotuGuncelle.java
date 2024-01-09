package tr.com.bilgeadam.universite.yonetim.sistemi.ui;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Readable;
import tr.com.bilgeadam.universite.yonetim.sistemi.abstraction.Updatable;
import tr.com.bilgeadam.universite.yonetim.sistemi.bl.bllFactory;
import tr.com.bilgeadam.universite.yonetim.sistemi.model.SinavNotu;

public class SınavNotuGuncelle extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<SinavNotu> sinavNotu;
	private Readable<SinavNotu> BLL = bllFactory.getSinavNotuBLL();
	private Updatable<SinavNotu> UPDATE = bllFactory.getSinavNotuUpdate();

	public SınavNotuGuncelle() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

	private void veriGoster() {
		sinavNotu = BLL.hepsiniOku();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("DERS ADI");
		model.addColumn("KREDİ");
		model.addColumn("GEÇME NOTU");
		model.addColumn("1. VİZE NOTU");
		model.addColumn("2. VİZE NOTU");
		model.addColumn("FİNAL SINAVI NOTU");

		Object[] row = new Object[7];

		int rowCount = sinavNotu.size();

		for (SinavNotu sinavNotu : sinavNotu) {

			row[0] = sinavNotu.getId();
			row[1] = sinavNotu.getAd();
			row[2] = sinavNotu.getKredi();
			row[3] = sinavNotu.getGecmeNotu();
			row[4] = sinavNotu.getBirinceVizeNotu();
			row[5] = sinavNotu.getIkinciVizeNotu();
			row[6] = sinavNotu.getFinalNotu();
			model.addRow(row);
		}
//		dersKayitTablosu.setModel(model);
	}

}
