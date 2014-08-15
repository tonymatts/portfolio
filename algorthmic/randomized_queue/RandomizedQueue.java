import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
 private Node first;
 private Node last;
 private int count;
 
 private class Node {
  private Item item;
  private Node next;
  private Node prev;     
 }
 
    // construct an empty randomized queue
    public RandomizedQueue() {
        first = null;
        last = null;
        count = 0;
    }
   
    // is the queue empty?
    public boolean isEmpty() {
        return count == 0;
    }
   
    // return the number of items on the queue
    public int size() {
     return count;
    }
   
    // add the item
    public void enqueue(Item item) {
     if (item == null)
      throw new java.lang.NullPointerException();
     if (count == 0) {
      first = new Node();
      first.item = item;
      first.next = null;
      first.prev = null;
      last = first;
     }
     else {
      Node prevLast = last;
      last = new Node();
      last.item = item;
      last.next = null;
      last.prev = prevLast;
      prevLast.next = last;
     }
     count++;
    }
   
    // delete and return a random item
    public Item dequeue() {
     if (count == 0)
      throw new java.util.NoSuchElementException();
     int n = StdRandom.uniform(count);
     Node temp = first;
     for (int i = 1; i <= n; i++)
      temp = temp.next;
     Item item = temp.item;
     if (n == 0) {
      first = temp.next;
      temp.prev = null;
     }
     if (n == (count - 1)) {
      last = temp.prev;
      temp.next = null;
     }
     if (temp.prev != null)
      temp.prev.next = temp.next;
     if (temp.next != null)
      temp.next.prev = temp.prev;
     count--;
     return item;  
    }
   
    // return (but do not delete) a random item
    public Item sample() {
     if (count == 0)
      throw new java.util.NoSuchElementException();
     int n = StdRandom.uniform(count);
     Node temp = first;
     for (int i = 1; i <= n; i++)
      temp = temp.next;
     Item item = temp.item;
     return item;  
    }
   
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
     return new RandQuIterator();
    }
    
    private class RandQuIterator implements Iterator<Item> {
     private int c = count;
     public boolean hasNext() {
      return c != 0;
     }
     public void remove() {
      throw new java.lang.UnsupportedOperationException();
     }
     public Item next() {
      if (c == 0)
       throw new java.util.NoSuchElementException();
      int n = StdRandom.uniform(c);
      Node temp = first;
      for (int i = 1; i <= n; i++)
       temp = temp.next;
      Item item = temp.item;
      if (n != (count-1)) {
       if (n == 0) {
        first = first.next;
        first.prev = null;
       }
       else {
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
       }
       temp.prev = last;
       temp.next = null;
       last.next = temp;
       last = temp;
      }
      c--;
      return item;
     }
    }
   
    // unit testing
    public static void main(String[] args) { }
}