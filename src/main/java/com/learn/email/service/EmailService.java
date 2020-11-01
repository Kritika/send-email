package com.learn.sendmail.service;

import com.learn.email.vos.EmailRequestVo;

public interface EmailService {

    void sendEmail(EmailRequestVo emailRequestVo);

    void sendEmailWithCalenderInvite(EmailRequestVo emailRequestVo);


    }
