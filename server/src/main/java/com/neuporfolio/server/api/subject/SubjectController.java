package com.neuporfolio.server.api.subject;

import com.neuporfolio.server.domain.Subjects;
import com.neuporfolio.server.service.SubjectsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/api/subject")
public class SubjectController {
    @Resource
    SubjectsService subjectsService;

    @GetMapping
    public ResponseEntity<SubjectResponseForm> all(
            @RequestParam(value = "pageIndex", defaultValue = "0") Long pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Long pageSize
    ) {
        if (pageIndex == null)
            pageIndex = Long.valueOf(1);
        if (pageSize == null)
            pageSize = Long.valueOf(10);
        List<Subjects> list = subjectsService.getAll();
        if (null == list) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //目前不分页
        SubjectResponseForm form = new SubjectResponseForm(list);
        return new ResponseEntity<SubjectResponseForm>(form, HttpStatus.OK);
    }
}
