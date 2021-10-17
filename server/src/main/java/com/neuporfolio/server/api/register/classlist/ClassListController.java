package com.neuporfolio.server.api.register.classlist;

import com.neuporfolio.server.domain.Classroom;
import com.neuporfolio.server.service.ClassroomService;
import com.neuporfolio.server.utils.formformat.ComForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/***
 * 获取班级列表
 */

@Slf4j
@RestController
@RequestMapping(path = "/api/register/classlist")
public class ClassListController {
    @Resource
    ClassroomService classroomService;

    /***
     * 获取班级列表
     * @param id 专业id
     * @return 列表，包含所有班级对象
     */
    @GetMapping
    public ResponseEntity<?> getClassListById(@RequestParam("major") Integer id) {
        ClassListForm classListForm = new ClassListForm(200, classroomService.findByMajorId(id));
        return classListForm.toResponseEntity();
    }

    /***
     * {
     *     "list":[
     *         {
     *             "id":1,
     *             "class":19001
     *         }
     *     ]
     * }
     */
    private static class ClassListForm extends ComForm {
        public ClassListForm(int code, List<Classroom> list) {
            super(code);
            super.put("list", list);
        }
    }
}
