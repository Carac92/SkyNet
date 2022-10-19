package com.safetynet.alert.controller;

import com.safetynet.alert.dto.PhoneAlertDTO;
import com.safetynet.alert.service.PhoneAlertService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/phoneAlert")
public class PhoneAlertController {

    @Autowired
    private PhoneAlertService phoneAlertService;

    private static final Logger log = LogManager.getLogger(PhoneAlertController.class);

    @GetMapping
    public ResponseEntity<Object> getPhoneAlertByStationNumber(@RequestParam(name = "firestation") Integer stationNumber) {
        log.info("Get phone of all Persons in the range of station : params{}", stationNumber);
        if(stationNumber.equals(0)||stationNumber.equals(null)) return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Station number is required");
        List<PhoneAlertDTO> phones = phoneAlertService.getAllPhoneForAFireStationNumber(stationNumber);
        if(phones.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("They are no phones for this station");
        return ResponseEntity.status(HttpStatus.OK).body(phones);
    }
}
