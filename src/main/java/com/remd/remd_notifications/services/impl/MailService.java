package com.remd.remd_notifications.services.impl;

import com.remd.remd_notifications.services.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MailService implements IMailService {
    public static final String NEW_USER_ACCOUNT = "New User Account";
    public static final String SEND_TOKEN = "Token For Reset Password";
    public static final String TROUVE = "Objet Retrouvé";
    public static final String POTENTIELLEMENT_TROUVE = "Objet potentiellement retrouvé";
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

    public static String __getEmailMessageRetrouve(String name){
        Date date = new Date();
        return "Bonjour " + name + "," +"\n\n Nous constatons avec plaisir que votre objet a été retrouvé. Vous l'avez signalé le "+ date.getDay() + date.getMonth()
        + date.getYear() + " à " + date.getHours() +":"+date.getMinutes() +"\n\n" + "Nous sommes heureux que notre application vous est utile et vous remercions pour votre collaboration. \n\n" +"Cordialement," + "\n\nThe support Team";
    }

    public static String __getEmailMessagePotientielementRetrouve(String name, String nomRetrouveur, String numberRetrouve, String mailRetrouveur){
        return "Bonjour, " + name + "," + "\n\n Nous vous signalons qu'un utilisateur (Mr/Mme )" + nomRetrouveur + " a potentiellement retrouvé votre document.\n" +
                "Nous vous prions de bien vouloir le contacter au numéro de téléphone +(237)" + numberRetrouve + " ou à l'adresse mail: " + mailRetrouveur +
                " et signalez sur l'application si il a été effectivement retrouvé." + "Si l'objet n'est pas retrouvé, nous vous prions de nous le signaler sur cette adresse mail. " +
                "\n\n Cordialement, \n\n" + "The support Team.";
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

    public void sendObjetTrouve(String name, String to){
        SimpleMailMessage message = _sendSimpleMailMessage(name, to);
        message.setSubject(TROUVE);
        message.setText(__getEmailMessageRetrouve(name));
        emailSender.send(message);
    }
    public void sendObjetPotentiellementRetrouve(String name, String to, String nomRetrouveur, String numberRetrouveur, String mailRetrouveur){
        SimpleMailMessage message = _sendSimpleMailMessage(name,to);
        message.setSubject(POTENTIELLEMENT_TROUVE);
        message.setText(__getEmailMessagePotientielementRetrouve(name,nomRetrouveur,numberRetrouveur, mailRetrouveur));
        emailSender.send(message);
    }

}
