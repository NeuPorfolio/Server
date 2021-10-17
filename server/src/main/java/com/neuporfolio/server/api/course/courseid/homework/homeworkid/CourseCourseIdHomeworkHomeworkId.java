package com.neuporfolio.server.api.course.courseid.homework.homeworkid;


import com.neuporfolio.server.domain.Homework;
import com.neuporfolio.server.service.HomeworkService;
import com.neuporfolio.server.utils.formformat.ComFailureForm;
import com.neuporfolio.server.utils.formformat.ObjectForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/*
{{baseurl}}/api/course/{courseid}/homework/{homeworkid}
 */
@RestController
@RequestMapping(path = "/api/course/{courseid}/homework/{homeworkid}")
public class CourseCourseIdHomeworkHomeworkId {

    @Resource
    HomeworkService homeworkService;

    @GetMapping
    public ResponseEntity<?> getHDetail(@PathVariable(name = "courseid") Integer courseId,
                                        @PathVariable(name = "homeworkid") Integer homeworkId
    ) {
        Homework homework = homeworkService.getById(homeworkId);
        if (homework == null || !Objects.equals(homework.getCourseId(), courseId)) {
            return new ComFailureForm(403, "提交了错误信息").toResponseEntity();
        }
        return new ObjectForm(homework).toResponseEntity();
    }
}
