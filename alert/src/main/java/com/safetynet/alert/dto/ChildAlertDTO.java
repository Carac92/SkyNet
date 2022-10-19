package com.safetynet.alert.dto;


import lombok.Data;

import java.util.List;
@Data
public class ChildAlertDTO {
    private String firstName;
    private String lastName;
    private String address;
    private Integer age;
    private List<String> personsOfTheSameAddress;
}
