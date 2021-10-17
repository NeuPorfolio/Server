package com.neuporfolio.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuporfolio.server.domain.Major;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.neuporfolio.server.domain.Major
 */
@Repository
public interface MajorMapper extends BaseMapper<Major> {
    List<Major> selectAll();

    Major selectOneById(@Param("id") Integer id);
}




