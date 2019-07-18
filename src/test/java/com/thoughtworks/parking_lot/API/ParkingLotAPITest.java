package com.thoughtworks.parking_lot.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.controller.ParkingLotController;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkingLotController.class)
public class ParkingLotAPITest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private ParkingLotService parkingLotService;

    @Test
    void should_add_parkinglot() throws Exception{
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setAddress("ZHA");
        parkingLot.setName("OOCL parking lot");
        parkingLot.setCapacity(10);

        when(parkingLotService.save(any())).thenReturn(parkingLot);

        ResultActions resultActions = mvc.perform(post("/parkinglots")
                .contentType(MediaType.APPLICATION_JSON).
                        content(mapper.writeValueAsString(parkingLot)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("OOCL parking lot")))
                .andExpect(jsonPath("$.address",is("ZHA")))
                .andExpect(jsonPath("$.capacity",is(10)));


    }

    @Test
    void should_delete_parkinglot() throws Exception{

        ResultActions resultActions = mvc.perform(delete("/parkinglots/{id}",1))
                .andExpect(status().isAccepted());



    }
    @Test
    void should_show_parkinglot_page() throws Exception{
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setAddress("ZHA");
        parkingLot.setName("OOCL parking lot");
        parkingLot.setCapacity(10);
        ParkingLot parkingLot1 = new ParkingLot();
        parkingLot1.setAddress("ZHA");
        parkingLot1.setName("CS parking lot");
        parkingLot1.setCapacity(10);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingLot1);

        when(parkingLotService.paging(anyInt(),anyInt())).thenReturn(parkingLots);

        ResultActions resultActions = mvc.perform(get("/parkinglots?page=0&pageSize=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name",is("OOCL parking lot")))
                .andExpect(jsonPath("$[0].address",is("ZHA")))
                .andExpect(jsonPath("$[0].capacity",is(10)));


    }
    @Test
    void should_find_by_name() throws Exception{
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setAddress("ZHA");
        parkingLot.setName("OOCL parking lot");
        parkingLot.setCapacity(10);


        when(parkingLotService.findById(anyInt())).thenReturn(parkingLot);

        ResultActions resultActions = mvc.perform(get("/parkinglots/{id}",parkingLot.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("OOCL parking lot")))
                .andExpect(jsonPath("$.address",is("ZHA")))
                .andExpect(jsonPath("$.capacity",is(10)));


    }
}
