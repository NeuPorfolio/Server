package com.neuporfolio.server.api.course.courseid.question;

import com.alibaba.fastjson.annotation.JSONField;
import com.neuporfolio.server.domain.Question;
import com.neuporfolio.server.service.CourseService;
import com.neuporfolio.server.service.StudentService;

import javax.annotation.Resource;

public class SimplyQuestion {
    /***
     * {
     *      *             "questionid":1,
     *      *             "title":"",
     *      *             "studentname":"hhh",
     *      *             "coursename":"高数",
     *      *             "is_answer":false, // 有教师没有回答的问题
     *      *             "brief":"老师，请问。。。" // 学生提问的前10个字（不要写死，可能会变化）
     *      *         }
     */
    @JSONField(name = "questionid")
    Integer questionId;

    @JSONField(name = "title")
    String title;

    @JSONField(name = "studentname")
    String studentName;

    @JSONField(name = "coursename")
    String courseName;

    @JSONField(name = "is_answer")
    Boolean isAnswer;

    @JSONField(name = "brief")
    String brief;

    @Resource
    @JSONField(serialize = false)
    StudentService studentService;

    @Resource
    @JSONField(serialize = false)
    CourseService courseService;

    public SimplyQuestion(Question question) {
        this.questionId = question.getQuestionId();
        this.title = question.getTittle();
        this.studentName = studentService.getById(question.getStudentId()).getRealName();
        this.courseName = courseService.getById(question.getCourseId()).getName();
        this.isAnswer = question.getIsAnswer();
        this.brief = question.getDetail().substring(0, 10) + "...";
    }
}
