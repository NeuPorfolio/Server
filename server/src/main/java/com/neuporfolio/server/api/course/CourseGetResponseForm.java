package com.neuporfolio.server.api.course;

import com.neuporfolio.server.domain.Courses;
import lombok.Data;

import java.util.List;

@Data
public class CourseGetResponseForm {
    List<Courses> results;
    private Long count;
    private String msg;

    public CourseGetResponseForm(List<Courses> list) {
        this.count = Long.valueOf(list.size());
        this.results = list;
    }
}
