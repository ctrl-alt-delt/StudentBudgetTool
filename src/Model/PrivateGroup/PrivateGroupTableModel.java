package Model.PrivateGroup;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class PrivateGroupTableModel extends AbstractTableModel {
	
	private String[] columnNames = {"Group Name", "Rate", "Times They've Come", "Total"};
	private ArrayList<PrivateGroup> privateGroups;
	public PrivateGroupTableModel() {
		privateGroups = PrivateGroupList.getInstance().getPrivGroupList();
	}

	@Override
	public int getRowCount() {
		int size;
		if (privateGroups == null) {
			size = 0;
		} else {
			size = privateGroups.size();
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
			temp = privateGroups.get(rowIndex).getName();
		} else if (columnIndex == 1) {
			temp = privateGroups.get(rowIndex).getRate();
		} else if (columnIndex == 2) {
			temp = privateGroups.get(rowIndex).getFrequency();
		} else if (columnIndex == 3) {
			temp = privateGroups.get(rowIndex).getTotal();
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