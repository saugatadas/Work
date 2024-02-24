import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
        while(!StdIn.isEmpty()) {
            randomQueue.enqueue(StdIn.readString());
        }

        int k = Integer.parseInt(args[0]);
        for (String s : randomQueue) {
            StdOut.println(s);
            if (k==0) break;
        }
    }
}