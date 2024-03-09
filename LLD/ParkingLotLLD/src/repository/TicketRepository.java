package repository;
import exception.*;
import models.*;
import java.util.HashMap;

public class TicketRepository {
    HashMap<Integer, Ticket> ticketMap;

    public TicketRepository() {
        ticketMap = new HashMap<>();
    }

    public Ticket get(int ticketId) {
        if (ticketMap.containsKey(ticketId)) {
            return ticketMap.get(ticketId);
        }
        else {
            throw new ParkingSpotNotFoundException("Ticket not found");
        }
    }

    public void put(Ticket ticket) {
        ticketMap.put(ticket.getId(), ticket);
    }
}
