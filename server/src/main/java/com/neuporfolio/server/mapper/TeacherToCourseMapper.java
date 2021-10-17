package com.neuporfolio.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuporfolio.server.domain.TeacherToCourse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.neuporfolio.server.domain.TeacherToCourse
 */
@Repository
public interface TeacherToCourseMapper extends BaseMapper<TeacherToCourse> {
    int deleteByCourseId(@Param("courseId") Integer courseId);

    List<TeacherToCourse> findAllByTeacherId(@Param("teacherId") Integer teacherId);

    List<TeacherToCourse> findAllByCourseId(@Param("courseId") Integer courseId);
}




