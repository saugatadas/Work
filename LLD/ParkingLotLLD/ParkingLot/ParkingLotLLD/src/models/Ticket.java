package models;

import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private LocalDateTime time;
    private Vehicle vehicle;
    private ParkingSpot spot;

    public Ticket(int id, LocalDateTime time, Vehicle vehicle, ParkingSpot spot) {
        this.id = id;
        this.time = time;
        this.vehicle = vehicle;
        this.spot = spot;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }
}
