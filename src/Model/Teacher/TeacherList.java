package Model.Teacher;

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

public class TeacherList 
{
	private static TeacherList INSTANCE = new TeacherList();
	private ArrayList<Teacher> teacherList;

	public TeacherList() {
		teacherList = new ArrayList<Teacher>();

	}

	public static TeacherList getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new TeacherList();
		}

		return INSTANCE;
	}

	public ArrayList<Teacher> getTeacherList() 
	{
		return teacherList;
	}

	public void addTeacherToList(Teacher teacherToAdd) 
	{
		teacherList.add(teacherToAdd);
	}

	public void removeTeacherFromList(String teacherToRemove) 
	{
		Iterator i = teacherList.iterator();
		while (i.hasNext()) {
			if (((Teacher) i.next()).getName().equalsIgnoreCase(teacherToRemove)) {
				i.remove();
			}
		}
	}

	public boolean isTeacherInList(String teacherNameToCheck) {
		boolean inList = false;

		for (Teacher teacher : teacherList) {
			if (teacher.getName().equalsIgnoreCase(teacherNameToCheck)) {
				inList = true;
				break;
			}
		}

		return inList;
	}

	public int getTeacherIndex(String teacherNameToFind) {
		int idx = -1;

		for (Teacher teacher : teacherList) {
			if (teacher.getName().equalsIgnoreCase(teacherNameToFind)) {
				idx = teacherList.indexOf(teacher);
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
					String name = tokens[0].trim();
					String addr = tokens[1].trim();
					String birthday = tokens[2].trim();
					String phone = tokens[3].trim();
					String email = tokens[4].trim();
					String lineId = tokens[5].trim();
					Teacher teacher = new Teacher(name, addr, birthday, phone, email, lineId);
					teacherList.add(teacher);

					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				Utils.logError(e);
			}
		}
	}

	public void saveToCSV() {
		File file = new File(Constants.TEACHERFILEPATH);
		file.getParentFile().mkdirs();
		file.delete();
		String fileString = "";

		for (Teacher teacher : teacherList) {
			fileString = fileString.concat(teacher.getName() + "," + 
					teacher.getAddr() + "," + 
					teacher.getBirthday() + "," + 
					teacher.getPhone() + "," + 
					teacher.getEmail() + "," + 
					teacher.getLineId() + 
					System.lineSeparator());
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
