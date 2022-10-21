package com.safetynet.alert.dto;

import lombok.Data;

/**
 * @author Quentin_Caracatzanis
 * Data Transfer Object for Fire Station end point. It goes into FireStationFinalDTO as a list.
 */
@Data
public class FireStationDTO {
    private String lastName;
    private String firstName;
    private String address;
    private String phoneNumber;
    private boolean minor = false;
}
