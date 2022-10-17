package com.safetynet.alert.controller;

import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonService personService;
    private static final Logger log = LogManager.getLogger(PersonController.class);

    @PostMapping
    public ResponseEntity <String> addPerson(@RequestBody Person person){
        log.info("Add Person - params: {}", person.toString());
        if(StringUtils.isEmpty(person.getFirstName())||StringUtils.isEmpty(person.getLastName())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Last Name and First Name are required");
        }
        boolean addP = personService.addPerson(person);
        if(addP == false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Person already exists");
        }else{
            log.info("Adding Person - Response: {}", person.toString());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body ("Person added successfully " + person.toString());
        }
    }

    @PutMapping
    public ResponseEntity <String> updatePerson(@RequestBody Person person){
        log.info("Update Person -params: {}", person.toString());
        if(StringUtils.isEmpty(person.getFirstName()) || StringUtils.isEmpty(person.getLastName())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Last Name and First Name are required");
        }
        boolean updateP = personService.updatePerson(person);
        if(updateP == false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Person does not exist");
        }else{
            log.info("Deleting Person - Response: {}", person.toString());
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Person updated successfully: " + person.toString());
        }
    }

    @DeleteMapping
    public ResponseEntity <String> deletePerson(@RequestBody Person person) {
        log.info("Delete Person - params: {}", person.toString());
        if(StringUtils.isEmpty(person.getFirstName()) || StringUtils.isEmpty(person.getLastName())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Last Name and First Name are required");
        }
        boolean deleteP = personService.deletePerson(person);
        if(deleteP == false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Person does not exist");
        }else{
            log.info("Deleting Person - Response: {}", person.toString());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(" Person deleted successfully: "+ person.toString());
        }
    }

}
