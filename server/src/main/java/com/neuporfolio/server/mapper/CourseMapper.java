package com.neuporfolio.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuporfolio.server.domain.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.neuporfolio.server.domain.Course
 */
public interface CourseMapper extends BaseMapper<Course> {
    List<Course> selectAllByMajorId(@Param("majorId") Integer majorId);

    Course selectOneById(@Param("id") Integer id);
}




