package Model.BigGroup;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class BigGroupTableModel extends AbstractTableModel {
	
	private String[] columnNames = {"Group Name", "Price", "Num Students", "Total"};
	private ArrayList<BigGroup> bigGroups;
	public BigGroupTableModel() {
		bigGroups = BigGroupList.getInstance().getBigGroupList();
	}

	@Override
	public int getRowCount() {
		int size;
		if (bigGroups == null) {
			size = 0;
		} else {
			size = bigGroups.size();
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
			temp = bigGroups.get(rowIndex).getName();
		} else if (columnIndex == 1) {
			temp = bigGroups.get(rowIndex).getPrice();
		} else if (columnIndex == 2) {
			temp = bigGroups.get(rowIndex).getNumStudents();
		} else if (columnIndex == 3) {
			temp = bigGroups.get(rowIndex).getTotal();
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
			return Double.class;
		} else if (colIdx == 2) {
			return Integer.class;
		} else if (colIdx == 3) {
			return Double.class;
		} else {
			return String.class;
		}
	}
}