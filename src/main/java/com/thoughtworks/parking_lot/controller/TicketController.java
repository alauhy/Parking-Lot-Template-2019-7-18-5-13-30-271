package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.Ticket;
import com.thoughtworks.parking_lot.repository.TicketRepository;
import com.thoughtworks.parking_lot.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/parkinglot/{id}/tickets")
    public Ticket create(@PathVariable int id, @RequestBody Ticket ticket) throws Exception{
        return ticketService.save(id,ticket);
    }
    @PutMapping("/tickets/{id}")
    public Ticket off(@PathVariable int id){
        return ticketService.off(id);
    }

}
