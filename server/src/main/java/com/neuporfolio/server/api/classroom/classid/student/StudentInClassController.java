package com.neuporfolio.server.api.classroom.classid.student;

import com.github.pagehelper.PageInfo;
import com.neuporfolio.server.api.ComFailureForm;
import com.neuporfolio.server.api.ObjectPagerForm;
import com.neuporfolio.server.domain.Classroom;
import com.neuporfolio.server.domain.Major;
import com.neuporfolio.server.domain.Student;
import com.neuporfolio.server.service.ClassroomService;
import com.neuporfolio.server.service.MajorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/api/class/{classid}/student")
public class StudentInClassController {

    @Resource
    ClassroomService classroomService;

    @Resource
    MajorService majorService;

    /***
     * {
     *     "class":19001,
     *     "major":{
     *         "id":1,
     *         "name":"计算机科学与技术"
     *     },
     *     "student":{
     *         "list":[
     *             {
     *                 "id":1,
     *                 "name":"xxx"
     *             }
     *         ],
     *         "page_info":{
     *             "current_page": 1,
     *             "current_size": 30,
     *             "has_next_page": false,
     *             "has_prev_page": false
     *         }
     *     }
     * }
     */
    @GetMapping
    public ResponseEntity<?> getStudentInfo(@PathVariable(name = "classid") Integer classid,
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
        获取学生信息
         */
        List<Student> list = classroomService.findStudentByClassidAndPager(classid, page, pagesize);
        /*
        获取专业
         */
        Major major = majorService.findById(classroom.getMajorId());
        /*
        pageinfo
         */
        PageInfo<Student> pageInfo = new PageInfo<Student>(list);
        ObjectPagerForm objectPagerForm = new ObjectPagerForm(200, "student", list, pageInfo);
        objectPagerForm.put("class", classroom.getName());
        objectPagerForm.put("major", major);
        return objectPagerForm.toResponseEntity();
    }
}
