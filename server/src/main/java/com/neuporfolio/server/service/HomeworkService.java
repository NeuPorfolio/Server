package com.neuporfolio.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuporfolio.server.domain.Homework;

import java.util.List;

/**
 *
 */
public interface HomeworkService extends IService<Homework> {
    int add(Homework homework);

    List<Homework> getByCourseId(Integer courseId);

    List<Homework> getByStudentId(Integer studentId);

    List<Homework> getByCourseIdAndPager(Integer courseId, int page, int size);
}
