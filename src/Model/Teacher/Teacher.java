package Model.Teacher;

import java.util.Date;

public class Teacher {
	
	private String name;
	private String addr;
	private String birthday;
	private String phone;
	private String email;
	private String lineId;
	
	public Teacher(String name, String addr, String birthday, String phone, String email, String lineId) {
		setName(name);
		setAddr(addr);
		setBirthday(birthday);
		setPhone(phone);
		setEmail(email);
		setLineId(lineId);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
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
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String exportToCsv() {
		String csvStr = "";
		
		csvStr = name + "," + 
		         addr + "," + 
				 birthday + "," + 
		         phone + "," + 
				 email + "," + 
				 lineId;
		
		return csvStr;
	}
}
