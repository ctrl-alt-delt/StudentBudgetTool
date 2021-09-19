package GUI.Main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPHeaderCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import GUI.BigGroup.BigGroupPanel;
import GUI.PrivateGroup.PrivateGroupPanel;
import GUI.Student.StudentPanel;
import GUI.Summary.SummaryPanel;
import GUI.Teacher.TeacherPanel;
import Utils.Utils;

public class ExportDialog extends JDialog {
	
	private static ExportDialog INSTANCE = new ExportDialog();

	private JCheckBox privGroupsCheckBox;
	private JCheckBox bigGroupsCheckBox;
	private JCheckBox summaryCheckBox;
	private JCheckBox teachersCheckBox;
	private JCheckBox studentsCheckBox;

	private Document doc;
	private JButton btnExportAll;

	public ExportDialog() {
		setTitle("Export");
		setResizable(false);
		setBounds(100, 100, 376, 242);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(MainWindow.getInstance());

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		privGroupsCheckBox = new JCheckBox("Private Groups");
		privGroupsCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(privGroupsCheckBox);

		bigGroupsCheckBox = new JCheckBox("Big Groups");
		bigGroupsCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(bigGroupsCheckBox);

		summaryCheckBox = new JCheckBox("Summary");
		summaryCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(summaryCheckBox);

		teachersCheckBox = new JCheckBox("Teachers");
		teachersCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(teachersCheckBox);

		studentsCheckBox = new JCheckBox("Students");
		studentsCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(studentsCheckBox);

		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JButton exportButton = new JButton("Export");
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportData();
				setVisible(false);
			}
		});
		buttonPanel.add(exportButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		btnExportAll = new JButton("Export All");
		btnExportAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				privGroupsCheckBox.setSelected(true);
				bigGroupsCheckBox.setSelected(true);
				summaryCheckBox.setSelected(true);
				teachersCheckBox.setSelected(true);
				studentsCheckBox.setSelected(true);
				exportData();
				setVisible(false);
			}
		});
		buttonPanel.add(btnExportAll);
		buttonPanel.add(cancelButton);
	}
	
	public static ExportDialog getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new ExportDialog();
		}
		
		return INSTANCE;
	}

	private void exportData() {
		if (privGroupsCheckBox.isSelected() || bigGroupsCheckBox.isSelected() || summaryCheckBox.isSelected() || teachersCheckBox.isSelected() || studentsCheckBox.isSelected()) {
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int returnValue = jfc.showSaveDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = new File(jfc.getSelectedFile().getAbsolutePath() + ".pdf");
				try {
					doc = new Document();
					PdfWriter.getInstance(doc, new FileOutputStream(selectedFile));
					doc.open();

					if (privGroupsCheckBox.isSelected()) {
						Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, GrayColor.GRAYBLACK);
						Paragraph p = new Paragraph("Private Groups", font);
						doc.add(p);
						addTableToPdf(PrivateGroupPanel.getInstance().privGroupTable);
					} 

					if (bigGroupsCheckBox.isSelected()) {
						Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, GrayColor.GRAYBLACK);
						Paragraph p = new Paragraph("Big Groups", font);
						doc.add(p);
						addTableToPdf(BigGroupPanel.getInstance().bigGroupTable);	
					}

					if (summaryCheckBox.isSelected()) {
						Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, GrayColor.GRAYBLACK);
						Paragraph p = new Paragraph("Summary", font);
						doc.add(p);
						addTableToPdf(SummaryPanel.getInstance().summaryTable);
					}

					if (teachersCheckBox.isSelected()) {
						Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, GrayColor.GRAYBLACK);
						Paragraph p = new Paragraph("Teachers", font);
						doc.add(p);
						addTableToPdf(TeacherPanel.getInstance().teacherTable);
					} 

					if (studentsCheckBox.isSelected()) {
						Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, GrayColor.GRAYBLACK);
						Paragraph p = new Paragraph("Students", font);
						doc.add(p);
						addTableToPdf(StudentPanel.getInstance().StudentTable);
					}

					doc.close();
				} catch (Exception e) {
					Utils.logError(e);
				}	
			}
		} else {
			JOptionPane.showMessageDialog(MainWindow.getInstance(), "Select data to export.");
		}
	}

	private void addTableToPdf(JTable table) {
		PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
		pdfTable.setSpacingBefore(20f);
		pdfTable.setSpacingAfter(50f);
		pdfTable.setWidthPercentage(100);
		pdfTable.setKeepTogether(true);
		NumberFormat df = NumberFormat.getCurrencyInstance();
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setCurrencySymbol("");
		dfs.setGroupingSeparator(',');
		dfs.setMonetaryDecimalSeparator('.');
		((DecimalFormat) df).setDecimalFormatSymbols(dfs);
		try {

			// Headers
			for (int i = 0; i < table.getColumnCount(); i++) {
				PdfPHeaderCell cell = new PdfPHeaderCell();
				cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
				Font font = new Font(FontFamily.HELVETICA, 12, Font.BOLD, GrayColor.GRAYBLACK);
				Paragraph p = new Paragraph(table.getColumnName(i), font);
				p.setAlignment(Element.ALIGN_CENTER);
				cell.addElement(p);
				pdfTable.addCell(cell);
			}

			for (int rows = 0; rows < table.getRowCount(); rows++) {
				for (int cols = 0; cols < table.getColumnCount(); cols++) {
					if (table.getModel().getValueAt(rows, cols) instanceof Double) {
						pdfTable.addCell(df.format(table.getValueAt(rows, cols)));
					} else {
						pdfTable.addCell(table.getValueAt(rows, cols).toString());
					}
				}
			}

			doc.add(pdfTable);
		} catch (Exception e) {
			Utils.logError(e);
		}
	}
}
