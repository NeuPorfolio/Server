package com.neuporfolio.server.api.course.courseid.question.questionid.answer;


import com.alibaba.fastjson.JSONObject;
import com.neuporfolio.server.domain.Question;
import com.neuporfolio.server.service.QuestionService;
import com.neuporfolio.server.utils.formformat.ComFailureForm;
import com.neuporfolio.server.utils.formformat.ComForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/course/{courseid}/question/{questionid}/answer")
public class CourseCourseIdQuestionQuestionIdAnswerController {
    @Resource
    QuestionService questionService;

    @GetMapping
    public ResponseEntity<?> setAnswer(@PathVariable(name = "courseid") Integer courseId,
                                       @PathVariable(name = "questionid") Integer questionId,
                                       @RequestBody JSONObject json
    ) {
        Question question = questionService.getById(questionId);
        if (question == null || !Objects.equals(question.getCourseId(), courseId)) {
            return new ComFailureForm(403, "提交了错误信息").toResponseEntity();
        }
        String answer = json.getString("detail");
        question.setAnswer(answer);
        question.setIsAnswer(true);
        question.setAnswerTime(new Date());
        questionService.save(question);
        return new ComForm(200).toResponseEntity();
    }
}
