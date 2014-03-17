package com.example;

import com.example.Person.Gender;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TableView extends JFrame {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TableView frame = new TableView();
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
    public TableView() throws ParseException {
        setTitle("Personen");
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person(Gender.MALE, "Frank", "Müller", dateFormat.parse("24.10.1973")));
        personList.add(new Person(Gender.FEMALE, "Katharina", "Müller", dateFormat.parse("08.05.1981")));
        personList.add(new Person(Gender.FEMALE, "Sophie", "Müller", dateFormat.parse("01.05.2005")));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new PersonTableModel(personList));
        table.setDefaultRenderer(Person.Gender.class, new PersonGenderRenderer());
        table.setDefaultRenderer(Date.class, new DateRenderer());
        table.setAutoCreateRowSorter(true);
    }

    private static class PersonGenderRenderer extends DefaultTableCellRenderer {
        public void setValue(Object value) {
            if (value == Person.Gender.MALE) {
                setText("m");
            } else {
                setText("f");
            }
        }
    }

    private static class DateRenderer extends DefaultTableCellRenderer {
        public void setValue(Object value) {
            if (value == null) {
                setText("");
                return;
            }

            setText(dateFormat.format(value));
        }
    }
}
