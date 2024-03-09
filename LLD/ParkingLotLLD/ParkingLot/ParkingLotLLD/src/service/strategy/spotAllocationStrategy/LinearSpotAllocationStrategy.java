package service.strategy.spotAllocationStrategy;
import models.ParkingFloor;
import models.ParkingLot;
import models.ParkingSpot;
import models.Vehicle;
import models.enums.SpotStatus;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LinearSpotAllocationStrategy implements SpotAllocationStrategy {
    @Override
    public ParkingSpot getSpotForVehicle(ParkingLot parkingLot, Vehicle vehicle) {
        ArrayList<ParkingFloor> floors = parkingLot.getFloors();
        for (int i=0; i<floors.size(); i++) {
            ArrayList<ParkingSpot> spot = floors.get(i).getParkingSpots();
            for (int j=0; j<spot.size(); j++) {
                if (spot.get(j).getStatus()== SpotStatus.FREE && spot.get(j).getType()==vehicle.getType())
                    return spot.get(j);
            }
        }

        return null;
    }
}
