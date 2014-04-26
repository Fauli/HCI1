package arztpraxis;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class AufgabenPanel extends JPanel implements TableModelListener {
	final JtableAufgabenModel tableDaysModel = new JtableAufgabenModel();
	final JTable theTable = new JTable(tableDaysModel);

	public AufgabenPanel() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

		JButton btnNewButton = new JButton("Aufgabe hinzufügen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aufgabe newAufgabe = new Aufgabe("");
				tableDaysModel.addAufgabe(newAufgabe);
				repaintTable();
				System.out.println("added aufgabe: "
						+ tableDaysModel.getRowCount());
			}
		});
		add(btnNewButton, BorderLayout.NORTH);
		add(new JScrollPane(theTable));
	}

	public void repaintTable() {
		theTable.repaint();
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.toString());
		//tableDaysModel.addAufgabe(aufgabe);
	}
}
