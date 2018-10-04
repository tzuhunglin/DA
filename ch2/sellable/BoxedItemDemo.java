import java.util.Scanner;
public class BoxedItemDemo{
	public static void main(String[] args){
		String desc = "This is desc"; 
		int p = 10; 
		int w = 20;
		boolean h = true;

		BoxedItem boxedItem = new BoxedItem(desc, p, w, h);
		Transportable transportable = boxedItem;
		Sellable sellable = boxedItem;

		System.out.println(sellable.description());
		System.out.println(sellable.listPrice());
		System.out.println(sellable.lowestPrice());
		System.out.println();
		System.out.println(transportable.weight());
		System.out.println(transportable.isHazardous());





	}
}