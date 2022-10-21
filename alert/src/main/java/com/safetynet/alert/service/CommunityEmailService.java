package com.safetynet.alert.service;

import com.safetynet.alert.dto.CommunityEmailDTO;

import java.util.List;

/**
 * @author Quentin_Caracatzanis
 * Interface of the Community Email service.
 * Generate a Get method.
 */
public interface CommunityEmailService {
    List<CommunityEmailDTO> getAllEmailOfCity(String city);
}
