import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int N = 0;

   public RandomizedQueue() {
       s = (Item[]) new Object[1];
    }

    public void enqueue(Item item) {
	   int randomInt;
       if (item == null) throw new IllegalArgumentException();
       if (N == s.length) resize(2 * s.length);
       //randomly assign new item
       if (N > 0) {
           randomInt = StdRandom.uniform(N);
       } else {
           randomInt = 0;
       }
       s[N++] = s[randomInt];
       s[randomInt] = item;
    }
    
    public int size() {
        return N;
    }

    public boolean isEmpty() {
       return N == 0;
    }
    
    public Item sample() {
       //test if this needs to be equal to 0 or less than
       if (N == 0) throw new NoSuchElementException();
       return s[StdRandom.uniform(N)];
    }    

    private void resize (int capacity) {
       Item[] copy = (Item[]) new Object[capacity];
       for (int i = 0; i < N; i++) {
           copy[i] = s[i];
       }
       s = copy;
    }
    
    //might be more random to reorder during dequeue
    public Item dequeue() {
       if (N == 0) throw new NoSuchElementException();
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
            return list[0] != null;
        }

        public Item next() {
            if (loc == 0) throw new NoSuchElementException();
            int randomInt = StdRandom.uniform(loc);
            Item temp = list[randomInt];
            list[randomInt] = list[--loc];
            list[loc] = null;
            return temp;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    public static void main(String[] args) {
        RandomizedQueue test = new RandomizedQueue();
    }
	
}
