/**
 * 
 * @author Tanya Kalianda
 * Bank Account Class
 */
public abstract class BankAccount
{
	//fields
	private static int nextAccNum;
	private String name;
	private int accNum;
	private double balance;
	
	//constructors
	/**
	 * creates a bank account that initializes name to n, assigns the next account number, and sets the balance to 0
	 * @param n      name
	 */
	public BankAccount(String n)
	{
		name = n;
		accNum = nextAccNum;
		balance = 0;
	}
	
	/**
	 * creates a bank account that initializes name to n, assigns the next account number, and assigns balance to b
	 * @param n           name
	 * @param b           balance
	 */
	public BankAccount(String n, double b)
	{
		name = n;
		accNum = nextAccNum;
		balance = b;
	}
	
	//methods
	/**
	 * adds the amount deposited to the balance
	 * @param amt          Amount deposited
	 */
	public void deposit(double amt)
	{
		balance += amt;
	}
	
	/**
	 * subtracts the amount withdrawn from the balance
	 * @param amt          Amount withdrawn
	 */
	public void withdraw(double amt)
	{
		balance -= amt;
	}
	
	/**
	 * returns the name of the account holder
	 * @return name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * returns the current balance stored in the account
	 * @return balance
	 */
	public double getBalance()
	{
		return balance;
	}
	
	/**
	 * 
	 */
	public abstract double endOfMonthUpdate();
	
	/**
	 * withdraws money from the first account and deposits the money into the other account
	 * @param other            another bank account
	 * @param amt              amount withdrawn from 1 bank account and deposited to the other bank account
	 */
	public void transfer(BankAccount other, double amt)
	{
		withdraw(amt);
		other.deposit(amt);
	}
	
	/**
	 * returns the account number, the name of the account holder, and the balance stored in the account
	 */
	public String toString()
	{
		return accNum + "/t" + name + "/t" + "$" + balance;
	}
	
	
}
