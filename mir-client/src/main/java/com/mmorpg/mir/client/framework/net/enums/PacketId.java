package com.mmorpg.mir.client.framework.net.enums;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PacketId {

    short value();
}
