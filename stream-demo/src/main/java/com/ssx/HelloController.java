package com.ssx;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by SSX on 2017/8/14.
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "hello world";
    }
}
