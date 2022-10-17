package com.safetynet.alert.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.format.datetime.DateFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AgeCalculatorTest {
    @Mock
    AgeCalculator ageCalculator;

    @Test
    public void testCalculateAge() throws Exception {
        // Given
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = dateFormatter.parse("03/07/1900");

        // When
        int result = AgeCalculator.calculateAge(date);

        // Then

        assertThat(result).isEqualTo(122);


    }
}
