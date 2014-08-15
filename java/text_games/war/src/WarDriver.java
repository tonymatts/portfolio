import java.util.Random;
import java.util.Scanner;
/**WarDriver.java
 * 
 * @author Tony Matts
 *
 * driver class for the object class war, 
 * which simulates the card game War.
 */
public class WarDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//attributes
		War war = new War();
		Scanner scanD = new Scanner(System.in);
		int playGame = 1;
		
		//-------------------------------------------------------
		//user prompt to play a game
		System.out.println("Welcome to the card game, War!");
	
		//create a deck of cards
		war.createDeck();
		System.out.println("\nDeck Prepared.");
		
		//shuffle the deck
		war.shuffleDeck();
		System.out.println("\nDeck Shuffled.");
		
		//cards are dealt evenly among the Player and Computer
		war.dealCards();
		System.out.println("\nCards Dealt.");
		
		//rules
		System.out.println("\nEnter 1=Start or 0=Show Rules");
		playGame = scanD.nextInt();
		
		if (playGame == 0) {
			war.printRules();
			playGame = 1;
		}	
		//-------------------------------------------------------
		
		
		//method for while the player wants to continue
		while (playGame==1 && war.getPlayerSize()!=0 && war.getCompSize()!=0) {
			System.out.println("\nEnter 1 when you're ready to draw against the computer. Enter 0 to Quit.");
			playGame = scanD.nextInt();
			
			//battles carried out if player wants to continue playing
			if (playGame == 1)
				war.battle();
			
			//print results at the end of each round
			System.out.println("\nYour Deck Count: " + war.getPlayerSize());
			System.out.println("Computer Deck Count: " + war.getCompSize());
			System.out.println("\nYour Wins: " + war.getPlayerWins());
			System.out.println("Computer's Wins: " + war.getCompWins());
			System.out.println("Ties: " + war.getTies());
			System.out.println("-------------------------------------------");
		}
		
		//print final score
		war.endGameResults();
	}
}
