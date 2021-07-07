package com.neuporfolio.server.api.course;

import com.neuporfolio.server.domain.Courses;
import com.neuporfolio.server.service.CoursesService;
import com.neuporfolio.server.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(path = "/api/course")
public class CourseController {
    @Resource
    CoursesService coursesService;

    @Resource
    HttpSession httpSession;

    @Resource
    UsersService usersService;

    @GetMapping
    public ResponseEntity<CourseGetResponseForm> all(@RequestParam(value = "pageIndex", required = false) Long pageIndex,
                                                     @RequestParam(value = "pageSize", required = false) Long pageSize,
                                                     @RequestParam(value = "subject", required = false) String subject) {
        CourseGetResponseForm courseGetResponseForm;

        if (pageIndex == null)
            pageIndex = Long.valueOf(1);
        if (pageSize == null)
            pageSize = Long.valueOf(10);
        if (subject == null)
            courseGetResponseForm = new CourseGetResponseForm(coursesService.getAll());
        else courseGetResponseForm = new CourseGetResponseForm(coursesService.searchBySubjects(subject));
        return new ResponseEntity<>(courseGetResponseForm, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CoursePostResponseForm> add(CoursePostForm coursePostForm, HttpSession session) {
        CoursePostResponseForm coursePostResponseForm;

        //获取用户信息
        SecurityContextImpl securityContext = (SecurityContextImpl) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        if (null == securityContext) {
            coursePostResponseForm = new CoursePostResponseForm("未验证的用户");
            return new ResponseEntity<>(coursePostResponseForm, HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();

        //检查完整性
        if (coursePostForm.getName() == null || coursePostForm.getSubject() == null) {
            coursePostResponseForm = new CoursePostResponseForm("失败，表单信息不完全");
            return new ResponseEntity<>(coursePostResponseForm, HttpStatus.BAD_REQUEST);
        }

        //补充courses表单
        //sponsor
        coursePostForm.setSponsor(usersService.findByUserName(userDetails.getUsername()).getId());
        Courses courses = coursePostForm.toCourses();

        if (!coursesService.add(courses)) {
            coursePostResponseForm = new CoursePostResponseForm("失败，已经存在该课程");
            return new ResponseEntity<>(coursePostResponseForm, HttpStatus.BAD_REQUEST);
        }

        coursePostResponseForm = new CoursePostResponseForm("添加课程成功", courses);
        return new ResponseEntity<>(coursePostResponseForm, HttpStatus.OK);
    }
}
