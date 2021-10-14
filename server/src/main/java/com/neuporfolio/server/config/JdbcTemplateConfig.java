//package com.neuporfolio.server.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
///***
// * jdbc配置,实际用Mybatis所以用不到
// */
//@Configuration
//public class JdbcTemplateConfig {
//    @Bean("jdbcTemplate")
//    JdbcTemplate jdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//}
