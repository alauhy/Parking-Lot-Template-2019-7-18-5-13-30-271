package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping("/parkinglots")
    public ParkingLot save(@RequestBody ParkingLot parkingLot) {
        return parkingLotService.save(parkingLot);
    }

    @DeleteMapping("/parkinglots/{name}")
    public ResponseEntity delete(@PathVariable String name) {
        parkingLotService.delete(name);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


}
