package com.neuporfolio.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuporfolio.server.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.neuporfolio.server.domain.User
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> selectAllByUsername(@Param("username") String username);

    int setIsRegFinishedByUsername(@Param("isRegFinished") Boolean isRegFinished, @Param("username") String username);

    int insertAll(User user);
}




