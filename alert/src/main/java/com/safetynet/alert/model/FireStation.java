package com.safetynet.alert.model;

/**
 * @author Quentin_Caracatzanis
 * Model of FireStation contains number of Station and Address.
 */


public class FireStation {
    private Integer station;
    private String address;


    public FireStation() {
    }

    public FireStation(Integer station, String address) {
        this.station = station;
        this.address = address;
    }

    public Integer getStation() {
        return station;
    }

    public void setStation(Integer station) {
        this.station = station;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "FireStation{" +
                "station=" + station +
                ", address='" + address + '\'' +
                '}';
    }
}
