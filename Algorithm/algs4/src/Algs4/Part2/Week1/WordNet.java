import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Digraph;


public class WordNet {
    private Map<Integer, String> idToMap;
    private Map<String, Set<Integer>> nounToId;
    private Digraph G;
    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        int count = 0;
        idToMap = new HashMap<>();
        nounToId = new HashMap<>();

        In input = new In(synsets);
        while(input.hasNextLine()) {
            String line = input.readLine();
            String[] s = line.split(",");
            int id = Integer.valueOf(s[0]);

            idToMap.put(id, s[1]);
            String[] nouns = s[1].split(" ");

            for (String noun: nouns) {
                if (nounToId.containsKey(noun)) {
                    nounToId.get(noun).add(id);
                }
                else {
                    Set<Integer> p = new HashSet<Integer>();
                    p.add(id);
                    nounToId.put(noun, p);
                }
            }

            count++;
        }

        G = new Digraph(count);

        input = new In(hypernyms);
        while(input.hasNextLine()) {
            String line = input.readLine();
            String[] s = line.split(",");
            int id = Integer.valueOf(s[0]);
            int index = 1;

            while(index<s.length) {
                int h = Integer.valueOf(s[index]);
                G.addEdge(id, h);
                index++;
            }
        }

        sap = new SAP(G);

    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounToId.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word==null)
            throw new NullPointerException();

        return nounToId.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (isNoun(nounA) && isNoun(nounB)) {
            Set<Integer> p = nounToId.get(nounA);
            Set<Integer> q = nounToId.get(nounB);

            return sap.length(p, q);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (isNoun(nounA) && isNoun(nounB)) {
            Set<Integer> p = nounToId.get(nounA);
            Set<Integer> q = nounToId.get(nounB);
            int a = sap.ancestor(p, q);
            return idToMap.get(a);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    // do unit testing of this class
    public static void main(String[] args) {
    }

}
