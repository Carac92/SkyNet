package com.safetynet.alert.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class AgeCalculator {
    public static Integer calculateAge(Date birthdate){
        return Period.between(birthdate.toInstant()
                        .atZone(ZoneId.of("UTC"))
                        .toLocalDate(), LocalDate.now()).getYears();
    }
}
