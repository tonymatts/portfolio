/*====================AppLogic.java====================
 * 
 * @author Tony Matts, CSCI 426
 * created 11/09/13
 * last modified 12/02/13
 * 
 *-----------------------------------------------------
 * 
 * Contains most of the logic for running the program
 * like reading a file, inserting an item, deleting an
 * item, and displaying an item.
 * 
 *----------------------------------------------------- 
 */
public class AppLogic {
    
    private static FoodStorage CurrentMenu;
    
    //-------------------readFile-------------------
    // creates array filling it with the menu
    //----------------------------------------------
    private static void readFile() {
        CurrentMenu = new FoodStorage();
        CurrentMenu.CreateMenu();
        System.out.println("\nFile Loaded");
    }
    
    //-------------------displayMenu-------------------
    // displays the current menu to the user
    //-------------------------------------------------
    private static void displayMenu() {
        if (CurrentMenu==null) {
            System.out.println("\nPlease Load a File");
        }
        else {
            CurrentMenu.DisplayMenu();
        }
    }
    
    //-------------------searchedItem-------------------
    // prints a menu item that the user searches for
    //--------------------------------------------------
    private static void searchedItem() {
        CurrentMenu.PrintMenuItem();
    }
    
    //-------------------insertItem-------------------
    // allows the user to insert an item into the
    // current menu
    //------------------------------------------------
    private static void insertItem() {
        CurrentMenu.InsertItem();
    }
    
    //-------------------deleteItem-------------------
    // allows the user to delete an item fromt the 
    // current menu
    //------------------------------------------------
    private static void deleteItem() {
        CurrentMenu.DeleteItem();
    }
    
    //-----------------createOrder-----------------
    // allows the user to create a new order
    //---------------------------------------------
    private static void createOrder() {
        CurrentMenu.createTheOrder();
    }
    
    //-------------------orderForm-------------------
    // allows the user to create an order or add to 
    // a current order
    //-----------------------------------------------
    private static void addOrder() {
        CurrentMenu.AddToOrder();
    }
    
    //-------------------diplayOrder-------------------
    // prints the current order for the user
    //-------------------------------------------------
    private static void displayOrder() {
        CurrentMenu.ShowOrder();
    }
    
    //-------------------payOrder-------------------
    // user can pay for the current order
    //----------------------------------------------
    private static void payOrder() {
        CurrentMenu.payForOrder();
    }
    
    //----------------displayAllOrders----------------
    // prints all orders that have received payment
    //------------------------------------------------
    private static void displayAllOrders() {
        CurrentMenu.displayOrders();
    }
    
    //=========================================================================
    //=========================================================================
    
    //------------------------main------------------------
    // Displays a menu of options for the user 
    // to choose from including exit.
    //----------------------------------------------------
    public static void main(String[] args) {
        boolean usingMenu=true;
        boolean fileLoaded=false;
        int userChoice;
        
        //First MENU which allows the user to load a txt file
        while (!fileLoaded && usingMenu) {
            System.out.println("MAIN MENU: Enter an option number (1 or 2):");
            System.out.println("1 - Load a File\n2 - Exit");
            userChoice = UI.Input();
            if (userChoice==1) {
                readFile();
                fileLoaded=true;
            }
            else if(userChoice==2) {
                usingMenu = false;
            }
            else {
               UI.invalid();
            }
        }
        
        //2nd MENU allows the user to manipulate the loaded txt file
        while(usingMenu) {
            UI.Output();
            userChoice = UI.Input();
            
            if(userChoice==1) {
                displayMenu();
            }
            else if(userChoice==2) {
                searchedItem();
            }
            else if(userChoice==3) {
                insertItem();
            }
            else if(userChoice==4) {
                deleteItem();
            }
            else if(userChoice==5) {
                createOrder();
            }
            else if(userChoice==6) {
                addOrder();
            }
            else if(userChoice==7) {
                displayOrder();
            }
            else if(userChoice==8) {
                payOrder();
            }
            else if(userChoice==9) {
                displayAllOrders();
            }
            else if(userChoice==10) {
                usingMenu = false;
            }
            else {
               UI.invalid();
            }
        }
    }
}
