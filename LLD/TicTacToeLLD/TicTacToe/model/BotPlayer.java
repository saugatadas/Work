import java.util.Random;

public class BotPlayer extends Player {
    private int botDifficultyLevel;
    private BotPlayingStrategy botStrategy;


    BotPlayer(int id, String name, char symbol, int botDifficultyLevel, BotPlayingStrategyName strategyName) {
        super(id, name, symbol);
        this.botDifficultyLevel = botDifficultyLevel;
        BotPlayingStrategyFactory factory = new BotPlayingStrategyFactory();
        botStrategy = factory.getBotPlayingStrategy(strategyName);
    }

    public void setBotStrategy(BotPlayingStrategy botStrategy) {
        this.botStrategy = botStrategy;
    }

    @Override
    public void makeMove(Game game) {
        Move move = botStrategy.makeMove(game, getSymbol());
        int x = move.getRow();
        int y = move.getCol();
        System.out.println("Bot entered " + x + " " + y);
        game.makeMove(x, y, this);
    }

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.BOT;
    }
}
