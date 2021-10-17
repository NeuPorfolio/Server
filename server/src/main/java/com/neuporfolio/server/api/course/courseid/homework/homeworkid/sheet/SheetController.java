package com.neuporfolio.server.api.course.courseid.homework.homeworkid.sheet;

import com.alibaba.fastjson.JSONObject;
import com.neuporfolio.server.domain.Sheet;
import com.neuporfolio.server.domain.Student;
import com.neuporfolio.server.domain.Teacher;
import com.neuporfolio.server.service.SheetService;
import com.neuporfolio.server.utils.AuthChecker;
import com.neuporfolio.server.utils.formformat.ComFailureForm;
import com.neuporfolio.server.utils.formformat.ComForm;
import com.neuporfolio.server.utils.formformat.ListForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/*
{{baseurl}}/api/course/{courseid}/homework/{homeworkid}/sheet
 */
@RestController
@RequestMapping(path = "/api/course/{courseid}/homework/{homeworkid}/sheet")
public class SheetController {


    @Resource
    SheetService sheetService;

    @Resource
    AuthChecker authChecker;

    @Resource
    HttpSession session;

    @PostMapping
    public ResponseEntity<?> addSheet(@PathVariable(name = "courseid") Integer courseId,
                                      @PathVariable(name = "homeworkid") Integer homeworkId,
                                      @RequestBody JSONObject json
    ) {
        if (!authChecker.isStuAuth(SecurityContextHolder.getContext().getAuthentication())) {
            return new ComFailureForm(403, "没有权限").toResponseEntity();
        }
        Integer studentId = (Integer) session.getAttribute("uid");

        Sheet sheet = new Sheet();
        sheet.setDetail(json.getString("detail"));
        sheet.setStudentId(studentId);
        sheet.setHomeworkId(homeworkId);
        sheet.setMark(false);
        sheet.setImgs(json.getString("imagelink"));
        sheet.setFiles(json.getString("filelink"));
        sheet.setTime(new Date());
        ComForm comForm = new ComForm(200);
        comForm.put("id", sheetService.build(sheet));
        return comForm.toResponseEntity();
    }

    @GetMapping
    public ResponseEntity<?> getSheetList(@PathVariable(name = "courseid") Integer courseId,
                                          @PathVariable(name = "homeworkid") Integer homeworkId,
                                          @RequestBody JSONObject json
    ) {
        Object srvObj;
        if (authChecker.isTeaAuth(SecurityContextHolder.getContext().getAuthentication())) {
            srvObj = new Teacher();
        } else if (authChecker.isStuAuth(SecurityContextHolder.getContext().getAuthentication())) {
            srvObj = new Student();
        } else {
            return new ComFailureForm(403, "权限不足").toResponseEntity();
        }
        List<Sheet> list = sheetService.getByHomeworkId(homeworkId);
        return new ListForm(200, list).toResponseEntity();
    }
}
