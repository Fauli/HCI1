package arztpraxis;

import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.example.hci1.arztpraxis.domain.Patient;
import com.example.hci1.arztpraxis.service.DiaryController;

public class PatientenPanel extends JPanel {
	
	JTablePatientenModel tableModel = new JTablePatientenModel();
	final JTable theTable = new JTable(tableModel);
	
	DiaryController dc = new DiaryController();
	
	public PatientenPanel() {
//		theTable.set
		add(new JScrollPane(theTable));
		
		theTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = theTable.rowAtPoint(evt.getPoint());
				int col = theTable.columnAtPoint(evt.getPoint());
				System.out.println("row: " + row + " col: " + col);
				AddPatientenFrame addPatientFrame = new AddPatientenFrame();
				Patient selectedPatient = (Patient) tableModel.getObjectAt(row);
				addPatientFrame.setPatient(selectedPatient);
				addPatientFrame.setVisible(true);
			}
		});
		
		Button addNewPatientButton = new Button("Neuen Patienten erfassen");
		
		addNewPatientButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AddPatientenFrame addPatientFrame = new AddPatientenFrame();
				addPatientFrame.setVisible(true);
			}
		});
		
		add(addNewPatientButton);
		// TODO Auto-generated constructor stub
//		for (Patient patient : dc.getPatients()) {
//			add(new Label(patient.getFirstName()));
//		}

	}

}
