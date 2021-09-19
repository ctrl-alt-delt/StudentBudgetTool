package Model.Summary;

import Model.BigGroup.BigGroup;
import Model.BigGroup.BigGroupList;
import Model.PrivateGroup.PrivateGroup;
import Model.PrivateGroup.PrivateGroupList;
import Model.Teacher.TeacherList;

public class Summary {

	private static Summary INSTANCE = new Summary();

	private double privGroupTotal;
	private double bigGroupTotal;
	private double overallTotal;
	private double schoolCut;
	private double teacherCut;
	private double teacherSaves;

	private int NUM_TEACHERS = 1;
	private final int TEACHER_INCOME_LIMIT = 15000;
	private final double schoolPercentage = .50;

	public Summary() {
		refreshTotals();
	}
	
	public static Summary getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new Summary();
		}

		return INSTANCE;
	}

	public void refreshTotals() {
		setOverallTotal(0);
		NUM_TEACHERS = TeacherList.getInstance().getTeacherList().size(); // Get the number of teachers.
		setPrivGroupTotal(calculatePrivGroupTotal());
		setBigGroupTotal(calculateBigGroupTotal());
		setOverallTotal((privGroupTotal + bigGroupTotal));
		setSchoolCut((overallTotal * schoolPercentage));

		double teacherPool = getSchoolCut(); // School gets half, so get this as the other half.

		// If the amount split up between the teachers will be more than the teacher limit,
		// Cap the teachers at the income limit then add the rest to the school.
		// Otherwise, just split up between the teachers.
		if (NUM_TEACHERS == 0) {
			setTeacherCut(0);
			setTeacherSaves(0);
		} else {
			if ((teacherPool / NUM_TEACHERS) > TEACHER_INCOME_LIMIT) {
				setTeacherCut(TEACHER_INCOME_LIMIT);
				setTeacherSaves((teacherPool - (teacherCut * NUM_TEACHERS)));
				setSchoolCut((schoolCut + teacherSaves));
			} else {
				setTeacherCut((teacherPool / NUM_TEACHERS));
			}
		}
	}

	private double calculatePrivGroupTotal() {
		double pgTotal = 0;
		for (PrivateGroup privGroup : PrivateGroupList.getInstance().getPrivGroupList()) {
			pgTotal += privGroup.getTotal();
		}

		return pgTotal;
	}

	private double calculateBigGroupTotal() {
		double bgTotal = 0;
		for (BigGroup bigGroup : BigGroupList.getInstance().getBigGroupList()) {
			bgTotal += bigGroup.getTotal();
		}

		return bgTotal;
	}

	public double getPrivGroupTotal() {
		return privGroupTotal;
	}

	public void setPrivGroupTotal(double privGroupTotal) {
		this.privGroupTotal = privGroupTotal;
	}

	public double getBigGroupTotal() {
		return bigGroupTotal;
	}

	public void setBigGroupTotal(double bigGroupTotal) {
		this.bigGroupTotal = bigGroupTotal;
	}

	public double getOverallTotal() {
		return overallTotal;
	}

	public void setOverallTotal(double overallTotal) {
		this.overallTotal = overallTotal;
	}

	public double getSchoolCut() {
		return schoolCut;
	}

	public void setSchoolCut(double schoolCut) {
		this.schoolCut = schoolCut;
	}

	public double getTeacherCut() {
		return teacherCut;
	}

	public void setTeacherCut(double teacherCut) {
		this.teacherCut = teacherCut;
	}

	public double getTeacherSaves() {
		return teacherSaves;
	}

	public void setTeacherSaves(double teacherSaves) {
		this.teacherSaves = teacherSaves;
	}
}
