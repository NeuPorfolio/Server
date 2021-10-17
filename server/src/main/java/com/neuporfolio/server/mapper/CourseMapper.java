package com.neuporfolio.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuporfolio.server.domain.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.neuporfolio.server.domain.Course
 */
@Repository
public interface CourseMapper extends BaseMapper<Course> {
    List<Course> selectAllByMajorId(@Param("majorId") Integer majorId);

    Course selectOneById(@Param("id") Integer id);

    List<Course> selectAll();


}




