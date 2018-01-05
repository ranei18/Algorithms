package Week2;

public class ResizingArrayStackOfStrings {
    private String[] s;
    private int N = 0;

   public ResizingArrayStackOfStrings() {
       s = new String[1];
    }

    public void push(String item) {
       if (N == s.length) resize(2 * s.length);
       s[N++] = item;
    }

    private void resize (int capacity) {
       String[] copy = new String[capacity];
       for (int i = 0; i < N; i++) {
           copy[i] = s[i];
       }
       s = copy;
    }

    public String pop() {
       String item = s[--N];
       s[N] = null;
        //wait until array reaches quarter fullness to avoid thrashing
        //occurs when sequential push pop operations occur around half full
        //causing the array to consistently resize
       if (N > 0 && N == s.length/4) resize(s.length/2);
       return item;
    }
}
