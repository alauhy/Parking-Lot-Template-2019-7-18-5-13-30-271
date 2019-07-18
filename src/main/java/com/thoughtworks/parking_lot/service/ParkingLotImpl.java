package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;

public interface ParkingLotImpl {
    ParkingLot save(ParkingLot parkingLot);

    void delete(String name);

}
