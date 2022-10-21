package com.safetynet.alert.dto;


import lombok.Data;

import java.util.List;

/**
 * @author Quentin_Caracatzanis
 * Data Transfer Object for Flood end point.
 */
@Data
public class FloodDTO {
    private Integer stationNumber;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private Integer age;
    private List<String> medications;
    private List<String> allergies;
}
