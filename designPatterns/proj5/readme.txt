Description of the Program:
The Program UI5 allows users to create orders by implementing the use of states into the previous build of the program. The orders are numbered sequentially starting with #1. An order has the following states:

final static int NEW - Order contains no items.
final static int INPROCESS - Order has one or more items.
final static int TENDED - Customer has been given the Order displayed.
final static int PAID - Customer has paid for the Order, state cannot change.

The following actions affect the state of an order:
Create Order: State = New
Add item to Current Order: State = InProcess
Display Order For Tending: State = Tended
Customer pays for Order: State = Paid (permanent)


Running the program:
- The program can be run by executing the jar file which will prompt the user to choose a txt file to load or exit. 
- The MAIN menu with 10 options appears after a txt file is loaded.
- Options 5 - 10 deal with creating and adding to orders, relevant to Assignment 5.
- Orders can be created, added to, displayed as one, or all orders displayed sequentially.
- The total for each order is also printed.
- The orders and any changes made to them will also print the current state.
- Enter 10 to EXIT.