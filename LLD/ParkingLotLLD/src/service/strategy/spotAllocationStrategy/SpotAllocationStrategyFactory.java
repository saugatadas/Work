package service.strategy.spotAllocationStrategy;

public class SpotAllocationStrategyFactory {
    public static SpotAllocationStrategy getSpotAllocationStrategy() {
        return new LinearSpotAllocationStrategy();
    }
}
