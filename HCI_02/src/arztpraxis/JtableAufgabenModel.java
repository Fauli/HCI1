package arztpraxis;

import java.util.Calendar;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class JtableAufgabenModel implements TableModel  {
	private Vector listenersVector = new Vector();
	Calendar firstWeekDay = Calendar.getInstance();
	int index = 1;

	public JtableAufgabenModel(){
		
	}
	
	public int getColumnCount() {
		return 1;
	}

	public void addAufgabe() {
		index++;
	}

	public int getRowCount() {
		// return personVector.size();
		return index;
	}

	public String getColumnName(int column) {

		return "asdf";

	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return "";
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
		return true;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// nicht beachten
	}
}
