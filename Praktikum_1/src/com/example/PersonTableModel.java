package com.example;

import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.List;

public class PersonTableModel extends AbstractTableModel {

    private enum Column {
        FIRST_NAME, LAST_NAME, BIRTHDAY, GENDER
    }

    private List<Person> personList;

    public PersonTableModel(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public int getRowCount() {
        return personList.size();
    }

    @Override
    public int getColumnCount() {
        return Column.values().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == Column.GENDER.ordinal()) {
            return personList.get(rowIndex).getGender();
        } else if (columnIndex == Column.FIRST_NAME.ordinal()) {
            return personList.get(rowIndex).getFirstName();
        } else if (columnIndex == Column.LAST_NAME.ordinal()) {
            return personList.get(rowIndex).getLastName();
        } else if (columnIndex == Column.BIRTHDAY.ordinal()) {
            return personList.get(rowIndex).getBirthday();
        }

        throw new IllegalArgumentException("Column at index " + columnIndex
                + " is unknown.");
    }

    @Override
    public String getColumnName(int column) {
        if (column == Column.GENDER.ordinal()) {
            return "Geschlecht";
        } else if (column == Column.FIRST_NAME.ordinal()) {
            return "Vorname";
        } else if (column == Column.LAST_NAME.ordinal()) {
            return "Nachname";
        } else if (column == Column.BIRTHDAY.ordinal()) {
            return "Geburtstag";
        }

        throw new IllegalArgumentException("No name specified for column at "
                + "index " + column + ".");
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == Column.GENDER.ordinal()) {
            return Person.Gender.class;
        }
        if (columnIndex == Column.BIRTHDAY.ordinal()) {
            return Date.class;
        }
        return super.getColumnClass(columnIndex);
    }
}
