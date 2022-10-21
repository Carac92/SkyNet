package com.safetynet.alert.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Quentin_Caracatzanis
 * Data Transfer Object for Fire Station end point. Contains the list of FireStationDTO.
 */
@Data
public class FireStationFinalDTO {
    private Integer numberOfMinor;
    private Integer numberOfAdults;
    private List<FireStationDTO> fireStationDTOS;
}
