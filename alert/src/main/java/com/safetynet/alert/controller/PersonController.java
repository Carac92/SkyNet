package com.safetynet.alert.controller;

import com.safetynet.alert.model.Person;
import com.safetynet.alert.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Quentin_Caracatzanis
 * Controller of Person Service.
 * Autowired to PersonService. Generate a POST/PUT/DELETE endpoints for the person at the address :
 * http_address/person
 * request a person model in the body.
 */
@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonService personService;
    private static final Logger log = LogManager.getLogger(PersonController.class);

    @PostMapping
    public ResponseEntity <String> addPerson(@RequestBody Person person){
        log.info("Add Person - params: {}", person);
        if(person.getFirstName()==null||person.getLastName()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Last Name and First Name are required");
        }
        boolean addP = personService.addPerson(person);
        if(addP == false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Person already exists");
        }else{
            log.info("Adding Person - Response: {}", person);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body ("Person added successfully " + person.toString());
        }
    }

    @PutMapping
    public ResponseEntity <String> updatePerson(@RequestParam (name = "firstName") String firstName,
                                                @RequestParam (name = "lastName") String lastName,
                                                @RequestBody Person person){
        log.info("Update Person -params: {}", person);
        if(person==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Body is reququired");
        }else if(firstName.isEmpty() || lastName.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Last Name and First Name are required");
        }
        boolean updateP = personService.updatePerson(firstName,lastName, person);
        if(!updateP){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Person does not exist");
        }else{
            log.info("Deleting Person - Response: {}", person);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Person updated successfully: " + person.toString());
        }
    }

    @DeleteMapping
    public ResponseEntity <String> deletePerson(@RequestParam (name = "firstName") String firstName,
                                                @RequestParam (name = "lastName") String lastName) {
        log.info("Delete Person - params: {}", firstName + " " + lastName);
        if(firstName.isEmpty() || lastName.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Last Name and First Name are required");
        }
        boolean deleteP = personService.deletePerson(firstName, lastName);
        if(deleteP == false){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Person does not exist");
        }else{
            log.info("Deleting Person - Response: {}", firstName + " " + lastName);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(" Person deleted successfully: "+ firstName + " " + lastName);
        }
    }

}
