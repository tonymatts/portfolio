/*====================IceCreamFactory.java====================
 * @author Tony Matts, CSCI 426-01
 * created 11/09/13
 * last modified 11/13/13
 *-----------------------------------------------------
 * 
 */
public class IceCreamFactory {
    
    private static boolean TF_4,TF_6;
    
    public static IceCream createIC(String[] itemToCreate) {
        
        //find boolean value from column 4
        if (itemToCreate[4].equals("T"))
            TF_4 = true;
        else
            TF_4 = false;
        //find boolean value from column 6
        if (itemToCreate[4].equals("T"))
            TF_6 = true;
        else
            TF_6 = false;
        
        
        if (itemToCreate[0].equals("SODA")) {
            return new Soda(itemToCreate[1],Float.parseFloat(itemToCreate[2]),Float.parseFloat(itemToCreate[3]),itemToCreate[4],itemToCreate[5],TF_6);
            }
        else {
            return new Milkshake(itemToCreate[1],Float.parseFloat(itemToCreate[2]),Float.parseFloat(itemToCreate[3]),itemToCreate[4],itemToCreate[5],Integer.parseInt(itemToCreate[6]));
        }
    }
    
}
