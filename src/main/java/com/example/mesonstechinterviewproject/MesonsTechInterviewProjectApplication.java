package com.example.mesonstechinterviewproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MesonsTechInterviewProjectApplication {

    public static void main(String[] args) {
        System.out.println("Application Running");
        SpringApplication.run(MesonsTechInterviewProjectApplication.class, args);
    }
}
