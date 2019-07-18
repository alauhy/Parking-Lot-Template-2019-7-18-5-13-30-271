package com.thoughtworks.parking_lot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkingLot.class)
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
}
