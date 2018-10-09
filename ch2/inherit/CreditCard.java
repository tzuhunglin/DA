public class CreditCard{
	public String customer;
	public String bank;
	public String account;
	public int limit;//額度
	public double balance;//欠款

	public CreditCard(String cust, String bk, String acnt, int lim, double initialBal){
		customer = cust;
		bank = bk;
		account = acnt;
		limit = lim;//額度
		balance = initialBal;//欠款
	}

	public boolean charge(double price){
		if(price + balance > limit){
			return false;
		}
		balance += price;
		return true;
	}

	public void makePayment(double amount){
		balance -= amount;
	}
}