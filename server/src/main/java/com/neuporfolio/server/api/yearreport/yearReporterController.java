package com.neuporfolio.server.api.yearreport;


import com.neuporfolio.server.utils.AuthChecker;
import com.neuporfolio.server.utils.formformat.ComFailureForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/yearreport")
public class yearReporterController {

    @Resource
    AuthChecker authChecker;

    @GetMapping
    public ResponseEntity<?> get() {
        /***
         * 接口测试::
         */
        List<String> list = new ArrayList<>();
        if (authChecker.isTeaAuth(SecurityContextHolder.getContext().getAuthentication())) {
            list.add("你在2020年9月来到了这个平台");
            list.add("你已学习 1000 小时了");
            list.add("你已经向老师请教了 60 个问题");
            list.add("高数这门课是你问题最多的课，看来高数给你带来了很多的困扰呢");
            list.add("英语这门课是你问题最少的课，都已经全部掌握了么？");
            list.add("你累计点击学习C语言课程最多次,看来对这么课很感兴趣呀");
            list.add("你已经完成了 60 份作业啦");
            list.add("你已经。。。");
            list.add("真是勤学好问的好学生！");
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        if (authChecker.isStuAuth(SecurityContextHolder.getContext().getAuthentication())) {
            list.add("您在2020年9月来到了这个平台");
            list.add("您已工作 1000 小时了");
            list.add("您已经给学生们回答了 60 个问题");
            list.add("19001班的www提问次数最多，真是一个勤学好问的学生！");
            list.add("19001班是问题最多的班，看来这个班比较好问呢");
            list.add("19002班是问题最少的班，是都掌握了还是学生们不喜欢问问题呢？");
            list.add("19001班点击学习课程次数是最多的");
            list.add("你已经批改了 60 份作业啦");
            list.add("老师辛苦啦了！！");
            return new ResponseEntity<>(list, HttpStatus.OK);
        }

        return new ComFailureForm(403, "没有权限").toResponseEntity();
    }

}
