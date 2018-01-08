import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue {
    private String[] s;
    private int N = 0;

   public RandomizedQueue() {
       s = new String[1];
    }

    public void enqueue(String item) {
       if (N == s.length) resize(2 * s.length);
       //randomly assign new item
       int randomInt = StdRandom.uniform(N);
       s[N++] = s[randomInt]
       s[randomInt] = item;
    }
    
    public int size() {
        return N;
    }
    
    public String sample() {
        return s[StdRandom.uniform(N)];        
    }    

    private void resize (int capacity) {
       String[] copy = new String[capacity];
       for (int i = 0; i < N; i++) {
           copy[i] = s[i];
       }
       s = copy;
    }

    public String dequeue() {
       String item = s[--N];
       s[N] = null;
        //wait until array reaches quarter fullness to avoid thrashing
       if (N > 0 && N == s.length/4) resize(s.length/2);
       return item;
    }
}
