package com.neuporfolio.server.service;

import com.neuporfolio.server.dao.CoursesRepository;
import com.neuporfolio.server.domain.Courses;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CoursesService {
    @Resource
    CoursesRepository coursesRepository;

    public List<Courses> searchBySubjects(String subject) {
        return coursesRepository.findBySubject(subject);
    }

    public List<Courses> getAll() {
        return coursesRepository.findAll();
    }

    public boolean add(Courses courses) {
        if (null == coursesRepository.findByNameAndSubject(courses.getName(), courses.getSubject())) {
            courses.setGenerationDate(new Date());
            coursesRepository.save(courses);
            return true;
        }
        return false;
    }
}
