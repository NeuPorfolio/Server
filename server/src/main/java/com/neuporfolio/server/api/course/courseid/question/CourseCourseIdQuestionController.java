package com.neuporfolio.server.api.course.courseid.question;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.neuporfolio.server.domain.Question;
import com.neuporfolio.server.domain.Student;
import com.neuporfolio.server.domain.Teacher;
import com.neuporfolio.server.service.QuestionService;
import com.neuporfolio.server.utils.AuthChecker;
import com.neuporfolio.server.utils.formformat.ComFailureForm;
import com.neuporfolio.server.utils.formformat.ComForm;
import com.neuporfolio.server.utils.formformat.PagerForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/course/{courseid}/question")
public class CourseCourseIdQuestionController {

    @Resource
    AuthChecker authChecker;

    @Resource
    QuestionService questionService;


    @Resource
    HttpSession httpSession;

    /***
     *
     *
     * {
     *     "list":[
     *         {
     *             "questionid":1,
     *             "tittle":"",
     *             "studentname":"hhh",
     *             "coursename":"高数",
     *             "is_answer":false, // 有教师没有回答的问题
     *             "brief":"老师，请问。。。" // 学生提问的前10个字（不要写死，可能会变化）
     *         }
     *     ],
     *     "page_info":{
     *         "current_page": 1,
     *         "current_size": 30,
     *         "has_next_page": false,
     *         "has_prev_page": false
     *     }
     * }
     * {{baseurl}}/api/course/{courseid}/question?page=1&pagesize=30&is_answer=true
     */
    @GetMapping
    public ResponseEntity<?> getQListByC(@PathVariable(name = "courseid") Integer courseId,
                                         @RequestParam(name = "page", defaultValue = "1") Integer page,
                                         @RequestParam(name = "pagesize", defaultValue = "30") Integer pagesize,
                                         @RequestParam(name = "is_answer") Boolean isAnswer
    ) {
        Object srvObj;
        if (authChecker.isTeaAuth(SecurityContextHolder.getContext().getAuthentication())) {
//            /*
//            检查是否属于该课程
//             */
//            Integer teacherId= (Integer) httpSession.getAttribute("uid");
//            if(teacherId==null)
//            {
//                return new ComFailureForm(403,"session丢失").toResponseEntity();
//            }
            srvObj = new Teacher();
        } else if (authChecker.isStuAuth(SecurityContextHolder.getContext().getAuthentication())) {
            srvObj = new Student();
        } else {
            return new ComFailureForm(403, "权限不足").toResponseEntity();
        }
        List<Question> questionList;
        if (srvObj instanceof Teacher) {
            if (isAnswer == null)
                questionList = questionService.getByCourseIdAndPager(courseId, page, pagesize);
            else {
                questionList = questionService.getByCourseIdAndPagerAndIsAnswer(courseId, isAnswer, page, pagesize);
            }
        } else {
            Integer studentId = (Integer) httpSession.getAttribute("uid");
            if (studentId == null) {
                return new ComFailureForm(403, "session丢失").toResponseEntity();
            }
            if (isAnswer == null)
                questionList = questionService.getByCourseIdAndStudentIdAndPager(courseId, studentId, page, pagesize);
            else {
                questionList = questionService.getByCourseIdAndStudentIdAndPagerAndIsAnswer(courseId, studentId, isAnswer, page, pagesize);
            }
        }


        List<SimplyQuestion> simplyQuestionList = new ArrayList<>();
        for (Question i : questionList) {
            simplyQuestionList.add(new SimplyQuestion(i));
        }
        PageInfo<Question> pageInfo = new PageInfo<>(questionList);
        return new PagerForm(200, simplyQuestionList, pageInfo).toResponseEntity();
    }

    //{{baseurl}}/api/course/{courseid}/question
    @PostMapping
    public ResponseEntity<?> addQuestion(@RequestParam(name = "courseid") Integer courseId,
                                         @RequestBody JSONObject json) {
        if (!authChecker.isStuAuth(SecurityContextHolder.getContext().getAuthentication())) {
            return new ComFailureForm(403, "权限不足").toResponseEntity();
        }
        String title = (String) json.get("title");
        String detail = json.getString("detail");
        if (title == null) {
            title = "";
        }
        Question question = new Question();
        question.setTittle(title);
        question.setDetail(detail);
        question.setAnswer("");
        question.setCourseId(courseId);
        question.setStudentId((Integer) httpSession.getAttribute("uid"));
        question.setIsClosed(false);
        question.setIsAnswer(false);
        questionService.save(question);
        return new ComForm(200).toResponseEntity();
    }

}
