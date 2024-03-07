import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private LocalDateTime time;
    private Vehicle vehicle;
    private ParkingSpot spot;

    public Ticket(int id, Vehicle vehicle, ParkingSpot spot) {
        this.id = id;
        this.vehicle = vehicle;
        this.spot = spot;
    }
    
}
