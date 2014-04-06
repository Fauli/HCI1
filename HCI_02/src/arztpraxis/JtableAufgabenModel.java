package arztpraxis;

import java.util.Calendar;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class JtableAufgabenModel implements TableModel  {
	private Vector listenersVector = new Vector();
	private Vector aufgabenVector = new Vector();
	Calendar firstWeekDay = Calendar.getInstance();
	int index = 1;

	public JtableAufgabenModel(){
		Aufgabe addAufgabe=new Aufgabe("Example Aufgabe");
		addAufgabe(addAufgabe);
	}
	
	public void addAufgabe(Aufgabe aufgabe) {
		int index = aufgabenVector.size();
		aufgabenVector.add(aufgabe);

		TableModelEvent e = new TableModelEvent((TableModel) this, index,
				index, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);

		for (int i = 0, n = listenersVector.size(); i < n; i++) {
			((TableModelListener) listenersVector.get(i)).tableChanged(e);
		}
	}
	
	public int getColumnCount() {
		return 1;
	}

	public int getRowCount() {
		// return personVector.size();
		return aufgabenVector.size();
	}

	public String getColumnName(int column) {
		return "Aufgaben";
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Aufgabe aufgabe = (Aufgabe) aufgabenVector.get(rowIndex);
		return aufgabe.getAufgabe();
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
