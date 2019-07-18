package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.Ticket;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService implements TicketImpl {
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket off(int id) {
        Ticket ticket = ticketRepository.findById(id).get();
        ticket.setStatus(false);
        ticket.setLeaveTime(new Date().getTime());

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket save(int parkinglot_id, Ticket ticket) throws Exception{
        ParkingLot parkingLot = parkingLotRepository.findById(parkinglot_id).get();
        if(parkingLot.getCapacity()-parkingLot.getTickets().size()>0){
            List<Ticket> tickets = new ArrayList<>();
            tickets.add(ticket);
            parkingLot.setTickets(tickets);
            parkingLotRepository.save(parkingLot);
            ticket.setParkinglotName(parkingLot.getName());
            return ticketRepository.save(ticket);
        }
        throw new Exception("is full");

    }
}
