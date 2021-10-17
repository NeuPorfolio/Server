package com.neuporfolio.server.api.teacher;

import com.github.pagehelper.PageInfo;
import com.neuporfolio.server.domain.Teacher;
import com.neuporfolio.server.service.TeacherService;
import com.neuporfolio.server.utils.AuthChecker;
import com.neuporfolio.server.utils.formformat.ComFailureForm;
import com.neuporfolio.server.utils.formformat.PagerForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

//{{baseurl}}/api/teacher?page=1&pagesize=30
@RestController
@RequestMapping(path = "/api/teacher")
public class TeacherController {

    @Resource
    TeacherService teacherService;

    @Resource
    AuthChecker authChecker;

    /***
     * {
     *     "list": [
     *         {
     *             "id": 1,
     *             "name": "xxx"
     *         }
     *     ],
     *     "page_info": {
     *         "current_page": 1,
     *         "current_size": 30,
     *         "has_next_page": false,
     *         "has_prev_page": false
     *     }
     * }
     */
    @GetMapping
    public ResponseEntity<?> getTeacherList(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                            @RequestParam(name = "pagesize", defaultValue = "30") Integer size) {
        if (!authChecker.isAdminAuth(SecurityContextHolder.getContext().getAuthentication())) {
            return new ComFailureForm(403, "权限不足").toResponseEntity();
        }
        List<Teacher> list = teacherService.findByPager(page, size);
        PageInfo<Teacher> pageInfo = new PageInfo<>(list);
        return new PagerForm(200, list, pageInfo).toResponseEntity();
    }
}
