package com.neuporfolio.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuporfolio.server.domain.StuToClass;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.neuporfolio.server.domain.StuToClass
 */
@Repository
public interface StuToClassMapper extends BaseMapper<StuToClass> {
    int insertAll(StuToClass stuToClass);

    List<StuToClass> findStuIdByClassId(@Param("classId") Integer classId);

    int deleteByClassId(@Param("classId") Integer classId);
}




