/*====================Dessert.java====================
 * @author Tony Matts, CSCI 426-01
 * created 11/09/13
 * last modified 11/13/13
 *-----------------------------------------------------
 * 
 * Inherits from MenuItem.
 * 
 *----------------------------------------------------- 
 */

public class Dessert extends MenuItem {
    private boolean isCake;
    private int initialServings;
    private boolean isSpecial;
    
    public Dessert (String name, float cost, float price, boolean isCake, int initialServings, boolean isSpecial) {
        this.name=name;
        this.cost=cost;
        this.price=price;
        this.isCake = isCake;
        this.initialServings = initialServings;
        this.isSpecial = isSpecial;
    }
    
    public String toString () {
        String menLine = name;
        menLine += " ";
        menLine += cost;
        menLine += " ";
        menLine += price;
        menLine += " ";
        menLine += isCake;
        menLine += " ";
        menLine += initialServings;
        menLine += " ";
        menLine += isSpecial;
        return menLine;
    }
}
