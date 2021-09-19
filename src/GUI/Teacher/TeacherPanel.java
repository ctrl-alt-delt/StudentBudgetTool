package GUI.Teacher;
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
import Model.Teacher.Teacher;
import Model.Teacher.TeacherList;
import Model.Teacher.TeacherTableModel;

public class TeacherPanel extends JPanel {

	private static TeacherPanel INSTANCE = new TeacherPanel();
	public JTable teacherTable;
	public TeacherTableModel model = new TeacherTableModel();

	public TeacherPanel() {
		setLayout(new BorderLayout(0, 0));

		teacherTable = new JTable(model);
		teacherTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableCellRenderer rendererFromHeader = teacherTable.getTableHeader().getDefaultRenderer();
		JLabel headerLabel = (JLabel) rendererFromHeader;
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		JScrollPane scrollPane = new JScrollPane(teacherTable);
		add(scrollPane, BorderLayout.CENTER);

		JPanel teacherBtnPanel = new JPanel();
		add(teacherBtnPanel, BorderLayout.SOUTH);

		JButton btnAddTeacher = new JButton("Add Teacher...");
		btnAddTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TeacherDialog dialog = new TeacherDialog();
				dialog.setVisible(true);
			}
		});
		teacherBtnPanel.add(btnAddTeacher);

		JButton btnEditTeacher = new JButton("Edit Teacher...");
		btnEditTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = teacherTable.getSelectedRow();
				if (row != -1) {
					Teacher privGroup = TeacherList.getInstance().getTeacherList().get(row);
					TeacherDialog dialog = new TeacherDialog(privGroup);
					dialog.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(INSTANCE, "Please select a row.");
				}
			}
		});
		teacherBtnPanel.add(btnEditTeacher);

		JButton btnRemoveTeacher = new JButton("Remove Teacher");
		btnRemoveTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = teacherTable.getSelectedRow();
				if (row != -1) {
					String groupName = (String) teacherTable.getValueAt(row, 0);
					TeacherList.getInstance().removeTeacherFromList(groupName);
					teacherTable.setModel(new TeacherTableModel());
					Summary.getInstance().refreshTotals();
					SummaryPanel.getInstance().refreshTable();
				} else {
					JOptionPane.showMessageDialog(INSTANCE, "Please select a row.");
				}
			}
		});
		teacherBtnPanel.add(btnRemoveTeacher);
	}

	public static TeacherPanel getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new TeacherPanel();
		}

		return INSTANCE;
	}
}
