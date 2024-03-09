package controller;

import models.Bill;
import models.enums.PaymentStatus;
import models.enums.PaymentType;
import service.BillService;
import service.PaymentService;

public class PaymentController {
    private PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public PaymentStatus payBill(Bill bill, PaymentType paymentType) {
        return paymentService.payBill(bill, paymentType);
    }
}
