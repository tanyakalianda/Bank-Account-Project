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
	
	public void addInterest()
	{
		super.deposit(getBalance() * intRate); 
	}
	
	public void endOfMonthUpdate()
	{
		addInterest();
	}
	
}
