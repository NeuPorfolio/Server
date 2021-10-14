package com.neuporfolio.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neuporfolio.server.domain.Major;

import java.util.List;

/**
 *
 */
public interface MajorService extends IService<Major> {
    List<Major> getAll();

    Major findById(int id);

    List<Major> FindByPager(int page, int size);
}
