package repository;
import exception.*;
import models.*;
import java.util.HashMap;

public class ParkingFloorRepository {
    HashMap<Integer, ParkingFloor> floorRepoMap;
    public ParkingFloorRepository() {
        floorRepoMap = new HashMap<>();
    }

    public ParkingFloor get(int floorId) {
        if (floorRepoMap.containsKey(floorId)) {
            return floorRepoMap.get(floorId);
        }
        else {
            throw new GateNotFoundException("Floor not found");
        }
    }

    public void put(ParkingFloor floor) {
        floorRepoMap.put(floor.getFloorId(), floor);
    }
}