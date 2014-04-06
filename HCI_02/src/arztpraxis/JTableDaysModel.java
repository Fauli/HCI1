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

	public int getColumnCount() {
		return 6;
	}

	public int getRowCount() {
//		return personVector.size();
		return 0;
	}

	public String getColumnName(int column) {
		Calendar cali = firstWeekDay;
		cali.add(Calendar.DATE, column);
		Date date = cali.getTime();  
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = format1.format(date);
		return date1;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
//		Calendar person = (Calendar) personVector.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return "ahoi1";
		case 1:
			return "ahoi2";
		case 2:
			return "ahoi3";
		case 3:
			return "ahoi4";
		default:
			return "bllubbbbbbb";
		}
	}

	public Class getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		default:
			return null;
		}
	}

	public void setFirtWeekDay(Calendar firstWeekDay) {
		this.firstWeekDay = firstWeekDay;
		
		int index = 1;
		TableModelEvent e = new TableModelEvent((TableModel) this, index,
				index, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
		
		for (int i = 0, n = listenersVector.size(); i < n; i++) {
			((TableModelListener) listenersVector.get(i)).tableChanged(e);
		}

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