package com.safetynet.alert.config;

import com.safetynet.alert.model.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Quentin_Caracatzanis
 * test that verify that a bean of Data is generated by the Json
 */
public class JsonDataCollectorTest {
    ApplicationContextRunner context = new ApplicationContextRunner()
            .withUserConfiguration(JsonDataCollector.class);
    @Test
    public void dataCollectorTest(){

        context.run(it -> {
            assertThat(it).hasSingleBean(Data.class);
        });
    }

}
