import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args)
    {
        String c1 = StdIn.readString();
        int i=0;

        while (!StdIn.isEmpty())
        {
            double result;
            String c2 = StdIn.readString();
            i++;
            if (StdRandom.bernoulli(1/i)) {
                c1 = c2;
            }
        }

        System.out.println(c1);
    }
}
