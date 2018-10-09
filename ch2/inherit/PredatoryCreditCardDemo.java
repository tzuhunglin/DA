import java.util.Scanner;

public class PredatoryCreditCardDemo{

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

		System.out.print("Enter your rate: ");
		double rate = input.nextDouble();

		PredatoryCreditCard predatoryCreditCard = new PredatoryCreditCard(cust, bk, acnt, lim, initialBal, rate);
		// System.out.println(creditCard);
		System.out.println(predatoryCreditCard.customer);
		System.out.println(predatoryCreditCard.bank);
		System.out.println(predatoryCreditCard.account);
		System.out.println(predatoryCreditCard.limit);
		System.out.println(predatoryCreditCard.balance);


		// System.out.print("Enter the price: ");
		// double price = input.nextDouble();

		// if(creditCard.charge(price)){
		// 	System.out.println("The balance now is" + creditCard.balance);
		// }else{
		// 	System.out.println("You cannot spent so much money");
		// }

		// System.out.print("Enter the money you wanna pay: ");
		// double amount = input.nextDouble();
		// creditCard.makePayment(amount);
		// System.out.print(creditCard.balance);





	}
}