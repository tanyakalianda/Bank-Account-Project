/**
 * 
 * @author Tanya Kalianda
 * Savings Account
 */
public class SavingsAccount extends BankAccount
{
	//fields
	private double intRate;
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	
	//constructors
	public SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n, b);
		intRate = r;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;		
	}
	
	public SavingsAccount(String n, double r, double mb, double mbf)
	{
		this(n, 0, r, mb, mbf);		
	}
	
	//methods
	
	/**
	 * deposits a non-negative amount to the balance
	 * @param amt        amount 
	 */
	public void deposit(double amt)
	{
		if (amt >=0)
		{
			super.deposit(amt);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * if the balance is not negative, it withdraws a non-negative amount
	 * if the balance goes below the minimum balance, it withdraws the minimum balance fee
	 * @param amt           amount
	 */
	public void withdraw(double amt)
	{		
		if (amt > 0 && amt <= getBalance())
		{
			super.withdraw(amt);
			if (getBalance() - amt < MIN_BAL)
			{
				super.withdraw(MIN_BAL_FEE);
			}
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * if the accounts are under the same name and the amount is less than/equal to the balance, it transfers the amount to the other bank account
	 * @param other          other BankAccount
	 * @param amt            amount
	 */
	public void transfer(BankAccount other, double amt)
	{
		if (getName().equals(other.getName()) && amt <= getBalance())
		{
			super.transfer(other, amt);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * adds the interest to the balance
	 */
	public void addInterest()
	{
		super.deposit(getBalance() * intRate); 
	}
	
	/**
	 * uses the addInterest() method for the update at the end of the month
	 */
	public void endOfMonthUpdate()
	{
		addInterest();
	}
	
}
