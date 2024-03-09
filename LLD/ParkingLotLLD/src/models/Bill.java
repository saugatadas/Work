package models;

import models.enums.BillStatus;

public class Bill {
    private int id;
    private double amount;
    private BillStatus status;
    private Ticket ticket;

    public Bill(int id, double amount, BillStatus status, Ticket ticket) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public BillStatus getStatus() {
        return status;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }
}
