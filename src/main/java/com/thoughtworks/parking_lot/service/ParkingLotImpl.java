package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;

import java.util.List;

public interface ParkingLotImpl {
    ParkingLot save(ParkingLot parkingLot);

    void delete(String name);

    List<ParkingLot> paging(int page, int pageSize);
}
