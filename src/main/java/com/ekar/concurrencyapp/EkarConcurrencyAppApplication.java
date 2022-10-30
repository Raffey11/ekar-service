package com.ekar.concurrencyapp;

import com.ekar.concurrencyapp.config.EkarApiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties(value = {EkarApiConfig.class})
public class EkarConcurrencyAppApplication {

    public static void main(String[] args) {
        configureSystem();
        SpringApplication.run(EkarConcurrencyAppApplication.class, args);
    }

    private static void configureSystem() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
