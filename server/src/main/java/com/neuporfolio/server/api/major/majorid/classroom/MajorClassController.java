package com.neuporfolio.server.api.major.majorid.classroom;

import com.github.pagehelper.PageInfo;
import com.neuporfolio.server.api.ComFailureForm;
import com.neuporfolio.server.api.ObjectPagerForm;
import com.neuporfolio.server.domain.Classroom;
import com.neuporfolio.server.service.ClassroomService;
import com.neuporfolio.server.service.MajorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/api/major/{majorid}/class")
public class MajorClassController {

    @Resource
    ClassroomService classroomService;

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
        获取classroom
         */
        List<Classroom> list = classroomService.findByMajorIdAndPager(majorid, page, pagesize);
        /*
        获取pageinfo
         */
        PageInfo<Classroom> pageInfo = new PageInfo<>(list);

        ObjectPagerForm objectPagerForm = new ObjectPagerForm(200, "class", list, pageInfo);
        objectPagerForm.put("name", majorname);
        return objectPagerForm.toResponseEntity();
    }

}
