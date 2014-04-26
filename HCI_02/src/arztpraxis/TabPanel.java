package arztpraxis;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TabPanel extends JPanel {

	public TabPanel() {
		super(new GridLayout(1, 1));

		JTabbedPane tabbedPane = new JTabbedPane();
		ImageIcon icon = createImageIcon("images/middle.gif");

		JComponent panel11 = makeCalendarPanel();
		JComponent panel12 = makeAufgabenPanel();
		JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel11, panel12);
		splitPane1.setDividerLocation(600);
		tabbedPane.addTab("Michaela Schneider", icon, splitPane1, "Does nothing");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel21 = makeCalendarPanel();
		JComponent panel22 = makeAufgabenPanel();
		JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel21, panel22);
		splitPane2.setDividerLocation(600);
		tabbedPane.addTab("Franz Bodmer", icon, splitPane2, "Does twice as much nothing");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		JComponent panel3 = makeCalendarPanel();
		tabbedPane.addTab("Karin Müller", icon, panel3, "Still does nothing");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		JComponent panel4 = makeCalendarPanel();
		tabbedPane.addTab("Peter Meier", icon, panel4, "Does nothing at all");
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

		JComponent panel5 = makeCalendarPanel();
		tabbedPane.addTab("Sophie Keller ", icon, panel5, "Does nothing at all");
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
		
		JComponent panel6 = makePatientenPanel();
		tabbedPane.addTab("Patienten verwalten", icon, panel6, "Does nothing at all");
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
		
		
		
		// Add the tabbed pane to thi s panel.
		add(tabbedPane);

		// The following line enables to use scrolling tabs.
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	public JComponent makeCalendarPanel() {
		CalendarPanel calendarPanel = new CalendarPanel();
		// calendarPanel.setLayout(new GridLayout(1, 1));
		return calendarPanel;
	}

	public JComponent makeAufgabenPanel() {
		AufgabenPanel aufgabenPanel = new AufgabenPanel();
		// calendarPanel.setLayout(new GridLayout(1, 1));
		return aufgabenPanel;
	}

	public JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}
	
	public JComponent makePatientenPanel() {
		PatientenPanel panel = new PatientenPanel();
		return panel;
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	public static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = TabPanel.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("TabbedPaneDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new TabPanel(), BorderLayout.CENTER);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}

}
