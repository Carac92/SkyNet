package com.safetynet.alert.service;

import com.safetynet.alert.dto.PersonInfoDTO;

import java.util.List;

public interface PersonInfoService {
    List<PersonInfoDTO> getPersonInfoByName(String firstName, String lastName);
}
