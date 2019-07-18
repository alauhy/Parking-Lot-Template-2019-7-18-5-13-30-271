package com.thoughtworks.parking_lot.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.controller.TicketController;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.Ticket;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import com.thoughtworks.parking_lot.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TicketController.class)
@ExtendWith(SpringExtension.class)
public class TicketAPITest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private TicketService ticketService;

    @MockBean
    private ParkingLotService parkingLotService;

    @Test
    void should_add_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("OOCL parking lot");
        parkingLot.setAddress("ZHA");
        parkingLot.setCapacity(10);
        when(parkingLotService.save(parkingLot)).thenReturn(parkingLot);
        Ticket ticket = new Ticket();
        ticket.setParkinglotName(parkingLot.getName());
        ticket.setCreateTime(111223344);
        ticket.setCarNumber("粤B 12234");

        when(ticketService.save(anyInt(), any())).thenReturn(ticket);

        ResultActions actions = mvc.perform(post("/parkinglot/{id}/tickets", parkingLot.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(ticket)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.parkinglotName", is("OOCL parking lot")))
                .andExpect(jsonPath("$.carNumber", is("粤B 12234")))
                .andExpect(jsonPath("$.createTime", is(111223344)));

    }

    @Test
    void should_turn_ticket_off() throws Exception {

        Ticket ticket = new Ticket();
        ticket.setParkinglotName("OOCL parking lot");
        ticket.setCreateTime(111223344);
        ticket.setCarNumber("粤B 12234");
        ticket.setStatus(false);

        when(ticketService.off(anyInt())).thenReturn(ticket);

        ResultActions actions = mvc.perform(put("/tickets/{id}",ticket.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.parkinglotName", is("OOCL parking lot")))
                .andExpect(jsonPath("$.carNumber", is("粤B 12234")))
                .andExpect(jsonPath("$.createTime", is(111223344)))
                .andExpect(jsonPath("$.status",is(false)));

    }

}
