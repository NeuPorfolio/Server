package com.neuporfolio.server.dao;

import com.neuporfolio.server.domain.Subjects;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectsRepository extends CrudRepository<Subjects, Long> {

    List<Subjects> findAll();
}
