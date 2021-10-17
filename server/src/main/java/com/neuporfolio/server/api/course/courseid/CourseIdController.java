package com.neuporfolio.server.api.course.courseid;


import com.alibaba.fastjson.JSONObject;
import com.neuporfolio.server.domain.Course;
import com.neuporfolio.server.service.CourseService;
import com.neuporfolio.server.utils.AuthChecker;
import com.neuporfolio.server.utils.formformat.ComFailureForm;
import com.neuporfolio.server.utils.formformat.ComForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/course/{courseid}")
public class CourseIdController {

    @Resource
    AuthChecker authChecker;

    @Resource
    CourseService courseService;

    /***
     * {
     *     "teacherid":1,
     *     "majorid":1,
     *     "imagelink":"http://imageLink",
     *     "link":"http://baidu.com",
     *     "classid":[1,2,3] //可选
     * }
     */
    @PatchMapping
    public ResponseEntity<?> editCourse(@RequestBody JSONObject json,
                                        @PathVariable(name = "courseid") int courseId) {
        /*
        未登录返回401
        {
            "detail":{
            "msg":"未登录！"
             }
           }
         */
        if (!authChecker.isAdminAuth(SecurityContextHolder.getContext().getAuthentication())) {
            return new ComFailureForm(403, "权限不足").toResponseEntity();
        }

        Integer teacherId = (Integer) json.get("teahcerid");
        Integer majorId = (Integer) json.get("majorid");
        String imageLink = (String) json.get("imagelink");
        String link = (String) json.get("link");
        List<Integer> classList = json.getJSONArray("classid").toJavaList(Integer.class);

        /*
        获取course
         */
        Course course = courseService.findById(courseId);
        /*
        course 不存在
         */
        if (course == null) {
            return new ComFailureForm(-2, "课程不存在").toResponseEntity();
        }
        if (teacherId != null) {
            List<Integer> list = new ArrayList<>();
            list.add(teacherId);
            courseService.editTeacher(courseId, list);
        }
        if (majorId != null) {
            course.setMajorId(majorId);
        }
        if (imageLink != null) {
            course.setImg(imageLink);
        }
        if (link != null) {
            course.setLink(link);
        }
        if (!classList.isEmpty()) {
            courseService.editClass(courseId, classList);
        }
        return new ComForm(200).toResponseEntity();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCourse(@PathVariable(name = "courseid") int courseId) {
        {
        /*
        未登录返回401
        {
            "detail":{
            "msg":"未登录！"
             }
           }
         */
            if (!authChecker.isAdminAuth(SecurityContextHolder.getContext().getAuthentication())) {
                return new ComFailureForm(403, "权限不足").toResponseEntity();
            }
        /*
        删除course
         */
            courseService.delete(courseId);
            return new ComForm(200).toResponseEntity();
        }
    }
}
