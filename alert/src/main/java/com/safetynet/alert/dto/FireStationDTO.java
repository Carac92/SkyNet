package com.safetynet.alert.dto;

import lombok.Data;

@Data
public class FireStationDTO {
    private String lastName;
    private String firstName;
    private String address;
    private String phoneNumber;
    private boolean minor = false;
}
