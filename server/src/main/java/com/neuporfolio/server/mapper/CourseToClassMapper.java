package com.neuporfolio.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuporfolio.server.domain.CourseToClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.neuporfolio.server.domain.CourseToClass
 */
public interface CourseToClassMapper extends BaseMapper<CourseToClass> {

    int deleteByClassId(@Param("classId") Integer classId);

    int insertAll(CourseToClass courseToClass);

    List<CourseToClass> findByClassId(@Param("classId") Integer classId);
}




