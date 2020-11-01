package com.learn.email.controller;

import com.learn.email.vos.EmailRequestVo;
import com.learn.sendmail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/send")
    public String sendEmail(@RequestBody EmailRequestVo emailRequestVo) {
        emailService.sendEmail(emailRequestVo);
        return "Email sent successfully";
    }
    @PostMapping(value = "/send-with-invite")
    public String sendEmailWithInvite(@RequestBody EmailRequestVo emailRequestVo) {
        emailService.sendEmailWithCalenderInvite(emailRequestVo);
        return "Email sent successfully";
    }
}