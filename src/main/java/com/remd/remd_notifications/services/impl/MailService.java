package com.remd.remd_notifications.services.impl;

import com.remd.remd_notifications.services.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService {
    public static final String NEW_USER_ACCOUNT = "New User Account";
    public static final String SEND_TOKEN = "Token For Reset Password";
    @Autowired
    private JavaMailSender emailSender;


    @Value("${spring.mail.username}")
    private String fromEmail;


    @Override
    public SimpleMailMessage _sendSimpleMailMessage(String name, String to) {
        SimpleMailMessage message;
        try {
            message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
        return message;
    }


    public static String __getEmailMessageNewAccount(String name) {
        return "Hello " + name + ",\n\nYour new account has been created. Thanks for using our application. \n\n" +
                "\n\nThe support Team";
    }

    public static String __getEmailMessageToken(String name, String token) {
        return "Hello " + name + ",\n\n A new token to reset your password has been created. The Token is : " + token  + " Please confirm to change your password account. \n\n" +
                 "\n\nThe support Team";
    }

    public void sendNewUserAccount(String name, String to){
        SimpleMailMessage message = _sendSimpleMailMessage(name, to);
        message.setSubject(NEW_USER_ACCOUNT);
        message.setText(__getEmailMessageNewAccount(name));
        emailSender.send(message);
    }

    public void sendToken(String name, String to, String token){
        SimpleMailMessage message = _sendSimpleMailMessage(name, to);
        message.setSubject(SEND_TOKEN);
        message.setText(__getEmailMessageToken(name,token));
        emailSender.send(message);
    }


}
