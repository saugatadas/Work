package models;
import models.enums.SpotStatus;
import models.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpot {
    private int Id;
    private int floorId;
    private VehicleType type;
    private SpotStatus status;

    public ParkingSpot(int id, int floorId, VehicleType type) {
        this.Id = id;
        this.floorId = floorId;
        this.type = type;
        this.status = SpotStatus.FREE;
    }

    public int getId() {
        return Id;
    }

    public int getFloorId() {
        return floorId;
    }

    public VehicleType getType() {
        return type;
    }

    public SpotStatus getStatus() {
        return status;
    }

    public void setStatus(SpotStatus status) {
        this.status = status;
    }
}
