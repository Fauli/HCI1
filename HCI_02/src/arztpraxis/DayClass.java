package arztpraxis;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.hci1.arztpraxis.domain.Appointment;

public class DayClass {
	Calendar day;
	private String[] thingi = new String[37];

	public DayClass(Calendar day) {
		this.day = day;
	}

	public void addAppointment(Appointment appointment) {
//		add appointment to this day
		int from = (appointment.getStart().get(HOUR_OF_DAY) - 8) * 4 + appointment.getStart().get(MINUTE) / 15;
		int to = (appointment.getEnd().get(HOUR_OF_DAY) - 8) * 4 + appointment.getEnd().get(MINUTE) / 15;
		
		for (int i = from; i <= to; i++) {
			thingi[i] = "42";
		}

	}

	public DayClass() {
		Calendar cal = Calendar.getInstance();
		cal.set(MINUTE, 0);
		cal.set(HOUR_OF_DAY, 8);
		for (int i = 0; i <= 36; i++) {
			SimpleDateFormat testFormat = new SimpleDateFormat("HH:mm");
			String startHours = testFormat.format(cal.getTime());
			thingi[i] = startHours;
			cal.add(MINUTE, 15);
		}
	}

	public String getThingi(int index) {
		return thingi[index];
	}
}
