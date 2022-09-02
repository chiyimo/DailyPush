package com.daily.daily_push;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DailyPushApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyPushApplication.class, args);
    }

}
