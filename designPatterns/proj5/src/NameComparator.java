/*====================NameComparator.java====================
 * @author Tony Matts, CSCI 426-01
 * created 11/09/13
 * last modified 11/13/13
 *-----------------------------------------------------
 * 
 */

public class NameComparator extends Comparator {
    
    public NameComparator (String name, float cost, float price) {
        super (name,cost,price);
    }
    
    //swaps two elements in an array
    public void arraySwap(Comparator[] m, int ch, int pa) {
        Comparator temp = m[ch];
        m[ch] = m[pa];
        m[pa] = temp;
    }
}
