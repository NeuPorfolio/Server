package com.neuporfolio.server.config;

import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class MybatisConfig {

    @Resource
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactory() throws IOException {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //设置springVFS
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
//            //领域扫描路径
//            sqlSessionFactoryBean.setTypeAliasesPackage("com.neuporfolio.server.domain");
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        org.springframework.core.io.Resource[] resources = resolver.getResources("classpath*:**/*Mapper.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);
        return sqlSessionFactoryBean;
    }
}
