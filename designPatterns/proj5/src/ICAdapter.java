/*====================ICAdapter.java====================
 * @author Tony Matts, CSCI 426-01
 * created 11/09/13
 * last modified 11/13/13
 *-----------------------------------------------------
 * 
 */
public class ICAdapter extends MenuItem{
    
    private IceCream iCream;
    
    public ICAdapter (String[] ICitem) {
        
        iCream = IceCreamFactory.createIC(ICitem);
        
    }
    
    //--------------------toString--------------------
    // Prints item to string for output.
    //-----------------------------------------------
    @Override
    public String toString() {
        return iCream.toString();
    }
    
    //--------------------GETTERS--------------------
    //
    //-----------------------------------------------
    @Override
    public String getName() {
        return iCream.getName();
    }
    @Override
    public float getCost() {
        return iCream.getCost();
    }
    @Override
    public float getPrice() {
        return iCream.getPrice();
    }
    @Override
    public float getProfit () {
        return (iCream.getPrice() - iCream.getCost());
    }
    
}
