package Model.Teacher;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TeacherTableModel extends AbstractTableModel {
	
	private String[] columnNames = {"Name", "Address", "Birthday", "Phone", "LineID"};
	private ArrayList<Teacher> teacherList;
	public TeacherTableModel() {
		teacherList = TeacherList.getInstance().getTeacherList();
	}

	@Override
	public int getRowCount() {
		int size;
		if (teacherList == null) {
			size = 0;
		} else {
			size = teacherList.size();
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
			temp = teacherList.get(rowIndex).getName();
		} else if (columnIndex == 1) {
			temp = teacherList.get(rowIndex).getAddr();
		} else if (columnIndex == 2) {
			temp = teacherList.get(rowIndex).getBirthday();
		} else if (columnIndex == 3) {
			temp = teacherList.get(rowIndex).getPhone();
		} else if (columnIndex == 4) {
			temp = teacherList.get(rowIndex).getLineId();
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
		} else {
			return String.class;
		}
	}
}