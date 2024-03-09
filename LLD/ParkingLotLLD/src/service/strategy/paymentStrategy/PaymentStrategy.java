package service.strategy.paymentStrategy;
import models.Bill;

public interface PaymentStrategy {
    Bill payBill(Bill bill);
}
