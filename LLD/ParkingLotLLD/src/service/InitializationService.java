package service;

import models.*;
import models.enums.VehicleType;
import repository.GateRepository;
import repository.ParkingFloorRepository;
import repository.ParkingLotRepository;
import repository.ParkingSpotRepository;

public class InitializationService {
    private GateRepository gateRepository;
    private ParkingLotRepository parkingLotRepository;
    private ParkingFloorRepository parkingFloorRepository;
    private ParkingSpotRepository parkingSpotRepository;

    public InitializationService(GateRepository gateRepository, ParkingLotRepository parkingLotRepository, ParkingFloorRepository parkingFloorRepository, ParkingSpotRepository parkingSpotRepository) {
        this.gateRepository = gateRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.parkingFloorRepository = parkingFloorRepository;
        this.parkingSpotRepository = parkingSpotRepository;
    }

    public ParkingLot init() {
        System.out.println("**** Starting parking lot initialization ***** ");
        int parkingLotId = 1;
        int parkingSpotId = 1;
        int gateId = 1;

        ParkingLot.Builder parkingLotBuilder = new ParkingLot.Builder();
        parkingLotBuilder.setId(parkingLotId);

        for (int i=1; i<=10; i++) {
            // prepare 10 floors. Each floor with 20 parking slots
            ParkingFloor.Builder floorBuilder = new ParkingFloor.Builder();
            System.out.println("Making floor " + i);
            floorBuilder.setFloorId(i);
            EntryGate entry = new EntryGate(gateId++, i, parkingLotId);
            ExitGate exit = new ExitGate(gateId++, i, parkingLotId);
            floorBuilder.addEntryGate(entry);
            floorBuilder.addExitGate(exit);
            gateRepository.put(entry);
            gateRepository.put(exit);
            for (int j=0; j<10; j++) {
                ParkingSpot spot = new ParkingSpot(parkingSpotId++, i, VehicleType.FOUR_WHEELER);
                parkingSpotRepository.put(spot);
                floorBuilder.addParkingSpot(spot);
            }
            for (int j=0; j<5; j++) {
                ParkingSpot spot = new ParkingSpot(parkingSpotId++, i, VehicleType.TWO_WHEELER);
                parkingSpotRepository.put(spot);
                floorBuilder.addParkingSpot(spot);
            }
            for (int j=0; j<5; j++) {
                ParkingSpot spot = new ParkingSpot(parkingSpotId++, i, VehicleType.EV);
                parkingSpotRepository.put(spot);
                floorBuilder.addParkingSpot(spot);
            }
            ParkingFloor floor = floorBuilder.build();
            parkingFloorRepository.put(floor);
            parkingLotBuilder.AddFloor(floorBuilder.build());
        }

        ParkingLot parkingLot = parkingLotBuilder.build();
        parkingLotRepository.put(parkingLot);
        return parkingLot;
    }
}
