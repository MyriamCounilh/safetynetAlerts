package com.myriamcounilh.safetynetalerts.model;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Firestation {

    @NotNull(message = "address obligatory")
    private String address;

    @NotNull(message = "station obligatory")
    private Integer station;

    public Firestation() { }

    public Firestation(Integer station, String address) {
        this.station = station;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStation() {
        return station;
    }

    public void setStation(Integer station) {
        this.station = station;
    }
}
