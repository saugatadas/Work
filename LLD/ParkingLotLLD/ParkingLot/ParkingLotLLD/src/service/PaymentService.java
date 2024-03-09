package service;

import models.Bill;
import models.ExitGate;
import models.Ticket;
import models.enums.PaymentStatus;
import models.enums.PaymentType;
import repository.BillRepository;
import service.strategy.paymentStrategy.PaymentStrategy;
import service.strategy.paymentStrategy.PaymentStrategyFactory;

public class PaymentService {
    BillRepository repo;

    public PaymentService(BillRepository repo) {
        this.repo = repo;
    }

    public PaymentStatus payBill(Bill bill, PaymentType paymentType) {
        PaymentStrategy strategy = PaymentStrategyFactory.getPaymentStrategy(paymentType);
        bill = strategy.payBill(bill);
        repo.put(bill);
        return PaymentStatus.PAYMENT_SUCCESS;
    }
}
