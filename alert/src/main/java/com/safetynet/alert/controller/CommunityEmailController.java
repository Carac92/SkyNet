package com.safetynet.alert.controller;

import com.safetynet.alert.dto.CommunityEmailDTO;
import com.safetynet.alert.service.CommunityEmailService;
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
@RequestMapping("/communityEmail")
public class CommunityEmailController {

    @Autowired
    private CommunityEmailService communityEmailService;

    private final static Logger log = LogManager.getLogger(CommunityEmailController.class);

    @GetMapping
    public ResponseEntity<Object> getEmailOfPeopleInCity(@RequestParam (name = "city") String city){
        log.info("Getting email of the people in params: {}", city);
        if(city.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("city is required");
        List<CommunityEmailDTO> response = communityEmailService.getAllEmailOfCity(city);
        if(response.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No email found");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
