import java.util.Iterator;
import java.util.NoSuchElementException;

public class Dequeue<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }

    public void addLast(Item item) {
        if(item == null) throw new IllegalArgumentException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        
        //handles empty queue
        if(isEmpty()) first = last;
        else oldLast.next = last;
        size++;
    }
    
    public void addFirst(Item item) {
        if(item == null) throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        
        //handles empty queue 
        if(isEmpty()) last = first;
        size++;
    }

    public Item removeFirst() {
        if (first == null) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        size--;
        //handles empty queue
        if(isEmpty()) last = null;
        return item;
    }
    
    public Item removeLast() {
        if (last == null) throw new NoSuchElementException();
        Item item = last.item;
        last = last.next;
        size--;
        //handles empty queue
        if(isEmpty()) first = null; 
        return item;
    }
    
    //create iterator method for dequeue
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    //Create iterator inner class and methods
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    public static void main(String[] args) {
        Dequeue test = new Dequeue();
        test.addFirst("a");
        test.removeLast();
   
    }
    
}
