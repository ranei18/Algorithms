import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int N = 0;

   public RandomizedQueue() {
       s = new Item[1];
    }

    public void enqueue(Item item) {
       if (N == s.length) resize(2 * s.length);
       //randomly assign new item
       int randomInt = StdRandom.uniform(N);
       s[N++] = s[randomInt]
       s[randomInt] = item;
    }
    
    public int size() {
        return N;
    }
    
    public Item sample() {
        return s[StdRandom.uniform(N)];        
    }    

    private void resize (int capacity) {
       Item[] copy = new Item[capacity];
       for (int i = 0; i < N; i++) {
           copy[i] = s[i];
       }
       s = copy;
    }

    public Item dequeue() {
       Item item = s[--N];
       s[N] = null;
        //wait until array reaches quarter fullness to avoid thrashing
       if (N > 0 && N == s.length/4) resize(s.length/2);
       return item;
    }
    
        //create iterator method for dequeue
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    //Create iterator inner class and methods
    //Need to test implementation for array
    private class ListIterator implements Iterator<Item> {
        private int loc = N;
        private Item[] list = s;
        
        public boolean hasNext() {
            return list[0] == null;
        }

        public Item next() {
            Item item = list[--loc];
            return item;           
        }
    }
    
    public static void main(String[] args) {
        RandomizedQueue test = new RandomizedQueue();
    }
}
