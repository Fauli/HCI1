package arztpraxis;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.example.hci1.arztpraxis.domain.Patient;
import com.example.hci1.arztpraxis.domain.Patient.Gender;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddPatientenFrame extends JFrame {

	private JPanel contentPane;
	private JTextField vornameText;
	private JTextField nachnameText;
	private JTextField adresseText;
	private JTextField plzText;
	private JTextField ortText;
	private JTextField telefonText;
	UtilDateModel model = new UtilDateModel();
	JDatePanelImpl datePanel = new JDatePanelImpl(model);
	JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPatientenFrame frame = new AddPatientenFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	JComboBox genderComboBox;

	/**
	 * Create the frame.
	 */
	public AddPatientenFrame() {
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblVorname = new JLabel("Vorname");
		GridBagConstraints gbc_lblVorname = new GridBagConstraints();
		gbc_lblVorname.anchor = GridBagConstraints.EAST;
		gbc_lblVorname.insets = new Insets(0, 0, 5, 5);
		gbc_lblVorname.gridx = 0;
		gbc_lblVorname.gridy = 0;
		contentPane.add(lblVorname, gbc_lblVorname);

		vornameText = new JTextField();
		GridBagConstraints gbc_vornameText = new GridBagConstraints();
		gbc_vornameText.insets = new Insets(0, 0, 5, 5);
		gbc_vornameText.fill = GridBagConstraints.HORIZONTAL;
		gbc_vornameText.gridx = 1;
		gbc_vornameText.gridy = 0;
		contentPane.add(vornameText, gbc_vornameText);
		vornameText.setColumns(10);

		JLabel lblNachname = new JLabel("Nachname");
		GridBagConstraints gbc_lblNachname = new GridBagConstraints();
		gbc_lblNachname.anchor = GridBagConstraints.EAST;
		gbc_lblNachname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNachname.gridx = 0;
		gbc_lblNachname.gridy = 1;
		contentPane.add(lblNachname, gbc_lblNachname);

		nachnameText = new JTextField();
		GridBagConstraints gbc_nachnameText = new GridBagConstraints();
		gbc_nachnameText.insets = new Insets(0, 0, 5, 5);
		gbc_nachnameText.fill = GridBagConstraints.HORIZONTAL;
		gbc_nachnameText.gridx = 1;
		gbc_nachnameText.gridy = 1;
		contentPane.add(nachnameText, gbc_nachnameText);
		nachnameText.setColumns(10);

		JLabel lblGeschlecht = new JLabel("Geschlecht");
		GridBagConstraints gbc_lblGeschlecht = new GridBagConstraints();
		gbc_lblGeschlecht.anchor = GridBagConstraints.EAST;
		gbc_lblGeschlecht.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeschlecht.gridx = 0;
		gbc_lblGeschlecht.gridy = 2;
		contentPane.add(lblGeschlecht, gbc_lblGeschlecht);

		DefaultComboBoxModel<String> genderBoxModel = new DefaultComboBoxModel<String>();
		genderBoxModel.addElement("Geschlecht auswählen..");

		genderBoxModel.addElement(Gender.FEMALE.toString());
		genderBoxModel.addElement(Gender.MALE.toString());

		genderComboBox = new JComboBox(genderBoxModel);
		GridBagConstraints gbc_genderComboBox = new GridBagConstraints();
		gbc_genderComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_genderComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_genderComboBox.gridx = 1;
		gbc_genderComboBox.gridy = 2;
		contentPane.add(genderComboBox, gbc_genderComboBox);

		JLabel lblAdresse = new JLabel("Adresse");
		GridBagConstraints gbc_lblAdresse = new GridBagConstraints();
		gbc_lblAdresse.anchor = GridBagConstraints.EAST;
		gbc_lblAdresse.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdresse.gridx = 0;
		gbc_lblAdresse.gridy = 3;
		contentPane.add(lblAdresse, gbc_lblAdresse);

		adresseText = new JTextField();
		GridBagConstraints gbc_adresseText = new GridBagConstraints();
		gbc_adresseText.insets = new Insets(0, 0, 5, 5);
		gbc_adresseText.fill = GridBagConstraints.HORIZONTAL;
		gbc_adresseText.gridx = 1;
		gbc_adresseText.gridy = 3;
		contentPane.add(adresseText, gbc_adresseText);
		adresseText.setColumns(10);

		JLabel lblPlz = new JLabel("PLZ");
		GridBagConstraints gbc_lblPlz = new GridBagConstraints();
		gbc_lblPlz.anchor = GridBagConstraints.EAST;
		gbc_lblPlz.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlz.gridx = 0;
		gbc_lblPlz.gridy = 4;
		contentPane.add(lblPlz, gbc_lblPlz);

		plzText = new JTextField();
		GridBagConstraints gbc_plzText = new GridBagConstraints();
		gbc_plzText.insets = new Insets(0, 0, 5, 5);
		gbc_plzText.fill = GridBagConstraints.HORIZONTAL;
		gbc_plzText.gridx = 1;
		gbc_plzText.gridy = 4;
		contentPane.add(plzText, gbc_plzText);
		plzText.setColumns(10);

		JLabel lblOrt = new JLabel("Ort");
		GridBagConstraints gbc_lblOrt = new GridBagConstraints();
		gbc_lblOrt.anchor = GridBagConstraints.EAST;
		gbc_lblOrt.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrt.gridx = 0;
		gbc_lblOrt.gridy = 5;
		contentPane.add(lblOrt, gbc_lblOrt);

		ortText = new JTextField();
		GridBagConstraints gbc_ortText = new GridBagConstraints();
		gbc_ortText.insets = new Insets(0, 0, 5, 5);
		gbc_ortText.fill = GridBagConstraints.HORIZONTAL;
		gbc_ortText.gridx = 1;
		gbc_ortText.gridy = 5;
		contentPane.add(ortText, gbc_ortText);
		ortText.setColumns(10);

		JLabel lblTelefon = new JLabel("Telefon");
		GridBagConstraints gbc_lblTelefon = new GridBagConstraints();
		gbc_lblTelefon.anchor = GridBagConstraints.EAST;
		gbc_lblTelefon.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefon.gridx = 0;
		gbc_lblTelefon.gridy = 6;
		contentPane.add(lblTelefon, gbc_lblTelefon);

		telefonText = new JTextField();
		GridBagConstraints gbc_telefonText = new GridBagConstraints();
		gbc_telefonText.insets = new Insets(0, 0, 5, 5);
		gbc_telefonText.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefonText.gridx = 1;
		gbc_telefonText.gridy = 6;
		contentPane.add(telefonText, gbc_telefonText);
		telefonText.setColumns(10);

		JLabel lblGeburtstag = new JLabel("Geburtstag");
		GridBagConstraints gbc_lblGeburtstag = new GridBagConstraints();
		gbc_lblGeburtstag.anchor = GridBagConstraints.EAST;
		gbc_lblGeburtstag.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeburtstag.gridx = 0;
		gbc_lblGeburtstag.gridy = 7;
		contentPane.add(lblGeburtstag, gbc_lblGeburtstag);


		datePicker.getJFormattedTextField().setHorizontalAlignment(
				SwingConstants.LEFT);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 7;
		contentPane.add(datePicker, gbc_btnNewButton);

		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out
						.println("WOULD now save the entry, but currently not.. needs to be implemented");
				setVisible(false);
				dispose();
			}
		});
		GridBagConstraints gbc_btnSpeichern = new GridBagConstraints();
		gbc_btnSpeichern.insets = new Insets(0, 0, 0, 5);
		gbc_btnSpeichern.gridx = 1;
		gbc_btnSpeichern.gridy = 8;
		contentPane.add(btnSpeichern, gbc_btnSpeichern);
	}

	public void setPatient(Patient selectedPatient) {
		// TODO Auto-generated method stub
		vornameText.setText(selectedPatient.getFirstName());
		nachnameText.setText(selectedPatient.getLastName());
		genderComboBox.setSelectedItem(selectedPatient.getGender());
		adresseText.setText(selectedPatient.getAddress());
		plzText.setText(selectedPatient.getPostCode());
		ortText.setText(selectedPatient.getCity());
		telefonText.setText(selectedPatient.getPhoneNumber());
//		datePanel.set
	}
}
