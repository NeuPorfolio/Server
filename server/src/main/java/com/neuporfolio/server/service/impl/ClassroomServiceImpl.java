package com.neuporfolio.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.neuporfolio.server.domain.*;
import com.neuporfolio.server.mapper.*;
import com.neuporfolio.server.service.ClassroomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class ClassroomServiceImpl extends ServiceImpl<ClassroomMapper, Classroom>
        implements ClassroomService {

    @Resource
    ClassroomMapper classroomMapper;

    @Resource
    CourseToClassMapper courseToClassMapper;

    @Resource
    StuToClassMapper stuToClassMapper;

    @Resource
    StudentMapper studentMapper;

    @Resource
    CourseMapper courseMapper;


    @Override
    public void deleteClass(Integer classid) {
        /*
        删除ctoc
         */
        courseToClassMapper.deleteByClassId(classid);
        /*
        删除stoc
         */
        stuToClassMapper.deleteByClassId(classid);
        /*
        删除c
         */
        classroomMapper.deleteById(classid);
    }

    @Override
    public Classroom findById(Integer classid) {
        return classroomMapper.findOneById(classid);
    }

    @Override
    public void editCourse(Integer classid, ArrayList<Integer> integers) {
        /*
        删除ctoc
         */
        courseToClassMapper.deleteByClassId(classid);
        /*
        添加ctoc
         */
        for (Integer i : integers) {
            courseToClassMapper.insertAll(new CourseToClass(i, classid));
        }
    }

    @Override
    public void editStu(Integer classid, ArrayList<Integer> integers) {
        /*
        删除stoc
         */
        stuToClassMapper.deleteByClassId(classid);
        /*
        添加stoc
         */
        for (Integer i : integers) {
            stuToClassMapper.insertAll(new StuToClass(i, classid));
        }
    }

    @Override
    public List<Student> findStudentByClassidAndPager(Integer classid, Integer page, Integer pagesize) {
        PageHelper.startPage(page, pagesize);
        List<StuToClass> list1 = stuToClassMapper.findStuIdByClassId(classid);
        List<Student> ans = new ArrayList<>();
        for (StuToClass i : list1) {
            ans.add(studentMapper.selectOneByUid(i.getStuId()));
        }
        return ans;
    }

    @Override
    public List<Classroom> findByMajorIdAndPager(Integer majorid, Integer page, Integer pagesize) {
        PageHelper.startPage(page, pagesize);
        return classroomMapper.selectAllByMajorId(majorid);
    }

    @Override
    public List<Course> findCourseByClassidAndPager(Integer classid, Integer page, Integer pagesize) {
        PageHelper.startPage(page, pagesize);
        List<CourseToClass> list1 = courseToClassMapper.findByClassId(classid);
        List<Course> ans = new ArrayList<>();
        for (CourseToClass i : list1) {
            ans.add(courseMapper.selectOneById(i.getCourseId()));
        }
        return ans;
    }

    @Override
    public int buildClass(String classname, Integer majorid) {
        Classroom classroom = new Classroom();
        classroom.setName(classname);
        classroom.setMajorId(majorid);
        return classroomMapper.insertAll(classroom);
    }

    @Override
    public List<Classroom> getAllByPager(Integer page, Integer pagesize) {
        PageHelper.startPage(page, pagesize);
        return classroomMapper.selectAll();
    }

    @Override
    public List<Classroom> findByMajorId(int id) {
        return classroomMapper.selectAllByMajorId(id);
    }
}




