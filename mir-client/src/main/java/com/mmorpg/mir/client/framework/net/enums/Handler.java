package com.mmorpg.mir.client.framework.net.enums;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Component
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Handler {
}
