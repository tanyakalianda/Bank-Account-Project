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
		int accNum = -1;
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
	
		
		boolean terminate = false;
		Scanner in = new Scanner(System.in);
			
		while (!terminate)
		{
			
			System.out.print("Enter \"add\" to add an account, enter \"transaction\" to make a transaction, or enter \"end\" to terminate the program: ");
			String answer = in.nextLine();
			if (answer.equals("add"))
			{
				System.out.print("Enter \"checking\" to create a checking account or \"savings\" to create a savings account: ");
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
					accNum++;
				}
				else if (account.equals("savings"))
				{
					System.out.print("Enter the name of the account: ");
					String name = in.nextLine();
					accounts.add(new SavingsAccount(name, RATE, MIN_BAL, MIN_BAL_FEE));
					accNum++;
				}
			}
			else if (answer.equals("transaction"))
			{
				//find my account and store it in a local variable
				System.out.print("Please enter your account number: ");
				String num = in.nextLine();
				while(!isNumeric(num))
				{
					System.out.print("You have not entered a numeric value. Please enter your account number: ");
					num = in.nextLine();
				}
				BankAccount number = null;
				boolean accountNum = true;
				while (accountNum)
				{
					for (int i = 0; i < accounts.size(); i++)
					{
						if (accounts.get(i).getAccountNumber() == (Integer.parseInt(num)))
						{
							number = accounts.get(i);			
						}
					}
					if (number == null)
					{
						System.out.println("This is not a valid account number.");
						System.out.print("Please type \"reenter\" if you would like to reenter your account number or type \"name\" to get your account number: ");
						String ans = in.nextLine();
						boolean tryAgain = true;
						while (tryAgain)
						{
							if (ans.equals("reenter"))
							{
								System.out.print("Enter your account number: ");
								num = in.nextLine();
								while(!isNumeric(num))
								{
									System.out.print("You have not entered a numeric value. Please enter your account number: ");
									num = in.nextLine();
								}
								tryAgain = false;
							}
							else if (ans.equals("name"))
							{
								ArrayList<BankAccount> account2 = new ArrayList<BankAccount>();
								while (account2.size() == 0)
								{
									System.out.print("Enter your name: ");       
									String possName = in.nextLine();
									for (int i = 0; i < accounts.size(); i++)
									{
										String name = accounts.get(i).getName();
										if (possName.equals(name))
										{
											account2.add(accounts.get(i));
										}
									}
									if (account2.size() == 0)
									{
										System.out.println("You have not entered a valid name. Please try again.");
									}
								}
								for (int i = 0; i < account2.size(); i++)
								{
									if (account2.get(i) instanceof CheckingAccount)
									{
										System.out.println("This is your checking account: " + account2.get(i).toString());
									}
									else if (account2.get(i) instanceof SavingsAccount)
									{
										System.out.println("This is your savings account:  " + account2.get(i).toString());
									}
								}
								tryAgain = false;
								System.out.print("Please enter your account number: ");
								num = in.nextLine();
								while(!isNumeric(num))
								{
									System.out.print("You have not entered a numeric value. Please enter your account number: ");
									num = in.nextLine();
								}
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
						accountNum = false;
						System.out.print("Enter \"w\" to withdraw money, \"d\" to deposit money, \"t\" to transfer money, or \"n\" to get the account number: ");
						String response = in.nextLine();
						switch(response) 
						{
							case "w":
							{									
								System.out.print("Enter the amount you wish to withdraw: ");    
								String amount = in.nextLine();
								while(!isNumeric(amount))
								{
									System.out.print("You have not entered a numeric value. Please enter your account number: ");
									amount = in.nextLine();
								}
								try
								{
									number.withdraw(Double.parseDouble(amount));	
									System.out.println(number.toString());
								}
								catch(IllegalArgumentException e)
								{
									System.out.println("Transaction not authorized");
								}
								break;
							}
							case "d":
							{						
								System.out.print("Enter the amount you wish to deposit: ");     
								String amount = in.nextLine();
								while(!isNumeric(amount))
								{
									System.out.print("You have not entered a numeric value. Please enter your account number: ");
									amount = in.nextLine();
								}
								try
								{	
									number.deposit(Double.parseDouble(amount));	
									System.out.println(number.toString());
								}
								catch(IllegalArgumentException e)
								{
									System.out.println("Transaction not authorized");
								}
								break;
							}
							case "t":
							{ 
								System.out.print("Enter the amount you wish to transfer: ");  
								String amount = in.nextLine();
								while(!isNumeric(amount))
								{
									System.out.print("You have not entered a numeric value. Please enter your account number: ");
									amount = in.nextLine();
								}
								System.out.print("Please enter the account number you wish to transfer the money to: ");
								String num2 = in.nextLine();
								while(!isNumeric(num2))
								{
									System.out.print("You have not entered a numeric value. Please enter your account number: ");
									num2 = in.nextLine();
								}
								BankAccount number2 = null;
								boolean accountNum2 = true;
								while (accountNum2)
								{
									for (int i = 0; i < accounts.size(); i++)
									{
										if (accounts.get(i).getAccountNumber() == (Integer.parseInt(num)))
										{
											number2 = accounts.get(i);			
										}
									}
									if (number2 == null)
									{
										System.out.println("This is not a valid account number.");
										System.out.print("Please type \"reenter\" if you would like to reenter your account number or type \"name\" to get your account number: ");
										String ans = in.nextLine();
										boolean tryAgain = true;
										while (tryAgain)
										{
											if (ans.equals("reenter"))
											{
												System.out.print("Enter your account number: ");
												num2 = in.nextLine();
												while(!isNumeric(num))
												{
													System.out.print("You have not entered a numeric value. Please enter your account number: ");
													num2 = in.nextLine();
												}
												tryAgain = false;
											}
											else if (ans.equals("name"))
											{
												ArrayList<BankAccount> account2 = new ArrayList<BankAccount>();
												while (account2.size() == 0)
												{
													System.out.print("Enter your name: ");       
													String possName = in.nextLine();
													for (int i = 0; i < accounts.size(); i++)
													{
														String name = accounts.get(i).getName();
														if (possName.equals(name))
														{
															account2.add(accounts.get(i));
														}
													}
													if (account2.size() == 0)
													{
														System.out.println("You have not entered a valid name. Please try again.");
													}
												}
												for (int i = 0; i < account2.size(); i++)
												{
													if (account2.get(i) instanceof CheckingAccount)
													{
														System.out.println("This is your checking account: " + account2.get(i).toString());
													}
													else if (account2.get(i) instanceof SavingsAccount)
													{
														System.out.println("This is your savings account: " + account2.get(i).toString());
													}
												}
												tryAgain = false;
												System.out.print("Please enter your account number: ");
												num2 = in.nextLine();
												while(!isNumeric(num2))
												{
													System.out.print("You have not entered a numeric value. Please enter your account number: ");
													num2 = in.nextLine();
												}
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
										accountNum2 = false;
										BankAccount transferAccount = accounts.get(Integer.parseInt(num2));
										try
										{
											number2.transfer(transferAccount, Double.parseDouble(amount));          //cannot transfer from a checkings to a savings + vice versa
											//System.out.println(accounts.get(Integer.parseInt(num2)).toString());  
											System.out.println(accounts.toString());
										}								
										catch(IllegalArgumentException e)
										{
											System.out.println("Transaction not authorized");
										}
									}
								}
								break;
							}
							case "n":
							{
								System.out.println("Your account number: " + number.getAccountNumber());
								break;
							}
							default: 
							{
								System.out.println("Invalid response. Please try again. ");
								break;
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
				System.out.println("Invalid input. Please try again.");
			}
		}	
	}
	private static boolean isNumeric(String str)
	{		
		try
		{
			Double.parseDouble(str);
			return true;
		}
		catch (IllegalArgumentException e)
		{
			return false;
		}
	}
}
