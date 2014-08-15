/*=======================UI.java=======================
 * 
 * @author Tony Matts, CSCI 426-01
 * created 11/09/13
 * last modified 12/02/13
 * 
 *-----------------------------------------------------
 * 
 * Contains methods for a console based user interface 
 * used by AppLogic.java to display menu options and 
 * read an integer input from the user.
 * 
 *----------------------------------------------------- 
 */

import java.util.Scanner;

public class UI {
    
    //--------------------------------Output--------------------------------
    // Main Menu after file is loaded by the user
    //----------------------------------------------------------------------
    public static void Output() {
        System.out.println("\nFILE MENU: Enter an option number (1 - 10):");
        System.out.println("\n1 - Display Current Menu\n2 - Search for an Item\n" + 
                "3 - Insert an Item\n4 - Delete an Item\n" + 
                "\n5 - Create New Order\n6 - Add an Item to Current Order\n" + 
                "7 - Show Current Order Form For Tending\n8 - Pay For Current Order\n" +
                "9 - Show All Orders\n10 - EXIT");
    }
    
    //--------------------------------Input---------------------------------
    // Handles int inputs from the user
    //----------------------------------------------------------------------
    public static int Input() {
        Scanner scan = new Scanner(System.in);
        int In = scan.nextInt();
        return In;
    }
    
    //--------------------------------invalid--------------------------------
    // prints error message
    //----------------------------------------------------------------------
    public static void invalid() {
        System.out.println("\n-- Invlaid Response --");
    }
    
    //-----------------------------formatOptions----------------------------
    // prints format options to the user
    //----------------------------------------------------------------------
    public static void formatOptions() {
        System.out.println("\nPlease enter information separated by commas, no spaces:\n");
        System.out.println("Appetizer Format: APPETIZER,name,cost(float),price(float),isCold(TorF),prepTime(integer)");
        System.out.println("Meal Format: MEAL,name,cost(float),price(float),isSalad(TorF),cookTime(integer)");
        System.out.println("Dessert Format: DESSERT,name,cost(float),price(float),isCake(TorF),inititalServings(integer)");
        System.out.println("Milkshake Format: MILKSHAKE,name,cost(float),price(float),flavor,ice cream,malt scoops(integer)");
        System.out.println("Soda Format: SODA,name,cost(float),price(float),flavor,ice cream,whip cream(TorF)");
    }
    
    //---------------------------currencyOptions----------------------------
    // prints currency options for displaying the menu item(s)
    //----------------------------------------------------------------------
    public static void currencyOptions() {
        System.out.println("\nEnter currency type:\n1 - Dollar\n2 - Euro\n3 - Yen");
    }
    
    public static void unitOptions() {
        System.out.println("\nEnter unit type:\n1 - Cost\n2 - Price\n3 - Profit");
    }
    
    public static void enterFloat() {
        System.out.println("\nEnter Amount as a float:");
    }
}
