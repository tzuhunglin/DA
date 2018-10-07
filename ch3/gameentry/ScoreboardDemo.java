import java.util.Scanner;
public class ScoreboardDemo{
	public static void main(String[] aggs){
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the capacity of the socreboard");
		int capacity = input.nextInt();
		Scoreboard scoreboard = new Scoreboard(capacity); 
		System.out.println("");


		while(true){
			String type = input.nextLine();
			System.out.println("add: add a Entry Game");
			System.out.println("remove: remove a Entry Game");
			System.out.println("show: show all Entry Games");
			System.out.println("");

	        switch(type) { 
	            case "add": 
					System.out.println("Please enter the name");
					String name = input.nextLine();
					System.out.println("Please enter the score");
					int score = input.nextInt();
					GameEntry gameEntry = new GameEntry(name, score);
					scoreboard.add(gameEntry);
	            	scoreboard.show();

	                break; 
	            case "remove": 
	            	scoreboard.show();
	                System.out.println("Please enter the order");
					int order = Integer.parseInt(input.nextLine());
					scoreboard.remove(order-1);
	            	scoreboard.show();
	                break; 
	            case "show": 
	            	scoreboard.show();
	                break; 
	        }
		}
	}
	
}