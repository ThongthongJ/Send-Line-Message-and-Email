package com.demo.sendemaillinedemo.controller;

import com.demo.sendemaillinedemo.service.SendEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/email")
@Slf4j
public class EmailController {
    private final SendEmailService sendEmailService;

    public EmailController(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

    @PostMapping("send")
    public String sendEmail(@RequestParam(value = "file", required = false) MultipartFile[] files,
                            String toEmail,
                            String subject,
                            String body,
                            String[] cc) {
        log.info("controller file : {}",files);
        return this.sendEmailService.sendEmail(toEmail, cc, subject, body, files);
    }

}
