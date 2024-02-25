public class BotPlayingStrategyFactory {
    public BotPlayingStrategy getBotPlayingStrategy(BotPlayingStrategyName strategyName) {
        if (strategyName==BotPlayingStrategyName.RANDOM) {
            return new RandomBotPlayingStrategy();
        }
        else {
            return null;
        }
    }
}
