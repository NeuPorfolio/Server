package com.neuporfolio.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuporfolio.server.domain.Homework;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.neuporfolio.server.domain.Homework
 */
public interface HomeworkMapper extends BaseMapper<Homework> {
    List<Homework> selectAllByCourseId(@Param("courseId") Integer courseId);
}




