package GUI.BigGroup;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import GUI.Summary.SummaryPanel;
import Model.BigGroup.BigGroup;
import Model.BigGroup.BigGroupList;
import Model.BigGroup.BigGroupTableModel;
import Model.Summary.Summary;

public class BigGroupPanel extends JPanel {

	private static BigGroupPanel INSTANCE = new BigGroupPanel();
	public JTable bigGroupTable;
	public BigGroupTableModel model = new BigGroupTableModel();

	public BigGroupPanel() {
		setLayout(new BorderLayout(0, 0));

		bigGroupTable = new JTable(model);
		bigGroupTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableCellRenderer rendererFromHeader = bigGroupTable.getTableHeader().getDefaultRenderer();
		JLabel headerLabel = (JLabel) rendererFromHeader;
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		JScrollPane scrollPane = new JScrollPane(bigGroupTable);
		add(scrollPane, BorderLayout.CENTER);

		JPanel bigGroupBtnPanel = new JPanel();
		add(bigGroupBtnPanel, BorderLayout.SOUTH);

		JButton btnAddBigGroup = new JButton("Add Big Group...");
		btnAddBigGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BigGroupDialog dialog = new BigGroupDialog();
				dialog.setVisible(true);
			}
		});
		bigGroupBtnPanel.add(btnAddBigGroup);

		JButton btnEditBigGroup = new JButton("Edit Big Group...");
		btnEditBigGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = bigGroupTable.getSelectedRow();
				if (row != -1) {
					BigGroup bigGroup = BigGroupList.getInstance().getBigGroupList().get(row);
					BigGroupDialog dialog = new BigGroupDialog(bigGroup);
					dialog.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(INSTANCE, "Please select a row.");
				}
			}
		});
		bigGroupBtnPanel.add(btnEditBigGroup);

		JButton btnRemoveBigGroup = new JButton("Remove Big Group");
		btnRemoveBigGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = bigGroupTable.getSelectedRow();
				if (row != -1) {
					String groupName = (String) bigGroupTable.getValueAt(row, 0);
					BigGroupList.getInstance().removeGroupFromList(groupName);
					bigGroupTable.setModel(new BigGroupTableModel());
					Summary.getInstance().refreshTotals();
					SummaryPanel.getInstance().refreshTable();
				} else {
					JOptionPane.showMessageDialog(INSTANCE, "Please select a row.");
				}
			}
		});
		bigGroupBtnPanel.add(btnRemoveBigGroup);
	}

	public static BigGroupPanel getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new BigGroupPanel();
		}

		return INSTANCE;
	}
}
