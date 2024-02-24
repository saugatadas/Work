import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TrieST;

import java.util.HashSet;

public class BoggleSolver {

    private static final int R = 26;

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    private static int[][] neighbors = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };

    private class TrieNode {
        private boolean valid;
        private TrieNode[] next;

        TrieNode() {
            next = new TrieNode[R];
            valid = false;
        }
    }

    private TrieNode root;

    private TrieNode addTrie(TrieNode node, String word, int index) {
        if (node == null) {
            node = new TrieNode();
        }

        if (index == word.length())
            node.valid = true;
        else {
            char c = word.charAt(index);
            int index2 = c - 'A';
            node.next[index2] = addTrie(node.next[index2], word, index+1);
        }
        return node;
    }


    private boolean contains(TrieNode node, String word, int index) {
        if (node == null)
            return false;

        if (index == word.length())
            return node.valid;

        char c = word.charAt(index);
        int index2 = c - 'A';
        return contains(node.next[index2], word, index+1);
    }

    public BoggleSolver(String[] dictionary) {
        if (dictionary == null)
            throw new IllegalArgumentException();

        for (String p : dictionary) {
            root = addTrie(root, p, 0);
        }
    }

    private void getheWords(TrieNode node, BoggleBoard board, boolean[][] visited, StringBuilder sb, int i, int j, HashSet<String> words) {
        TrieNode next = null;

        char c = board.getLetter(i, j);
        int d = c - 'A';
        next = node.next[d];
        if (next == null)
            return;

        if (c == 'Q') {
            int d1 = 'U' - 'A';
            next = next.next[d1];
            if (next == null)
                return;
        }

        visited[i][j] = true;

        sb.append(c);
        if (c=='Q') {
            sb.append('U');
        }

        if (sb.toString().length()>=3 && next.valid)
            words.add(sb.toString());

        for (int[] n : neighbors) {
           int i1 = i + n[0];
           int j1 = j + n[1];

            if (i1<0 || j1<0 || i1>= board.rows() || j1 >= board.rows())
                continue;

           if (!visited[i1][j1])
               getheWords(next, board, visited, new StringBuilder(sb), i1, j1, words);
        }

        visited[i][j] = false;
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        if (board == null)
            throw new IllegalArgumentException();

        HashSet<String> words = new HashSet<String>();

        for (int i=0; i<board.rows(); i++) {
            for (int j=0; j< board.cols(); j++) {
                boolean[][] visited = new boolean[board.rows()][board.cols()];
                StringBuilder sb = new StringBuilder();
                getheWords(root, board, visited, sb, i, j, words);
            }
        }

        return words;
    }


    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (word == null)
            throw new IllegalArgumentException();

        if (!contains(root, word, 0))
            return 0;

        int len = word.length();

        if (len<3)
            return 0;
        if (len<= 7)
            return len-2;
        return 11;
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }
}
