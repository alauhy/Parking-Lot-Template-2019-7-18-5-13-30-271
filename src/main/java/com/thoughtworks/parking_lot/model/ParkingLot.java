package com.thoughtworks.parking_lot.model;

import org.jboss.logging.MessageLogger;

import javax.persistence.*;

@Entity
@Table(name = "parkinglot")
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;
    private int capacity;
    private String address;
    @Transient
    private int validCapacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValidCapacity() {
        return validCapacity;
    }

    public void setValidCapacity(int validCapacity) {
        this.validCapacity = validCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
