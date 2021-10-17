package com.neuporfolio.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuporfolio.server.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.neuporfolio.server.domain.User
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    User selectOneByUsername(@Param("username") String username);

    int setIsRegFinishedByUsername(@Param("isRegFinished") Boolean isRegFinished, @Param("username") String username);

    int insertAll(User user);
}




