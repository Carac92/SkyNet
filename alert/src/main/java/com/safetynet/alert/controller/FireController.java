package com.safetynet.alert.controller;

import com.safetynet.alert.dto.FireDTO;
import com.safetynet.alert.service.FireService;
import lombok.Data;
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

/**
 * @author Quentin_Caracatzanis
 * Controller for the fire end point.
 * Autowired to the FireService. Create a get endpoint that give a Json of the List of FireDTO at
 * http_address/fire?address="address"
 * request an address in the parameter.
 */
@RestController
@RequestMapping("/fire")
public class FireController {
    @Autowired
    private FireService fireService;

    private static final Logger log = LogManager.getLogger(FireController.class);

    @GetMapping
    public ResponseEntity<Object> getPeopleByAddress(@RequestParam(name = "address") String address) {
        log.info("getting people by address : params{}", address);
        if(address.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("address is required");
        List<FireDTO> result = fireService.getAllPeopleByFireStationAddress(address);
        if(result.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No people were found");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
