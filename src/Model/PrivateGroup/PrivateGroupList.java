package Model.PrivateGroup;

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

public class PrivateGroupList 
{
	private static PrivateGroupList INSTANCE = new PrivateGroupList();
	private ArrayList<PrivateGroup> privGroupList;

	public PrivateGroupList() {
		privGroupList = new ArrayList<PrivateGroup>();

	}

	public static PrivateGroupList getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new PrivateGroupList();
		}

		return INSTANCE;
	}

	public ArrayList<PrivateGroup> getPrivGroupList() 
	{
		return privGroupList;
	}

	public void addPrivGroupToList(PrivateGroup privGroupToAdd) 
	{
		privGroupList.add(privGroupToAdd);
	}

	public void removeGroupFromList(String privGroupToRemove) 
	{
		Iterator i = privGroupList.iterator();
		while (i.hasNext()) {
			if (((PrivateGroup) i.next()).getName().equalsIgnoreCase(privGroupToRemove)) {
				i.remove();
			}
		}
	}

	public boolean isGroupInList(String groupNameToCheck) {
		boolean inList = false;

		for (PrivateGroup privGroup : privGroupList) {
			if (privGroup.getName().equalsIgnoreCase(groupNameToCheck)) {
				inList = true;
				break;
			}
		}

		return inList;
	}

	public int getGroupIndex(String groupNameToFind) {
		int idx = -1;

		for (PrivateGroup privGroup : privGroupList) {
			if (privGroup.getName().equalsIgnoreCase(groupNameToFind)) {
				idx = privGroupList.indexOf(privGroup);
				break;
			}
		}

		return idx;
	}

	public void readFromCSV(String filename) 
	{
		File file = new File(filename);

		if (file.exists() && file.isFile()) {
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(file));
				String line = reader.readLine();
				while (line != null) {
					String[] tokens = line.trim().split(",");
					String groupName = tokens[0].trim();
					int frequency = Integer.parseInt(tokens[1].trim());
					double rate = Double.parseDouble(tokens[2].trim());
					PrivateGroup privGroup = new PrivateGroup(groupName, frequency, rate);
					privGroupList.add(privGroup);

					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				Utils.logError(e);
			}
		}
	}

	public void saveToCSV() {
		File file = new File(Constants.PRIVGROUPFILEPATH);
		file.getParentFile().mkdirs();
		file.delete();
		String fileString = "";

		for (PrivateGroup privGroup : privGroupList) {
			fileString = fileString.concat(privGroup.getName() + "," + privGroup.getFrequency() + "," + privGroup.getRate() + "," + privGroup.getTotal() + System.lineSeparator());
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
