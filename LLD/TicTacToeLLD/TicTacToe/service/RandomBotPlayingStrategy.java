import java.util.Random;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{
    private Random rd;
    RandomBotPlayingStrategy() {
        rd = new Random();
    }

    @Override
    public Move makeMove(Game game, char symbol) {
        int dimension = game.getDimension();
        int x = Math.abs(rd.nextInt()) % dimension;
        int y = Math.abs(rd.nextInt()) % dimension;
        while(game.isFilled(x, y)) {
            x = Math.abs(rd.nextInt()) % dimension;
            y = Math.abs(rd.nextInt()) % dimension;
        }
        return new Move(x, y, symbol);
    }
}
