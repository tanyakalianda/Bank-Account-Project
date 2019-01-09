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
	public BankAccount(String n)
	{
		name = n;
		accNum = nextAccNum;
		balance = 0;
		nextAccNum++;
	}
	
	public BankAccount(String n, double b)
	{
		name = n;
		accNum = nextAccNum;
		balance = b;
		nextAccNum++;
	}
	
	//methods
	/**
	 * checks that the amount is not a negative number
	 * adds the amount deposited to the balance
	 * @param amt          Amount deposited
	 */
	public void deposit(double amt)
	{
		if (amt >= 0)
		{
			balance += amt;
		}
	}
	
	/**
	 * checks that the amount is not a negative number
	 * subtracts the amount withdrawn from the balance
	 * @param amt          Amount withdrawn
	 */
	public void withdraw(double amt)
	{
		if (amt >= 0)
		{
			balance -= amt;
		}
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
	 * updates the accounts at the end of the month
	 */
	public abstract void endOfMonthUpdate();
	
	/**
	 * checks that the amount is not a negative number
	 * withdraws money from the first account and deposits the money into the other account
	 * @param other            another bank account
	 * @param amt              amount withdrawn from 1 bank account and deposited to the other bank account
	 */
	public void transfer(BankAccount other, double amt)
	{
		if (amt >= 0)
		{
			withdraw(amt);
			other.deposit(amt);
		}
	}
	
	/**
	 * returns the user's account number
	 * @return account number
	 */
	public int getAccountNumber()
	{
		return accNum;
	}
	
	/**
	 * @return  account number, the name of account holder, and balance stored in the account
	 */
	public String toString()
	{
		return "Account number: " + accNum + "\t" + "Name: " + name + "\t" + "Balance: $" + balance;
	}
	
	
}
