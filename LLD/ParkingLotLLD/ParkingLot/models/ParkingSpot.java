import enums.ParkingSpotStatus;
import enums.VehicleType;

public class ParkingSpot {
    private int id;
    private int floor;
    private ParkingSpotStatus parkingStatus;
    private VehicleType vehicleType;
    private Vehicle vehicle;

    public ParkingSpot(int id, int floor, VehicleType vehicleType) {
        this.id = id;
        this.floor = floor;
        this.vehicleType = vehicleType;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getId() {
        return id;
    }

    public ParkingSpotStatus getParkingStatus() {
        return parkingStatus;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
