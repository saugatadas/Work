package service.strategy.paymentStrategy;
import exception.PaymentStrategyNotFoundException;
import models.enums.PaymentType;

public class PaymentStrategyFactory {
    public static PaymentStrategy getPaymentStrategy(PaymentType type) {
        return switch(type) {
                case UPI ->  new UPI();
                case CARD -> new Card();
                case CASH -> new Cash();
                default -> throw new PaymentStrategyNotFoundException("");
        };
    }
}