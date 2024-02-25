public class WinningStrategyFactory {
    public WinningStrategy getWinningStrategy(WinningStrategyName strategy, int dimension) {
        if (strategy==WinningStrategyName.ORDERONESTRATEGY) {
            return new orderOneWinningStrategy(dimension);
        }
        else {
            return null;
        }
    }
}
