package service.strategy.paymentStrategy;
import models.Bill;
import models.enums.BillStatus;

public class Cash implements PaymentStrategy {
    @Override
    public Bill payBill(Bill bill) {
        System.out.println("Paying " + bill.getAmount() + " by cash");
        bill.setStatus(BillStatus.BILL_PAID);
        return bill;
    }
}
