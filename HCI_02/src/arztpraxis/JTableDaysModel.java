package arztpraxis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

class JTableDaysModel implements TableModel {
	private Vector listenersVector = new Vector();
	Calendar firstWeekDay = Calendar.getInstance();
	private Vector dayVector = new Vector();

	public JTableDaysModel() {
		DayClass rowLabel = new DayClass();
		addDay(rowLabel);
	}

	public int getColumnCount() {
		return dayVector.size();
	}

	public void addDay(DayClass day) {
		int index = dayVector.size();
		dayVector.add(day);

		TableModelEvent e = new TableModelEvent((TableModel) this, index,
				index, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);

		for (int i = 0, n = listenersVector.size(); i < n; i++) {
			((TableModelListener) listenersVector.get(i)).tableChanged(e);
		}

	}

	public int getRowCount() {
		// return personVector.size();
		return 37;
	}
	
	

	public String getColumnName(int column) {
		if (0 == column) {
			return "Ziitvorschau";
		} else {
			Calendar cali = firstWeekDay;
			cali.add(Calendar.DATE, column - 1);
			Date date = cali.getTime();
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String date1 = format1.format(date);
			return date1;
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		DayClass day = (DayClass) dayVector.get(columnIndex);
		return day.getThingi(rowIndex);
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