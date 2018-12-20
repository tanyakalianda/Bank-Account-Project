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
	 * deposits an amount greater than 0 to the balance. 
	 * It applies the transaction fee if the number of transactions exceeds the number of free transactions allowed
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
	 * withdraws an amount if and only if the balance is not negative. 
	 * If the balance goes negative after the transaction, it applies the OverDraftFee. 
	 * It checks if the number of transactions exceeds the number of free transactions allowed to apply the transaction fee. 
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
	 * if the accounts are under the same name and the amount is less than the balance of the account, it will transfer the amount. 
	 * If there is a transaction fee, it will check that the total amount withdrawn does not cause the balance to be negative. For every transaction, it increases the numTransactions.
	 * @param other amt             other Bank Account, amount
	 */
	public void transfer(BankAccount other, double amt)
	{//if i should transwer, call the super class;s trans
		// throw an exception
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
	 * resets the number of transactions to 0 and returns the number of transactions.
	 * @return numTransactions
	 */
	public void endOfMonthUpdate()
	{
		numTransactions = 0;
	}
	
}
