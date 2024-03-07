import java.util.HashMap;

public class TicketRepository {
    HashMap<Integer, ParkingSpot> ticketMap;

    public ParkingSpotRepository() {
        spotMap = new HashMap<>();
    }

    public ParkingSpot get(int spotId) {
        if (spotMap.containsKey(spotId)) {
            return spotMap.get(spotId);
        }
        else {
            throw new ParkingSpotNotFoundException("Parking spot not found");
        }
    }

    public void put(ParkingSpot spot) {
        spotMap.put(spot.getId(), spot);
    }
}
