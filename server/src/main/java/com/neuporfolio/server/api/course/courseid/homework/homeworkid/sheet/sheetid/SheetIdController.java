package com.neuporfolio.server.api.course.courseid.homework.homeworkid.sheet.sheetid.mark;

import com.neuporfolio.server.domain.Sheet;
import com.neuporfolio.server.service.SheetService;
import com.neuporfolio.server.utils.AuthChecker;
import com.neuporfolio.server.utils.formformat.ComFailureForm;
import com.neuporfolio.server.utils.formformat.ComForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/*
{{baseurl}}/api/course/{courseid}/homework/{homeworkid}
 */
@RestController
@RequestMapping(path = "/api/course/{courseid}/homework/{homeworkid}/sheet/{sheetid}")
public class SheetIdController {

    @Resource
    SheetService sheetService;

    @Resource
    AuthChecker authChecker;

    @PostMapping
    public ResponseEntity<?> getSDetail(@PathVariable(name = "courseid") Integer courseId,
                                        @PathVariable(name = "homeworkid") Integer homeworkId,
                                        @PathVariable(name = "sheetid") Integer sheetId
    ) {
        if (authChecker.isTeaAuth(SecurityContextHolder.getContext().getAuthentication())) {
            return new ComFailureForm(403, "权限不足").toResponseEntity();
        }
        Sheet sheet = sheetService.getById(sheetId);
        if (sheet == null || !Objects.equals(sheet.getHomeworkId(), homeworkId)) {
            return new ComFailureForm(403, "提交了错误信息").toResponseEntity();
        }
        sheet.setMark(true);
        sheetService.save(sheet);
        return new ComForm(200).toResponseEntity();
    }

}
