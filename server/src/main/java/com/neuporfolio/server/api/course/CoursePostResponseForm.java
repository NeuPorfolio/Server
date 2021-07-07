package com.neuporfolio.server.api.course;

import com.neuporfolio.server.domain.Courses;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoursePostResponseForm {
    private String msg;
    private Courses courses;

    public CoursePostResponseForm(String msg) {
        this.msg = msg;
    }

    public CoursePostResponseForm(Courses courses) {
        this.courses = courses;
    }
}
