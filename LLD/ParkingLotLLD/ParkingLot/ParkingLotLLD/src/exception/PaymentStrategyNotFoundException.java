package exception;

public class PaymentStrategyNotFoundException extends RuntimeException {
    public PaymentStrategyNotFoundException(String message) {
        super(message);
    }
}