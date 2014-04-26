package arztpraxis;

import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CalendarPanel extends JPanel {
	Calendar cal = Calendar.getInstance();
	int firstDayOfWeek = 1;
	int dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);

	final JTableDaysModel tableDaysModel = new JTableDaysModel();

	public CalendarPanel() {
		// super(new GridLayout(1, 1));

		drawCalendar();
		Button lastWeekBtn = new Button("Letzte Woche");
		Button nextWeekBtn = new Button("NŠchste Woche");
		this.add(lastWeekBtn);

		final JTable theTable = new JTable(tableDaysModel);

		lastWeekBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				decDayOfYear(7);
				drawCalendar();
				revalidate();
			}
		});

		Calendar boxCalendar = Calendar.getInstance();
		DefaultComboBoxModel<String> dateBoxModel = new DefaultComboBoxModel<String>();

		DateFormat df = new SimpleDateFormat("EEE dd.MM.yyyy");
		boxCalendar.setFirstDayOfWeek(2);
		for (int i = 0; i < 52; i++) {
			System.out.println(df.format(boxCalendar.getTime()));
			boxCalendar.add(Calendar.DATE, 7);
			dateBoxModel.addElement(df.format(boxCalendar.getTime()));
		}

		JComboBox<String> dateBox = new JComboBox<String>(dateBoxModel);
		add(dateBox);

		this.add(nextWeekBtn);
		nextWeekBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				incDayOfYear(7);
				drawCalendar();
				revalidate();

			}
		});

		add(new JScrollPane(theTable));
		theTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = theTable.rowAtPoint(evt.getPoint());
				int col = theTable.columnAtPoint(evt.getPoint());
				System.out.println("row: " + row + " col: " + col);
				AddAppointmentFrame addAppFrame = new AddAppointmentFrame();
				addAppFrame.setVisible(true);
			}
		});

		// add(theTable);

	}

	private void drawCalendar() {
		cal.set(HOUR_OF_DAY, 0);
		cal.set(MINUTE, 0);
		cal.set(SECOND, 0);
		cal.set(MILLISECOND, 0);
		cal.set(DAY_OF_WEEK, 1);
		// cal.set(2014, 4, 6);
		cal.set(Calendar.DAY_OF_YEAR, dayOfYear);
		System.out.println("Setting the day of year to " + dayOfYear);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		// tableDaysModel.clearDays(); -> löscht zu viel
		tableDaysModel.setFirtWeekDay(cal);

		for (int i = 0; i < 6; i++) {
			// für jede tag
			Calendar cali = cal;
			cali.add(Calendar.DATE, i);
			DayClass day = new DayClass(cal);
			// get all appointments --> hinzuefüge zu DayClass

			// fucking static shit
			tableDaysModel.addDay(day);

		}
	}

	private void incDayOfYear(int days) {
		this.dayOfYear = this.dayOfYear + days;
		System.out.println("set day of year to " + dayOfYear);
	}

	private void decDayOfYear(int days) {
		this.dayOfYear = this.dayOfYear - days;
		System.out.println("set day of year to " + dayOfYear);

	}

}
