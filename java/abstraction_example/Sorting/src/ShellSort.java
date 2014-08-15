

public class ShellSort extends Sorter {

	   public ShellSort(int[] i) {
		super(i);
		// TODO Auto-generated constructor stub
	}

	/**
     * Shellsort, using a sequence suggested by Gonnet.
     * @param a an array of Comparable items.
     */
	
    public void shellsort( int [ ] a )
    {
        for( int gap = a.length / 2; gap > 0;
                     gap = gap == 2 ? 1 : (int) ( gap / 2.2 ) )
            for( int i = gap; i < a.length; i++ )
            {
                int tmp = a[ i ];
                int j = i;

                for( ; j >= gap && tmp < a[ j - gap ] ; j -= gap )
                    a[ j ] = a[ j - gap ];
                a[ j ] = tmp;
            }
    }

	@Override
	void sort() {
		shellsort(list);
	}
}
