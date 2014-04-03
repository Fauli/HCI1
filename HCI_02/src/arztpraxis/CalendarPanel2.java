package arztpraxis;

import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class CalendarPanel2 extends JPanel {
	Calendar cal = Calendar.getInstance();
	final JTableDaysModel tableDaysModel = new JTableDaysModel();

	public CalendarPanel2() {
		super(new GridLayout(1, 1));

		System.out.println(cal.getTime());
		cal.set(HOUR_OF_DAY, 0);
		cal.set(MINUTE, 0);
		cal.set(SECOND, 0);
		cal.set(MILLISECOND, 0);
		cal.set(DAY_OF_WEEK, 1);

		System.out.println(cal.getTime());
		System.out.println(cal.getWeeksInWeekYear());
		System.out.println(cal.getFirstDayOfWeek());
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		
		

//		for (int i = 0; i <= 6; i++) {
//			Calendar cali = cal;
//			cali.add(Calendar.DATE, 1);
//			JPanel dayPanel = new JPanel();
//			add(dayPanel);
//			JLabel dayLabel = new JLabel("" + cal.getTime());
//			JTable tblCalendar = new JTable();
//
//			dayPanel.add(tblCalendar);
//			dayPanel.add(dayLabel);
//		}
		
		JTable theTable = new JTable(tableDaysModel);
		
		add(theTable);
		
		
		System.out.println(dayOfWeek);
		System.out.println();

	}
}
