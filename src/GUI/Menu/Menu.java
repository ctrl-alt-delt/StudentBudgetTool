package GUI.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import GUI.Main.ExportDialog;
import Utils.Utils;

public class Menu extends JMenuBar {
	
	private static Menu INSTANCE = new Menu();
	
	public Menu() {
		JMenu fileMenu = new JMenu("File");
		add(fileMenu);
		
		JMenuItem exportMenuItem = new JMenuItem("Export...");
		fileMenu.add(exportMenuItem);
		exportMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportDialog.getInstance().setVisible(true);	
			}
		});
		
		JMenuItem fileExitMenuItem = new JMenuItem("Exit");
		fileExitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utils.saveAllTables();
				System.exit(0);
			}
		});
		fileMenu.add(fileExitMenuItem);
	}
	
	public static Menu getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new Menu();
		}
		return INSTANCE;
	}
}
