/*====================MenuItemFactory.java====================
 * @author Tony Matts, CSCI 426-01
 * created 11/09/13
 * last modified 11/13/13
 *-----------------------------------------------------
 * 
 */
public class MenuItemFactory {
    private static FileIO textFile;
    private static int numOfItems = 0;
    private static String mLine;
    private static boolean torf_4,torf_6;
    public static MenuItem[] mItem = new MenuItem[100];
    
    
    public static void makeMenu() {
        textFile = new FileIO();
        numOfItems = textFile.itemCount();
        textFile.removeFirstLine();
        for (int i=1; i<=numOfItems; i++) {
            if (FoodStorage.MenuChoice==1) {
                mLine = textFile.readCostLine(); 
            }
            else if (FoodStorage.MenuChoice==2) {
                mLine = textFile.readPriceLine();
            }
            else if (FoodStorage.MenuChoice==3) {
                mLine = textFile.readProfitLine();
            }
            else {
                mLine = textFile.readNameLine();
            }
            String splitLine[] = mLine.split(",");
            String returnLine[];
            
            //find boolean value from column 4
            if (splitLine[4].equals("T"))
                torf_4 = true;
            else
                torf_4 = false;
            //find boolean value from column 6
            if (splitLine[4].equals("T"))
                torf_6 = true;
            else
                torf_6 = false;
            
            //determine menItem type and add to array
            if (splitLine[0].equals("APPETIZER")) {
                mItem[i] = new Appetizer(splitLine[1],Float.parseFloat(splitLine[2]),Float.parseFloat(splitLine[3]),torf_4,Integer.parseInt(splitLine[5]),splitLine[6]);
            }
            else if (splitLine[0].equals("MEAL")) {
                mItem[i] = new Meal(splitLine[1],Float.parseFloat(splitLine[2]),Float.parseFloat(splitLine[3]),torf_4,Integer.parseInt(splitLine[5]),torf_6);
            }
            else if (splitLine[0].equals("DESSERT")) {
                mItem[i] = new Dessert(splitLine[1],Float.parseFloat(splitLine[2]),Float.parseFloat(splitLine[3]),torf_4,Integer.parseInt(splitLine[5]),torf_6);
            }
            else {
                mItem[i] = new ICAdapter(splitLine);
            }
        }//end for(i)
    }
}
