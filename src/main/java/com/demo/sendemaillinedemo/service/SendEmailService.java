package com.demo.sendemaillinedemo.service;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class SendEmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;

    public String sendEmail(String toEmail,String[] cc, String subject, String body, MultipartFile[] files) {
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            log.info("files : {}",files);
            if (!ObjectUtils.isEmpty(files)) {
                for (int i = 0; i < files.length; i++) {
                    mimeMessageHelper.addAttachment(
                            files[i].getOriginalFilename(),
                            new ByteArrayResource(files[i].getBytes())
                    );
                }
            }

            javaMailSender.send(mimeMessage);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }

        return "send email to " + toEmail + " success";
    }
}
