/*====================Dollar.java====================
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

public class Dollar implements Currency {

    //-------------------convert-------------------
    // Converts a dollar amount to a unit amount.
    //---------------------------------------------
    @Override
    public float convert(float currency) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Float.parseFloat(df.format((currency - 0.05)/1.1));
    }

}
