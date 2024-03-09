package models;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int id;
    private ArrayList<ParkingFloor> floors;
    private String name;
    private String addr;

    public ParkingLot(int id, ArrayList<ParkingFloor> floors, String name, String addr) {
        this.id = id;
        this.floors = floors;
        this.name = name;
        this.addr = addr;
    }

    public int getId() {
        return id;
    }

    public ArrayList<ParkingFloor> getFloors() {
        return floors;
    }

    public String getName() {
        return name;
    }

    public String getAddr() {
        return addr;
    }

    public static class Builder {
        private int id;
        private ArrayList<ParkingFloor> floors;
        private String name;
        private String addr;

        public Builder() {
            floors = new ArrayList<>();
            name = null;
            addr = null;
            id = 0;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder AddFloor(ParkingFloor floor) {
            this.floors.add(floor);
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddr(String addr) {
            this.addr = addr;
            return this;
        }

        public ParkingLot build() {
            return new ParkingLot(id, floors, name, addr);
        }
    }
}
