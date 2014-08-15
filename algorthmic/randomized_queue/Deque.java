import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
 
    private Node first;
    private Node last;
    private int n;
   
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
 
    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }
   
    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }
   
    // return the number of items on the deque
    public int size() {
        return n;
    }
   
    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException();
        if (n == 0) {
           first = new Node();
           first.item = item;
           first.next = null;
           first.prev = null;
           last = first;
        }
        else {
            Node prevFirst = first;
            first = new Node();
            first.item = item;
            first.next = prevFirst;
            first.prev = null;
            prevFirst.prev = first;
        }
        n++;  
    }
   
    // insert the item at the end
    public void addLast(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException();
        if (n == 0) {
            last = new Node();
            last.item = item;
            last.next = null;
            last.prev = null;
            first = last;
        }
        else {
            Node prevLast = last;
            last = new Node();
            last.item = item;
            last.prev = prevLast;
            prevLast.next = last;
        }
        n++;
    }
   
    // delete and return the item at the front
    public Item removeFirst() {
        if (n == 0)
            throw new java.util.NoSuchElementException();
        else {
            Item item = first.item;
            Node size = first;
            if (n != 1) {
                first = first.next;
                first.prev = null;
            }
            else {
                first = null;
                last = null;
            }
            n--;
            size = null;
            return item;
        }    
    }
   
    // delete and return the item at the end
    public Item removeLast() {
        if (n == 0)
            throw new java.util.NoSuchElementException();
        else {
            Item item = last.item;
            Node size = last;
            if (n != 1) {
                last = last.prev;
                last.next = null;
            }
            else {
                first = null;
                last = null;
            }
            n--;
            size = null;
            return item;
        }   
    }
   
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DeqIterator();
    }
   
    private class DeqIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        public Item next() {
            if (current == null)
                throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;   
        }   
    }
   
    // unit testing
    public static void main(String[] args) { }
}