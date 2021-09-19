package Model.Student;

public class Student {
	
	private String name;
	private String nickname;
	private String birthday;
	private String phone;
	private String addr;
	private String school;
	private String grade;
	private String parentName;
	private String parentPhone;
	
	public Student(String name, String nickname, String birthday, String phone, String addr, String school, String grade, String parentName, String parentPhone) {
		setName(name);
		setNickname(nickname);
		setBirthday(birthday);
		setPhone(phone);
		setAddr(addr);
		setSchool(school);
		setGrade(grade);
		setParentName(parentName);
		setParentPhone(parentPhone);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getParentPhone() {
		return parentPhone;
	}
	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}
}
