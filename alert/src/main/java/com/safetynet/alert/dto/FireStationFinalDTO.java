package com.safetynet.alert.dto;

import lombok.Data;

import java.util.List;

@Data
public class FireStationFinalDTO {
    private Integer numberOfMinor;
    private Integer numberOfAdults;
    private List<FireStationDTO> fireStationDTOS;
}
