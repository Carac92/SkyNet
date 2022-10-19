package com.safetynet.alert.service;

import com.safetynet.alert.dto.CommunityEmailDTO;

import java.util.List;

public interface CommunityEmailService {
    List<CommunityEmailDTO> getAllEmailOfCity(String city);
}
