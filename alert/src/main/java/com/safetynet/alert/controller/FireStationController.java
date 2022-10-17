package com.safetynet.alert.controller;

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

@RestController
@RequestMapping("firestation")
public class FireStationController {

    private static final Logger log = LogManager.getLogger(FireStationController.class);

    private final FireStationService fireStationService;
@Autowired
    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }


    @PostMapping
    public ResponseEntity <String> addFireStation(@RequestBody FireStation fireStation) {
        log.info("Adding FireStation - params {}", fireStation.toString());
        if(StringUtils.isEmpty(fireStation.getAddress()) || fireStation.getStation()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Adress And Station Required");
        }
        List<FireStation> fireStations= fireStationService.addFireStation(fireStation);
        if(!fireStations.contains(fireStation)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("FireStation and address already exists");
        }else{
            log.info("addFireStation - Response : {}", fireStation.toString());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Added : " + fireStation.toString());
        }
    }
//    @PutMapping
//    public ResponseEntity<String> updateFireStation(@RequestBody FireStation fireStation) {
//        log.info("Updating FireStation - params:{}", fireStation.toString());
//        if(StringUtils.isEmpty(fireStation.getAddress()) || fireStation.getStation()==null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Adress And Station Required");
//        }
//        FireStation updateFS= fireStationService.updateFireStation(fireStation);
//        if(updateFS==null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("address already mapped to a Firestation");
//        }else{
//            log.info("updateFireStation - Response:{}", fireStation.toString());
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body("Updated : " + fireStation.toString());
//        }
//    }
    @DeleteMapping
    public ResponseEntity <String> deleteFireStation(@RequestBody FireStation fireStation){
        log.info("Deleting Firestation - params : {}", fireStation.toString());
        if(StringUtils.isEmpty(fireStation.getAddress()) || fireStation.getStation()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Adress And Station Required");
        }
        boolean deleteFS= fireStationService.removeFireStation(fireStation);
        if(deleteFS==false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("FireStation does not exist");
        }else{
            log.info("Removing Fire Station - Response: {}", fireStation.toString());
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Updated : " + fireStation.toString());
        }
    }
}