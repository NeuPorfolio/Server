package com.neuporfolio.server;

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
        Date t=new Date();
        return t.toString();
    }
}
