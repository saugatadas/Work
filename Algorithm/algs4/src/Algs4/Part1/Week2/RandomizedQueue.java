import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int size;
    private int offset;

    private class itr implements Iterator<Item> {
        private int current;
        private int[] order;

        public itr() {
            current = 0;
            order = new int[offset];
            for (int i=0; i<offset; i++)
                order[i] = i;
            StdRandom.shuffle(order);
        }

        public boolean hasNext() {
            return current<offset;
        }

        public Item next() {
            if (hasNext()) {
                return queue[order[current++]];
            }
            throw new java.util.NoSuchElementException();
        }

        public Item Remove() {
            throw new java.lang.UnsupportedOperationException ();
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        offset = 0;
        queue = null;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size==0?true:false;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {

        if (offset == size) {
            int l = (size == 0) ? 8 : size*2;
            Item[] m = (Item[]) new Object[l];
            for (int i=0; i<size; i++)
                m[i] = queue[i];
            queue = m;
            size = l;
        }

        queue[offset] = item;
        offset++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (offset==0) throw new java.util.NoSuchElementException();
        int r = StdRandom.uniformInt(offset);
        Item res = queue[r];
        queue[r] = queue[offset-1];
        offset--;
        if ((2*offset) < size) {
            int l = size / 2;
            Item[] m = (Item[]) new Object[l];
            for (int i=0; i<offset; i++)
                m[i] = queue[i];
            queue = m;
            size = l;
        }
        return res;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (offset==0) throw new java.util.NoSuchElementException();
        int r = StdRandom.uniformInt(offset);
        return queue[r];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new itr();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomQueue = new RandomizedQueue<Integer>();
        randomQueue.enqueue(1);
        randomQueue.enqueue(0);
        randomQueue.enqueue(2);
        randomQueue.enqueue(3);
        for (Integer i : randomQueue) {
            StdOut.println(i);
        }
        StdOut.println("dequed: " + randomQueue.dequeue());
        for (Integer i : randomQueue) {
            StdOut.println(i);
        }
    }
}