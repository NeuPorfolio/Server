package com.neuporfolio.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.neuporfolio.server.domain.*;
import com.neuporfolio.server.mapper.*;
import com.neuporfolio.server.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
        implements CourseService {

    @Resource
    CourseMapper courseMapper;

    @Resource
    TeacherToCourseMapper teacherToCourseMapper;

    @Resource
    CourseToClassMapper courseToClassMapper;

    @Resource
    StudentToCourseMapper studentToCourseMapper;

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    ClassroomMapper classroomMapper;

    @Override
    public List<Course> findByMajorIdAndPager(Integer majorid, Integer page, Integer pagesize) {
        PageHelper.startPage(page, pagesize);
        return courseMapper.selectAllByMajorId(majorid);
    }

    @Override
    public List<Course> getAllByPager(Integer page, Integer pagesize) {
        PageHelper.startPage(page, pagesize);
        return courseMapper.selectAll();
    }

    @Override
    public int buildCourse(String coursename, Integer majorid, String image, String link) {
        Course course = new Course();
        course.setName(coursename);
        course.setMajorId(majorid);
        course.setImg(image);
        course.setLink(link);
        return courseMapper.insert(course);
    }

    @Override
    public Course findById(int courseId) {
        return courseMapper.selectOneById(courseId);
    }

    @Override
    public void editTeacher(int courseId, List<Integer> teacherId) {
        teacherToCourseMapper.deleteById(courseId);
        for (Integer i : teacherId) {
            teacherToCourseMapper.insert(new TeacherToCourse(courseId, i));
        }
    }

    @Override
    public void editClass(int courseId, List<Integer> classId) {
        courseToClassMapper.deleteByCourseId(courseId);
        for (Integer i : classId) {
            courseToClassMapper.insert(new CourseToClass(courseId, i));
        }
    }

    @Override
    public void delete(int courseId) {
        courseToClassMapper.deleteByCourseId(courseId);
        teacherToCourseMapper.deleteByCourseId(courseId);
        studentToCourseMapper.deleteByCourseId(courseId);
        courseMapper.deleteById(courseId);
    }

    @Override
    public List<Teacher> getTeacher(int courseId) {
        List<TeacherToCourse> list = teacherToCourseMapper.findAllByCourseId(courseId);
        List<Teacher> list1 = new ArrayList<>();
        for (TeacherToCourse i : list) {
            list1.add(teacherMapper.selectById(i.getTeacherId()));
        }
        return list1;
    }

    @Override
    public Classroom getClassroom(int courseId) {
        List<CourseToClass> list = courseToClassMapper.findByCourseId(courseId);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return classroomMapper.selectById(list.get(0));
    }

}




