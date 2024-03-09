package service.strategy.billCalculationStrategy;

import exception.VehicleTypeNotFoundException;
import models.Bill;
import models.Ticket;
import models.enums.BillStatus;
import models.enums.VehicleType;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SimpleBillCalculationStrategy implements BillCalculationStrategy{
    @Override
    public double getAmount (Ticket ticket) {
        LocalDateTime entryTime = ticket.getTime();
        LocalDateTime currentTime = LocalDateTime.now();
        double seconds = ChronoUnit.SECONDS.between(currentTime, entryTime);
        double minutes = (seconds)/60;
        double hours = (minutes) / 60;

        double amount = switch (ticket.getVehicle().getType()) {
            case EV -> 50 + 80*hours;
            case FOUR_WHEELER -> 100 + 120*hours;
            case TWO_WHEELER -> 80 + 100*hours;
            default -> throw new VehicleTypeNotFoundException("");
        };

        return amount;
    }
}
