package com.neuporfolio.server.api.major.majorid.student;

import com.github.pagehelper.PageInfo;
import com.neuporfolio.server.api.ComFailureForm;
import com.neuporfolio.server.api.ObjectPagerForm;
import com.neuporfolio.server.domain.Student;
import com.neuporfolio.server.service.MajorService;
import com.neuporfolio.server.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/api/major/{majorid}/student")
public class MajorStudentController {

    @Resource
    StudentService studentService;

    @Resource
    MajorService majorService;

    @GetMapping
    public ResponseEntity<?> getStudentDetail(@PathVariable(name = "majorid") Integer majorid,
                                              @RequestParam(name = "page", defaultValue = "1") Integer page,
                                              @RequestParam(name = "pagesize", defaultValue = "30") Integer pagesize) {
        /*
        未登录返回401
        {
    "detail":{
        "msg":"未登录！"
    }
}
         */
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return new ComFailureForm(401, "未登录").toResponseEntity();
        }

        /*
        获取majorname
         */
        String majorname = majorService.findById(majorid).getName();
        /*
        获取student
         */
        List<Student> list = studentService.findByMajorIdAndPager(majorid, page, pagesize);
        /*
        获取pageinfo
         */
        PageInfo<Student> pageInfo = new PageInfo<>(list);

        ObjectPagerForm objectPagerForm = new ObjectPagerForm(200, "student", list, pageInfo);
        objectPagerForm.put("name", majorname);
        return objectPagerForm.toResponseEntity();
    }

}
