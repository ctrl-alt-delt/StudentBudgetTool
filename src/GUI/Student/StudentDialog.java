package GUI.Student;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import GUI.PrivateGroup.PrivateGroupPanel;
import GUI.Summary.SummaryPanel;
import Model.Student.Student;
import Model.Student.StudentList;
import Model.Student.StudentTableModel;
import Model.Summary.Summary;

public class StudentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField nicknameField;
	private Student StudentPassedIn = null;
	private JTextField birthdayField;
	private JTextField phoneField;
	private JTextField addrField;
	private JTextField schoolField;
	private JTextField gradeField;
	private JTextField parentNameField;
	private JTextField parentPhoneField;

	public StudentDialog() {
		setResizable(false);
		initGUI();
	}

	public StudentDialog(Student Student) {
		StudentPassedIn = Student;
		initGUI();
	}

	void initGUI() {
		setTitle("Student");
		setBounds(100, 100, 264, 376);
		setLocationRelativeTo(StudentPanel.getInstance());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPanel.setLayout(new GridLayout(9, 2, 0, 10));
		
		JLabel nameLabel = new JLabel("Name");
		contentPanel.add(nameLabel);
		nameField = new JTextField();
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		JLabel Nickname = new JLabel("Nickname");
		contentPanel.add(Nickname);
		nicknameField = new JTextField();
		contentPanel.add(nicknameField);
		nicknameField.setColumns(10);
		
		JLabel birthdayLabel = new JLabel("Birthday");
		contentPanel.add(birthdayLabel);
		birthdayField = new JTextField();
		contentPanel.add(birthdayField);
		birthdayField.setColumns(10);
		
		JLabel phoneLabel = new JLabel("Phone");
		contentPanel.add(phoneLabel);
		phoneField = new JTextField();
		contentPanel.add(phoneField);
		phoneField.setColumns(10);
		
		JLabel addrLabel = new JLabel("Address");
		contentPanel.add(addrLabel);
		addrField = new JTextField();
		contentPanel.add(addrField);
		addrField.setColumns(10);
		
		JLabel schoolLabel = new JLabel("School");
		contentPanel.add(schoolLabel);
		schoolField = new JTextField();
		contentPanel.add(schoolField);
		schoolField.setColumns(10);
		
		JLabel gradeLabel = new JLabel("Grade");
		contentPanel.add(gradeLabel);
		gradeField = new JTextField();
		contentPanel.add(gradeField);
		gradeField.setColumns(10);
		
		JLabel parentLabel = new JLabel("Parent Name");
		contentPanel.add(parentLabel);
		parentNameField = new JTextField();
		contentPanel.add(parentNameField);
		parentNameField.setColumns(10);
		
		JLabel parentPhoneLabel = new JLabel("Parent Phone");
		contentPanel.add(parentPhoneLabel);
		parentPhoneField = new JTextField();
		contentPanel.add(parentPhoneField);
		parentPhoneField.setColumns(10);
		
		if (null != StudentPassedIn) {
			nameField.setText(StudentPassedIn.getName());
			nicknameField.setText(StudentPassedIn.getAddr());
			birthdayField.setText(StudentPassedIn.getBirthday().toString());
			phoneField.setText(StudentPassedIn.getPhone());
			addrField.setText(StudentPassedIn.getAddr());
			addrField.setText(StudentPassedIn.getAddr());
			schoolField.setText(StudentPassedIn.getSchool());
			gradeField.setText(StudentPassedIn.getGrade());
			parentNameField.setText(StudentPassedIn.getParentName());
			parentPhoneField.setText(StudentPassedIn.getParentPhone());
		}

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		/**
		 * OK Button
		 */
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText().length() > 0 &&
					nicknameField.getText().length() > 0 &&
					birthdayField.getText().length() > 0 && 
					phoneField.getText().length() > 0 &&
					addrField.getText().length() > 0 &&
					schoolField.getText().length() > 0 &&
					gradeField.getText().length() > 0 &&
					parentNameField.getText().length() > 0 &&
					parentPhoneField.getText().length() > 0) {
						
					final String name = nameField.getText().trim();
					final String nickname = nicknameField.getText().trim();
					final String birthday = birthdayField.getText().trim();
					final String phone = phoneField.getText().trim();
					final String addr = addrField.getText().trim();
					final String school = schoolField.getText().trim();
					final String grade = gradeField.getText().trim();
					final String parentName = parentNameField.getText().trim();
					final String parentPhone = parentPhoneField.getText().trim();

					// If the group passed in is in our list, edit that entry.
					// Otherwise, just add to the list.
					if (null != StudentPassedIn) {
						int idx = StudentList.getInstance().getStudentList().indexOf(StudentPassedIn);
						StudentList.getInstance().getStudentList().get(idx).setName(name);
						StudentList.getInstance().getStudentList().get(idx).setNickname(nickname);
						StudentList.getInstance().getStudentList().get(idx).setBirthday(birthday);
						StudentList.getInstance().getStudentList().get(idx).setPhone(phone);
						StudentList.getInstance().getStudentList().get(idx).setAddr(addr);
						StudentList.getInstance().getStudentList().get(idx).setSchool(school);
						StudentList.getInstance().getStudentList().get(idx).setGrade(grade);
						StudentList.getInstance().getStudentList().get(idx).setParentName(parentName);
						StudentList.getInstance().getStudentList().get(idx).setParentPhone(parentPhone);
					} else {
						StudentList.getInstance().addStudentToList(new Student(name, nickname, birthday, phone, addr, school, grade, parentName, parentPhone));
					}
					
					StudentPanel.getInstance().StudentTable.setModel(new StudentTableModel());
					Summary.getInstance().refreshTotals();
					SummaryPanel.getInstance().refreshTable();
					dispose();
				} else {
					JOptionPane.showMessageDialog(PrivateGroupPanel.getInstance(), "Fields were empty.");
				}
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		/**
		 * Cancel Button
		 */
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
}