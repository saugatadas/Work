package service;
import models.*;
import models.enums.SpotStatus;
import repository.GateRepository;
import repository.ParkingLotRepository;
import repository.TicketRepository;
import service.strategy.spotAllocationStrategy.SpotAllocationStrategy;
import service.strategy.spotAllocationStrategy.SpotAllocationStrategyFactory;
import java.time.LocalDateTime;

public class TicketService {
    private TicketRepository ticketRepo;
    private ParkingLotRepository parkingLotRepo;
    private GateRepository gateRepo;
    private int ticketId;

    public TicketService(TicketRepository ticketRepo, ParkingLotRepository parkingLotRepo, GateRepository gateRepo) {
        this.ticketRepo = ticketRepo;
        this.parkingLotRepo = parkingLotRepo;
        this.gateRepo = gateRepo;
        this.ticketId = 0;
    }

    public Ticket generateTicket(Vehicle vehicle, int parkingLotId, int gateId) {
        SpotAllocationStrategy strategy = SpotAllocationStrategyFactory.getSpotAllocationStrategy();
        ParkingLot parkingLot = parkingLotRepo.get(parkingLotId);
        ParkingSpot spot = strategy.getSpotForVehicle(parkingLot, vehicle);
        if (spot==null) {
            return null;
        }
        ticketId++;
        Ticket ticket = new Ticket(ticketId, LocalDateTime.now(), vehicle, spot);
        ticketRepo.put(ticket);
        spot.setStatus(SpotStatus.OCCUPIED);
        return ticket;
    }
}