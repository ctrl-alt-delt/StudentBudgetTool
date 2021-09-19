package Model.PrivateGroup;

public class PrivateGroup {
	
	private String name;
	private int frequency;
	private double rate;
	private double total;
	
	public PrivateGroup(String name, int frequency, double rate) {
		setName(name);
		setFrequency(frequency);
		setRate(rate);
		setTotal(frequency * rate);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}