//==============================================================
// SortDriver.java by Tony Matts
//
// Creates an array of 10000 integers, then uses an 
// abstract  method for calling a sort method, depending on the 
// sorting object created.
//==============================================================
import java.util.Random;

public class SortDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random();
		int tempNum = 0;
		int[] listArray = new int[100000];
		
		//loop assigns random ints to an array
		for (int i = 0; i < 100000; i++) {
			tempNum = rand.nextInt(500000);
			listArray[i] = tempNum;
		}
		
		//-----------------------------------------------
		//creates sorting objects and passed the random
		//list array in
		//-----------------------------------------------
		//BubbleSort b = new BubbleSort(listArray);
		//b.sort();

		//HeapSort h = new HeapSort(listArray);
		//h.sort();
		
		//InsertionSort i = new InsertionSort(listArray);
		//i.sort();
		
		//MergeSort m = new MergeSort(listArray);
		//m.sort();
		
		//QuickSort q = new QuickSort(listArray);
		//q.sort();
		
		//SelectionSort se = new SelectionSort(listArray);
		//se.sort();
		
		ShellSort sh = new ShellSort(listArray);
		long startTime = System.currentTimeMillis();
		sh.sort();
		long endTime = System.currentTimeMillis();
		System.out.println("That took " + (endTime - startTime) + " milliseconds");
	}

}
