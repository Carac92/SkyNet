package com.safetynet.alert.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.format.datetime.DateFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

/**
 * @author Quentin_Caracatzanis
 * Test of the Util AgeCalculator
 * verify that it calculate accuratly the age.
 */
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
    @Test
    public void testCalculateAgeWithDateNull() throws Exception {
        // Given
        Date date = new Date();

        // WHEN
        int result = AgeCalculator.calculateAge(date);

        //Then
        assertThat(result).isEqualTo(0);
    }
}
