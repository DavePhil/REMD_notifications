package com.remd.remd_notifications.Controller;

import com.remd.remd_notifications.services.impl.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notification/")
public class MailController {
    @Autowired
    private MailService mailService;
    @PostMapping("newAccount/{name}/{to}")
    public void newAccount(@PathVariable("name") String name, @PathVariable("to") String to){
     mailService.sendNewUserAccount(name, to);
    }

    @PostMapping("sendToken/{name}/{to}/{token}")
    public void sendToken(@PathVariable("name") String name, @PathVariable("to") String to, @PathVariable("token") String token){
        mailService.sendToken(name, to, token);
    }
}
