package com.neuporfolio.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neuporfolio.server.domain.Sheet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.neuporfolio.server.domain.Sheet
 */
public interface SheetMapper extends BaseMapper<Sheet> {
    List<Sheet> selectAllByHomeworkId(@Param("homeworkId") Integer homeworkId);
}




