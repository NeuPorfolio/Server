package com.neuporfolio.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuporfolio.server.domain.Identity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.neuporfolio.server.domain.Identity
 */
@Mapper
public interface IdentityMapper extends BaseMapper<Identity> {
    List<Identity> selectByAllStyleRoleName(@Param("roleName") String roleName);
}




