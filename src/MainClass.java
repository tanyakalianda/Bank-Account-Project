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
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
		accounts.add(new CheckingAccount("Tanya", 15, 1.5, 10));
		accounts.add(new SavingsAccount("Tanya", 0.025, 300, 10));
		
		boolean terminate = false;
		while (!terminate)
		{
			Scanner in = new Scanner();
			System.out.print("Enter \"add\" to add an account, enter \"transaction\" to make a transaction, or enter \"terminate\" to terminate the program: ");
			String answer = in.nextLine();
			if (answer.equals("add"))
			{
				
			}
			else if (answer.equals("transaction"))
			{
				
			}
			else if (answer.equals("terminate"))
			{
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
