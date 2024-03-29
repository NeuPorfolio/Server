package com.neuporfolio.server.api.classroom;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.neuporfolio.server.domain.Classroom;
import com.neuporfolio.server.service.ClassroomService;
import com.neuporfolio.server.utils.AuthChecker;
import com.neuporfolio.server.utils.formformat.ComFailureForm;
import com.neuporfolio.server.utils.formformat.ComForm;
import com.neuporfolio.server.utils.formformat.PagerForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/class")
public class ClassController {

    @Resource
    ClassroomService classroomService;

    @Resource
    AuthChecker authChecker;

    @GetMapping
    public ResponseEntity<?> getClassList(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                          @RequestParam(name = "pagesize", defaultValue = "30") Integer pagesize) {
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
        获取classroom
         */
        List<Classroom> list = classroomService.getAllByPager(page, pagesize);
        /*
        获取pageinfo
         */
        PageInfo<Classroom> pageInfo = new PageInfo<>(list);
        return new PagerForm(200, list, pageInfo).toResponseEntity();
    }

    /***
     * {
     *     "class":19001,
     *     "majorid":1,
     *     "courseid":1,
     *     "teacherid":1,
     *     "studentid":[1,2,3]
     * }
     */
    @PostMapping
    public ResponseEntity<?> addClass(@RequestBody JSONObject json) {
        String classname = (String) json.get("class");
        Integer majorid = (Integer) json.get("majorid");
        /*
        转换成Array
         */
        @SuppressWarnings("SuspiciousToArrayCall") Integer[] courseid = json.getJSONArray("courseid").toArray(new Integer[0]);
        @SuppressWarnings("SuspiciousToArrayCall") Integer[] students = json.getJSONArray("studentid").toArray(new Integer[0]);
        /*
        表单检查
         */
        if (classname == null || majorid == null) {
            return new ComFailureForm(-1, "表单信息不全").toResponseEntity();
        }
        /*
        新建班级
         */
        int classid = classroomService.buildClass(classname, majorid);
        /*
        添加课程
         */
        classroomService.editCourse(classid, new ArrayList<>(Arrays.asList(courseid)));

        /*
        添加学生
         */
        classroomService.editStu(classid, new ArrayList<>(Arrays.asList(students)));

        /*
        响应体
         */
        ComForm comForm = new ComForm(200);
        comForm.put("id", classid);
        return comForm.toResponseEntity();
    }
}
