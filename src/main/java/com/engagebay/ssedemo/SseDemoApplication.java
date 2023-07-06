package com.engagebay.ssedemo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.engagebay.ssedemo"})
public class SseDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SseDemoApplication.class, args);
    }

}
