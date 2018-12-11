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
	}
	
	public CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		this(n, 0, odf, tf, freeTrans);
	}
	
	
	//methods
	public void deposit(double amt)
	{
		if (amt >= 0)
		{
			if (numTransactions <= FREE_TRANS)
			{
				super.deposit(amt);
				numTransactions++;
			}
			else if (numTransactions > FREE_TRANS)
			{
				super.deposit(amt);
				super.withdraw(TRANSACTION_FEE);
				numTransactions++;
			}
		}
		else 
		{
			throw new IllegalArgumentException();
		}		
	}
	
	public void withdraw(double amt)
	{
		if (numTransactions <= FREE_TRANS)
		{
			if (super.getBalance() >= 0)
			{
				super.withdraw(amt);
				if (super.getBalance() < 0)
				{
					super.withdraw(OVER_DRAFT_FEE);
				}
		}
		else if (super.getBalance() < 0)
		{
			throw new IllegalArgumentException();
		}
	}
	}
	
	
}
