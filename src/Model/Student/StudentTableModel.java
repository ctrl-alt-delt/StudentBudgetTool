package Model.Student;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class StudentTableModel extends AbstractTableModel {
	
	private String[] columnNames = {"Name", "Nickname", "Birthday", "Phone", "Address", "School", "Grade", "Parent Name", "Parent Phone"};
	private ArrayList<Student> stuList;
	public StudentTableModel() {
		stuList = StudentList.getInstance().getStudentList();
	}

	@Override
	public int getRowCount() {
		int size;
		if (stuList == null) {
			size = 0;
		} else {
			size = stuList.size();
		}
		
		return size;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object temp = null;
		if (columnIndex == 0) {
			temp = stuList.get(rowIndex).getName();
		} else if (columnIndex == 1) {
			temp = stuList.get(rowIndex).getNickname();
		} else if (columnIndex == 2) {
			temp = stuList.get(rowIndex).getBirthday();
		} else if (columnIndex == 3) {
			temp = stuList.get(rowIndex).getPhone();
		} else if (columnIndex == 4) {
			temp = stuList.get(rowIndex).getAddr();
		} else if (columnIndex == 5) {
			temp = stuList.get(rowIndex).getSchool();
		} else if (columnIndex == 6) {
			temp = stuList.get(rowIndex).getGrade();
		} else if (columnIndex == 7) {
			temp = stuList.get(rowIndex).getParentName();
		} else if (columnIndex == 8) {
			temp = stuList.get(rowIndex).getParentPhone();
		}
		
		return temp;
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Class getColumnClass(int colIdx) {
		if (colIdx == 0) {
			return String.class;
		} else if (colIdx == 1) {
			return String.class;
		} else if (colIdx == 2) {
			return String.class;
		} else if (colIdx == 3) {
			return String.class;
		} else if (colIdx == 4) {
			return String.class;
		} else if (colIdx == 5) {
			return String.class;
		} else if (colIdx == 6) {
			return String.class;
		} else if (colIdx == 7) {
			return String.class;
		} else {
			return String.class;
		}
	}
}