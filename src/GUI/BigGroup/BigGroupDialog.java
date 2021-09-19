package GUI.BigGroup;

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

import GUI.Summary.SummaryPanel;
import Model.BigGroup.BigGroup;
import Model.BigGroup.BigGroupList;
import Model.BigGroup.BigGroupTableModel;
import Model.Summary.Summary;

public class BigGroupDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField groupNameField;
	private JTextField numStudentsField;
	private JTextField priceField;
	private BigGroup groupPassedIn = null;

	public BigGroupDialog() {
		setResizable(false);
		initGUI();
	}

	public BigGroupDialog(BigGroup group) {
		groupPassedIn = group;
		initGUI();
	}

	void initGUI() {
		setTitle("Big Group");
		setBounds(100, 100, 203, 186);
		setLocationRelativeTo(BigGroupPanel.getInstance());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPanel.setLayout(new GridLayout(3, 2, 0, 10));
		JLabel groupNameLabel = new JLabel("Group Name");
		contentPanel.add(groupNameLabel);
		groupNameField = new JTextField();
		contentPanel.add(groupNameField);
		groupNameField.setColumns(10);
		if (null != groupPassedIn) {
			groupNameField.setText(groupPassedIn.getName());

		}

		/**
		 * Num Students
		 */
		JLabel priceLabel = new JLabel("Price");
		contentPanel.add(priceLabel);
		priceField = new JTextField();
		contentPanel.add(priceField);
		priceField.setColumns(10);
		JLabel numStudentsLabel = new JLabel("Num Students");
		contentPanel.add(numStudentsLabel);
		numStudentsField = new JTextField();
		contentPanel.add(numStudentsField);
		numStudentsField.setColumns(10);
		if (null != groupPassedIn) {
			numStudentsField.setText(String.valueOf(groupPassedIn.getNumStudents()));
		}

		/**
		 * Price
		 */
		if (null != groupPassedIn) {
			priceField.setText(String.valueOf(groupPassedIn.getPrice()));

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

				if (fieldsAreValid()) {
					final String groupName = groupNameField.getText().trim();
					final int numStudents = Integer.parseInt(numStudentsField.getText().trim());
					final double price = Double.parseDouble(priceField.getText().trim());
					final double total = numStudents * price;

					// If we have a group passed in, edit that entry.
					// Otherwise add a new Big Group to the list.
					if (null != groupPassedIn) {
						int idx = BigGroupList.getInstance().getBigGroupList().indexOf(groupPassedIn);
						BigGroup editGroup = BigGroupList.getInstance().getBigGroupList().get(idx);
						editGroup.setName(groupName);
						editGroup.setNumStudents(numStudents);
						editGroup.setPrice(price);
						editGroup.setTotal(total);
					} else {
						BigGroupList.getInstance().addBigGroupToList(new BigGroup(groupName, numStudents, price));
					}
				
					// Update the Big Group table and refresh the summary.
					BigGroupPanel.getInstance().bigGroupTable.setModel(new BigGroupTableModel());
					Summary.getInstance().refreshTotals();
					SummaryPanel.getInstance().refreshTable();
					dispose();
				} else {
					JOptionPane.showMessageDialog(BigGroupPanel.getInstance(), "Invalid input.");
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
	
	private boolean fieldsAreValid() {
		boolean isValid = false;
		
		if (groupNameField.getText().length() > 0 && numStudentsField.getText().length() > 0 && priceField.getText().length() > 0) {
			//Make sure num students is an integer.
			//Make sure price is a double
			isValid = true;
		}
		
		return isValid;
	}
}