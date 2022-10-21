package com.safetynet.alert.controller;

import com.safetynet.alert.dto.FloodDTO;
import com.safetynet.alert.service.FloodService;
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
 * Controller for Flood Service.
 * Autowired to Flood Service. Generate a get endpoint to get a list of FloodDTO in the JSON format at the address :
 * http_address/flood/stations?stations="ListOfStations"
 * needs a List of Stations number in parameter.
 */
@RestController
@RequestMapping("flood/stations")
public class FloodController {

    @Autowired
    private FloodService floodService;

    @GetMapping
    public ResponseEntity<Object> getFloodHousesByStationNumbers(@RequestParam(name= "stations")List<Integer> stationsNumber){
        if(stationsNumber.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("At least one station number is required");
        List<FloodDTO> result = floodService.getAllHousesByAListOfStationNumber(stationsNumber);
        if(result.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No one found");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
