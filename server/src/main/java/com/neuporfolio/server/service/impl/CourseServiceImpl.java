package com.neuporfolio.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.neuporfolio.server.domain.Course;
import com.neuporfolio.server.mapper.CourseMapper;
import com.neuporfolio.server.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
        implements CourseService {
    @Resource
    CourseMapper mapper;

    @Override
    public List<Course> findByMajorIdAndPager(Integer majorid, Integer page, Integer pagesize) {
        PageHelper.startPage(page, pagesize);
        return mapper.selectAllByMajorId(majorid);
    }
}




