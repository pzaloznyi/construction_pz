package model;

public class Deposit {
	private double amount;

	public Deposit(double amount) {
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
