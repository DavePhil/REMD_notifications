package com.remd.remd_notifications.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;

public interface IMailService {

    SimpleMailMessage _sendSimpleMailMessage(String name, String to);

}
