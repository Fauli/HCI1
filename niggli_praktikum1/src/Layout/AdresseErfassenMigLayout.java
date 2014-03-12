package Layout;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AdresseErfassenMigLayout extends JFrame {

	private JPanel contentPane;
	private JTextField txtInput_3;
	private JTextField txtInput_4;
	private JTextField txtInput;
	private JTextField txtInput_1;
	private JTextField txtInput_2;
	private JLabel lblNachname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdresseErfassenMigLayout frame = new AdresseErfassenMigLayout();
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
	public AdresseErfassenMigLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));
		
		txtInput_3 = new JTextField();
		txtInput_3.setText("Input");
		contentPane.add(txtInput_3, "cell 1 0,growx");
		txtInput_3.setColumns(10);
		
		lblNachname = new JLabel("Nachname");
		contentPane.add(lblNachname, "flowx,cell 0 1");
		
		txtInput_4 = new JTextField();
		txtInput_4.setText("Input");
		contentPane.add(txtInput_4, "cell 1 1,growx");
		txtInput_4.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Adresse");
		contentPane.add(lblAdresse, "cell 0 2,alignx trailing");
		
		txtInput = new JTextField();
		txtInput.setText("Input");
		contentPane.add(txtInput, "cell 1 2,growx");
		txtInput.setColumns(10);
		
		JLabel lblPlzort = new JLabel("PLZ/Ort");
		contentPane.add(lblPlzort, "cell 0 3,alignx trailing");
		
		txtInput_1 = new JTextField();
		txtInput_1.setText("Input");
		contentPane.add(txtInput_1, "flowx,cell 1 3,alignx left");
		txtInput_1.setColumns(10);
		
		txtInput_2 = new JTextField();
		txtInput_2.setText("Input");
		contentPane.add(txtInput_2, "cell 1 3,growx");
		txtInput_2.setColumns(10);
		
		JButton btnSpeichern = new JButton("Speichern");
		contentPane.add(btnSpeichern, "flowx,cell 1 4");
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		contentPane.add(btnAbbrechen, "cell 1 4,alignx right");
	}

}
