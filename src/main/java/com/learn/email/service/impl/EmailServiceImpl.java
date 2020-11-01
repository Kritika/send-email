package com.learn.email.service.impl;


import com.learn.email.helper.EmailHelper;
import com.learn.email.vos.EmailRequestVo;
import com.learn.sendmail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailHelper emailHelper;

    @Override
    public void sendEmail(EmailRequestVo emailRequestVo) {

        try {
            Session session = emailHelper.getSessionForSendingMail();
            Message msg = emailHelper.setSenderAndReceiver(session, emailRequestVo);

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(emailRequestVo.getMessage(), "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            msg.setContent(multipart);
            Transport.send(msg);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }





    @Override
    public void sendEmailWithCalenderInvite(EmailRequestVo emailRequestVo) {

        try {
            Session session = emailHelper.getSessionForSendingMail();
            Message msg = emailHelper.setSenderAndReceiver(session, emailRequestVo);

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(emailRequestVo.getMessage(), "text/html");

            Multipart multipart = new MimeMultipart();


            StringBuffer buffer = emailHelper.getCalenderInviteCode();

            messageBodyPart.setHeader("Content-Class", "urn:content-  classes:calendarmessage");
            messageBodyPart.setHeader("Content-ID", "calendar_message");
            messageBodyPart.setDataHandler(new DataHandler(
                    new ByteArrayDataSource(buffer.toString(), "text/calendar")));
            multipart.addBodyPart(messageBodyPart);
            msg.setContent(multipart);
            Transport.send(msg);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }




}
