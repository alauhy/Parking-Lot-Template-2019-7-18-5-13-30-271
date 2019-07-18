package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.Ticket;

public interface TicketImpl {
    Ticket save(int parkinglot_id, Ticket ticket) throws Exception;

    Ticket off(int id);
}
