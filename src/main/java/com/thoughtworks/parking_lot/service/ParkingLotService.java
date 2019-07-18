package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ParkingLotService implements ParkingLotImpl {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Override
    public ParkingLot save(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

    @Override
    public ParkingLot findById(int id) {
        return parkingLotRepository.findById(id).get();
    }

    @Override
    public List<ParkingLot> paging(int page, int pageSize) {
        Pageable pageable = new PageRequest (Math.max(0,page-1),pageSize);
        return parkingLotRepository.findAll(pageable).getContent();
    }

    @Override
    public ParkingLot update(int id, ParkingLot parkingLot) {
        parkingLot.setId(id);
        return parkingLotRepository.save(parkingLot);
    }

    @Override
    public void delete(int id) {
        parkingLotRepository.deleteById(id);
    }
}
