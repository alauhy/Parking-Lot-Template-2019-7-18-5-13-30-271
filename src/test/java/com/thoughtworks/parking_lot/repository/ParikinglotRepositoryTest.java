package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.model.ParkingLot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ParikinglotRepositoryTest {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Test
    void should_add_parkinglot_and_find_parkinglot() {
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

    @Test
    void should_delete_parkinglot_by_name() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("OOCL parking lot");
        parkingLot.setCapacity(10);
        parkingLot.setAddress("ZHA");
        ParkingLot parkingLot1 = new ParkingLot();
        parkingLot1.setName("CS parking lot");
        parkingLot1.setCapacity(110);
        parkingLot1.setAddress("ZHA");
        parkingLotRepository.save(parkingLot);
        parkingLotRepository.save(parkingLot1);

        parkingLotRepository.deleteByName(parkingLot.getName());
        List<ParkingLot> parkingLots = parkingLotRepository.findAll();

        assertThat(parkingLots.get(0).getName()).isEqualTo("CS parking lot");
        assertThat(parkingLots.get(0).getAddress()).isEqualTo("ZHA");
        assertThat(parkingLots.get(0).getCapacity()).isEqualTo(110);
    }

    @Test
    void should_page_parkinglot() {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("OOCL parking lot");
        parkingLot.setCapacity(10);
        parkingLot.setAddress("ZHA");
        ParkingLot parkingLot1 = new ParkingLot();
        parkingLot1.setName("CS parking lot");
        parkingLot1.setCapacity(110);
        parkingLot1.setAddress("ZHA");
        parkingLotRepository.save(parkingLot);
        parkingLotRepository.save(parkingLot1);
        Pageable pageable = new PageRequest(0, 2, Sort.Direction.DESC,"name");

        Page<ParkingLot> parkingLots = parkingLotRepository.findAll(pageable);

        assertThat(parkingLots.getContent().size()).isEqualTo(2);
        assertThat(parkingLots.getContent().get(0).getName()).isEqualTo("OOCL parking lot");
        assertThat(parkingLots.getContent().get(0).getAddress()).isEqualTo("ZHA");
        assertThat(parkingLots.getContent().get(0).getCapacity()).isEqualTo(10);
    }


}
