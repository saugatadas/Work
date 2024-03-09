package service.strategy.paymentStrategy;

import models.Bill;
import models.enums.BillStatus;

public class Card implements PaymentStrategy {
    @Override
    public Bill payBill(Bill bill) {
        System.out.println("Paying " + bill.getAmount() + " by card");
        bill.setStatus(BillStatus.BILL_PAID);
        return bill;
    }
}
