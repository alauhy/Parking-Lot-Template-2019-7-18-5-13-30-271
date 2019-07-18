package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.model.Ticket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepository;
    @Test
    void should_created_ticket_and_find_by_id(){
        Ticket ticket = new Ticket();
        ticket.setCarNumber("粤R 44665");
        ticket.setCreateTime(11223344);
        ticket.setParkinglotName("OOCL parking lot");

        ticketRepository.save(ticket);
        Ticket ticket1 = ticketRepository.findById(ticket.getId()).get();

        assertThat(ticket1.getCarNumber()).isEqualTo("粤R 44665");
        assertThat(ticket1.getParkinglotName()).isEqualTo("OOCL parking lot");
        assertThat(ticket1.getCreateTime()).isEqualTo(11223344);

    }
    @Test
    void should_turn_ticket_off(){
        Ticket ticket = new Ticket();
        ticket.setCarNumber("粤R 44665");
        ticket.setCreateTime(11223344);
        ticket.setParkinglotName("OOCL parking lot");

        ticketRepository.save(ticket);
        Ticket ticket1 = ticketRepository.findById(ticket.getId()).get();
        ticket1.setLeaveTime(new Date().getTime());
        ticket1.setStatus(false);
        ticketRepository.save(ticket);

        Ticket ticket2 = ticketRepository.findById(ticket.getId()).get();
        assertThat(ticket2.isStatus()).isEqualTo(false);



    }
}
