package models;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private int floorId;
    private ArrayList<ParkingSpot> parkingSpots;
    private EntryGate entry;
    private ExitGate exit;

    public ParkingFloor(int floorId, ArrayList<ParkingSpot> parkingSpots, EntryGate entry, ExitGate exit) {
        this.floorId = floorId;
        this.parkingSpots = parkingSpots;
        this.entry = entry;
        this.exit = exit;
    }

    public int getFloorId() {
        return floorId;
    }

    public ArrayList<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public EntryGate getEntry() {
        return entry;
    }

    public ExitGate getExit() {
        return exit;
    }

    public static class Builder {
        private int floorId;
        private ArrayList<ParkingSpot> parkingSpots;
        private EntryGate entry;
        private ExitGate exit;

        public Builder() {
            parkingSpots = new ArrayList<>();
            entry = null;
            exit = null;
            floorId = 0;
        }
        public Builder setFloorId(int floorId) {
            this.floorId = floorId;
            return this;
        }

        public Builder addParkingSpot(ParkingSpot spot) {
            this.parkingSpots.add(spot);
            return this;
        }

        public Builder addEntryGate(EntryGate gate) {
            this.entry = gate;
            return this;
        }

        public Builder addExitGate(ExitGate gate) {
            this.exit = gate;
            return this;
        }

        public ParkingFloor build() {
            return new ParkingFloor(floorId, parkingSpots, entry, exit);
        }
    }
}
