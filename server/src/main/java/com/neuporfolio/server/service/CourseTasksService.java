package com.neuporfolio.server.service;

import com.neuporfolio.server.dao.CourseTasksRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CourseTasksService {
    @Resource
    CourseTasksRepository courseTasksRepository;
}
