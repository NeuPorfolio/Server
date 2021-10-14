package com.neuporfolio.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuporfolio.server.domain.Classroom;
import com.neuporfolio.server.domain.Course;
import com.neuporfolio.server.domain.Student;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public interface ClassroomService extends IService<Classroom> {

    void deleteClass(Integer classid);

    Classroom findById(Integer classid);

    void editCourse(Integer classid, ArrayList<Integer> integers);

    void editStu(Integer classid, ArrayList<Integer> integers);

    List<Student> findStudentByClassidAndPager(Integer classid, Integer page, Integer pagesize);

    List<Classroom> findByMajorIdAndPager(Integer majorid, Integer page, Integer pagesize);

    List<Course> findCourseByClassidAndPager(Integer classid, Integer page, Integer pagesize);

    int buildClass(String classname, Integer majorid);

    List<Classroom> getAllByPager(Integer page, Integer pagesize);

    List<Classroom> findByMajorId(int id);
}
