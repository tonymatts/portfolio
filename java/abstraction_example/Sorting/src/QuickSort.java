

public class QuickSort extends Sorter{
	
	int comparisons = 0;
	int exchanges = 0;
   
    public QuickSort(int[] i) {
		super(i);
		// TODO Auto-generated constructor stub
	}

	public  void quicksort(int[] a) {
        
    	shuffle(a);                        // to guard against worst-case
        quicksort(a, 0, a.length - 1);
    }

    public void sort()
    {
    	quicksort(list);
    }
    // quicksort a[left] to a[right]
    public  void quicksort(int[] a, int left, int right) {
        if (right <= left) return;
        int i = partition(a, left, right);
        quicksort(a, left, i-1);
        quicksort(a, i+1, right);
    }

    // partition a[left] to a[right], assumes left < right
    private  int partition(int[] a, int left, int right) {
        int i = left - 1;
        int j = right;
        while (true) {
            while (less(a[++i], a[right]))      // find item on left to swap
                ;                               // a[right] acts as sentinel
            while (less(a[right], a[--j]))      // find item on right to swap
                if (j == left) break;           // don't go out-of-bounds
            if (i >= j) break;                  // check if pointers cross
            exch(a, i, j);                      // swap two elements into place
        }
        exch(a, i, right);                      // swap with partition element
        return i;
    }

    // is x < y ?
    private  boolean less(double x, double y) {
        comparisons++;
        return (x < y);
    }

    // exchange a[i] and a[j]
    private  void exch(int[] a, int i, int j) {
        exchanges++;
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // shuffle the array a[]
    private  void shuffle(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = i + (int) (Math.random() * (N-i));   // between i and N-1
            exch(a, i, r);
        }
    }



}

