//==================================================
// Driver.java by Tony Matts
//
// Sorts a random array of integers 1-50 and 
// displays a bar for each value.
//==================================================
import javax.swing.JFrame;
import java.util.Random;

public class Driver {

	public static void main(String[] args) {
		
		//vars
		Random rand = new Random();
		int num;
		
		int[] myArray = new int[50];
		
		JFrame frame = new JFrame ("GO GO GO Bars!");
		
		//random values from 1-50 are assigned to the array
		for(int index = 0; index < myArray.length; index++) {
			num = rand.nextInt(49) + 1;
			myArray[index] = num;
		}
		/*
		for(int output : myArray)
			System.out.println(output);
		
		System.out.println();
			
		SelectSort.selectionSort(myArray);
		
		for(int output : myArray)
			System.out.println(output);
		 */
		
		//myArray is used in the sorting method
		SelectSort.selectionSort(myArray);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(SelectSort.getObject());
		frame.pack();
		frame.setVisible(true);
	}

}
