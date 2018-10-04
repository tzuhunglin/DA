import java.util.Scanner;

public class CreditCardDemo{

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);

		System.out.print("Enter your name: ");
		String cust = input.nextLine();

		System.out.print("Enter your bank: ");
		String bk = input.nextLine();

		System.out.print("Enter your account: ");
		String acnt = input.nextLine();

		System.out.print("Enter your limit: ");
		int lim = input.nextInt();

		System.out.print("Enter your initial balance: ");
		double initialBal = input.nextDouble();

		CreditCard creditCard = new CreditCard(cust, bk, acnt, lim, initialBal);
		// System.out.println(creditCard);
		System.out.println(creditCard.customer);
		System.out.println(creditCard.bank);
		System.out.println(creditCard.account);
		System.out.println(creditCard.limit);
		System.out.println(creditCard.balance);

		System.out.print("Enter the price: ");
		double price = input.nextDouble();

		if(creditCard.charge(price)){
			System.out.println("The balance now is" + creditCard.balance);
		}else{
			System.out.println("You cannot spent so much money");
		}

		System.out.print("Enter the money you wanna pay: ");
		double amount = input.nextDouble();
		creditCard.makePayment(amount);
		System.out.print(creditCard.balance);





	}
}