package com.neuporfolio.server.api.course.courseid.question.questionid;

import com.neuporfolio.server.domain.Question;
import com.neuporfolio.server.service.QuestionService;
import com.neuporfolio.server.utils.formformat.ComFailureForm;
import com.neuporfolio.server.utils.formformat.ObjectForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/course/{courseid}/question/{questionid}")
public class CourseCourseIdQuestionQuestionIdController {

    @Resource
    QuestionService questionService;

    @GetMapping
    public ResponseEntity<?> getQDetail(@PathVariable(name = "courseid") Integer courseId,
                                        @PathVariable(name = "questionid") Integer questionId
    ) {
        Question question = questionService.getById(questionId);
        if (question == null || !Objects.equals(question.getCourseId(), courseId)) {
            return new ComFailureForm(403, "提交了错误信息").toResponseEntity();
        }
        return new ObjectForm(question).toResponseEntity();
    }
}
