package com.neuporfolio.server.api.register.majorlist;

import com.neuporfolio.server.domain.Major;
import com.neuporfolio.server.service.MajorService;
import com.neuporfolio.server.utils.formformat.ComForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/***
 * 获取专业列表
 */

@Slf4j
@RestController
@RequestMapping(path = "/api/register/majorlist")
public class MajorListController {

    @Resource
    MajorService majorService;

    /***
     * 获取所有专业列表
     * @return 列表，包含所有班级对象
     */
    @GetMapping
    public ResponseEntity<?> getAllMajorList() {
        List<Major> list = majorService.getAll();
        MajorListForm majorListForm = new MajorListForm(200, list);
        return majorListForm.toResponseEntity();
    }

    /***
     * {
     *     "list":[
     *         {
     *             "id":1,
     *             "name":"计算机科学与技术"
     *         }
     *     ]
     * }
     */
    private static class MajorListForm extends ComForm {
        public MajorListForm(int code, List<Major> list) {
            super(code);
            super.put("list", list);
        }
    }
}