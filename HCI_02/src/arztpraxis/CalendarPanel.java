package arztpraxis;

import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

import java.awt.GridLayout;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class CalendarPanel extends JPanel {
	Calendar cal = Calendar.getInstance();
	final JTableDaysModel tableDaysModel = new JTableDaysModel();

	public CalendarPanel() {
		// super(new GridLayout(1, 1));

		cal.set(HOUR_OF_DAY, 0);
		cal.set(MINUTE, 0);
		cal.set(SECOND, 0);
		cal.set(MILLISECOND, 0);
		cal.set(DAY_OF_WEEK, 1);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		tableDaysModel.setFirtWeekDay(cal);

		JTable theTable = new JTable(tableDaysModel);

		add(theTable);
		System.out.println("table added");

		cal.set(MINUTE, 0);
		cal.set(HOUR_OF_DAY, 8);
		for (int i = 0; i <= 36; i++) {
			SimpleDateFormat testFormat = new SimpleDateFormat("HH:mm");
			String startHours = testFormat.format(cal.getTime());
			System.out.println(startHours);

			cal.add(MINUTE, 15);
		}

	}
}
