/*====================Milkshake.java====================
 * @author Tony Matts, CSCI 426-01
 * created 11/09/13
 * last modified 11/13/13
 *-----------------------------------------------------
 * 
 */
public class Milkshake extends IceCream {
    
    private int maltScoop;
    
    public Milkshake (String name, float cost, float price, String flavor, String iceCream, int maltScoop) {
        super (name,cost,price,flavor,iceCream);
        this.maltScoop = maltScoop;
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
        menLine += maltScoop;
        return menLine;
    }
}
