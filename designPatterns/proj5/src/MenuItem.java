/*====================MenuItem.java====================
 * 
 * @author Tony Matts, CSCI 426
 * created 11/09/13
 * last modified 11/13/13
 * 
 *-----------------------------------------------------
 * 
 * Inherited by Appetizer.java, Meal.java, and 
 * Dessert.java.
 * 
 *-----------------------------------------------------
 */
public class MenuItem {
    
    protected String name;
    protected float cost,price;
    
    //--------------------GETTERS--------------------
    //
    //-----------------------------------------------
    public String getName() {
        return name;
    }
    public float getCost() {
        return cost;
    }
    public float getPrice() {
        return price;
    }
    public float getProfit() {
        return (price - cost);
    }
    
    //--------------------SETTERS--------------------
    //
    //-----------------------------------------------
    public void setName(String name) {
        this.name = name;
    }
    public void setCost(float cost) {
        this.cost = cost;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    
}
