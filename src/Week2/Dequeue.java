package Week2;

public class Dequeue<Item> implements Iterable<Item> {

    private Node first, last;
    private int size = 0;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return size;
    }

    public void addLast(String item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        size++;
        //handles empty queue
        if(isEmpty()) first = last;
        else oldLast.next = last;
    }
    
    public void addFirst(String item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        size++;
        //handles empty queue //need to test
        if(isEmpty()) first = last;
        else oldFirst.next = last;
    }

    public String removeFirst() {
        String item = first.item;
        first = first.next;
        size--;
        //handles empty queue
        if(isEmpty()) last = null;
        return item;
    }
    
    public String removeLast() {
        String item = last.item;
        last = last.next;
        size--;
        if(isEmpty()) first = null; //is this correct?
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
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    public static void main(String[] args) {
    //unit testing        
    }
    
}
