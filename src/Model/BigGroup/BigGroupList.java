package Model.BigGroup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import Constants.Constants;
import Utils.Utils;

public class BigGroupList {

	private static BigGroupList INSTANCE = new BigGroupList();
	private ArrayList<BigGroup> bigGroupList;


	public BigGroupList() {
		bigGroupList = new ArrayList<BigGroup>();
	}

	public static BigGroupList getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new BigGroupList();
		}

		return INSTANCE;
	}

	public ArrayList<BigGroup> getBigGroupList() {
		return bigGroupList;
	}

	public void addBigGroupToList(BigGroup bigGroupToAdd) {
		bigGroupList.add(bigGroupToAdd);
	}

	public void removeGroupFromList(String bigGroupToRemove) {
		Iterator i = bigGroupList.iterator();
		while (i.hasNext()) {
			if (((BigGroup) i.next()).getName().equalsIgnoreCase(bigGroupToRemove)) {
				i.remove();
			}
		}
	}

	public boolean isGroupInList(String groupNameToCheck) {
		boolean inList = false;

		for (BigGroup bigGroup : bigGroupList) {
			if (bigGroup.getName().equalsIgnoreCase(groupNameToCheck)) {
				inList = true;
				break;
			}
		}

		return inList;
	}

	public int getGroupIndex(String groupNameToFind) {
		int idx = -1;
		for (BigGroup bigGroup : bigGroupList) {
			if (bigGroup.getName().equalsIgnoreCase(groupNameToFind)) {
				idx = bigGroupList.indexOf(bigGroup);
				break;
			}
		}

		return idx;
	}

	public void readFromCSV(String filename) {
		File file = new File(filename);

		if (file.exists() && file.isFile()) {
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(file));
				String line = reader.readLine();
				while (line != null) {
					String[] tokens = line.trim().split(",");
					String groupName = tokens[0].trim();
					int numStudents = Integer.parseInt(tokens[1].trim());
					double price = Double.parseDouble(tokens[2].trim());
					BigGroup bigGroup = new BigGroup(groupName, numStudents, price);
					bigGroupList.add(bigGroup);

					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				Utils.logError(e);
			}
		}
	}

	public void saveToCSV() {
		File file = new File(Constants.BIGGROUPFILEPATH);
		file.getParentFile().mkdirs();
		file.delete();
		String fileString = "";

		for (BigGroup bigGroup : bigGroupList) {
			fileString = fileString.concat(bigGroup.getName() + "," + bigGroup.getNumStudents() + "," + bigGroup.getPrice() + "," + bigGroup.getTotal() + System.lineSeparator());
		}

		try {
			file.createNewFile();

		BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true));
			writer.write(fileString);
			writer.close();
		} catch (IOException e) {
			Utils.logError(e);
		}
	}
}
