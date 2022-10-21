package com.safetynet.alert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author "Quentin_Caracatzanis"
 * Application of SafetyNet Alert. This application use a data.json file to have data. No database.
 * the data is modified by the put post delete endpoint for the models.
 * The get endpoints are in case of emergencies like fire, flood, ouragan.
 * it gives to the first rescousse team all the information needed about the person in their range.
 * FireStation are mapped by address of the house.
 */
@SpringBootApplication
public class AlertApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertApplication.class, args);
	}

}
