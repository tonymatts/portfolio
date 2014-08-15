

public class InsertionSort extends Sorter{
	
	
    public InsertionSort(int[] i) {
		super(i);
		// TODO Auto-generated constructor stub
	}

	/**
     * Simple insertion sort.
     * @param a an array of Comparable items.
     */
    public void insertionSort( int [ ] a )
    {
        for( int p = 1; p < a.length; p++ )
        {
            int tmp = a[ p ];
            int j = p;

            for( ; j > 0 && tmp < a[ j - 1 ]; j-- )
                a[ j ] = a[ j - 1 ];
            a[ j ] = tmp;
        }
    }

	@Override
	void sort() {
		insertionSort(list);
	}


}
