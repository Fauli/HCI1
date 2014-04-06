package arztpraxis;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.hci1.arztpraxis.domain.Appointment;

public class DayClass {
	Calendar day;
	private String[] thingi = new String[36];

	public DayClass(Calendar day) {
		this.day = day;
	}

	public void addAppointment(Appointment appointment) {
		Date dateStart = (Date) appointment.getStart().getTime();             
		Date dateEnd = (Date) appointment.getEnd().getTime();    
		SimpleDateFormat hours = new SimpleDateFormat("hh");
		SimpleDateFormat minuts = new SimpleDateFormat("mm");
		String startHours = hours.format(dateStart);            
		String startMinuts = minuts.format(dateStart);
		String endHours = hours.format(dateStart);            
		String endMinuts = minuts.format(dateStart);
	}
	
	public void setDayAsLabel(){
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
}
