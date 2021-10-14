package com.neuporfolio.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.neuporfolio.server.domain.Major;
import com.neuporfolio.server.mapper.MajorMapper;
import com.neuporfolio.server.service.MajorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major>
        implements MajorService {
    @Resource
    MajorMapper mapper;

    @Override
    public List<Major> getAll() {
        return mapper.selectAll();
    }

    @Override
    public Major findById(int id) {
        return mapper.selectOneById(id);
    }

    @Override
    public List<Major> FindByPager(int page, int size) {
        PageHelper.startPage(page, size);
        return mapper.selectAll();
    }
}




