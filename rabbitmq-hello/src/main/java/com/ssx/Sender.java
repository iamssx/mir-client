package com.ssx;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by SSX on 2017/8/10.
 */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void hello() {
        String msg = "hello " + new Date().toString();
        System.out.println(msg);
        amqpTemplate.convertAndSend("hello", msg);
    }
}
