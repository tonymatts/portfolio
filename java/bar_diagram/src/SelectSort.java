//==================================================
//SelectSort.java by Tony Matts
//
//uses a selection sort method to sort an int array
//==================================================
public class SelectSort {

	static BarsPanel object;
	
	//sorts int in ascending order
	public static void selectionSort (int[] list) {
		int min;
		int temp;
		object = new BarsPanel(list);
		
		for (int index = 0; index < list.length - 1; index++) {
			min = index;
			for (int scan = index + 1; scan < list.length; scan++) {
				if (list[scan] < list[min])
					min = scan;
			}	
			
			temp = list[min];
			list[min] = list[index];
			list[index] = temp;
			
			//meant to delay each sort and display it
			//graphically using a repaint() method
			try {
				Thread.sleep(30);
				object.drawBars(list);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
	}
	//returns the BarsPanel object
	public static BarsPanel getObject() {
		return object;
	}
	
}

