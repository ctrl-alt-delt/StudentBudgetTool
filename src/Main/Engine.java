package Main;
import java.awt.EventQueue;

import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;

import Constants.Constants;
import GUI.Main.MainWindow;
import Model.PrivateGroup.PrivateGroupList;
import Utils.Utils;

public class Engine {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			        Utils.initFolders();
					Utils.initializeDataFromFiles();
					
					MainWindow mainWin = MainWindow.getInstance();
					mainWin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	
	
}
