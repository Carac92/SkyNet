package com.safetynet.alert.controller;

import com.safetynet.alert.dto.PersonInfoDTO;
import com.safetynet.alert.service.PersonInfoService;
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
 * Controller for Person Information Service.
 * Autowired to PersonInfoService. Generate a get endpoint that gives a Json of a List of PersonInfoDTO at the address:
 * http_address/personInfo?firstName="firstName"&lastName="lastName"
 * Request a first name and last name in parameters.
 */
@RestController
@RequestMapping("/personInfo")
public class PersonInfoController {
    @Autowired
    private PersonInfoService personInfoService;

    @GetMapping
    public ResponseEntity<Object> getPersonInfoByName(@RequestParam(name = "firstName") String firstName,
                                                      @RequestParam(name = "lastName") String lastName){
        if(firstName == null||lastName == null||firstName.isEmpty()||lastName.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("First name and last name must be provided");
        }
        List<PersonInfoDTO> result = personInfoService.getPersonInfoByName(firstName, lastName);
        if(result.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nobody's found");
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }
}
