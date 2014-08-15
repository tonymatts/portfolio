/*====================Comparator.java====================
 * @author Tony Matts, CSCI 426
 * created 11/09/13
 * last modified 11/13/13
 *-----------------------------------------------------
 * 
 */

public abstract class Comparator {
    
    private String nameC;
    private float costC;
    private float priceC;
    private CostComparator costCompare;
    private PriceComparator priceCompare;
    private ProfitComparator profitCompare;
    
    public Comparator (String nameC, float costC, float priceC) {
        this.nameC = nameC;
        this.costC = costC;
        this.priceC = priceC;
    }
    
    //heapsort method for either sorting by cost, price, or profit
    public void compareBy (Comparator[] menu, int userPick) {
        //Insertion onto heap
        for (int heapsize=0; heapsize<menu.length; heapsize++) {
            int n = heapsize; // the index of the inserted int
            while (n > 0) { // until the root of the heap is reached
                int p = (n-1)/2; // the index of the parent of n
                if (userPick==1 && menu[n].getCost() > menu[p].getCost()) { // child is larger than parent
                    costCompare.arraySwap(menu,n,p); // swap child with parent
                    n = p; // check parent
                }
                else if (userPick==2 && menu[n].getPrice() > menu[p].getPrice()) {
                   priceCompare.arraySwap(menu, n, p); // swap child with parent
                    n = p; // check parent 
                }
                else if (userPick==3 && menu[n].getProfit() > menu[p].getProfit()) {
                   profitCompare.arraySwap(menu, n, p); // swap child with parent
                    n = p; // check parent 
                }
                else // parent is larger than child
                    break; // all is good in the heap
            }
        }
        //Removal from heap
        for (int heapsize=menu.length; heapsize>0;) {
            // swap root with the last heap element
            if (userPick==1) {
                costCompare.arraySwap(menu, 0, --heapsize);
            }
            else if (userPick==2) {
                priceCompare.arraySwap(menu, 0, --heapsize);
            }
            else if (userPick==3) {
                profitCompare.arraySwap(menu, 0, --heapsize);
            }
            int n = 0; // index of the element being moved down the tree
            while (true) {
                int left = (n*2)+1;
                if (left >= heapsize) // node has no left child
                    break; // reached the bottom; heap is heapified
                int right = left+1;
                if (right >= heapsize) { // node has a left child, but no right child
                    if (userPick==1 && menu[left].getCost() > menu[n].getCost()) // if left child is greater than node
                        costCompare.arraySwap(menu, left, n); // swap left child with node
                    else if(userPick==2 && menu[left].getPrice() > menu[n].getPrice()) {
                        priceCompare.arraySwap(menu, left, n);
                    }
                    else if(userPick==3 && menu[left].getProfit() > menu[n].getProfit()) {
                        profitCompare.arraySwap(menu, left, n);
                    }
                    break; // heap is heapified
                }
                if (userPick==1 && menu[left].getCost() > menu[n].getCost()) { // (left > n)
                    if (menu[left].getCost() > menu[right].getCost()) { // (left > right) & (left > n)
                        costCompare.arraySwap(menu, left, n);
                        n = left; continue; // continue recursion on left child
                    } 
                    else { // (right > left > n)
                        costCompare.arraySwap(menu, right, n);
                        n = right; continue; // continue recursion on right child
                    }
                }
                else if (userPick==2 && menu[left].getPrice() > menu[n].getPrice()) { // (left > n)
                    if (menu[left].getCost() > menu[right].getCost()) { // (left > right) & (left > n)
                        priceCompare.arraySwap(menu, left, n);
                        n = left; continue; // continue recursion on left child
                    } 
                    else { // (right > left > n)
                        priceCompare.arraySwap(menu, right, n);
                        n = right; continue; // continue recursion on right child
                    }
                }
                else if (userPick==3 && menu[left].getProfit() > menu[n].getProfit()) { // (left > n)
                    if (menu[left].getCost() > menu[right].getCost()) { // (left > right) & (left > n)
                        profitCompare.arraySwap(menu, left, n);
                        n = left; continue; // continue recursion on left child
                    } 
                    else { // (right > left > n)
                        profitCompare.arraySwap(menu, right, n);
                        n = right; continue; // continue recursion on right child
                    }
                }
                else { // (n > left)
                    if (userPick==1 && menu[right].getCost() > menu[n].getCost()) { // (right > n > left)
                        costCompare.arraySwap(menu, right, n);
                        n = right; continue; // continue recursion on right child
                    }
                    else if (userPick==2 && menu[right].getPrice() > menu[n].getPrice()) { // (right > n > left)
                        priceCompare.arraySwap(menu, right, n);
                        n = right; continue; // continue recursion on right child
                    }
                    else if (userPick==3 && menu[right].getProfit() > menu[n].getProfit()) { // (right > n > left)
                        profitCompare.arraySwap(menu, right, n);
                        n = right; continue; // continue recursion on right child
                    }
                    else { // (n > left) & (n > right)
                        break; // node is greater than both children, so it's heapified
                    }
                }
            }
        }
        System.out.println(menu);
    }
    
    //getters
    public String getName() {
        return nameC;
    }
    public float getCost() {
        return costC;
    }
    public float getPrice() {
        return priceC;
    }
    public float getProfit() {
        return priceC-costC;
    }
    
    //setters
    public void setName(String nameC) {
        this.nameC = nameC;
    }
    public void setCost(float costC) {
        this.costC = costC;
    }
    public void setPrice(float priceC) {
        this.priceC = priceC;
    }
    
    //out to string
    public String toString () {
        String menLine = nameC;
        menLine += " ";
        menLine += costC;
        menLine += " ";
        menLine += priceC;
        return menLine;
    }
}
