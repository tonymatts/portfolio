/*====================IceCream.java====================
 * @author Tony Matts, CSCI 426-01
 * created 11/09/13
 * last modified 11/13/13
 *-----------------------------------------------------
 * 
 */
public class IceCream {
    
    protected String name, flavor, iceCream;
    protected float cost, price;
    
    public IceCream (String name, float cost, float price, String flavor, String iceCream) {
        this.name = name;
        this.cost = cost;
        this.price = price;
        this.flavor = flavor;
        this.iceCream = iceCream;
    }
    
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
    public String getFlavor() {
        return flavor;
    }
    public String getICream() {
        return iceCream;
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
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }
    public void setICream(String iceCream) {
        this.iceCream = iceCream;
    }
}
