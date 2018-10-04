import java.util.Scanner;
public class SellableDemo{

	public static void main(String[] args){
		String desc = "this is description"; 
		int p = 100; 
		boolean c = true;
		Photograph photograph = new Photograph(desc, p, c);
		Sellable sellable = photograph;
		System.out.println(sellable.description());
		System.out.println(sellable.listPrice());
		System.out.println(sellable.lowestPrice());
	}
}