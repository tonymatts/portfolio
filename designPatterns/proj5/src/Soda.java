/*====================Soda.java====================
 * @author Tony Matts, CSCI 426-01
 * created 11/09/13
 * last modified 11/13/13
 *-----------------------------------------------------
 * 
 */
public class Soda extends IceCream{
    
    private boolean whipCream;
    
    public Soda (String name, float cost, float price, String flavor, String iceCream, boolean whipCream) {
        super (name,cost,price,flavor,iceCream);
        this.whipCream = whipCream;
    }
    
    public String toString() {
        String menLine = name;
        menLine += " ";
        menLine += cost;
        menLine += " ";
        menLine += price;
        menLine += " ";
        menLine += flavor;
        menLine += " ";
        menLine += iceCream;
        menLine += " ";
        menLine += whipCream;
        return menLine;
    }
}
