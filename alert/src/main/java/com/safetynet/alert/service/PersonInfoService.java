package com.safetynet.alert.service;

import com.safetynet.alert.dto.PersonInfoDTO;

import java.util.List;

/**
 * @author Quentin_Caracatzanis
 * Interface of the Person Information Service.
 * Generate a Get Method.
 */
public interface PersonInfoService {
    List<PersonInfoDTO> getPersonInfoByName(String firstName, String lastName);
}
