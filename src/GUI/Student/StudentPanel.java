package GUI.Student;
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
import Model.Student.Student;
import Model.Student.StudentList;
import Model.Student.StudentTableModel;

public class StudentPanel extends JPanel {

	private static StudentPanel INSTANCE = new StudentPanel();
	public JTable StudentTable;
	public StudentTableModel model = new StudentTableModel();

	public StudentPanel() {
		setLayout(new BorderLayout(0, 0));

		StudentTable = new JTable(model);
		StudentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableCellRenderer rendererFromHeader = StudentTable.getTableHeader().getDefaultRenderer();
		JLabel headerLabel = (JLabel) rendererFromHeader;
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		JScrollPane scrollPane = new JScrollPane(StudentTable);
		add(scrollPane, BorderLayout.CENTER);

		JPanel StudentBtnPanel = new JPanel();
		add(StudentBtnPanel, BorderLayout.SOUTH);

		JButton btnAddStudent = new JButton("Add Student...");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StudentDialog dialog = new StudentDialog();
				dialog.setVisible(true);
			}
		});
		StudentBtnPanel.add(btnAddStudent);

		JButton btnEditStudent = new JButton("Edit Student...");
		btnEditStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = StudentTable.getSelectedRow();
				if (row != -1) {
					Student privGroup = StudentList.getInstance().getStudentList().get(row);
					StudentDialog dialog = new StudentDialog(privGroup);
					dialog.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(INSTANCE, "Please select a row.");
				}
			}
		});
		StudentBtnPanel.add(btnEditStudent);

		JButton btnRemoveStudent = new JButton("Remove Student");
		btnRemoveStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = StudentTable.getSelectedRow();
				if (row != -1) {
					String groupName = (String) StudentTable.getValueAt(row, 0);
					StudentList.getInstance().removeStudentFromList(groupName);
					StudentTable.setModel(new StudentTableModel());
					Summary.getInstance().refreshTotals();
					SummaryPanel.getInstance().refreshTable();
				} else {
					JOptionPane.showMessageDialog(INSTANCE, "Please select a row.");
				}
			}
		});
		StudentBtnPanel.add(btnRemoveStudent);
	}

	public static StudentPanel getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new StudentPanel();
		}

		return INSTANCE;
	}
}
