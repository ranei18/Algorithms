package Week2;

public class LinkedQueueOfStrings {

    private Node first, last;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(String item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        //handles empty queue
        if(isEmpty()) first = last;
        else oldLast.next = last;
    }

    public String dequeue() {
        String item = first.item;
        first = first.next;
        //handles empty queue
        if(isEmpty()) last = null;
        return item;
    }
}
