package service.strategy.spotAllocationStrategy;
import models.*;

public interface SpotAllocationStrategy {
    ParkingSpot getSpotForVehicle(ParkingLot parkingLot, Vehicle vehicle);
}
