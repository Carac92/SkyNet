package com.safetynet.alert.dto;


import lombok.Data;
import java.util.List;
@Data
public class FireDTO {
    private Integer stationNumber;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private Integer age;
    private List<String> medications;
    private List<String> allergies;
}
