package com.neuporfolio.server.service;

import com.neuporfolio.server.dao.SubjectsRepository;
import com.neuporfolio.server.domain.Subjects;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SubjectsService {
    @Resource
    SubjectsRepository subjectsRepository;

    public List<Subjects> getAll() {
        return subjectsRepository.findAll();
    }
}
