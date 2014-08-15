/*====================Euro.java====================
 * 
 * @author Tony Matts, CSCI 426
 * created 11/09/13
 * last modified 11/14/13
 * 
 *-----------------------------------------------------
 * 
 * Implements Currency interface.
 * 
 *----------------------------------------------------- 
 */
import java.text.DecimalFormat;

public class Euro implements Currency {

    //-------------------convert-------------------
    // Converts a euro amount to a unit amount.
    //---------------------------------------------
    @Override
    public float convert(float currency) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Float.parseFloat(df.format((currency - 0.1)/1.05));
    }

}
