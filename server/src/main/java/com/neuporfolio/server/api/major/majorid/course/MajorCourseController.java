package com.neuporfolio.server.api.major.majorid.course;

import com.github.pagehelper.PageInfo;
import com.neuporfolio.server.domain.Course;
import com.neuporfolio.server.service.CourseService;
import com.neuporfolio.server.service.MajorService;
import com.neuporfolio.server.utils.formformat.ComFailureForm;
import com.neuporfolio.server.utils.formformat.ObjectPagerForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/api/major/{majorid}/course")
public class MajorCourseController {

    @Resource
    CourseService courseService;

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
        List<Course> list = courseService.findByMajorIdAndPager(majorid, page, pagesize);
        /*
        获取pageinfo
         */
        PageInfo<Course> pageInfo = new PageInfo<>(list);

        ObjectPagerForm objectPagerForm = new ObjectPagerForm(200, "course", list, pageInfo);
        objectPagerForm.put("name", majorname);
        return objectPagerForm.toResponseEntity();
    }
}
