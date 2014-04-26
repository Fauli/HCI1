package arztpraxis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.example.hci1.arztpraxis.domain.Patient;
import com.example.hci1.arztpraxis.service.DiaryController;

class JTablePatientenModel implements TableModel {
	private Vector listenersVector = new Vector();
	Calendar firstWeekDay = Calendar.getInstance();
	private Vector patientVector = new Vector();
	DiaryController dc = new DiaryController();
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

//	public JTablePatientenModel(ArrayList<Patient> patients) {
	public JTablePatientenModel() {
		DayClass rowLabel = new DayClass();
		
		
		for (Patient patient : dc.getPatients()) {
			addPatientColumn(patient.getFirstName());
			addPatientColumn(patient.getLastName());
			addPatientColumn(patient.getGender().toString());
			addPatientColumn(patient.getAddress());
			String formattedBirthday = format1.format(patient.getBirthday().getTime());
			addPatientColumn(formattedBirthday);
		}
		
		
//		addDay(rowLabel);
	}

	public int getColumnCount() {
		//return patientVector.size();
		return 5;
	}
	
	public void addPatientColumn(String val){
		int index = patientVector.size();
		patientVector.add(val);

		TableModelEvent e = new TableModelEvent((TableModel) this, index,
				index, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);

		for (int i = 0, n = listenersVector.size(); i < n; i++) {
			((TableModelListener) listenersVector.get(i)).tableChanged(e);
		}
	}

	

	public int getRowCount() {
		// return personVector.size();
		return patientVector.size();
	}
	
	

	public String getColumnName(int column) {
		if (0 == column) {
			return "Vorname";
		}if (1 == column) {
			return "Nachname";
		}if (2 == column) {
			return "Geschlecht";
		}if (3 == column) {
			return "Adresse";
		}if (4 == column) {
			return "Geburtstag";
		} else {
			return "??";
		}
//		else {
//			Calendar cali = firstWeekDay;
//			cali.add(Calendar.DATE, column - 1);
//			Date date = cali.getTime();
//			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//			String date1 = format1.format(date);
//			return date1;
//		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		String day = (String) patientVector.get(columnIndex);
//		patientVector.
		//patientVector.removeElementAt(columnIndex);
		return day;
	}

	public Class getColumnClass(int columnIndex) {
		return String.class;
	}

	public void setFirtWeekDay(Calendar firstWeekDay) {
		this.firstWeekDay = firstWeekDay;

	}

	public void addTableModelListener(TableModelListener l) {
		listenersVector.add(l);
	}

	public void removeTableModelListener(TableModelListener l) {
		listenersVector.remove(l);
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// nicht beachten
	}
}