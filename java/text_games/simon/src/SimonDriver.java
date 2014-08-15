/**SimonDriver.java
 * 
 * @author Tony Matts
 *
 * Classic simon memory game using
 * four colors which are picked randomly
 * and added to an array. Each color appears
 * in the console briefly, while the user 
 * has to memorize the order and enter
 * the colors they saw after each round.
 */
import java.util.Scanner;

public class SimonDriver {
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		//attributes
		Simon memGame = new Simon();
		int playGame = 1;
		Scanner scanD = new Scanner(System.in);
		int round = 1;
		
		System.out.println("----------------------------------");
		System.out.println("Welcome to the memory game, Simon!");
		System.out.println("----------------------------------\n");
		
		//rules
		System.out.println("\nNumbers represent colors as follows:" +
							"\n0 - green\n1 - yellow\n2 - red\n3 - blue");
		System.out.println("Enter colors one at a time using single integers, when prompted.");
		
		//game starts
		while (playGame == 1) {
			//user starts round by entering 1
			System.out.println("\nEnter 1 to Start Round " + round + ":");
			playGame = scanD.nextInt();
			
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			
			memGame.addColor();//color added to the queue
			
			memGame.showColors();//show each color for a smal duration of time
			
			memGame.check();//prompts the user for an answer, then checks it
			
			playGame = memGame.isValid();//if user is correct, game continues
			
			round++;
		}
		
		//prints score
		memGame.getScore();
	}

}
