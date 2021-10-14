package com.neuporfolio.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuporfolio.server.domain.Classroom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.neuporfolio.server.domain.Classroom
 */
public interface ClassroomMapper extends BaseMapper<Classroom> {
    List<Classroom> selectAllByMajorId(@Param("majorId") Integer majorId);

    List<Classroom> selectAll();

    Classroom findOneById(@Param("id") Integer id);

    int insertAll(Classroom classroom);
}




