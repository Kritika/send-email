package com.learn.email.helper;

import com.learn.email.vos.EmailRequestVo;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailHelper {


    public Session getSessionForSendingMail() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("kritika.tripathi28@gmail.com", "xx");
            }
        });
    }

    public Message setSenderAndReceiver(Session session, EmailRequestVo emailRequestVo) {
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(emailRequestVo.getTo(), false));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailRequestVo.getTo()));
            msg.setSubject(emailRequestVo.getSubject());
            msg.setContent(emailRequestVo.getMessage(), "text/html");
            msg.setSentDate(new Date());
            return msg;

        }catch (MessagingException ex){
            ex.printStackTrace();
        }
        return null;

    }

    public StringBuffer getCalenderInviteCode() {
        StringBuffer sb = new StringBuffer();

        return sb.append("BEGIN:VCALENDAR\n" +
                "PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\n" +
                "VERSION:2.0\n" +
                "METHOD:REQUEST\n" +
                "BEGIN:VEVENT\n" +
                "ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:xxx@xx.com\n" +
                "ORGANIZER:MAILTO:xx@xx.com\n" +
                "DTSTART:20180922T053000Z\n" +
                "DTEND:20180927T060000Z\n" +
                "LOCATION:Conference room\n" +
                "TRANSP:OPAQUE\n" +
                "SEQUENCE:0\n" +
                "UID:040000008200E00074C5B7101A82E00800000000002FF466CE3AC5010000000000000000100\n" +
                " 000004377FE5C37984842BF9440448399EB02\n" +
                "DTSTAMP:20180922T120102Z\n" +
                "CATEGORIES:Meeting\n" +
                "DESCRIPTION:This the description of the meeting.\n\n" +
                "SUMMARY:Test meeting request\n" +
                "PRIORITY:5\n" +
                "CLASS:PUBLIC\n" +
                "BEGIN:VALARM\n" +
                "TRIGGER:PT1440M\n" +
                "ACTION:DISPLAY\n" +
                "DESCRIPTION:Reminder\n" +
                "END:VALARM\n" +
                "END:VEVENT\n" +
                "END:VCALENDAR");
    }

}
