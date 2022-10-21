package com.safetynet.alert.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author
 * Method that calculate age base on a birthdate. This method is used each time the age of a medical record is asked.
 */
public class AgeCalculator {
    public static Integer calculateAge(Date birthdate){
        return Period.between(birthdate.toInstant()
                        .atZone(ZoneId.of("UTC"))
                        .toLocalDate(), LocalDate.now()).getYears();
    }
}
