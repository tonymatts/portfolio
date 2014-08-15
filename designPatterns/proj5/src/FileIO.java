/*====================FileIO.java====================
 * @author Tony Matts, CSCI 426-01
 * created 11/09/13
 * last modified 11/13/13
 *---------------------------------------------------
 * 
 * Reads in CostList, NameList, PriceList, and 
 * ProfitList txt files.
 * 
 *--------------------------------------------------- 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileIO {
    public File costFile, priceFile, profitFile, nameFile;
    public Scanner costScan, priceScan, profitScan, nameScan, costCount, costItem, priceCount, priceItem, profitCount, profitItem, nameCount, nameItem;
    int count = 0;
    
    public FileIO () {
        costFile = new File("CostList.txt");
        priceFile = new File("PriceList.txt");
        profitFile = new File("ProfitList.txt");
        nameFile = new File("NameList.txt");
        try {
            costScan = new Scanner(costFile);
            costCount = new Scanner(costFile);
            costItem = new Scanner(costFile);
            
            priceScan = new Scanner(priceFile);
            priceCount = new Scanner(priceFile);
            priceItem = new Scanner(priceFile);
            
            profitScan = new Scanner(profitFile);
            profitCount = new Scanner(profitFile);
            profitItem = new Scanner(profitFile);
            
            nameScan = new Scanner(nameFile);
            nameCount = new Scanner(nameFile);
            nameItem = new Scanner(nameFile);
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("--I refuse your file and substitute an error.--");
        }
    }
    
    //reads first line which is just the title of the item list.
    public void removeFirstLine() {
        costItem.next();
        priceItem.next();
        profitItem.next();
        nameItem.next();
    }
    
    //counts numbers of items in the list of menu items
    public int itemCount() {
        costCount.next();
        while (costCount.hasNext()) {
            costCount.next();
            count++;
        }
        return count;
    }
    
    //returns a single line from one of the menus.
    public String readCostLine() {
        return costItem.next();
    }
    public String readPriceLine() {
        return priceItem.next();
    }
    public String readProfitLine() {
        return profitItem.next();
    }
    public String readNameLine() {
        return nameItem.next();
    }

}
