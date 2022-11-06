package com.safetynet.alert.controller;

import com.safetynet.alert.dto.FireStationDTO;
import com.safetynet.alert.dto.FireStationFinalDTO;
import com.safetynet.alert.model.FireStation;
import com.safetynet.alert.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Quentin_Caracatzanis
 * Controller for fire station.
 * Generate a put post and delete endpoints which needs a FireStation in the body.
 * Generate a get endpoint which generate a JSON response of FireStationFinalDTO at the address :
 * http_address/firestation?stationNumber="stationNumber".
 * request a fire station number in parameter.
 */
@RestController
@RequestMapping("firestation")
public class FireStationController {

    private static final Logger log = LogManager.getLogger(FireStationController.class);

    @Autowired
    private FireStationService fireStationService;

    @PostMapping
    public ResponseEntity <String> addFireStation(@RequestBody FireStation fireStation) {
        log.info("Adding FireStation - params {}", fireStation.toString());
        if(StringUtils.isEmpty(fireStation.getAddress()) || fireStation.getStation()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Adress And Station Required");
        }
        boolean addFs= fireStationService.addFireStation(fireStation);
        if(addFs==false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("FireStation and address already exists");
        }else{
            log.info("addFireStation - Response : {}", fireStation.toString());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Added : " + fireStation.toString());
        }
    }
    @PutMapping
    public ResponseEntity<String> updateFireStation(@RequestParam(name="address") String address,
                                                    @RequestBody FireStation fireStation) {
        log.info("Updating FireStation - params:{}", fireStation.toString());
        if(address.isEmpty()|| address==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Adress Required");
        } else if (fireStation==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("body required");

        }
        boolean updateFS= fireStationService.updateFireStation(address, fireStation);
        if(updateFS==false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("address already mapped to a Firestation");
        }else{
            log.info("updateFireStation - Response:{}", fireStation.toString());
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Updated : " + fireStation.toString());
        }
    }
    @DeleteMapping
    public ResponseEntity <String> deleteFireStation(@RequestParam(name="station") Integer station,
                                                     @RequestParam (name="address")String address){
        log.info("Deleting Firestation - params : {}", station + ":" + address);
        if((address.isEmpty() || address == null) || station==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Adress And Station Required");
        }
        boolean deleteFS= fireStationService.removeFireStation(station,address);
        if(deleteFS==false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("FireStation does not exist");
        }else{
            log.info("Removing Fire Station - Response: {}", station + ":" + address);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Deleted : " + station + ":" + address);
        }
    }
    @GetMapping
    public ResponseEntity<Object> getPersonInfoByFireStationNumber(@RequestParam (name = "stationNumber") Integer stationNumber) {
        if(stationNumber==null||stationNumber==0) return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Station Number must be specified");
        List<FireStationDTO> result = fireStationService.getAllPeopleInTheFireStation(stationNumber);
        if(result.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No person found");
        FireStationFinalDTO finalResult = fireStationService.GetAllPeopleCoveredByFireStation(result);
        return ResponseEntity.status(HttpStatus.OK).body(finalResult);
    }
}
