package Model.BigGroup;

public class BigGroup {
	
	private String name;
	private int numStudents;
	private double price;
	private double total;
	
	public BigGroup(String name, int numStudents, double price) {
		setName(name);
		setNumStudents(numStudents);
		setPrice(price);
		setTotal(numStudents * price);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumStudents() {
		return numStudents;
	}

	public void setNumStudents(int numStudents) {
		this.numStudents = numStudents;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}