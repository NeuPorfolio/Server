//package com.neuporfolio.server.database.mssql;
//
//import org.hibernate.metamodel.model.domain.internal.EmbeddableTypeImpl;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource primaryDataSource(){
//        return new EmbeddedDatabaseBuilder()
//                .setType(H2)
//                .addScript()
//    }
//
//    @Bean
//    @Primary
//    public JdbcTemplate primaryJdbcTemplate(DataSource dataSource){
//        return new JdbcTemplate(dataSource);
//    }
//}
