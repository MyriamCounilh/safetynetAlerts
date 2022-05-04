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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Firestation that = (Firestation) o;
        return address.equals(that.address) && station.equals(that.station);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, station);
    }
}
