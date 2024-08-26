package com.mulei.blisscart.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mulei.blisscart.service.EmailService;

import jakarta.mail.MessagingException;

@RestController
public class DemoController {

    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    @GetMapping("/dem")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello from unsecured url");
    }

    @GetMapping("/admin_only")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("Hello from admin only url");
    }



    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody Map<String, String> request) throws  MessagingException, UnsupportedEncodingException {
        String email = request.get("email");

        EmailService emailSender = new EmailService(mailSender);
        // Call the sendEmail method to send an email
        String recipientEmail = "brianmulei0@gmail.com";
        String subject = "Hello from Spring Boot";
        String content = "<p>Hello,</p><p>This is a test email sent from Spring Boot.</p>";

        try {
            emailSender.sendEmail(recipientEmail, subject, content);
            return "Email sent successfully.";
        } catch (MessagingException | UnsupportedEncodingException e) {
            return "Failed to send email. Error: " + e.getMessage();
        }
    }
}
