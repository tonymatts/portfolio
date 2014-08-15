/**War.java
 * 
 * @author Tony Matts
 *
 * Object class for the card game war.
 * The shuffle method picks a random
 * card from the stack and pushes it
 * on top of the cards. The shuffle 
 * method does this 5000 times, which
 * in retrospect seems like overkill 
 * on running time, but it turns out to
 * be only negligible.
 */
import java.util.Random;
import java.util.Scanner;

public class War {

	//attributes
	protected ArrayStack<Integer> deck;
	protected ArrayStack<Integer> tempDeck;
	protected ArrayStack<Integer> playerDeck;
	protected ArrayStack<Integer> compDeck;
	protected int[] playerArray;
	protected int[] compArray;
	static Random rand;
	protected Scanner scanW;
	protected int randNum, count, tempCard, tempNum, playerWins, compWins, numOfTies;
	protected int p,c;
	
	//constructor
	public War() {
		deck = new ArrayStack<Integer>();
		tempDeck = new ArrayStack<Integer>();
		playerDeck = new ArrayStack<Integer>();
		compDeck = new ArrayStack<Integer>();
		playerArray = new int[52];
		compArray = new int[52];
		rand = new Random();
		scanW = new Scanner(System.in);
		randNum = 0;
		count = 0;
		tempCard = 0;
		tempNum = 0;
		playerWins = 0;
		compWins = 0;
		numOfTies = 0;
		p = 0;
		c = 0;
	}
	
	//get methods
	public int getPlayerSize() {
		return playerDeck.size();
	}
	
	public int getCompSize() {
		return compDeck.size();
	}
	
	public int getPArrayFirst() {
		return playerArray[0];
	}
	
	public int getCArrayFirst() {
		return compArray[0];
	}
	
	public int getPlayerWins() {
		return playerWins;
	}
	
	public int getCompWins() {
		return compWins;
	}
	
	public int getTies() {
		return numOfTies;
	}
	
	/**
	 * creates a deck of 52 cards in 4 sets of ints 2-14
	 * representing playing cards 2-ace.
	 * ace=14, king=13, queen=12,jack=11, 10=10,... etc
	 */
	public void createDeck() {
		for (int i=0; i<4; i++) 
			for (int j=2; j<15; j++)
				deck.push(j);
	}
	
	//shuffles the deck by removing a random index then placing it on top 5000 times
	public void shuffleDeck() {
		while (count < 5000) {
			randNum = rand.nextInt(52)+1;
			
			for(int i=1; i<randNum; i++)
				tempDeck.push(deck.pop());
			
			tempCard = deck.pop();
			
			for(int i=1; i<randNum; i++) 
				deck.push(tempDeck.pop());
			
			deck.push(tempCard);
			
			count++;
		}
	}
	
	//deals the cards evenly amongst the Player and Computer
	public void dealCards() {
		tempNum = (deck.size()/2);
		for(int i=1; i<=tempNum; i++) {
			playerDeck.push(deck.pop());
			compDeck.push(deck.pop());
		}	
	}
	
	//battle method determines who wins then calls the appropriate action
	public void battle() {
		if (playerDeck.top() > compDeck.top()) {
			playerWins = playerWins+1;
			playerWin();
		}	
		else if (playerDeck.top() < compDeck.top()) {
			compWins = compWins+1;
			compWin();
		}	
		else if (playerDeck.top() == compDeck.top()) {
			numOfTies = numOfTies+1;
			tieBattle();
		}	
	}
	
	//method if player wins the round
	public void playerWin() {
		System.out.println("\n-------------------------------------------");
		System.out.println("Your Top Card: " + playerDeck.top());
		System.out.println("Computer's Top Card: " + compDeck.top());
		System.out.println("\nYou win the round!");
		
		tempCard = playerDeck.pop();//sets aside player's tops card
		tempNum = playerDeck.size();
		
		//pops player's deck onto a temp deck
		for (int i=1; i<=tempNum; i++)
			tempDeck.push(playerDeck.pop());
		
		playerDeck.push(compDeck.pop());//computers top card is pushed onto the empty player deck
		playerDeck.push(tempCard);//players top card that won the round is pushed on top of the player deck
		
		//pushes any cards won from a tie battle onto the player deck
		if (p > 0) {
			for (int i=0; i<p; i++) {
				if (playerArray[i] != 0)
					playerDeck.push(playerArray[i]);
				if (compArray[i] != 0)
					playerDeck.push(compArray[i]);
			}
		}	
		p=0;//reset
		c=0;//reset
		
		//pushes the rest of the player's cards on top of the their used cards
		for (int i=1; i<=tempNum; i++)
			playerDeck.push(tempDeck.pop());
	}
	
	//method if computer wins the round
	public void compWin() {
		System.out.println("\n-------------------------------------------");
		System.out.println("\nYour Top Card: " + playerDeck.top());
		System.out.println("Computer's Top Card: " + compDeck.top());
		System.out.println("\nComputer wins the round!");
		
		tempCard = compDeck.pop();//sets aside computer's tops card
		tempNum = compDeck.size();
		
		//pops computer's deck onto a temp deck
		for (int i=1; i<=tempNum; i++)
			tempDeck.push(compDeck.pop());
		
		compDeck.push(playerDeck.pop());//player's top card is pushed onto the empty computer deck
		compDeck.push(tempCard);//computer's top card that won the round is pushed on top of the computer deck
		
		if (c > 0) {
			for (int i=0; i<c; i++) {
				if (compArray[i] != 0)
					compDeck.push(compArray[i]);
				if (playerArray[i] != 0)
					compDeck.push(playerArray[i]);
			}	
		}	
		c=0;//reset
		p=0;//reset
		
		//pushes the rest of the computer's cards on top of the it's used cards
		for(int i=1; i<=tempNum; i++)
			compDeck.push(tempDeck.pop());
	}
	
	//method if its a tie
	public void tieBattle() {
		System.out.println("\n-------------------------------------------");
		System.out.println("\nYour Top Card: " + playerDeck.top());
		System.out.println("Computer's Top Card: " + compDeck.top());
		System.out.println("\nIt's a draw. Prepare for WAR.\n");
		
		//checks to make sure the computer has enough cards for a full tie battle
		//then fills a temp array with the first 4 cards on top
		if (getCompSize() >= 4) {
			compArray[c] = compDeck.pop();
			c++;
			compArray[c] = compDeck.pop();
			c++;
			compArray[c] = compDeck.pop();
			c++;
			compArray[c] = compDeck.pop();
			c++;
			
			randNum = rand.nextInt(3)+1;//computer chooses which card to play
			
			//case select pulls the card the computer picked and puts the value of 0 in its place
			//then pushes that card on top of the computer deck
			switch(randNum) {
			case 1:
				compDeck.push(compArray[c-3]);
				compArray[c-3] = 0;
				break;
			case 2:
				compDeck.push(compArray[c-2]);
				compArray[c-2] = 0;
				break;
			case 3:
				compDeck.push(compArray[c-1]);
				compArray[c-1] = 0;
				break;
			default:
				compDeck.push(compArray[c-1]);
				compArray[c-1] = 0;
				break;	
			}
		}
		
		//checks # of cards if < 4
		else if (getCompSize() < 4) {
			tempNum = getCompSize();
			for (int i=0; i<tempNum; i++) {
				compArray[c] = compDeck.pop();
				c++;
			}
			
			randNum = rand.nextInt(tempNum)+1;//picks a card
			
			//case select pulls the card the computer picked and puts the value of 0 in its place
			//then pushes that card on top of the computer deck
			switch(randNum) {
			case 1:
				compDeck.push(compArray[c-3]);
				compArray[c-3] = 0;
				break;
			case 2:
				compDeck.push(compArray[c-2]);
				compArray[c-2] = 0;
				break;
			case 3:
				compDeck.push(compArray[c-1]);
				compArray[c-1] = 0;
				break;
			default:
				compDeck.push(compArray[c-1]);
				compArray[c-1] = 0;
				break;	
			}
		}
		
		//checks to make sure the player has enough cards for a full tie battle
		//then fills a temp array with the first 4 cards on top
		if (getPlayerSize() >= 4) {
			playerArray[p] = playerDeck.pop();
			p++;
			playerArray[p] = playerDeck.pop();
			p++;
			playerArray[p] = playerDeck.pop();
			p++;
			playerArray[p] = playerDeck.pop();
			p++;
			
			//player prompt to pick which card to play
			System.out.println("\nYou draw 3 cards off your stack. Choose which one to play. " +
			"1=1st off the stack, 2=2nd off the stack, 3=3rd off the stack");
			tempNum = scanW.nextInt();
			
			//case select pulls the card the player picked and puts the value of 0 in its place
			//then pushes that card on top of the player deck
			switch(tempNum) {
			case 1:
				playerDeck.push(playerArray[p-3]);
				playerArray[p-3] = 0;
				break;
			case 2:
				playerDeck.push(playerArray[p-2]);
				playerArray[p-2] = 0;
				break;
			case 3:
				playerDeck.push(playerArray[p-1]);
				playerArray[p-1] = 0;
				break;
			default:
				playerDeck.push(playerArray[p-1]);
				playerArray[p-1] = 0;
				break;
			}
		}	
		
		//checks # of cards if < 4
		else if (getPlayerSize() < 4) {
			tempNum = getPlayerSize();
			for (int i=0; i<tempNum; i++) {
				playerArray[p] = playerDeck.pop();
				p++;
			}
			
			//player prompt to pick which card to play
			System.out.println("\nYou have " + tempNum +" cards left. Choose which one to play. " +
			"1=1st off the stack, 2=2nd off the stack, 3=3rd off the stack.");
			tempNum = scanW.nextInt();
			
			//case select pulls the card the player picked and puts the value of 0 in its place
			//then pushes that card on top of the player deck
			switch(tempNum) {
			case 1:
				playerDeck.push(playerArray[p-3]);
				playerArray[p-3] = 0;
				break;
			case 2:
				playerDeck.push(playerArray[p-2]);
				playerArray[p-2] = 0;
				break;
			case 3:
				playerDeck.push(playerArray[p-1]);
				playerArray[p-1] = 0;
				break;
			default:
				playerDeck.push(playerArray[p-1]);
				playerArray[p-1] = 0;
				break;
			}
		}	
		
		//another battle carried out with new top cards
		battle();
	}
	
	//print rules
	public void printRules() {
		System.out.println("The deck is divided evenly among two players, " +
							"\ngiving each a down stack. In unison, each player reveals " +
							"\nthe top card on his deck, and the player with the higher card " +
							"\ntakes both the cards played and moves them to the bottom of their stack. " +
							"\nIf the two cards played are of equal value, each player lays down " +
							"\nthree face-down cards and picks one of the cards out out of the " +
							"\nthree, and the higher-valued card wins all of the cards on the table, " +
							"\nwhich are then added to the bottom of the player's stack. In the case of " +
							"\nanother tie, the war process is repeated until there is no tie. The " +
							"\nface value of each cards is as follows: Ace=14 King=13 Queen=12 Jack=11 " +
							"\n2 through 10=Same as number on card (10=10, etc.). A player wins " +
							"\nby collecting all the cards. If a player runs out of cards while " +
							"\ndealing the face-down cards of a war, they may play the last card in " +
							"\ntheir deck as their face-up card and still have a chance to stay in the " +
							"game.\n-courtesy Wikipedia");
	}

	//post scores
	public void endGameResults() {
		System.out.println("\n-------------------------------------------");
		System.out.println("Your Deck Size: " + playerDeck.size() +
							"\nComputer's Deck Size: " + compDeck.size());
		System.out.println("Your Wins: " + getPlayerWins());
		System.out.println("Computer's Wins: " + getCompWins());
		System.out.println("Ties: " + getTies());
		System.out.println("-------------------------------------------");
		
		if (playerDeck.size() > compDeck.size())
			System.out.println("You Win!");
		else if (playerDeck.size() < compDeck.size())
			System.out.println("Sorry, you lose. Better luck next time.");
		else if (playerDeck.size() == compDeck.size())
			System.out.println("You tied, it's a draw.");
	}
	
}
