package com.expleo.webcm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Contains default message to reset a password sent by mail.
 * */

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Compose the mail and send it to the recipient.
     *
     *  @param token is got from database and drive the application to the right user.
     *  @param mail is the email address of the recipient.
     *  @param name is the name of the person who will get this mail.
     * */
    public void sendMail(String token, String mail, String name){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try{
            mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setTo(mail);

            mimeMessageHelper.setFrom("ovidiu-marian.milea@expleogroup.com");
            mimeMessageHelper.setSubject("Reset password");
            mimeMessageHelper.setText("<html><body>Salut, "+name+"!<br/><a href='http://10.140.16.47/forgotPassword/newPassword?token="+token+"'>" +
                    " Apasa aici</a> pentru introduce o noua parola.</body></html>", true);
            javaMailSender.send(message);

        }catch (MessagingException e){
            System.out.println("Error sending mail: " + e.getMessage());
        }
    }
}
