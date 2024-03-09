package service;

import models.*;
import models.enums.BillStatus;
import models.enums.SpotStatus;
import repository.BillRepository;
import repository.GateRepository;
import repository.TicketRepository;
import service.strategy.billCalculationStrategy.BillCalculationStrategy;
import service.strategy.billCalculationStrategy.BillCalculationStrategyFactory;

public class BillService {
    private int billNumber;
    private BillRepository billRepo;
    private TicketRepository ticketRepo;
    private GateRepository gateRepo;

    public BillService(BillRepository billRepo, TicketRepository ticketRepo, GateRepository gateRepo) {
        this.billNumber = 0;
        this.billRepo = billRepo;
        this.ticketRepo = ticketRepo;
        this.gateRepo = gateRepo;
    }

    public Bill generateBill(int ticketId, int gateId) {
        Ticket ticket = ticketRepo.get(ticketId);
        Gate gate = gateRepo.get(gateId);
        BillCalculationStrategy billCalculationStrategy = BillCalculationStrategyFactory.getStrategy();
        double amount =  billCalculationStrategy.getAmount(ticket);
        billNumber++;
        Bill bill = new Bill(billNumber, amount, BillStatus.BILL_UNPAID, ticket);
        billRepo.put(bill);
        ParkingSpot spot = ticket.getSpot();
        spot.setStatus(SpotStatus.FREE);
        return bill;
    }
}
