import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Tanya Kalianda
 * Main Class
 */
public class MainClass 
{
	
	public static void main(String[] args)
	{
		final double OVER_DRAFT_FEE = 15;
		final double RATE = 0.0025;
		final double TRANSACTION_FEE = 1.5;
		final double MIN_BAL = 300;
		final double MIN_BAL_FEE = 10;
		final int FREE_TRANSACTIONS = 10;
		
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
	
		
		boolean terminate = false;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter \"add\" to add an account, enter \"transaction\" to make a transaction, or enter \"end\" to terminate the program: ");
		String answer = in.nextLine();
		
		while (!terminate)
		{
			if (answer.equals("add"))
			{
				System.out.print("Enter \"checking\" to create a checking account or \"savings\" to creaate a savings account: ");
				String account = in.nextLine();
				while(!account.equals("checking") && !account.equals("savings"))
				{
					System.out.print("Invalid input. Please try again: ");
					account = in.nextLine();
				}
				if (account.equals("checking"))
				{
					System.out.print("Enter the name of the account: ");
					String name = in.nextLine();
					accounts.add(new CheckingAccount(name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
				}
				else if (account.equals("savings"))
				{
					System.out.print("Enter the name of the account: ");
					String name = in.nextLine();
					accounts.add(new SavingsAccount(name, RATE, MIN_BAL, MIN_BAL_FEE));
				}
			}
			else if (answer.equals("transaction"))
			{
				System.out.print("Enter \"w\" to withdraw money, \"d\" to deposit money, \"t\" to transfer money, or \"num\" to get the account number: ");
				String response = in.nextLine();
				switch(response) 
				{
					case "w":
					{
						//find my account and store it in a local variable
						System.out.print("Please enter your account number: ");
						int num = in.nextInt();
						in.nextLine();
						BankAccount number = null;
						for (int i = 0; i < accounts.size(); i++)
						{
							if (accounts.get(i).getAccountNumber() == num)
							{
								number = num;
							}
						}
						if (number == null)
						{
							System.out.print("This is not a valid account number. Please try again: ");
						}
						System.out.print("Enter the amount you wish to withdraw: ");
						double amount = in.nextDouble();
						accounts.withdraw(amount);
					}
					case "d":
					{
						
					}
					
				}
			}
			else if (answer.equals("end"))
			{
				System.out.println("Program ended");
				terminate = true;
			}
			else
			{
				System.out.print("Invalid input. Please try again: ");
				answer = in.nextLine();
			}
		}
		
		//must create a getAccountNumber method in the bank account class (abstract)
		
		//must use letters (strings), not numbers
		//ie type "a" for transfer
	
		

	}

}
