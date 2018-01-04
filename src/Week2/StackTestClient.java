package Week2;

public class StackTestClient {
    StackOfStrings stack = new StackOfString();
    while (!StdIn.isEmpty()) {
        String s = StdIn.readString();
        if (s.equals("-")) StdOut.print(stack.pop());
        else stack.push(s);
    }
}
