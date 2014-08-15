//===============================================
//Sorter.java by Tony Matts
//
//abstract class for handling the sort() method
//===============================================
abstract public class Sorter {
	
	//attributes
	protected int[] list;
	
	//constructor
	public Sorter(int[] i){ 
		list = i;
	}

	//sorting method for all sorting classes
	abstract void sort();

}
