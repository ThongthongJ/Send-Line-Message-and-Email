package com.demo.sendemaillinedemo;

import com.demo.sendemaillinedemo.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SendEmailLineDemoApplication {
    @Autowired
    private SendEmailService sendEmailService;

    public static void main(String[] args) {
        SpringApplication.run(SendEmailLineDemoApplication.class, args);
    }
}
