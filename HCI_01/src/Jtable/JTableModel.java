package Jtable;

import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

class JTableModel implements TableModel {
	private Vector personVector = new Vector();
	private Vector listenersVector = new Vector();

	public void addPerson(Person2 person1) {
		int index = personVector.size();
		personVector.add(person1);

		TableModelEvent e = new TableModelEvent((TableModel) this, index,
				index, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);

		for (int i = 0, n = listenersVector.size(); i < n; i++) {
			((TableModelListener) listenersVector.get(i)).tableChanged(e);
		}
	}

	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return personVector.size();
	}

	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Firstname";
		case 1:
			return "Lastname";
		case 2:
			return "Gender";
		case 3:
			return "Birthday";
		default:
			return null;
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Person2 person = (Person2) personVector.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return person.getFirstName();
		case 1:
			return person.getLastName();
		case 2:
			return person.getGender();
		case 3:
			return person.getBirthday();
		default:
			return null;
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