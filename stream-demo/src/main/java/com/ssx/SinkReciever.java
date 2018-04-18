package com.ssx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.Objects;

/**
 * Created by SSX on 2017/8/11.
 */
@Slf4j
@EnableBinding(Sink.class)
public class SinkReciever {

    @StreamListener(Sink.INPUT)
    public void receive(Objects payload) {
        log.info("receive message: {}", payload);
    }
}
