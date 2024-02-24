import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;



public class MoveToFront {
    private static final int R = 256;

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        char[] code = new char[R];
        for (int i=0; i<R; i++) {
            code[i] = (char) i;
        }

        String input = BinaryStdIn.readString();
        for (int i=0; i<input.length(); i++) {
            char c = input.charAt(i);
            char t = code[0];

            for (int j=0; j<R; j++) {
                if (code[j] == c) {
                    code[0] = code[j];
                    code[j] = t;
                    BinaryStdOut.write((char) j);
                    break;
                }

                char m = code[j];
                code[j] = t;
                t = m;
            }
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        char[] code = new char[R];

        for (int i=0; i<R; i++)
            code[i] = (char) i;

        while (!BinaryStdIn.isEmpty()) {
            int enc = BinaryStdIn.readInt(8);
            BinaryStdOut.write(code[enc]);

            char c = code[enc];
            for (int j=enc; j>0; j--)
                code[j] = code[j-1];
            code[0] = c;
        }

        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) MoveToFront.encode();
        if (args[0].equals("+")) MoveToFront.decode();
    }

}