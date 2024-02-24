import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int count;
    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        Node(Item item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private class itr implements Iterator<Item> {
        Node current;

        public itr() {
            current = first;
        }

        public boolean hasNext() {
            return current!=null;
        }

        public Item next() {
            if (hasNext()) {
                Node n = current;
                current = current.next;
                return n.item;
            }
            throw new java.util.NoSuchElementException();
        }

        public Item Remove() {
            throw new java.lang.UnsupportedOperationException ();
        }

    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        count = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        if (count==0)
            return true;
        else
            return false;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException ();
        Node n = new Node(item, first, null);
        if (first != null)
            first.prev = n;
        first = n;
        if (last == null)
            last = n;
        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException ();
        Node n = new Node(item, null, last);
        if (last != null)
            last.next = n;
        last = n;
        if (first == null)
            first = n;
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Node n = first;
        first = first.next;
        if (first != null)
            first.prev = null;
        count--;
        if (count==0)
            last = null;
        n.next = null;
        n.prev = null;
        return n.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Node n = last;
        last = last.prev;
        if (last != null)
            last.next = null;
        count--;
        if (count==0)
            first = null;
        n.next = null;
        n.prev = null;
        return n.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new itr();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(0);
        deque.addLast(2);
        deque.addLast(3);
        for (Integer i : deque) {
            StdOut.println(i);
        }

        int d = deque.removeLast();
        StdOut.println("removed.. " + d);

        for (Integer i : deque) {
            StdOut.println(i);
        }
    }
}