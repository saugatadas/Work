package repository;
import exception.*;
import models.*;
import java.util.HashMap;

public class ParkingLotRepository {
    HashMap<Integer, ParkingLot> parkingLotMap;

    public ParkingLotRepository() {
        parkingLotMap = new HashMap<>();
    }

    public ParkingLot get(int parkingLotId) {
        if (parkingLotMap.containsKey(parkingLotId)) {
            return parkingLotMap.get(parkingLotId);
        }
        else {
            throw new ParkingLotNotFoundException("Parking lot not found");
        }
    }

    public void put(ParkingLot parkinglot) {
        parkingLotMap.put(parkinglot.getId(), parkinglot);
    }
}

