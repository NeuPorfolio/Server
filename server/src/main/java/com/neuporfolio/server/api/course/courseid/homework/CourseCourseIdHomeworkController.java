package com.neuporfolio.server.api.course.courseid.homework;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.neuporfolio.server.domain.*;
import com.neuporfolio.server.service.CourseService;
import com.neuporfolio.server.service.HomeworkService;
import com.neuporfolio.server.service.TeacherService;
import com.neuporfolio.server.utils.AuthChecker;
import com.neuporfolio.server.utils.formformat.ComFailureForm;
import com.neuporfolio.server.utils.formformat.ComForm;
import com.neuporfolio.server.utils.formformat.PagerForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api/course/{courseid}/homework")
public class CourseCourseIdHomeworkController {

    @Resource
    HttpSession session;

    @Resource
    AuthChecker authChecker;

    @Resource
    CourseService courseService;

    @Resource
    TeacherService teacherService;

    @Resource
    HomeworkService homeworkService;

    @PostMapping
    public ResponseEntity<?> addHomeword(@PathVariable(name = "courseid") Integer courseId,
                                         @RequestBody JSONObject json) {
        if (!authChecker.isTeaAuth(SecurityContextHolder.getContext().getAuthentication())) {
            return new ComFailureForm(403, "权限不足").toResponseEntity();
        }
        Course course = courseService.getById(courseId);
        if (course == null) {
            return new ComFailureForm(403, "信息错误").toResponseEntity();
        }
        Integer teacherId = (Integer) session.getAttribute("uid");
        if (teacherId == null) {
            return new ComFailureForm(401, "session丢失").toResponseEntity();
        }
        Teacher teacher = teacherService.getById(teacherId);
        /*
        teacher不属于这个课程
         */
        if (!courseService.getTeacher(courseId).contains(teacher)) {
            return new ComFailureForm(403, "没有权限").toResponseEntity();
        }
        Classroom classroom = courseService.getClassroom(courseId);
        String title = json.getString("title");
        String detail = json.getString("detail");
        Homework homework = new Homework();
        homework.setTeacherId(teacherId);
        homework.setTime(new Date());
        homework.setCourseId(courseId);
        homework.setDetail(detail);
        homework.setTitle(title);
        homework.setClassId(classroom.getId());
        homework.setClassName(classroom.getName());

        Integer homeworkId = homeworkService.add(homework);
        ComForm comForm = new ComForm(200);
        comForm.put("id", homeworkId);
        return comForm.toResponseEntity();
    }

    @GetMapping
    public ResponseEntity<?> getHomeworkList(@PathVariable(name = "courseid") Integer courseId
            , @RequestParam(name = "page", defaultValue = "1") Integer page,
                                             @RequestParam(name = "pagesize", defaultValue = "30") Integer pagesize) {
        Object srvObj;
        if (authChecker.isTeaAuth(SecurityContextHolder.getContext().getAuthentication())) {
            srvObj = new Teacher();
        } else if (authChecker.isStuAuth(SecurityContextHolder.getContext().getAuthentication())) {
            srvObj = new Student();
        } else {
            return new ComFailureForm(403, "权限不足").toResponseEntity();
        }
        List<Homework> list;
        if (srvObj instanceof Teacher) {
            list = homeworkService.getByCourseIdAndPager(courseId, page, pagesize);
        } else {
            Integer studentId = (Integer) session.getAttribute("uid");
            if (studentId == null) {
                return new ComFailureForm(403, "session丢失").toResponseEntity();
            }
            list = homeworkService.getByCourseIdAndPager(courseId, page, pagesize);
        }


        List<SimplyHomework> simplyHomeworks = new ArrayList<>();
        for (Homework i : list) {
            simplyHomeworks.add(new SimplyHomework(i));
        }
        PageInfo<Homework> pageInfo = new PageInfo<>(list);
        return new PagerForm(200, simplyHomeworks, pageInfo).toResponseEntity();
    }
}
