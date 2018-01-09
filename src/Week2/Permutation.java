package Week2;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int count = Integer.parseInt(args[0]);
        RandomizedQueue queue = new RandomizedQueue();

        while(!StdIn.isEmpty()) {
            String line = StdIn.readString();
            queue.enqueue(line);
        }
        for (int i = 0; i < count; i++) {
            System.out.println(queue.dequeue());
        }

    }
}
