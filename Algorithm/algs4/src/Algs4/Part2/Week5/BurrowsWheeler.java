import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;


import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BurrowsWheeler {

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String source = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(source);

        for (int i=0; i<csa.length(); i++) {
            if (csa.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }

        for (int i=0; i<csa.length(); i++) {
            int index = csa.index(i);

            index = (index + csa.length() - 1) % csa.length();
            BinaryStdOut.write(source.charAt(index));
        }

        BinaryStdOut.flush();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int start = BinaryStdIn.readInt();
        String source = BinaryStdIn.readString();
        char[] enc = source.toCharArray();

        char[] sorted = new char[enc.length];

        for (int i=0; i<enc.length; i++)
            sorted[i] = enc[i];

        Arrays.sort(sorted);

        int[] next = new int[enc.length];
        HashMap<Character, Queue<Integer>> m = new HashMap<>();

        for (int i=0; i<enc.length; i++) {
            char c = enc[i];
            if (m.containsKey(c)) {
                m.get(c).add(i);
            }
            else {
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                m.put(c, q);
            }
        }

        for (int i=0; i<sorted.length; i++) {
            int n = m.get(sorted[i]).remove();
            next[i] = n;
        }

        int current = start;
        for (int i=0; i<enc.length; i++) {
            BinaryStdOut.write(sorted[current]);
            current = next[current];
        }

        BinaryStdOut.flush();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        String p = args[0];

        if (p.equals("-")) {
            transform();
        }
        else if (p.equals("+")) {
            inverseTransform();
        }
    }
}
