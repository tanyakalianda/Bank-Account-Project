/**
 * 
 * @author Tanya Kalianda
 * Checking Account
 */
public class CheckingAccount extends BankAccount
{
	//fields
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	private int numTransactions;
	
	//constructors
	public CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
	{
		super(n, b);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
		numTransactions = 0;
	}
	
	public CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		this(n, 0, odf, tf, freeTrans);
		numTransactions = 0;
	}
	
	
	//methods
	/**
	 * deposits a non-negative amount to the balance
	 * once the number of transactions exceeds the number of free transactions allowed, it withdraws the transaction fee
	 * increments number of transactions
	 * @param amt         amount
	 */
	public void deposit(double amt)
	{
		if (amt >= 0)
		{
			super.deposit(amt);
			if (numTransactions > FREE_TRANS)
			{
				super.withdraw(TRANSACTION_FEE);
			}
			numTransactions++;
		}
		else 
		{
			throw new IllegalArgumentException();
		}		
	}
	
	/**
	 * withdraws a non-negative amount only if the initial balance is not negative  
	 * if number of transactions exceeds the number of free transactions, it withdraws the transaction fee
	 * if balance goes negative after the transaction, it applies the over draft fee
	 * increments number of transactions
	 * @param amt          amount
	 */
	public void withdraw(double amt)
	{
		//if the withdraw withdraw the money , apply trans fee, apply overdraft
		//else throw an exception
		if (amt >= 0 && getBalance() >= 0)
		{
			super.withdraw(amt);
			if (numTransactions >= FREE_TRANS)
			{
				super.withdraw(TRANSACTION_FEE);
			}
			if (getBalance() < 0)
			{
				super.withdraw(OVER_DRAFT_FEE);
			}
			numTransactions++;
		}
		else			
		{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * if the accounts are under the same name and the amount is less than/equal to the balance, it transfers the amount to the other bank account
	 * @param other           other Bank Account
	 * @param amt             amount
	 */
	public void transfer(BankAccount other, double amt)
	{
		//System.out.println(this.getName() + " " + other.getName());
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
	 * resets number of transactions to 0
	 */
	public void endOfMonthUpdate()
	{
		numTransactions = 0;
	}
	
}
