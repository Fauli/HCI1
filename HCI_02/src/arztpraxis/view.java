package arztpraxis;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.example.hci1.arztpraxis.domain.Allocation;
import com.example.hci1.arztpraxis.domain.Doctor;
import com.example.hci1.arztpraxis.domain.Patient;
import com.example.hci1.arztpraxis.service.DiaryController;

public class view extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view frame = new view();
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
	public view() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 963, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		//CalendarPanel calendarPanel = new CalendarPanel();
		//contentPane.add(calendarPanel); 
		

		TabPanel tabPanel = new TabPanel();
		contentPane.add(tabPanel);
		
		DiaryController controller = new DiaryController();
		for (Doctor allocation : controller.getDoctors()) {
		System.out.println(allocation);
		}
		
	}

}

