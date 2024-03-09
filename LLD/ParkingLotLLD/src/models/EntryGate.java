package models;

import models.enums.GateType;

public class EntryGate implements Gate {
    private int id;
    private int floorID;
    private int parkingLotID;

    public EntryGate(int id, int floorID, int parkingLotID) {
        this.id = id;
        this.floorID = floorID;
        this.parkingLotID = parkingLotID;
    }

    @Override
    public int getId() {
        return id;
    }

    public int getFloorID() {
        return floorID;
    }

    public int getParkingLotID() {
        return parkingLotID;
    }

    @Override
    public GateType getType() {
        return GateType.ENTRY;
    }
}
