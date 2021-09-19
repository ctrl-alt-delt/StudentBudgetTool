package GUI.Teacher;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import GUI.PrivateGroup.PrivateGroupPanel;
import GUI.Summary.SummaryPanel;
import Model.Summary.Summary;
import Model.Teacher.Teacher;
import Model.Teacher.TeacherList;
import Model.Teacher.TeacherTableModel;

public class TeacherDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField addrField;
	private Teacher teacherPassedIn = null;
	private JTextField birthdayField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField lineIdField;

	public TeacherDialog() {
		setResizable(false);
		initGUI();
	}

	public TeacherDialog(Teacher teacher) {
		teacherPassedIn = teacher;
		initGUI();
	}

	void initGUI() {
		setTitle("Teacher");
		setBounds(100, 100, 264, 275);
		setLocationRelativeTo(TeacherPanel.getInstance());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPanel.setLayout(new GridLayout(6, 2, 0, 10));
		
		JLabel nameLabel = new JLabel("Name");
		contentPanel.add(nameLabel);
		nameField = new JTextField();
		contentPanel.add(nameField);
		nameField.setColumns(10);
		
		JLabel addrLabel = new JLabel("Address");
		contentPanel.add(addrLabel);
		addrField = new JTextField();
		contentPanel.add(addrField);
		addrField.setColumns(10);
		
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
		
		JLabel emailLabel = new JLabel("Email");
		contentPanel.add(emailLabel);
		emailField = new JTextField();
		contentPanel.add(emailField);
		emailField.setColumns(10);
		
		JLabel lineIdLabel = new JLabel("Line ID");
		contentPanel.add(lineIdLabel);
		lineIdField = new JTextField();
		contentPanel.add(lineIdField);
		lineIdField.setColumns(10);
		
		if (null != teacherPassedIn) {
			nameField.setText(teacherPassedIn.getName());
			addrField.setText(teacherPassedIn.getAddr());
			birthdayField.setText(teacherPassedIn.getBirthday().toString());
			phoneField.setText(teacherPassedIn.getPhone());
			emailField.setText(teacherPassedIn.getEmail());
			lineIdField.setText(teacherPassedIn.getLineId());
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
				String nameFieldVal = nameField.getText();
				String addrFieldVal = addrField.getText();
				String birthdayFieldVal = birthdayField.getText();
				String phoneFieldVal = phoneField.getText();
				String emailFieldVal = emailField.getText();
				String lineIdFieldVal = lineIdField.getText();

				if (nameFieldVal.length() > 0 &&
					addrFieldVal.length() > 0 &&
					birthdayFieldVal.length() > 0 && 
					phoneFieldVal.length() > 0 &&
					emailFieldVal.length() > 0 &&
					lineIdFieldVal.length() > 0) {
						
					final String name = nameField.getText().trim();
					final String addr = addrField.getText().trim();
					final String birthday = birthdayField.getText().trim();
					final String phone = phoneField.getText().trim();
					final String email = emailField.getText().trim();
					final String lineId = lineIdField.getText().trim();

					// If the group passed in is in our list, edit that entry.
					// Otherwise, just add to the list.
					if (null != teacherPassedIn) {
						int idx = TeacherList.getInstance().getTeacherList().indexOf(teacherPassedIn);
						TeacherList.getInstance().getTeacherList().get(idx).setName(name);
						TeacherList.getInstance().getTeacherList().get(idx).setAddr(addr);
						TeacherList.getInstance().getTeacherList().get(idx).setBirthday(birthday);
						TeacherList.getInstance().getTeacherList().get(idx).setPhone(phone);
						TeacherList.getInstance().getTeacherList().get(idx).setEmail(email);
						TeacherList.getInstance().getTeacherList().get(idx).setLineId(lineId);
					} else {
						TeacherList.getInstance().addTeacherToList(new Teacher(name, addr, birthday, phone, email, lineId));
					}
					
					TeacherPanel.getInstance().teacherTable.setModel(new TeacherTableModel());
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