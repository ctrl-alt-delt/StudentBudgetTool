package Model.Student;

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

public class StudentList 
{
	private static StudentList INSTANCE = new StudentList();
	private ArrayList<Student> StudentList;

	public StudentList() {
		StudentList = new ArrayList<Student>();

	}

	public static StudentList getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new StudentList();
		}

		return INSTANCE;
	}

	public ArrayList<Student> getStudentList() 
	{
		return StudentList;
	}

	public void addStudentToList(Student StudentToAdd) 
	{
		StudentList.add(StudentToAdd);
	}

	public void removeStudentFromList(String StudentToRemove) 
	{
		Iterator i = StudentList.iterator();
		while (i.hasNext()) {
			if (((Student) i.next()).getName().equalsIgnoreCase(StudentToRemove)) {
				i.remove();
			}
		}
	}

	public boolean isStudentInList(String StudentNameToCheck) {
		boolean inList = false;

		for (Student Student : StudentList) {
			if (Student.getName().equalsIgnoreCase(StudentNameToCheck)) {
				inList = true;
				break;
			}
		}

		return inList;
	}

	public int getStudentIndex(String StudentNameToFind) {
		int idx = -1;

		for (Student Student : StudentList) {
			if (Student.getName().equalsIgnoreCase(StudentNameToFind)) {
				idx = StudentList.indexOf(Student);
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
					String nickname = tokens[1].trim();
					String birthday = tokens[2].trim();
					String phone = tokens[3].trim();
					String addr = tokens[4].trim();
					String school = tokens[5].trim();
					String grade = tokens[6].trim();
					String parentName = tokens[7].trim();
					String parentPhone = tokens[8].trim();
					Student Student = new Student(name, nickname, birthday, phone, addr, school, grade, parentName, parentPhone);
					StudentList.add(Student);

					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				Utils.logError(e);
			}
		}
	}

	public void saveToCSV() {
		File file = new File(Constants.STUDENTFILEPATH);
		file.getParentFile().mkdirs();
		file.delete();
		String fileString = "";

		for (Student Student : StudentList) {
			fileString = fileString.concat(Student.getName() + "," + 
					Student.getNickname() + "," + 
					Student.getBirthday() + "," + 
					Student.getPhone() + "," + 
					Student.getAddr() + "," + 
					Student.getSchool() + "," + 
					Student.getGrade() + "," + 
					Student.getParentName() + "," + 
					Student.getParentPhone() + "," + 
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
