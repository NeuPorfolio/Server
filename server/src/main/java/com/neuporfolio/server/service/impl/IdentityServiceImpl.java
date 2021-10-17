package com.neuporfolio.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neuporfolio.server.domain.Identity;
import com.neuporfolio.server.mapper.IdentityMapper;
import com.neuporfolio.server.service.IdentityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service
public class IdentityServiceImpl extends ServiceImpl<IdentityMapper, Identity>
        implements IdentityService {

    @Resource
    IdentityMapper identityMapper;

    @Override
    public Identity getByWholeName(String role) {
        return identityMapper.selectById(role);
    }
}




