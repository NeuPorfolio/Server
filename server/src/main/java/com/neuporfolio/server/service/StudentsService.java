package com.neuporfolio.server.service;

import com.neuporfolio.server.dao.StudentsRepository;
import com.neuporfolio.server.domain.Students;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentsService {
    @Resource
    StudentsRepository studentsRepository;

    public void add(Students students) {
        if (studentsRepository.findByUserId(students.getUserId()) == null) {
            studentsRepository.save(students);
        }
    }
}
