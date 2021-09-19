package GUI.Summary;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Model.Summary.Summary;

public class SummaryPanel extends JPanel {

	private static SummaryPanel INSTANCE = new SummaryPanel();

	DefaultTableModel model;
	public JTable summaryTable;


	public SummaryPanel() {
		setLayout(new BorderLayout(0, 0));

		model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Value");
        summaryTable = new JTable(model);
        TableCellRenderer rendererFromHeader = summaryTable.getTableHeader().getDefaultRenderer();
		JLabel headerLabel = (JLabel) rendererFromHeader;
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		JScrollPane scrollPane = new JScrollPane(summaryTable);
		add(scrollPane, BorderLayout.CENTER);
	}

	public static SummaryPanel getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new SummaryPanel();
		}

		return INSTANCE;
	}

	public void refreshTable() {
		model.setRowCount(0);
		model.addRow(new Object[] {"Private Group Total", Summary.getInstance().getPrivGroupTotal()});
		model.addRow(new Object[] {"Big Group Total", Summary.getInstance().getBigGroupTotal()});
		model.addRow(new Object[] {"Overall Total", Summary.getInstance().getOverallTotal()});
		model.addRow(new Object[] {"", ""}); 
		model.addRow(new Object[] {"School Cut", Summary.getInstance().getSchoolCut()});
		model.addRow(new Object[] {"Teacher Cut (each)", Summary.getInstance().getTeacherCut()});
		model.addRow(new Object[] {"Teacher Saves (each)", Summary.getInstance().getTeacherSaves()});
		summaryTable.setModel(model);
	}
}
