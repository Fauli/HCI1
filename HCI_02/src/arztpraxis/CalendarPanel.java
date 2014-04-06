package arztpraxis;

import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

		
		JTable theTable = new JTable(tableDaysModel);
		
		tableDaysModel.setFirtWeekDay(cal);
		for (int i = 0; i < 6; i++) {
//			f�r jede tag
			Calendar cali = cal;
			cali.add(Calendar.DATE, i);
			DayClass  day = new DayClass(cal);
//			get all appointments --> hinzuef�ge zu DayClass
		
//			fucking static shit
//			JTableDaysModel.addDay(day);
			
		}
		
		add(new JScrollPane(theTable));

		
//		add(theTable);
		

	}

}
