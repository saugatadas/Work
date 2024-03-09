package controller;

import models.Bill;
import service.BillService;

public class BillController {
    private BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    public Bill generateBill(int ticketId, int gateId) {
        return billService.generateBill(ticketId, gateId);
    }
}
