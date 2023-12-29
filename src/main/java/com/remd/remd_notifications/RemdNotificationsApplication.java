package com.remd.remd_notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class RemdNotificationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemdNotificationsApplication.class, args);
    }

}
