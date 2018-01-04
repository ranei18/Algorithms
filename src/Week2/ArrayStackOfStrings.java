package Week2;

public class ArrayStackOfStrings {
    private String[] s;
    private int N = 0;

    public ArrayStackOfStrings(int capacity ) {
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        s[N++] = item;
    }

    public String pop() {
        //this attempt holds references to objects when it is no longer needed.
        //this is called loitering
        /*
        return s[--N];
        */
        //the following code removes old references to avoid loitering
        String item = s[--N];
        s[N] = null;
        return item;

    }
}
