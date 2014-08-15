/*====================Meal.java====================
 * @author Tony Matts, CSCI 426-01
 * created 11/09/13
 * last modified 11/13/13
 *-----------------------------------------------------
 * 
 * Inherits from MenuItem.
 * 
 *-----------------------------------------------------
 */

public class Meal extends MenuItem {
    private boolean isSalad;
    private int cookTime;
    boolean isOrganic;
    
    public Meal (String name, float cost, float price, boolean isSalad, int cookTime, boolean isOrganic) {
        this.name=name;
        this.cost=cost;
        this.price=price;
        this.isSalad = isSalad;
        this.cookTime = cookTime;
        this.isOrganic = isOrganic;
    }
    
    public String toString () {
        String menLine = name;
        menLine += " ";
        menLine += cost;
        menLine += " ";
        menLine += price;
        menLine += " ";
        menLine += isSalad;
        menLine += " ";
        menLine += cookTime;
        menLine += " ";
        menLine += isOrganic;
        return menLine;
    }
}
