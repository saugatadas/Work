package repository;
import exception.*;
import models.*;

import java.util.HashMap;

public class GateRepository {
    HashMap<Integer, Gate> gateMap;

    public GateRepository() {
        gateMap = new HashMap<>();
    }

    public Gate get(int gateId) {
        if (gateMap.containsKey(gateId)) {
            return gateMap.get(gateId);
        }
        else {
            throw new GateNotFoundException("Gate not found");
        }
    }

    public void put(Gate gate) {
        gateMap.put(gate.getId(), gate);
    }
}

