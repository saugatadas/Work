import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private int id;
    private ArrayList<List<ParkingSpot>> parkingSpots;
    private Gate entry;
    private Gate exit;

    public ParkingLot(int id, ArrayList<List<ParkingSpot>> parkingSpots, Gate entry, Gate exit) {
        this.id = id;
        this.parkingSpots = parkingSpots;
        this.entry = entry;
        this.exit = exit;
    }

    public int getId() {
        return id;
    }

    public ArrayList<List<ParkingSpot>> getParkingSpots() {
        return parkingSpots;
    }

    public Gate getEntry() {
        return entry;
    }

    public Gate getExit() {
        return exit;
    }
}
