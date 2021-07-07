package com.neuporfolio.server.dao;

import com.neuporfolio.server.domain.Teachers;
import org.springframework.data.repository.CrudRepository;

public interface TeachersRepository extends CrudRepository<Teachers, Long> {
    Teachers findByUserId(Long userId);
}
