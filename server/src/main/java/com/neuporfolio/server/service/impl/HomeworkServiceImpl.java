package com.neuporfolio.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.neuporfolio.server.domain.Homework;
import com.neuporfolio.server.domain.StudentToCourse;
import com.neuporfolio.server.mapper.HomeworkMapper;
import com.neuporfolio.server.mapper.StudentToCourseMapper;
import com.neuporfolio.server.service.HomeworkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class HomeworkServiceImpl extends ServiceImpl<HomeworkMapper, Homework>
        implements HomeworkService {

    @Resource
    HomeworkMapper homeworkMapper;

    @Resource
    StudentToCourseMapper studentToCourseMapper;

    @Override
    public int add(Homework homework) {
        return homeworkMapper.insert(homework);
    }

    @Override
    public List<Homework> getByCourseId(Integer courseId) {
        return homeworkMapper.selectAllByCourseId(courseId);
    }

    @Override
    public List<Homework> getByStudentId(Integer studentId) {
        List<StudentToCourse> list = studentToCourseMapper.selectAllByStudentId(studentId);
        List<Homework> res = new ArrayList<>();
        for (StudentToCourse i : list) {
            List<Homework> tmp = homeworkMapper.selectAllByCourseId(i.getCourseId());
            res.addAll(tmp);
        }
        return res;
    }

    @Override
    public List<Homework> getByCourseIdAndPager(Integer courseId, int page, int size) {
        PageHelper.startPage(page, size);
        return homeworkMapper.selectAllByCourseId(courseId);
    }
}




