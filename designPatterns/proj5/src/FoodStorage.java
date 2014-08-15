/*====================FoodStorage.java====================
 * 
 * @author Tony Matts, CSCI 426
 * created 11/09/13
 * last modified 12/02/13
 * 
 *--------------------------------------------------------
 * 
 * Creates and maintains the MenuItem array containting
 * the menu to be manipulated by the user. Also contains
 * currency and State objects.
 * 
 *--------------------------------------------------------
 */
import java.util.Scanner;

public class FoodStorage {
    
    //-----------------------Variables-----------------------
    //
    //-------------------------------------------------------
    final static int NEW = 0; //orderItems[] contains no items
    final static int INPROCESS = 1; //orderItems[] has one or more items
    final static int TENDED = 2; //user has displayed orderItems[]
    final static int PAID = 3; //user has paid for the orderItems[], PAID state is permanent
    private int state[];
    
    Currency dAmount = new Dollar();
    Currency eAmount = new Euro();
    Currency yAmount = new Yen();
    
    static int MenuChoice;
    private int orderItemNum=0;
    private MenuItem[] menItem;
    private MenuItem[][] orderMultItems;//use 2d array for multiple orders
    private Comparator[] compare;
    private Comparator menuToCompare;
    private FileIO txtFile;
    private int numOfItems = 0;
    private String menuLine;
    private boolean TorF_4,TorF_6;
    private Scanner scanIn;
    private boolean found, entry;
    private String userInput;
    private int userInt;
    private boolean checking = true;
    int placeMark;
    private boolean comparing = true;
    private float userFloat;
    private String splitLine[];
    private float unitCost, unitPrice;
    private float orderPrice[];
    private int orderNum = 0;//each order is kept track of
    
    //-----------------------Constructor-----------------------
    //
    //---------------------------------------------------------
    public FoodStorage () {
        txtFile = new FileIO();
        menItem = new MenuItem[100];
        orderMultItems = new MenuItem[100][100];
        compare = new Comparator[100];
        scanIn = new Scanner(System.in);
        orderPrice = new float[100];
        state = new int[100];
    }
    
    //-----------------------CreateMenu-----------------------
    // Creates menu from txt file by adding each line's values 
    // into an array.
    //--------------------------------------------------------
    public void CreateMenu() {
        System.out.println("Which file would you like loaded?");
        System.out.println("1 - CostList\n2 - PriceList\n3 - ProfitList\n4 - NameList");
        MenuChoice = scanIn.nextInt();
        
        MenuItemFactory.makeMenu();
        numOfItems = txtFile.itemCount();
        for (int i=1; i<=numOfItems; i++) {
            menItem[i] = MenuItemFactory.mItem[i];
        }
    }
    
    //-----------------------DisplayMenu-----------------------
    // Displays current menu.
    //--------------------------------------------------------
    public void DisplayMenu() {
        for (int i=1; i<=numOfItems; i++) {
            System.out.println(menItem[i]);
        }
    }
    
    //----------------------PrintMenuItem---------------------
    // Searches for inputed menu item by either Name or
    // Currency and prints it.
    //--------------------------------------------------------
    public void PrintMenuItem () {
        System.out.println("\nFind an Item(s) by:\n1 - Name\n2 - Currency");
        userInt = scanIn.nextInt();
        
        //Name or Currency Condition 1 - Name
        if (userInt==1) {
            for (int i=1; i<=numOfItems; i++) {
                System.out.println(menItem[i].getName());
            }
            System.out.println("\nEnter the name of the food you'd like info for:");
            userInput = scanIn.next();
            for (int i=1; i<=numOfItems; i++) {
                if (menItem[i].getName().equals(userInput)) {
                    System.out.println("\n" + menItem[i]);
                    found = true;
                }
            }
            if(!found)
                UI.invalid();
        
            found = false;
            userInt=1;//reset
        }
        
        //Name or Currency Condition 2 - Currency
        else if(userInt==2) {
            UI.currencyOptions();
            userInt = scanIn.nextInt();
            
            //Currency Unit Conditional 1 - Dollar
            if (userInt==1) {
                amountCheck();
                userInt=1;//reset
            }
            //Currency Unit Conditional 2 Euro
            else if (userInt==2) {
                amountCheck();
                userInt=2;//reset
            }
            //Currency Unit Conditional 3 Yen
            else if (userInt==3) {
                amountCheck();
                userInt=3;//reset
            }
            //Currency Type Conditional
            else {
                UI.invalid();
            }
            userInt=2;//safety
        }
        
        //Item Name or Currency Conditional
        else {
            UI.invalid();
        }
    }
    
    public void amountCheck() {
        UI.unitOptions();
        userInt = scanIn.nextInt();
        
        //Amount Conditional 1 Cost
        if (userInt==1) {
            UI.enterFloat();
            userFloat = scanIn.nextFloat();
            System.out.println("$" + userFloat + " converts to " + dAmount.convert(userFloat));
            userFloat = dAmount.convert(userFloat);
            for (int i=1; i<=numOfItems; i++) {
                if (menItem[i].getCost()==userFloat) {
                    System.out.println("\n" + menItem[i]);
                }
            }
        }
        //Amount Conditional 2 Price
        else if (userInt==2) {
            UI.enterFloat();
            userFloat = scanIn.nextFloat();
            System.out.println("e" + userFloat + " converts to " + eAmount.convert(userFloat));
            userFloat = eAmount.convert(userFloat);
            for (int i=1; i<=numOfItems; i++) {
                if (menItem[i].getPrice()==userFloat) {
                    System.out.println("\n" + menItem[i]);
                }
            }
        }
        //Amount Conditional 3 Profit
        else if (userInt==3) {
            UI.enterFloat();
            userFloat = scanIn.nextFloat();
            System.out.println("Y" + userFloat + " converts to " + yAmount.convert(userFloat));
            userFloat = yAmount.convert(userFloat);
            for (int i=1; i<=numOfItems; i++) {
                if (menItem[i].getProfit()==userFloat) {
                    System.out.println("\n" + menItem[i]);
                }
            }
        }
    }
    
    //-----------------------InsertItem-----------------------
    // Insert an item into the menu array.
    //--------------------------------------------------------
    public void InsertItem() {
        numOfItems+=1;
        UI.currencyOptions();
        userInt = scanIn.nextInt();
        UI.formatOptions();//lists format options found in the UI class.
        userInput = scanIn.next();
        splitLine = userInput.split(",");
        
        //find boolean value from column 4
        if (splitLine[4].equals("T"))
            TorF_4 = true;
        else
            TorF_4 = false;
        //find boolean value from column 6
        if (splitLine[4].equals("T"))
            TorF_6 = true;
        else
            TorF_6 = false;
        
        //convert currency amount to unit.
        if (userInt==1) {
            unitCost = dAmount.convert(Float.parseFloat(splitLine[2]));
            unitPrice = dAmount.convert(Float.parseFloat(splitLine[3]));
        }
        else if (userInt==2) {
            unitCost = eAmount.convert(Float.parseFloat(splitLine[2]));
            unitPrice = eAmount.convert(Float.parseFloat(splitLine[3]));
        }
        else if (userInt==3) {
            unitCost = yAmount.convert(Float.parseFloat(splitLine[2]));
            unitPrice = yAmount.convert(Float.parseFloat(splitLine[3]));
        }
        
        //convert new unit to String since chooseMenType() requires string values.
        splitLine[2] = String.valueOf(unitCost);
        splitLine[3] = String.valueOf(unitPrice);
        
        //determine menItem type and add to array
        chooseMenType();
        
        if (entry) {
            System.out.println("\nMenu Item to Be Added: ");
            System.out.println(menItem[numOfItems]);
        }
        else {
            UI.invalid();
            System.out.println("\nMenu Item Failed to Be Added: ");
            numOfItems = numOfItems-1;
        }
    }
    
    public void chooseMenType() {
        if (splitLine[0].equals("APPETIZER")) {
            menItem[numOfItems] = new Appetizer(splitLine[1],Float.parseFloat(splitLine[2]),Float.parseFloat(splitLine[3]),TorF_4,Integer.parseInt(splitLine[5]),splitLine[6]);
            entry = true;
        }
        else if (splitLine[0].equals("MEAL")) {
            menItem[numOfItems] = new Meal(splitLine[1],Float.parseFloat(splitLine[2]),Float.parseFloat(splitLine[3]),TorF_4,Integer.parseInt(splitLine[5]),TorF_6);
            entry = true;
        }
        else if (splitLine[0].equals("DESSERT")) {
            menItem[numOfItems] = new Dessert(splitLine[1],Float.parseFloat(splitLine[2]),Float.parseFloat(splitLine[3]),TorF_4,Integer.parseInt(splitLine[5]),TorF_6);
            entry = true;
        }
        else if (splitLine[0].equals("MILKSHAKE")) {
            menItem[numOfItems] = new ICAdapter(splitLine);
            entry = true;
        }
        else if (splitLine[0].equals("SODA")) {
            menItem[numOfItems] = new ICAdapter(splitLine);
            entry = true;
        }
        else {
            UI.invalid();
            entry = false;
        }
    }
    
    //-----------------------DeleteItem-----------------------
    // Delete an item from the menu array.
    //--------------------------------------------------------
    public void DeleteItem () {
        DisplayMenu();
        System.out.println("\nDelete an Item(s) by:\n1 - Name\n2 - Currency");
        userInt = scanIn.nextInt();
        
        //Name or Currency Condtion 1 Name
        if (userInt==1) {
            System.out.println("\nEnter the name of the food you want deleted(all lowercase and one word).");
            userInput = scanIn.next();
            for (int i=1; i<=numOfItems; i++) {
                if (menItem[i].getName().equals(userInput)) {
                   placeMark = i;
                   found = true;
                }
            }
            if(found) {
                for (int i=placeMark; i<=numOfItems; i++) {
                   menItem[i] = menItem[i+1];
                }
            }
            if(!found) {
                UI.invalid();
            }
            found = false;
            numOfItems -= 1;
        }
        
        //Name or Currency Condtion 2 Currency
        else if (userInt==2) {
            UI.currencyOptions();
            userInt = scanIn.nextInt();
            //dollars chosen
            if (userInt==1) {
                amountDelete();
            }
            //euro chosen
            else if (userInt==2) {
                amountDelete();
            }
            //yen chosen
            else if (userInt==3) {
                amountDelete();
            }
            //invalid response
            else {
                UI.invalid();
            }
            userInt=2;//reset to original value
        }
        else {
            UI.invalid();
        }
            
    }
    
    public void amountDelete() {
        UI.unitOptions();
        userInt = scanIn.nextInt();
        //Amount Conditional 1 Cost
        if (userInt==1) {
            UI.enterFloat();
            userFloat = scanIn.nextFloat();
            System.out.println("$" + userFloat + " converts to " + dAmount.convert(userFloat));
            userFloat = dAmount.convert(userFloat);
            for (int i=1; i<=numOfItems; i++) {
                if (menItem[i].getCost()==userFloat) {
                    placeMark = i;
                    found = true;
                }
            }
            if(found) {
                for (int i=placeMark; i<=numOfItems; i++) {
                   menItem[i] = menItem[i+1];
                }
            }
            if(!found) {
                UI.invalid();
            }
            found = false;
            numOfItems -= 1;
        }
        //Amount Conditional 2 Price
        else if (userInt==2) {
            UI.enterFloat();
            userFloat = scanIn.nextFloat();
            System.out.println("e" + userFloat + " converts to " + eAmount.convert(userFloat));
            userFloat = eAmount.convert(userFloat);
            for (int i=1; i<=numOfItems; i++) {
                if (menItem[i].getPrice()==userFloat) {
                    placeMark = i;
                    found = true;
                }
            }
            if(found) {
                for (int i=placeMark; i<=numOfItems; i++) {
                   menItem[i] = menItem[i+1];
                }
            }
            if(!found) {
                UI.invalid();
            }
            found = false;
            numOfItems -= 1;
        }
        //Amount Conditional 3 Profit
        else if (userInt==3) {
            UI.enterFloat();
            userFloat = scanIn.nextFloat();
            System.out.println("Y" + userFloat + " converts to " + yAmount.convert(userFloat));
            userFloat = yAmount.convert(userFloat);
            for (int i=1; i<=numOfItems; i++) {
                if (menItem[i].getProfit()==userFloat) {
                    placeMark = i;
                    found = true;
                }
            }
            if(found) {
                for (int i=placeMark; i<=numOfItems; i++) {
                   menItem[i] = menItem[i+1];
                }
            }
            if(!found) {
                UI.invalid();
            }
            found = false;
            numOfItems -= 1;
        }
        //invalid response
        else {
           UI.invalid(); 
        }
    }
    
    //-----------------------createOrder-------------------------
    // orders are created one at a time. once a created order is
    // paid for, the state is permanently PAID until a new order 
    // is created
    //-----------------------------------------------------------
    public void createTheOrder() {
        orderNum+=1;
        orderPrice[orderNum]=0;//orderPrice initialized to 0
        state[orderNum] = NEW;//state initialized to NEW
        System.out.println("\nNew Order Form Ready For Items to Be Added\nstate = NEW");
        
    }
    
    //-----------------------AddOrder----------------------------
    // Add item to 2d order array. Keeps track of the order's price.
    // state can either be NEW if no items exist in the order
    // or INPROCESS if 1 or more items are in the order.
    //-----------------------------------------------------------
    public void AddToOrder() {
        if (state[orderNum] != PAID) {
            DisplayMenu();
            if (orderMultItems[1][orderNum]==null) {
                //in case the state is changed to TENDED beofre adding items to order
                state[orderNum] = NEW;
                System.out.println("\n-- New Order --\nstate = NEW");
            }
            System.out.println("\nEnter the name of the item you want to add");
            userInput = scanIn.next();
            for(int i=1; i<=numOfItems; i++) {
                if (state[orderNum]==NEW) {
                    orderItemNum=1;
                }
                if (menItem[i].getName().equals(userInput)) {
                    orderMultItems[orderItemNum][orderNum] = menItem[i];//item added to orderItem array
                    orderPrice[orderNum] = orderPrice[orderNum] + orderMultItems[orderItemNum][orderNum].getPrice();//keep running total for price of order
                    i = numOfItems+1;//allows exit of for loop after item is found
                    state[orderNum] = INPROCESS; //orderItems[] contains one or more items
                    System.out.println("\n-- Item Added to Order --\nstate = INPROCESS");
                    orderItemNum+=1;
                }
            }
        }
        else {
            System.out.println("\nMust create new order to add to it.");
        }
    }
    
    //-----------------------ShowOrder-----------------------
    // Display current order form.
    // state changes to TENDED, unless PAID
    //-------------------------------------------------------
    public void ShowOrder() {
        System.out.println("Current Order #" + orderNum);
        for(int i=1; i<orderItemNum; i++) {
            System.out.println("\t" + i + " - " + orderMultItems[i][orderNum]);
        }
        System.out.println("\tOrder Total: " + orderPrice[orderNum]);
        if (state[orderNum] != PAID) {
            state[orderNum] = TENDED;
            System.out.println("\n-- Order Tended --\nstate = TENDED");
        }
        else {
            System.out.println("\n-- Order Paid For--\nstate = PAID");
        }
    }
    //----------------------payForOrder----------------------
    // state changes to PAID permanently
    //-------------------------------------------------------
    public void payForOrder() {
        state[orderNum] = PAID;
        ShowOrder();
    }
    
    //----------------------displayOrders--------------------
    // diplays all PAID orders and the current order to user
    //-------------------------------------------------------
    public void displayOrders() {
        for (int i=1; i<=orderNum; i++) {
            orderItemNum=1;//reinitialize orderItemNum for printing an order
            System.out.println("\nOrder #" + i);
            while(orderMultItems[orderItemNum][i]!=null) {
                System.out.println("\t" + orderItemNum + " - " + orderMultItems[orderItemNum][i]);
                orderItemNum+=1;//orderItemNum incremented til orderMultItems == null
            }
            System.out.println("\tOrder Total: " + orderPrice[i]);
            if(state[i]==NEW) {
                System.out.println("\tstate = NEW");
            }
            else if (state[i]==INPROCESS) {
                System.out.println("\tstate = INPROCESS");
            }
            else if (state[i]==TENDED) {
                System.out.println("\tstate = TENDED");
            }
            else {
                System.out.println("\tstate = PAID");
            }
        }
    }
    
    //compare menu for sorting the menu either by cost, price, or profit
    public void compareItems() {
        while(comparing) {
            UI.Output();
            userInt = scanIn.nextInt();
            if (userInt == 1 || userInt == 2 || userInt == 3) {
                for(int i=1; i<=numOfItems; i++) {
                    if(userInt == 1) {
                        compare[i] = new CostComparator(menItem[i].getName(),menItem[i].getCost(),menItem[i].getPrice());
                    }
                    else if(userInt == 2) {
                        compare[i] = new PriceComparator(menItem[i].getName(),menItem[i].getCost(),menItem[i].getPrice());
                    }
                    else if(userInt == 3) {
                        compare[i] = new ProfitComparator(menItem[i].getName(),menItem[i].getCost(),menItem[i].getPrice());
                    }
                }
                menuToCompare.compareBy(compare, userInt);
            }
            else if (userInt == 4) {
                comparing = false;
            }
            else {
                UI.invalid();
            }  
        }
    }
}