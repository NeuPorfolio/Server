package com.neuporfolio.server.api.course;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.neuporfolio.server.domain.Course;
import com.neuporfolio.server.service.CourseService;
import com.neuporfolio.server.utils.AuthChecker;
import com.neuporfolio.server.utils.formformat.ComFailureForm;
import com.neuporfolio.server.utils.formformat.ComForm;
import com.neuporfolio.server.utils.formformat.PagerForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/api/course")
public class CourseController {

    @Resource
    AuthChecker authChecker;

    @Resource
    CourseService courseService;

    /***
     *{{baseurl}}/api/course?page=1&pagesize=30
     */
    @GetMapping
    public ResponseEntity<?> getCourseList(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                           @RequestParam(name = "pagesize", defaultValue = "30") Integer pagesize) {
        /*
        未登录返回401
        {
            "detail":{
            "msg":"未登录！"
             }
           }
         */
        if (authChecker.isAdminAuth(SecurityContextHolder.getContext().getAuthentication())) {
            return new ComFailureForm(403, "权限不足").toResponseEntity();
        }
        /*
        获取course
         */
        List<Course> list = courseService.getAllByPager(page, pagesize);
        /*
        获取pageinfo
         */
        PageInfo<Course> pageInfo = new PageInfo<>(list);
        return new PagerForm(200, list, pageInfo).toResponseEntity();
    }

    /***
     *{
     *     "name":"高数",
     *     "teacherid":1,
     *     "majorid":1,
     *     "classid":[1,2,3],
     *     "image":"https://imageLink",
     *     "link":"https://baidu.com"
     * }
     */
    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody JSONObject json) {

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

        String coursename = (String) json.get("name");
        Integer teacherId = (Integer) json.get("teacherid");
        Integer majorid = (Integer) json.get("majorid");
        List<Integer> classId = json.getJSONArray("classid").toJavaList(Integer.class);
        String image = (String) json.get("image");
        String link = (String) json.get("link");
        /*
        表单检查
         */
        if (coursename == null || teacherId == null || majorid == null || image == null || link == null) {
            return new ComFailureForm(-1, "表单信息不全").toResponseEntity();
        }
        /*
        新建课程
         */
        int courseId = courseService.buildCourse(coursename, majorid, image, link);

        ComForm comForm = new ComForm(200);
        comForm.put("id", courseId);
        return comForm.toResponseEntity();
    }
}
