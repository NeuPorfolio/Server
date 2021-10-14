package com.neuporfolio.server.betatestcomps;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/*
测试用类
*/
@RestController
@RequestMapping(path = "/test")
public class HelloController {

    @GetMapping(path = "/list")
    public Map<String, Object> getList() {
        Map<String, Object> m = new HashMap<>();

        List<TestForm> list = new ArrayList<>();

        list.add(new TestForm());
        m.put("list", list);
        return m;
    }

    @GetMapping(path = "/servertime")
    public Map<String, Object> getServerTime() {
        Map<String, Object> m = new HashMap<>();
        m.put("servertime", new Date().toString());
        return m;
    }

    public class TestForm {
        public int a = 1;
        public int b = 2;
    }
}
