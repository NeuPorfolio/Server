package com.neuporfolio.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuporfolio.server.domain.Identity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.neuporfolio.server.domain.Identity
 */
@Repository
public interface IdentityMapper extends BaseMapper<Identity> {
    Identity selectOneByAllStyleRoleName(@Param("roleName") String roleName);

}




