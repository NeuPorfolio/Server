package com.neuporfolio.server.api.course.courseid.homework.homeworkid.sheet.sheetid.mark;

import com.neuporfolio.server.domain.Sheet;
import com.neuporfolio.server.service.SheetService;
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
{{baseurl}}/api/course/{courseid}/homework/{homeworkid}/mark
 */
@RestController
@RequestMapping(path = "/api/course/{courseid}/homework/{homeworkid}/sheet/{sheetid}")
public class MarkController {

    @Resource
    SheetService sheetService;

    @GetMapping
    public ResponseEntity<?> markS(@PathVariable(name = "courseid") Integer courseId,
                                   @PathVariable(name = "homeworkid") Integer homeworkId,
                                   @PathVariable(name = "sheetid") Integer sheetId
    ) {

        Sheet sheet = sheetService.getById(sheetId);
        if (sheet == null || !Objects.equals(sheet.getHomeworkId(), homeworkId)) {
            return new ComFailureForm(403, "提交了错误信息").toResponseEntity();
        }
        return new ObjectForm(sheet).toResponseEntity();
    }
}
