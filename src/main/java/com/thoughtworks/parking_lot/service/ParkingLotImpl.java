package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;

import java.awt.print.Pageable;
import java.util.List;

public interface ParkingLotImpl {
    ParkingLot save(ParkingLot parkingLot);

    void delete(int id);

    List<ParkingLot> paging(int page, int pageSize);

    ParkingLot findById(int id);

    ParkingLot update(int id,ParkingLot parkingLot);
}
