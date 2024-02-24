import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class CircularSuffixArray {
    // circular suffix array of s
    private char[] arr;

    private class myclass implements Comparable<myclass> {
        int p;
        char[] a;
        int l;

        myclass(int p, char[] a) {
            this.p = p;
            this.a = a;
            this.l = a.length;
        }

        private void reverse(char[] s, int start, int end) {
            int i = start, j = end;

            while (i<j) {
                char t = s[j];
                s[j] = s[i];
                s[i] = t;
                i++;
                j--;
            }
        }

        private char[] rotate(int n) {
            char[] p1 = new char[a.length];

            for (int i=0; i<a.length; i++)
                p1[i] = a[i];

            reverse(p1, 0, p1.length-1);

            int m = p1.length - n;
            reverse(p1, 0, m - 1);
            reverse(p1, m, p1.length-1);
            return p1;
        }

        public int compareTo(myclass that) {
            char[] q1 = rotate(this.p);
            char[] q2 = rotate(that.p);

            for (int i=0; i<l; i++) {
                if (q1[i] < q2[i]) return -1;
                if (q1[i] > q2[i]) return 1;
            }
            return 0;
        }
    }

    private myclass[] suffixlist;

    public CircularSuffixArray(String s) {
        if (s==null)
            throw new NullPointerException();

        arr = s.toCharArray();
        suffixlist = new myclass[arr.length];
        for (int i=0; i<arr.length; i++) {
            suffixlist[i] = new myclass(i, arr);
        }

        Arrays.sort(suffixlist);
    }

    // length of s
    public int length() {
        return arr.length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        return suffixlist[i].p;
    }

    // unit testing (required)
    public static void main(String[] args) {
        //CircularSuffixArray csa = new CircularSuffixArray("ABRACADABRA!");
        //for (int i=0; i<csa.length(); i++)
        //    StdOut.println(csa.index(i));
    }
}
