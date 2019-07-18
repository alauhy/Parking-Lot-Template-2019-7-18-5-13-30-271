package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.model.ParkingLot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ParikinglotRepositoryTest {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Test
    void should_add_parkinglot_and_find_parkinglot(){
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("OOCL parking lot");
        parkingLot.setCapacity(10);
        parkingLot.setAddress("ZHA");

        parkingLotRepository.save(parkingLot);
        ParkingLot parkingLot1 = parkingLotRepository.findByName(parkingLot.getName());

        assertThat(parkingLot1.getName()).isEqualTo("OOCL parking lot");
        assertThat(parkingLot1.getAddress()).isEqualTo("ZHA");
        assertThat(parkingLot1.getCapacity()).isEqualTo(10);
    }






}
