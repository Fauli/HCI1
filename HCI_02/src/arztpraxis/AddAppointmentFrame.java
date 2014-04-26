package arztpraxis;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.example.hci1.arztpraxis.domain.Doctor;
import com.example.hci1.arztpraxis.domain.Patient;
import com.example.hci1.arztpraxis.service.DiaryController;

public class AddAppointmentFrame extends JFrame {

	private JPanel contentPane;
	private JTextField grundTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAppointmentFrame frame = new AddAppointmentFrame();
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
	public AddAppointmentFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		DiaryController dc = new DiaryController();
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel appointmentPanel = new JPanel();
		tabbedPane.addTab("Appointment", null, appointmentPanel, null);
		GridBagLayout gbl_appointmentPanel = new GridBagLayout();
		gbl_appointmentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_appointmentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0,0,0};
		gbl_appointmentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_appointmentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		appointmentPanel.setLayout(gbl_appointmentPanel);
		
		DefaultComboBoxModel<String> startZeitBoxModel = new DefaultComboBoxModel<String>();
		startZeitBoxModel.addElement("Startzeit auswählen..");
		
		Calendar cal = Calendar.getInstance();
		cal.set(MINUTE, 0);
		cal.set(HOUR_OF_DAY, 8);
		for (int i = 0; i <= 36; i++) {
			SimpleDateFormat testFormat = new SimpleDateFormat("HH:mm");
			String startHours = testFormat.format(cal.getTime());
			startZeitBoxModel.addElement(startHours);
			cal.add(MINUTE, 15);
		}
		
		DefaultComboBoxModel<String> endZeitBoxModel = new DefaultComboBoxModel<String>();
		endZeitBoxModel.addElement("Endzeit auswählen..");
		
		cal.set(MINUTE, 0);
		cal.set(HOUR_OF_DAY, 8);
		for (int i = 0; i <= 36; i++) {
			SimpleDateFormat testFormat = new SimpleDateFormat("HH:mm");
			String startHours = testFormat.format(cal.getTime());
			endZeitBoxModel.addElement(startHours);
			cal.add(MINUTE, 15);
		}
		
		DefaultComboBoxModel<String> arztBoxModel = new DefaultComboBoxModel<String>();
		arztBoxModel.addElement("Arzt auswählen..");
		
		Set<Doctor> doctors = dc.getDoctors();
		for (Doctor doctor : doctors) {
			arztBoxModel.addElement(doctor.getFirstName()+" "+doctor.getLastName().toString());
		}
		
		DefaultComboBoxModel<String> patientBoxModel = new DefaultComboBoxModel<String>();
		patientBoxModel.addElement("Patient auswählen..");
		
		Set<Patient> patients = dc.getPatients();
		for (Patient patient : patients) {
			patientBoxModel.addElement(patient.getFirstName()+" "+patient.getLastName().toString());
		}
		
		DefaultComboBoxModel<String> raumBoxModel = new DefaultComboBoxModel<String>();
		raumBoxModel.addElement("Keine Speziellen Räumlichkeiten benötigt");
		raumBoxModel.addElement("Praxisassisentenlabor 1");
		raumBoxModel.addElement("Praxisassisentenlabor 2");
		raumBoxModel.addElement("Ultraschallgerät Zimmer");
		raumBoxModel.addElement("Rönten Zimmer");
		raumBoxModel.addElement("Besprechungszimmer Karin Müller");
		raumBoxModel.addElement("Besprechungszimmer Peter Meier");
		raumBoxModel.addElement("Besprechungszimmer Sophie Keller");
		
		JLabel lblDatum = new JLabel("Datum");
		GridBagConstraints gbc_lblDatum = new GridBagConstraints();
		gbc_lblDatum.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatum.gridx = 0;
		gbc_lblDatum.gridy = 0;
		appointmentPanel.add(lblDatum, gbc_lblDatum);
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		appointmentPanel.add(datePicker, gbc_lblNewLabel);
		
		JLabel lblStartzeit = new JLabel("Startzeit");
		GridBagConstraints gbc_lblStartzeit = new GridBagConstraints();
		gbc_lblStartzeit.anchor = GridBagConstraints.EAST;
		gbc_lblStartzeit.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartzeit.gridx = 0;
		gbc_lblStartzeit.gridy = 1;
		appointmentPanel.add(lblStartzeit, gbc_lblStartzeit);
		
		JComboBox startZeitComboBox = new JComboBox(startZeitBoxModel);
		GridBagConstraints gbc_startZeitComboBox = new GridBagConstraints();
		gbc_startZeitComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_startZeitComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_startZeitComboBox.gridx = 1;
		gbc_startZeitComboBox.gridy = 1;
		appointmentPanel.add(startZeitComboBox, gbc_startZeitComboBox);
		
		JLabel lblEndzeit = new JLabel("Endzeit");
		GridBagConstraints gbc_lblEndzeit = new GridBagConstraints();
		gbc_lblEndzeit.anchor = GridBagConstraints.EAST;
		gbc_lblEndzeit.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndzeit.gridx = 0;
		gbc_lblEndzeit.gridy = 2;
		appointmentPanel.add(lblEndzeit, gbc_lblEndzeit);
		
		JComboBox endZeitComboBox = new JComboBox(endZeitBoxModel);
		GridBagConstraints gbc_endZeitComboBox = new GridBagConstraints();
		gbc_endZeitComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_endZeitComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_endZeitComboBox.gridx = 1;
		gbc_endZeitComboBox.gridy = 2;
		appointmentPanel.add(endZeitComboBox, gbc_endZeitComboBox);
		
		JLabel lblArzt = new JLabel("Arzt");
		GridBagConstraints gbc_lblArzt = new GridBagConstraints();
		gbc_lblArzt.anchor = GridBagConstraints.EAST;
		gbc_lblArzt.insets = new Insets(0, 0, 5, 5);
		gbc_lblArzt.gridx = 0;
		gbc_lblArzt.gridy = 3;
		appointmentPanel.add(lblArzt, gbc_lblArzt);
		
		JComboBox arztComboBox = new JComboBox(arztBoxModel);
		GridBagConstraints gbc_arztComboBox = new GridBagConstraints();
		gbc_arztComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_arztComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_arztComboBox.gridx = 1;
		gbc_arztComboBox.gridy = 3;
		appointmentPanel.add(arztComboBox, gbc_arztComboBox);
		
		JLabel lblPatient = new JLabel("Patient");
		GridBagConstraints gbc_lblPatient = new GridBagConstraints();
		gbc_lblPatient.anchor = GridBagConstraints.EAST;
		gbc_lblPatient.insets = new Insets(0, 0, 5, 5);
		gbc_lblPatient.gridx = 0;
		gbc_lblPatient.gridy = 4;
		appointmentPanel.add(lblPatient, gbc_lblPatient);
		
		JComboBox patientComboBox = new JComboBox(patientBoxModel);
		GridBagConstraints gbc_patientComboBox = new GridBagConstraints();
		gbc_patientComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_patientComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_patientComboBox.gridx = 1;
		gbc_patientComboBox.gridy = 4;
		appointmentPanel.add(patientComboBox, gbc_patientComboBox);
		
		JLabel lblGrund = new JLabel("Grund");
		GridBagConstraints gbc_lblGrund = new GridBagConstraints();
		gbc_lblGrund.anchor = GridBagConstraints.EAST;
		gbc_lblGrund.insets = new Insets(0, 0, 5, 5);
		gbc_lblGrund.gridx = 0;
		gbc_lblGrund.gridy = 5;
		appointmentPanel.add(lblGrund, gbc_lblGrund);
		
		grundTextField = new JTextField();
		GridBagConstraints gbc_grundTextField = new GridBagConstraints();
		gbc_grundTextField.insets = new Insets(0, 0, 5, 0);
		gbc_grundTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_grundTextField.gridx = 1;
		gbc_grundTextField.gridy = 5;
		appointmentPanel.add(grundTextField, gbc_grundTextField);
		grundTextField.setColumns(10);
		
		JLabel lblBemerkungen = new JLabel("Bemerkungen");
		GridBagConstraints gbc_lblBemerkungen = new GridBagConstraints();
		gbc_lblBemerkungen.insets = new Insets(0, 0, 5, 5);
		gbc_lblBemerkungen.gridx = 0;
		gbc_lblBemerkungen.gridy = 6;
		appointmentPanel.add(lblBemerkungen, gbc_lblBemerkungen);
		
		JTextArea grundTextArea = new JTextArea();
		GridBagConstraints gbc_grundTextArea = new GridBagConstraints();
		gbc_grundTextArea.insets = new Insets(0, 0, 5, 0);
		gbc_grundTextArea.fill = GridBagConstraints.BOTH;
		gbc_grundTextArea.gridx = 1;
		gbc_grundTextArea.gridy = 6;
		appointmentPanel.add(grundTextArea, gbc_grundTextArea);
		
		JLabel lblRaum = new JLabel("Raum");
		GridBagConstraints gbc_lblRaum = new GridBagConstraints();
		gbc_lblRaum.anchor = GridBagConstraints.EAST;
		gbc_lblRaum.insets = new Insets(0, 0, 5, 5);
		gbc_lblRaum.gridx = 0;
		gbc_lblRaum.gridy = 7;
		appointmentPanel.add(lblRaum, gbc_lblRaum);
		
		JComboBox raumComboBox = new JComboBox(raumBoxModel);
		GridBagConstraints gbc_raumComboBox = new GridBagConstraints();
		gbc_raumComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_raumComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_raumComboBox.gridx = 1;
		gbc_raumComboBox.gridy = 7;
		appointmentPanel.add(raumComboBox, gbc_raumComboBox);
		
		JPanel breakPanel = new JPanel();
		tabbedPane.addTab("Break", null, breakPanel, null);
		GridBagLayout gbl_breakPanel = new GridBagLayout();
		gbl_breakPanel.columnWidths = new int[]{0, 0, 0};
		gbl_breakPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_breakPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_breakPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		breakPanel.setLayout(gbl_breakPanel);
		
		JLabel lblStartzeit_Break = new JLabel("Startzeit");
		GridBagConstraints gbc_lblStartzeit_Break = new GridBagConstraints();
		gbc_lblStartzeit_Break.anchor = GridBagConstraints.EAST;
		gbc_lblStartzeit_Break.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartzeit_Break.gridx = 0;
		gbc_lblStartzeit_Break.gridy = 0;
		breakPanel.add(lblStartzeit_Break, gbc_lblStartzeit_Break);
		
		JComboBox startZeitBreakComboBox = new JComboBox(startZeitBoxModel);
		GridBagConstraints gbc_startZeitBreakComboBox = new GridBagConstraints();
		gbc_startZeitBreakComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_startZeitBreakComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_startZeitBreakComboBox.gridx = 1;
		gbc_startZeitBreakComboBox.gridy = 0;
		breakPanel.add(startZeitBreakComboBox, gbc_startZeitBreakComboBox);
		
		JLabel lblEndzeit_Break = new JLabel("Endzeit");
		GridBagConstraints gbc_lblEndzeit_Break = new GridBagConstraints();
		gbc_lblEndzeit_Break.anchor = GridBagConstraints.EAST;
		gbc_lblEndzeit_Break.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndzeit_Break.gridx = 0;
		gbc_lblEndzeit_Break.gridy = 1;
		breakPanel.add(lblEndzeit_Break, gbc_lblEndzeit_Break);
		
		JComboBox endZeitBreakComboBox = new JComboBox(endZeitBoxModel);
		GridBagConstraints gbc_endZeitBreakComboBox = new GridBagConstraints();
		gbc_endZeitBreakComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_endZeitBreakComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_endZeitBreakComboBox.gridx = 1;
		gbc_endZeitBreakComboBox.gridy = 1;
		breakPanel.add(endZeitBreakComboBox, gbc_endZeitBreakComboBox);
		
		JLabel lblArzt_Break = new JLabel("Arzt");
		GridBagConstraints gbc_lblArzt_Break = new GridBagConstraints();
		gbc_lblArzt_Break.insets = new Insets(0, 0, 0, 5);
		gbc_lblArzt_Break.anchor = GridBagConstraints.EAST;
		gbc_lblArzt_Break.gridx = 0;
		gbc_lblArzt_Break.gridy = 2;
		breakPanel.add(lblArzt_Break, gbc_lblArzt_Break);
		
		JComboBox arztBreakComboBox = new JComboBox(arztBoxModel);
		GridBagConstraints gbc_arztBreakComboBox = new GridBagConstraints();
		gbc_arztBreakComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_arztBreakComboBox.gridx = 1;
		gbc_arztBreakComboBox.gridy = 2;
		breakPanel.add(arztBreakComboBox, gbc_arztBreakComboBox);
		
		JPanel absencePanel = new JPanel();
		tabbedPane.addTab("Absenzen", null, absencePanel, null);
		GridBagLayout gbl_absencePanel = new GridBagLayout();
		gbl_absencePanel.columnWidths = new int[]{0, 0, 0};
		gbl_absencePanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_absencePanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_absencePanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		absencePanel.setLayout(gbl_absencePanel);
		
		JLabel lblStartzeit_Absenzen = new JLabel("Startzeit");
		GridBagConstraints gbc_lblStartzeit_Absenzen = new GridBagConstraints();
		gbc_lblStartzeit_Absenzen.anchor = GridBagConstraints.EAST;
		gbc_lblStartzeit_Absenzen.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartzeit_Absenzen.gridx = 0;
		gbc_lblStartzeit_Absenzen.gridy = 0;
		absencePanel.add(lblStartzeit_Absenzen, gbc_lblStartzeit_Absenzen);
		
		JComboBox startZeitAbsenzenComboBox = new JComboBox(startZeitBoxModel);
		GridBagConstraints gbc_startZeitAbsenzenComboBox = new GridBagConstraints();
		gbc_startZeitAbsenzenComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_startZeitAbsenzenComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_startZeitAbsenzenComboBox.gridx = 1;
		gbc_startZeitAbsenzenComboBox.gridy = 0;
		absencePanel.add(startZeitAbsenzenComboBox, gbc_startZeitAbsenzenComboBox);
		
		JLabel lblEndzeit_Absenzen = new JLabel("Endzeit");
		GridBagConstraints gbc_lblEndzeit_Absenzen = new GridBagConstraints();
		gbc_lblEndzeit_Absenzen.anchor = GridBagConstraints.EAST;
		gbc_lblEndzeit_Absenzen.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndzeit_Absenzen.gridx = 0;
		gbc_lblEndzeit_Absenzen.gridy = 1;
		absencePanel.add(lblEndzeit_Absenzen, gbc_lblEndzeit_Absenzen);
		
		JComboBox endZeitAbsenzenComboBox = new JComboBox(endZeitBoxModel);
		GridBagConstraints gbc_endZeitAbsenzenComboBox = new GridBagConstraints();
		gbc_endZeitAbsenzenComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_endZeitAbsenzenComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_endZeitAbsenzenComboBox.gridx = 1;
		gbc_endZeitAbsenzenComboBox.gridy = 1;
		absencePanel.add(endZeitAbsenzenComboBox, gbc_endZeitAbsenzenComboBox);
		
		JLabel lblArzt_Absenzen = new JLabel("Arzt");
		GridBagConstraints gbc_lblArzt_Absenzen = new GridBagConstraints();
		gbc_lblArzt_Absenzen.anchor = GridBagConstraints.EAST;
		gbc_lblArzt_Absenzen.insets = new Insets(0, 0, 0, 5);
		gbc_lblArzt_Absenzen.gridx = 0;
		gbc_lblArzt_Absenzen.gridy = 2;
		absencePanel.add(lblArzt_Absenzen, gbc_lblArzt_Absenzen);
		
		JComboBox arztAbsenzenComboBox = new JComboBox(arztBoxModel);
		GridBagConstraints gbc_arztAbsenzenComboBox = new GridBagConstraints();
		gbc_arztAbsenzenComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_arztAbsenzenComboBox.gridx = 1;
		gbc_arztAbsenzenComboBox.gridy = 2;
		absencePanel.add(arztAbsenzenComboBox, gbc_arztAbsenzenComboBox);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("WOULD now save the entry, but currently not.. needs to be implemented");
				setVisible(false);
				dispose();
			}
		});
		contentPane.add(btnSave, BorderLayout.SOUTH);
	}

}
