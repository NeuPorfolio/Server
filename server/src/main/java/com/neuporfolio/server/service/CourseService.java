package com.neuporfolio.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuporfolio.server.domain.Course;

import java.util.List;

/**
 *
 */
public interface CourseService extends IService<Course> {

    List<Course> findByMajorIdAndPager(Integer majorid, Integer page, Integer pagesize);
}
