package com.safetynet.alert.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonInfoDTO {
    private String LastName;
    private String FirstName;
    private String address;
    private String email;
    private Integer age;
    private List<String> medications;
    private List<String> allergies;
}
