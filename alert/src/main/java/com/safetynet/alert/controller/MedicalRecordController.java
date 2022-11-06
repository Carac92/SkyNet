package com.safetynet.alert.controller;
import com.safetynet.alert.model.MedicalRecord;
import com.safetynet.alert.service.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author Quentin_Caracatzanis
 * Controller for Medical Record Service.
 * Autowired to Medical Record Service. Generate a Post/ Put/Delete endpoints for MedicalRecord at the address :
 * http_address/medicalRecord
 * request a MedicalRecord in the body.
 */
@RestController
@RequestMapping("medicalRecord")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;
    private static final Logger log = LogManager.getLogger(MedicalRecordController.class);


    @PostMapping
    public ResponseEntity <String> addFireStation (@RequestBody MedicalRecord medicalRecord){
        log.info ("Adding MedicalRecord - params: {}", medicalRecord.toString());
        if(StringUtils.isEmpty(medicalRecord.getFirstName()) || StringUtils.isEmpty(medicalRecord.getLastName())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("First Name And Last Name Required");
        }
        boolean addMR = medicalRecordService.addMedicalRecord(medicalRecord);
        if(addMR==false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Medical Record already exists");
        }else{
            log.info("Adding Medical Record - Response: {}", medicalRecord.toString());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Medical Record added" + medicalRecord.toString());
        }
    }

    @PutMapping
    public ResponseEntity <String> updateMedicalRecord(@RequestParam(name="firstName") String firstName,
                                                       @RequestParam(name="lastName") String lastName,
                                                       @RequestBody MedicalRecord medicalRecord){
        log.info("Update Medical Record - params: {}", medicalRecord.toString());
        if((firstName.isEmpty()|| firstName==null) || (lastName.isEmpty()|| lastName==null)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("First Name and Last Name are required");
        }else if(medicalRecord ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("body required");
        }
        boolean updateMR = medicalRecordService.updateMedicalRecord(firstName,lastName, medicalRecord);
        if(updateMR == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Medical Record does not exist");
        }else {
            log.info("Updating Medical Record - Response: {}", medicalRecord.toString());
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Medical Record updated" + medicalRecord.toString());
        }
    }

    @DeleteMapping
    public ResponseEntity <String> deleteMedicalRecord(@RequestParam(name="firstName") String firstName,
                                                       @RequestParam(name="lastName") String lastName) {
        log.info("Delete Medical Record - params: {}", firstName + " " + lastName);
        if((firstName.isEmpty()|| firstName == null) || (lastName.isEmpty()|| lastName == null)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("First name and last name are required");
        }
        boolean deleteMR = medicalRecordService.removeMedicalRecord(firstName, lastName);
        if(deleteMR == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Medical Record does not exist");
        }else {
            log.info("Deleting Medical Record - Response: {}", firstName + " " + lastName);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Medical Record deleted " + firstName + " " + lastName);
        }
    }

}
