package com.safetynet.alert.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Quentin_Caracatzanis
 * Data Transfer Object for Person Information end point.
 */
@Data
public class PersonInfoDTO {
    private String lastName;
    private String firstName;
    private String address;
    private String email;
    private Integer age;
    private List<String> medications;
    private List<String> allergies;
}
