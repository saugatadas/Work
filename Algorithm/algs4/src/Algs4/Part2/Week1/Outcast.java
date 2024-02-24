
public class Outcast {
    private WordNet wordnet;

    public Outcast(WordNet wordnet) {
        // constructor takes a WordNet object
        this.wordnet = wordnet;
    }

    public String outcast(String[] nouns)  {
        // given an array of WordNet nouns, return an outcast
        int maxDistance = 0;
        String res = null;

        for (String p : nouns) {
            int distance = 0;

            for (String q : nouns) {
                distance += wordnet.distance(p, q);
            }

            if (distance > maxDistance) {
                maxDistance = distance;
                res = p;
            }
        }
        return res;
    }

    public static void main(String[] args)  {
        // see test client below
    }
}
