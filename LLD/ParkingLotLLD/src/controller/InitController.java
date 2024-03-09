package controller;

import models.ParkingLot;
import service.InitializationService;

public class InitController {
    private InitializationService initializationService;

    public InitController(InitializationService initialisationService) {
        this.initializationService = initialisationService;
    }

    public ParkingLot init(){
        return initializationService.init();
    }
}
