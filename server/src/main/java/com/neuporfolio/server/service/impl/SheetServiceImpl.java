package com.neuporfolio.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuporfolio.server.domain.Sheet;
import com.neuporfolio.server.mapper.SheetMapper;
import com.neuporfolio.server.service.SheetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class SheetServiceImpl extends ServiceImpl<SheetMapper, Sheet>
        implements SheetService {

    @Resource
    SheetMapper sheetMapper;

    @Override
    public int build(Sheet sheet) {
        return sheetMapper.insert(sheet);
    }

    @Override
    public List<Sheet> getByHomeworkId(Integer homeworkId) {
        return sheetMapper.selectAllByHomeworkId(homeworkId);
    }
}




