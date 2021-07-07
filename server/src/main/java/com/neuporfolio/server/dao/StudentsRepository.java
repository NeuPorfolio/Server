package com.neuporfolio.server.dao;

import com.neuporfolio.server.domain.Students;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends CrudRepository<Students, Long> {
    Students findByUserId(Long UserId);
}
