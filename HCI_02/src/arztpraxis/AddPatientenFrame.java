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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

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

	/**
	 * Create the frame.
	 */
	public AddPatientenFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblNachname = new JLabel("Nachname");
		GridBagConstraints gbc_lblNachname = new GridBagConstraints();
		gbc_lblNachname.anchor = GridBagConstraints.EAST;
		gbc_lblNachname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNachname.gridx = 0;
		gbc_lblNachname.gridy = 1;
		contentPane.add(lblNachname, gbc_lblNachname);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblGeschlecht = new JLabel("Geschlecht");
		GridBagConstraints gbc_lblGeschlecht = new GridBagConstraints();
		gbc_lblGeschlecht.anchor = GridBagConstraints.EAST;
		gbc_lblGeschlecht.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeschlecht.gridx = 0;
		gbc_lblGeschlecht.gridy = 2;
		contentPane.add(lblGeschlecht, gbc_lblGeschlecht);

		DefaultComboBoxModel<String> genderBoxModel = new DefaultComboBoxModel<String>();
		genderBoxModel.addElement("Geschlecht ausw�hlen..");

		genderBoxModel.addElement(Gender.FEMALE.toString());
		genderBoxModel.addElement(Gender.MALE.toString());

		JComboBox genderComboBox = new JComboBox(genderBoxModel);
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

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JLabel lblPlz = new JLabel("PLZ");
		GridBagConstraints gbc_lblPlz = new GridBagConstraints();
		gbc_lblPlz.anchor = GridBagConstraints.EAST;
		gbc_lblPlz.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlz.gridx = 0;
		gbc_lblPlz.gridy = 4;
		contentPane.add(lblPlz, gbc_lblPlz);

		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 4;
		contentPane.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		JLabel lblOrt = new JLabel("Ort");
		GridBagConstraints gbc_lblOrt = new GridBagConstraints();
		gbc_lblOrt.anchor = GridBagConstraints.EAST;
		gbc_lblOrt.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrt.gridx = 0;
		gbc_lblOrt.gridy = 5;
		contentPane.add(lblOrt, gbc_lblOrt);

		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 5;
		contentPane.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);

		JLabel lblTelefon = new JLabel("Telefon");
		GridBagConstraints gbc_lblTelefon = new GridBagConstraints();
		gbc_lblTelefon.anchor = GridBagConstraints.EAST;
		gbc_lblTelefon.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefon.gridx = 0;
		gbc_lblTelefon.gridy = 6;
		contentPane.add(lblTelefon, gbc_lblTelefon);

		textField_5 = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 6;
		contentPane.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);

		JLabel lblGeburtstag = new JLabel("Geburtstag");
		GridBagConstraints gbc_lblGeburtstag = new GridBagConstraints();
		gbc_lblGeburtstag.anchor = GridBagConstraints.EAST;
		gbc_lblGeburtstag.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeburtstag.gridx = 0;
		gbc_lblGeburtstag.gridy = 7;
		contentPane.add(lblGeburtstag, gbc_lblGeburtstag);

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
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
}