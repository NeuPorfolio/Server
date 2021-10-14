package com.neuporfolio.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuporfolio.server.domain.Major;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.neuporfolio.server.domain.Major
 */
public interface MajorMapper extends BaseMapper<Major> {
    List<Major> selectAll();

    Major selectOneById(@Param("id") Integer id);
}




