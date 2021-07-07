package com.neuporfolio.server.dao;

import com.neuporfolio.server.domain.Courses;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CoursesRepository extends CrudRepository<Courses, Courses.CoursesId> {
    //查询过期的课程
    Courses findByClosedDateBefore(Date now);

    List<Courses> findBySubject(String subject);

    List<Courses> findAll();

    Courses findByNameAndSubject(String name, String subject);
}
