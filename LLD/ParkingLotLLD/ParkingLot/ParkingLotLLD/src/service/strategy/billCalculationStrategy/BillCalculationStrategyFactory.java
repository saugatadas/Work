package service.strategy.billCalculationStrategy;

public class BillCalculationStrategyFactory {
    public static BillCalculationStrategy getStrategy() {
        return new SimpleBillCalculationStrategy();
    }
}