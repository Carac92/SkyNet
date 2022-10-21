package com.safetynet.alert.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alert.model.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * @author Quentin_Caracatzanis
 * Configuration of the Application.
 * Generate a Bean of Data Model based on the data.json file in the resources.
 */
@Configuration
public class JsonDataCollector {

    private static final Logger log = LogManager.getLogger(JsonDataCollector.class);

    @Value("classpath:data.json")
    Resource resourceFile;

    @Bean
    public Data data () {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            log.info("Json data collecting.");
            return objectMapper.readValue(resourceFile.getInputStream(), Data.class);
        } catch (Exception e) {
            log.error(e);
        }
        return new Data ();
    }
}