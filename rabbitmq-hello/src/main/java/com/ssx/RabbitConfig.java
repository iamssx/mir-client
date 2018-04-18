package com.ssx;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by SSX on 2017/8/10.
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue() {
        return new Queue("hello");
    }
}
