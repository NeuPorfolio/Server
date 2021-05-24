package com.neuporfolio.server.Security;

import com.neuporfolio.server.Security.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
