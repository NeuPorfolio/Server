package com.neuporfolio.server.viewpagecontrollers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/*
测试用类
*/
@RestController
public class HelloController {

    @RequestMapping("/servertime")
    public String hello(){
        return new Date().toString();
    }
}
