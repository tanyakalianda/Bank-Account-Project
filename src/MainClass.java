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
				//find my account and store it in a local variable
				System.out.print("Please enter your account number: ");
				int num = in.nextInt();
				in.nextLine();
				boolean accountNum = true;
				while (true)
				{
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
						System.out.print("This is not a valid account number");
						System.out.print("Please type \"reenter\" if you would like to reenter your account number or type \"name\" to get your account number: ");
						String ans = in.nextLine();
						boolean tryAgain = true;
						while (tryAgain)
						{
							if (ans.equals("reenter"))
							{
								System.out.print("Enter your account number: ");
								num = in.nextInt();
								in.nextLine();
								tryAgain = false;
							}
							else if (ans.equals("name"))
							{
								System.out.print("Enter your name: ");
								String name = in.nextLine();
								ArrayList<BankAccount> account2 = new ArrayList<BankAccount>();
								for (int i = 0; i < accounts.size(); i++);
								{
									String possName = accounts.get(i).getName();
									if (possName.equals(name))
									{
										account2.add(accounts.get(i));
									}
								}
								for (int i = 0; i < account2.size(); i++);
								{
									if (account2.get(i) instanceof CheckingAccount)
									{
										System.out.println("These are your checking accounts: " + account2.get(i).getNum());
									}
									if (account2.get(i) instanceof SavingsAccount)
									{
										System.out.println("These are your savings accounts: " + account2.get(i).getNum());
									}
								}
								tryAgain = false;
								System.out.print("Please enter your account number: ");
								num = in.nextInt();
								in.nextLine();
							}
							else
							{
								System.out.print("Invalid input. Please try again: ");
								ans = in.nextLine();
							}
						}
					}
					else
					{
						System.out.print("Enter \"w\" to withdraw money, \"d\" to deposit money, \"t\" to transfer money, or \"num\" to get the account number: ");
						String response = in.nextLine();
						switch(response) 
						{
							case "w":
							{
								try
								{
									System.out.print("Enter the amount you wish to withdraw: ");
									double amount = in.nextDouble();
									//must identify which account you want to use
									number.withdraw(amount);
									accountNum = false;
								}
								catch(IllegalArgumentException e)
								{
									System.out.println("Transaction not authorized");
								}
							}
							case "d":
							{
								try
								{
									System.out.print("Enter the amount you wish to deposit: ");
									double amount = in.nextDouble();
									number.deposit(amount);
									accountNum = false;
								}
								catch(IllegalArgumentException e)
								{
									System.out.println("Transaction not authorized");
								}
							}
							case "t":
							{ //must det which account the money is being transfered to
								try
								{
									System.out.print("Enter the amount you wish to transfer: ");
									double amount = in.nextDouble();
									in.nextLine();
									System.out.print("Enter the account number you wish to transfer the money to: ");
									int account = in.nextInt();
									in.nextLine();					
									number.transfer(account, amount);
									accountNum = false;
								}
								catch(IllegalArgumentException e)
								{
									System.out.println("Transaction not authorized");
								}
							}
							case "num":
							{
								System.out.print(number.getAccountNumber());
								accountNum = false;
							}
							default: 
							{
								System.out.println("Invalid response. Please try again: ");
							}
						}
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
	}

}
