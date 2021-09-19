package GUI.PrivateGroup;
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
import Model.PrivateGroup.PrivateGroupTableModel;
import Model.Summary.Summary;
import Model.PrivateGroup.PrivateGroup;
import Model.PrivateGroup.PrivateGroupList;

public class PrivateGroupPanel extends JPanel {

	private static PrivateGroupPanel INSTANCE = new PrivateGroupPanel();
	public JTable privGroupTable;
	public PrivateGroupTableModel model = new PrivateGroupTableModel();

	public PrivateGroupPanel() {
		setLayout(new BorderLayout(0, 0));

		privGroupTable = new JTable(model);
		privGroupTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		privGroupTable.setAutoCreateRowSorter(true);
		TableCellRenderer rendererFromHeader = privGroupTable.getTableHeader().getDefaultRenderer();
		JLabel headerLabel = (JLabel) rendererFromHeader;
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		JScrollPane scrollPane = new JScrollPane(privGroupTable);
		add(scrollPane, BorderLayout.CENTER);

		JPanel privGroupBtnPanel = new JPanel();
		add(privGroupBtnPanel, BorderLayout.SOUTH);

		JButton btnAddPrivGroup = new JButton("Add Private Group...");
		btnAddPrivGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrivateGroupDialog dialog = new PrivateGroupDialog();
				dialog.setVisible(true);
			}
		});
		privGroupBtnPanel.add(btnAddPrivGroup);

		JButton btnEditPrivGroup = new JButton("Edit Private Group...");
		btnEditPrivGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = privGroupTable.getSelectedRow();
				if (row != -1) {
					PrivateGroup privGroup = PrivateGroupList.getInstance().getPrivGroupList().get(row);
					PrivateGroupDialog dialog = new PrivateGroupDialog(privGroup);
					dialog.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(INSTANCE, "Please select a row.");
				}
			}
		});
		privGroupBtnPanel.add(btnEditPrivGroup);

		JButton btnRemovePrivGroup = new JButton("Remove Private Group");
		btnRemovePrivGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = privGroupTable.getSelectedRow();
				if (row != -1) {
					String groupName = (String) privGroupTable.getValueAt(row, 0);
					PrivateGroupList.getInstance().removeGroupFromList(groupName);
					privGroupTable.setModel(new PrivateGroupTableModel());
					Summary.getInstance().refreshTotals();
					SummaryPanel.getInstance().refreshTable();
				} else {
					JOptionPane.showMessageDialog(INSTANCE, "Please select a row.");
				}
			}
		});
		privGroupBtnPanel.add(btnRemovePrivGroup);
	}

	public static PrivateGroupPanel getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new PrivateGroupPanel();
		}

		return INSTANCE;
	}
}
