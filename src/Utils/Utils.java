package Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JOptionPane;

import Constants.Constants;
import GUI.Main.MainWindow;
import GUI.Summary.SummaryPanel;
import Model.BigGroup.BigGroupList;
import Model.PrivateGroup.PrivateGroupList;
import Model.Student.StudentList;
import Model.Summary.Summary;
import Model.Teacher.TeacherList;

/**
 * Various utility methods used by the application.
 */
public class Utils {
	
	public static void initFolders() {
		File dataDir = new File(Constants.DATADIR);
		if (false == dataDir.exists()) {
			dataDir.mkdir();
		}

		File logDir = new File(Constants.LOGDIR);
		if (false == logDir.exists()) {
			logDir.mkdir();
		}
	}

	/**
	 * Saves all the tables to their CSV files.
	 */
	public static void saveAllTables() 
	{
		PrivateGroupList.getInstance().saveToCSV();
		BigGroupList.getInstance().saveToCSV();
		TeacherList.getInstance().saveToCSV();
		StudentList.getInstance().saveToCSV();
	}

	/**
	 * Initializes the data tables based on the data in the CSV files.
	 */
	public static void initializeDataFromFiles() 
	{
		PrivateGroupList.getInstance().readFromCSV(Constants.PRIVGROUPFILEPATH);
		BigGroupList.getInstance().readFromCSV(Constants.BIGGROUPFILEPATH);
		TeacherList.getInstance().readFromCSV(Constants.TEACHERFILEPATH);
		StudentList.getInstance().readFromCSV(Constants.STUDENTFILEPATH);
		Summary.getInstance().refreshTotals();
		SummaryPanel.getInstance().refreshTable();
	}

	public static void logError(Exception ex) {
		File logFile = new File(Constants.LOGFILEPATH);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		
		if (false == logFile.exists()) {
			//logFile.getParentFile().mkdirs();
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(MainWindow.getInstance(), "Error creating log file: " + e.getMessage());
			}
		}

		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(logFile.getAbsolutePath(), true));
			writer.write(sw.toString());
			writer.write(System.lineSeparator());
			writer.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(MainWindow.getInstance(), "Error logging to log file: " + e.getMessage());
		}	

	}
}
