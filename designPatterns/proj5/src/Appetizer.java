/*====================Appetizer.java====================
 * @author Tony Matts, CSCI 426
 * created 11/09/13
 * last modified 11/13/13
 *-----------------------------------------------------
 * 
 * Inherits from MenuItem.
 * 
 * -----------------------------------------------------
 */

public class Appetizer extends MenuItem {
    private boolean isCold;
    private int prepTime;
    private String cOfOrigin;
    
    public Appetizer (String name, float cost, float price, boolean isCold, int prepTime, String cOfOrigin) {
        this.name=name;
        this.cost=cost;
        this.price=price;
        this.isCold = isCold;
        this.prepTime = prepTime;
        this.cOfOrigin = cOfOrigin;
    }
    
    public String toString () {
        String menLine = name;
        menLine += " ";
        menLine += cost;
        menLine += " ";
        menLine += price;
        menLine += " ";
        menLine += isCold;
        menLine += " ";
        menLine += prepTime;
        menLine += " ";
        menLine += cOfOrigin;
        return menLine;
    }
}
