package com.neuporfolio.server.api.classroom.classid.course;

import com.github.pagehelper.PageInfo;
import com.neuporfolio.server.domain.Classroom;
import com.neuporfolio.server.domain.Course;
import com.neuporfolio.server.domain.Major;
import com.neuporfolio.server.service.ClassroomService;
import com.neuporfolio.server.service.MajorService;
import com.neuporfolio.server.utils.formformat.ComFailureForm;
import com.neuporfolio.server.utils.formformat.ObjectPagerForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/api/class/{classid}/course")
public class CourseInClassController {

    @Resource
    ClassroomService classroomService;

    @Resource
    MajorService majorService;

    @GetMapping
    ResponseEntity<?> getCourseInfo(@PathVariable(name = "classid") Integer classid,
                                    @RequestParam(name = "page", defaultValue = "1") Integer page,
                                    @RequestParam(name = "pagesize", defaultValue = "30") Integer pagesize) {
        /*
        获取教室
         */
        Classroom classroom = classroomService.findById(classid);
        if (classroom == null) {
            return new ComFailureForm(-1, "班级不存在").toResponseEntity();
        }
        /*
        获取课程信息
         */
        List<Course> list = classroomService.findCourseByClassidAndPager(classid, page, pagesize);
        /*
        获取专业
         */
        Major major = majorService.findById(classroom.getMajorId());
        /*
        pageinfo
         */
        PageInfo<Course> pageInfo = new PageInfo<>(list);
        ObjectPagerForm objectPagerForm = new ObjectPagerForm(200, "course", list, pageInfo);
        objectPagerForm.put("class", classroom.getName());
        objectPagerForm.put("major", major);
        return objectPagerForm.toResponseEntity();
    }
}
