package com.neuporfolio.server.service;

import com.neuporfolio.server.dao.TeachersRepository;
import com.neuporfolio.server.domain.Teachers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeachersService {
    @Resource
    private TeachersRepository teachersRepository;

    public void add(Teachers teachers) {
        if (teachersRepository.findByUserId(teachers.getUserId()) == null) {
            teachersRepository.save(teachers);
        }
    }
}
