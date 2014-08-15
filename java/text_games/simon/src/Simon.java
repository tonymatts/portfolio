/**Simon.java
 * 
 * @author Tony Matts
 * 
 * object class for the game Simon
 */

import java .util.Scanner;
import java.util.Random;
import javax.swing.Timer;

public class Simon {
	
	//attributes
	protected ArrayQueue<Integer> colors;
	protected int tempColor, tempNum, scanNum, count, score;
	Scanner scan;
	Random rand;
	protected int delayMax = 2000;
	protected int delayMin = 500;
	protected boolean playerIsRight = true;
	
	//constructor
	public Simon() {
		colors = new ArrayQueue<Integer>();
		tempColor = 0;
		tempNum = 0;
		scanNum = 0;
		count = 0;
		score = 0;
		rand = new Random();
		scan = new Scanner(System.in);
	}
	
	//adds a random color to the queue
	public void addColor() {
		tempColor = rand.nextInt(4);
		colors.enqueue(tempColor);	
	}
	
	//displays colors using a delay which is decremented each time
	public void showColors() throws InterruptedException {
		tempNum = colors.size();
		for (int i=0; i<tempNum; i++) {
			tempColor = colors.dequeue();
			
			System.out.println("-----------------");
			switch(tempColor) {
			case 0:
				System.out.println("0 - green");
				break;
			case 1:
				System.out.println("1 - yellow");
				break;
			case 2:
				System.out.println("2 - red");
				break;
			case 3:
				System.out.println("3 - blue");
				break;	
			}
			System.out.println("-----------------");
			
			colors.enqueue(tempColor);
			Thread.sleep(delayMax);
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("-----------------");
			System.out.println("    =========    ");
			System.out.println("-----------------");
			Thread.sleep(delayMax);
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}
	}
	
	//checks each answer the user enters
	public void check() {
		count = 0;
		while (count < colors.size() && playerIsRight) {
			tempColor = colors.dequeue();
			colors.enqueue(tempColor);
			System.out.println("\n<===============Enter one color at a time===============>");
			System.out.println("\nNumbers represent colors as follows:\n" +
			"\n0 - green\n1 - yellow\n2 - red\n3 - blue");
			scanNum = scan.nextInt();
			
			if (scanNum == tempColor) {
				System.out.println("\nCorrect!");
				reduceDelay();//decrements delay each round
			}
			else {
				System.out.println("\nSorry, that color is incorrect.");
				System.out.println("Correct color is " + tempColor);
				playerIsRight = false;
			}
			count++;
		}
		score++;
	}
	
	//continues game if user is right
	public int isValid() {
		if (playerIsRight)
			tempNum = 1;
		else
			tempNum = 0;
		
		return tempNum;
	}
	
	//decrements the delay
	public void reduceDelay() {
		if (delayMax > delayMin)
			delayMax = delayMax-100;
	}
	
	public void getScore() {
		System.out.println("\nYou went " + score + " round(s)! Nice Job!");
	}
}
