package repository;
import exception.*;
import models.*;

import java.util.HashMap;

public class BillRepository {
    HashMap<Integer, Bill> billMap;

    public BillRepository() {
        billMap = new HashMap<>();
    }

    public Bill get(int billId) {
        if (billMap.containsKey(billId)) {
            return billMap.get(billId);
        }
        else {
            throw new BillNotFoundException("Bill not found");
        }
    }

    public void put(Bill bill) {
        billMap.put(bill.getId(), bill);
    }
}
