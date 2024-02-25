import java.util.Scanner;

public class HumanPlayer extends Player {
    private Scanner sc;
    HumanPlayer(int id, String name, char symbol) {
        super(id, name, symbol);
        sc = new Scanner(System.in);
    }


    @Override
    public void makeMove(Game game) {
        int dimension = game.getDimension();
        boolean input = true;
        System.out.println("Input position for player " + getName());
        int x = sc.nextInt();
        int y = sc.nextInt();
        while(game.isFilled(x, y)) {
            System.out.println("Position filled. Try again");
            x = sc.nextInt();
            y = sc.nextInt();
        }
        System.out.println("Position entered " + x + " " + y);
        game.makeMove(x, y, this);
    }

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.HUMAN;
    }
}
