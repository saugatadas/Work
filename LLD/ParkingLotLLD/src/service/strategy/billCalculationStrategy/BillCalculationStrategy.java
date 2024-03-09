package service.strategy.billCalculationStrategy;
import models.Ticket;

public interface BillCalculationStrategy {
    double getAmount(Ticket ticket);
}
