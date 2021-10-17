package com.neuporfolio.server.betatestcomps;

import com.neuporfolio.server.domain.Question;
import com.neuporfolio.server.mapper.QuestionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/*
测试用类
*/
@Slf4j
@RestController
@RequestMapping(path = "/test")
public class HelloController {

    @Resource
    QuestionMapper questionMapper;

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
        log.debug("debug");
        return m;
    }

    @ResponseBody
    @GetMapping(path = "/mybatis")
    public Question mybatis() {
        log.debug("mybatisTest");
        return questionMapper.findByCourseId(1).get(0);
    }


    public class TestForm {
        public int a = 1;
        public int b = 2;
    }
}
