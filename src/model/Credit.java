package model;

public class Credit {
	private double amount;

	public Credit(double amount) {
		this.amount = amount;
	}
	
	public boolean isMoreThan(double amount) {
		return this.amount > amount;
	}
	
	public double getAmount() {
		return amount;
	}
	
	@Override
	public String toString() {
		return "" + amount;
	}
}
