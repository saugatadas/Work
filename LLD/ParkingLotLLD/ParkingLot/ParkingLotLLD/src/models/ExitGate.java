package models;

import models.enums.GateType;

public class ExitGate implements Gate {
    private int id;
    private int floorID;
    private int parkingLotID;

    public ExitGate(int id, int floorID, int parkingLotID) {
        this.id = id;
        this.floorID = floorID;
        this.parkingLotID = parkingLotID;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getFloorID() {
        return floorID;
    }

    @Override
    public int getParkingLotID() {
        return parkingLotID;
    }

    @Override
    public GateType getType() {
        return GateType.EXIT;
    }
}
