package GUI.PrivateGroup;

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
import Model.PrivateGroup.PrivateGroupTableModel;
import Model.Summary.Summary;
import Model.PrivateGroup.PrivateGroup;
import Model.PrivateGroup.PrivateGroupList;

public class PrivateGroupDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField groupNameField;
	private JTextField freqField;
	private JTextField rateField;
	private PrivateGroup groupPassedIn = null;

	public PrivateGroupDialog() {
		setResizable(false);
		initGUI();
	}

	public PrivateGroupDialog(PrivateGroup group) {
		groupPassedIn = group;
		initGUI();
	}

	void initGUI() {
		setTitle("Private Group");
		setBounds(100, 100, 343, 175);
		setLocationRelativeTo(PrivateGroupPanel.getInstance());
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
		 * Frequency
		 */
		JLabel rateLabel = new JLabel("Rate");
		contentPanel.add(rateLabel);
		rateField = new JTextField();
		contentPanel.add(rateField);
		rateField.setColumns(10);
		JLabel freqLabel = new JLabel("Times They've Come");
		contentPanel.add(freqLabel);
		freqField = new JTextField();
		contentPanel.add(freqField);
		freqField.setColumns(10);
		if (null != groupPassedIn) {
			freqField.setText(String.valueOf(groupPassedIn.getFrequency()));

		}

		/**
		 * Rate
		 */
		if (null != groupPassedIn) {
			rateField.setText(String.valueOf(groupPassedIn.getRate()));

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
				String groupNameFieldVal = groupNameField.getText();
				String freqFieldVal = freqField.getText();
				String rateFieldVal = rateField.getText();

				if (groupNameFieldVal.length() > 0 && freqFieldVal.length() > 0 && rateFieldVal.length() > 0) {
					final String groupName = groupNameField.getText().trim();
					final Double rate = Double.parseDouble(rateField.getText().trim());
					final int freq = Integer.parseInt(freqField.getText().trim());
					final double total = freq * rate;

					// If the group passed in is in our list, edit that entry.
					// Otherwise, just add to the list.
					if (null != groupPassedIn) {
						int idx = PrivateGroupList.getInstance().getPrivGroupList().indexOf(groupPassedIn);
						PrivateGroup editGroup = PrivateGroupList.getInstance().getPrivGroupList().get(idx);
						editGroup.setName(groupName);
						editGroup.setFrequency(freq);
						editGroup.setRate(rate);
						editGroup.setTotal(total);
					} else {
						PrivateGroupList.getInstance().addPrivGroupToList(new PrivateGroup(groupName, freq, rate));
					}
					
					PrivateGroupPanel.getInstance().privGroupTable.setModel(new PrivateGroupTableModel());
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