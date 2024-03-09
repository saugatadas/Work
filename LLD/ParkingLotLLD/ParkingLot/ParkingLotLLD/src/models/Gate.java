package models;

import models.enums.GateType;

public interface Gate {
    public int getId();
    public GateType getType();
    public int getFloorID();
    public int getParkingLotID();

}
