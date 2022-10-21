package com.safetynet.alert.controller;

import com.safetynet.alert.dto.ChildAlertDTO;
import com.safetynet.alert.service.ChildAlertService;
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
 * Controller of childAlert
 * Is Autowired to ChildAlertService. Create a get endpoint return the list of childAlertDTO in the Json format.
 * Request an address as a parameter.
 */
@RestController
@RequestMapping("/childAlert")
public class ChildAlertController {

    private static final Logger log = LogManager.getLogger(ChildAlertController.class);
    @Autowired
    private ChildAlertService childAlertService;



    @GetMapping
    public ResponseEntity<Object> findMinorByAddress(@RequestParam(name="address") String address) {
        log.info("Requesting to find Minor at params:{}", address);
        if(address.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Address is required");
        }
        List<ChildAlertDTO> childAlertDTOs = childAlertService.findChildOfAnAddress(address);
        if(childAlertDTOs.size() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No child at this address is found");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(childAlertDTOs);
        }
    }
}
