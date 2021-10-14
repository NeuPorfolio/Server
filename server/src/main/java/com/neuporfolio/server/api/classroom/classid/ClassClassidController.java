package com.neuporfolio.server.api.classroom.classid;


import com.alibaba.fastjson.JSONObject;
import com.neuporfolio.server.Utils.ConstValues;
import com.neuporfolio.server.api.ComFailureForm;
import com.neuporfolio.server.api.ComForm;
import com.neuporfolio.server.domain.Classroom;
import com.neuporfolio.server.domain.Major;
import com.neuporfolio.server.domain.User;
import com.neuporfolio.server.service.ClassroomService;
import com.neuporfolio.server.service.MajorService;
import com.neuporfolio.server.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/class/{classid}")
public class ClassClassidController {
    @Resource
    UserService userService;

    @Resource
    ClassroomService classroomService;

    @Resource
    MajorService majorService;

    /***
     * 编辑班级信息，根据参数有无，增减成员
     *     {
     *         "class":19001,
     *         "majorid":1,
     *         "courseid":1,
     *         "teacherid":1,
     *         "studentid":[1,2,3]
     *     }
     */
    @DeleteMapping
    public ResponseEntity<?> deleteClass(@PathVariable(name = "classid") Integer classid) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(((User) userService.loadUserByUsername(username)).getRole(), ConstValues.roleAdminParameter)) {
            return new ComFailureForm(403, "权限不足").toResponseEntity();
        }
        /*
        获取classroom
         */
        Classroom classroom = classroomService.findById(classid);
        /*
        class 不存在
         */
        if (classroom == null) {
            return new ComFailureForm(-2, "班级不存在").toResponseEntity();
        }

        /*
        删除class
         */
        classroomService.deleteClass(classid);

        return new ComForm(200).toResponseEntity();
    }

    @PatchMapping
    public ResponseEntity<?> editClass(@RequestBody JSONObject json,
                                       @PathVariable(name = "classid") Integer classid) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(((User) userService.loadUserByUsername(username)).getRole(), ConstValues.roleAdminParameter)) {
            return new ComFailureForm(403, "权限不足").toResponseEntity();
        }
        /*
        获取classroom
         */
        Classroom classroom = classroomService.findById(classid);
        /*
        class 不存在
         */
        if (classroom == null) {
            return new ComFailureForm(-2, "班级不存在").toResponseEntity();
        }
        String classname = (String) json.get("class");
        if (classname != null) {
            classroom.setName(classname);
        }
        Integer majorid = (Integer) json.get("majorid");
        if (majorid != null) {
            classroom.setMajorId(majorid);
            /*
            需要majorname
             */
            Major major = majorService.findById(majorid);
            if (major == null)
                return new ComFailureForm(-1, "majorid错误").toResponseEntity();
            String majorname = major.getName();
            classroom.setMajorName(majorname);
        }
        /*
        转换成Array
         */
        @SuppressWarnings("SuspiciousToArrayCall") Integer[] courseid = json.getJSONArray("courseid").toArray(new Integer[0]);
        @SuppressWarnings("SuspiciousToArrayCall") Integer[] students = json.getJSONArray("studentid").toArray(new Integer[0]);
        /*
        添加课程
         */
        if (courseid.length > 0)
            classroomService.editCourse(classid, new ArrayList<>(Arrays.asList(courseid)));

        /*
        添加学生
         */
        if (students.length > 0)
            classroomService.editStu(classid, new ArrayList<>(Arrays.asList(students)));
        return new ComForm(200).toResponseEntity();
    }
}
