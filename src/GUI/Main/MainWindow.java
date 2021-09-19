package GUI.Main;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import GUI.BigGroup.BigGroupPanel;
import GUI.Menu.Menu;
import GUI.PrivateGroup.PrivateGroupPanel;
import GUI.Student.StudentPanel;
import GUI.Summary.SummaryPanel;
import GUI.Teacher.TeacherPanel;
import Utils.Utils;

public class MainWindow extends JFrame {

	private static MainWindow INSTANCE = new MainWindow();
	JTabbedPane tabbedPane;

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Student Budget Tool");
		setBounds(100, 100, 785, 433);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);
		
		JPanel privateGroupPanel = PrivateGroupPanel.getInstance();
		tabbedPane.addTab("Private Groups", null, privateGroupPanel, null);
		
		JPanel bigGroupPanel = BigGroupPanel.getInstance();
		tabbedPane.addTab("Big Groups", null, bigGroupPanel, null);
		
		JPanel summaryPanel = SummaryPanel.getInstance();
		tabbedPane.addTab("Summary", null, summaryPanel, null);
		
		JPanel studentPanel = StudentPanel.getInstance();
		tabbedPane.addTab("Students", null, studentPanel, null);
		
		JPanel teacherPanel = TeacherPanel.getInstance();
		tabbedPane.addTab("Teachers", null, teacherPanel, null);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	Utils.saveAllTables();
		    }
		});
		
		setJMenuBar(Menu.getInstance());
	}
	
	public int getSelectedTab() 
	{
		return tabbedPane.getSelectedIndex();
	}
	
	public static MainWindow getInstance() 
	{
		if (null == INSTANCE) {
			INSTANCE = new MainWindow();
		}
		
		return INSTANCE;
	}
}
