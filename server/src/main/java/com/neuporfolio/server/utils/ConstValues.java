package com.neuporfolio.server.utils;

import com.neuporfolio.server.mapper.IdentityMapper;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


public class ConstValues {

    public static String roleTeacherParameter = "ROLE_TEACHER";
    public static String roleStudentParameter = "ROLE_STUDENT";
    public static String roleAdminParameter = "ROLE_ADMIN";
    public static String roleSimplyTeacherParameter = "teacher";
    public static String roleSimplyStudentParameter = "student";
    public static String roleSimplyAdminParameter = "admin";
    public static Map<String, String> roleComplexToSimplyParameterMapper;
    @Resource
    IdentityMapper mapper;

    @Bean
    public ConstValues constValues() {
        /*
         从数据库初始化常量
         */
        roleTeacherParameter = mapper.selectOneByAllStyleRoleName("teacher").getRole();
        roleStudentParameter = mapper.selectOneByAllStyleRoleName("student").getRole();
        roleAdminParameter = mapper.selectOneByAllStyleRoleName("admin").getRole();

        roleSimplyTeacherParameter = mapper.selectOneByAllStyleRoleName("teacher").getSimplyRole();
        roleSimplyStudentParameter = mapper.selectOneByAllStyleRoleName("student").getSimplyRole();
        roleSimplyAdminParameter = mapper.selectOneByAllStyleRoleName("admin").getSimplyRole();

        roleComplexToSimplyParameterMapper = new HashMap<>();
        roleComplexToSimplyParameterMapper.put(roleTeacherParameter, roleSimplyTeacherParameter);
        roleComplexToSimplyParameterMapper.put(roleStudentParameter, roleSimplyStudentParameter);
        roleComplexToSimplyParameterMapper.put(roleAdminParameter, roleSimplyAdminParameter);


        return new ConstValues();
    }
}
