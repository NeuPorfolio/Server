package com.neuporfolio.server.api.course;

import com.neuporfolio.server.domain.Courses;
import com.sun.istack.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class CoursePostForm {
    @NotNull
    private String name; //课程名称
    @NotNull
    private String subject;  //隶属于的专业
    private Long sponsor;  //课程发布者
    private String supCourseName; //隶属于的上级课程
    private String supCourseSubject; //上级课程的隶属的专业
    private Date generationDate;  //项目创建时间
    private Date closedDate;  //自动清空时间

    public Courses toCourses() {
        return new Courses(name, subject, sponsor, supCourseName, supCourseSubject, generationDate, closedDate);
    }
}
