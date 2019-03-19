package model;

public class AccountOwner {
	
	private Credit credit;
	private Deposit deposit;
	private String firstname;
	private String lastname;

	public AccountOwner(double credit, double deposit, String firstname, String lastname) {
		this.credit = new Credit(credit);
		this.deposit = new Deposit(deposit);
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getName() {
		return lastname + " " + firstname;
	}
	
	public Credit getCredit() {
		return credit;
	}
	
	public Deposit getDeposit() {
		return deposit;
	}
}
