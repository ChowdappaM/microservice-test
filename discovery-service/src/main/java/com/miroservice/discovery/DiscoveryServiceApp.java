package com.miroservice.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DiscoveryServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServiceApp.class, args);
    }
}