package com.neuporfolio.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuporfolio.server.domain.Sheet;

import java.util.List;

/**
 *
 */
public interface SheetService extends IService<Sheet> {
    int build(Sheet sheet);

    List<Sheet> getByHomeworkId(Integer homeworkId);
}
