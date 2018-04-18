package com.netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by 37 on 2017/7/21.
 */
//@Component
public class StartEvent implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        SpringApplication springApplication = event.getSpringApplication();
        springApplication.setBanner(null);
    }
}
