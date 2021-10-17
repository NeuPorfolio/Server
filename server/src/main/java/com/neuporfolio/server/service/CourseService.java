package com.neuporfolio.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuporfolio.server.domain.Classroom;
import com.neuporfolio.server.domain.Course;
import com.neuporfolio.server.domain.Teacher;

import java.util.List;

/**
 *
 */
public interface CourseService extends IService<Course> {

    List<Course> findByMajorIdAndPager(Integer majorid, Integer page, Integer pagesize);

    List<Course> getAllByPager(Integer page, Integer pagesize);

    int buildCourse(String coursename, Integer majorid, String image, String link);

    Course findById(int courseId);

    void editTeacher(int courseId, List<Integer> teacherId);

    void editClass(int courseId, List<Integer> classId);

    void delete(int courseId);

    List<Teacher> getTeacher(int courseId);

    Classroom getClassroom(int courseId);
}
