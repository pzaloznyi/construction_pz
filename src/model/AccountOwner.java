package model;

public class AccountOwner {
	
	private Deposit deposit;
	private String firstname;
	private String lastname;

	public AccountOwner(double deposit, String firstname, String lastname) {
		this.deposit = new Deposit(deposit);
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getName() {
		return lastname + " " + firstname;
	}
	
	public Deposit getDeposit() {
		return deposit;
	}
}
