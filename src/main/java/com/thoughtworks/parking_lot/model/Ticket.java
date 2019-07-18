package com.thoughtworks.parking_lot.model;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String carNumber;
    private long createTime;
    private long leaveTime;
    private String parkinglotName;
    private boolean status = true;


    public String getParkinglotName() {
        return parkinglotName;
    }

    public void setParkinglotName(String parkinglotName) {
        this.parkinglotName = parkinglotName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(long leaveTime) {
        this.leaveTime = leaveTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
