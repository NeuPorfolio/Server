//package com.neuporfolio.server.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.ses
//
//@Configuration
//public class SpringSessionConfig {
//
//    public SpringSessionConfig() {
//    }
//
//    @Bean
//    public CookieSerializer httpSessionIdResolver() {
//        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
//        // 取消仅限同一站点设置
//        cookieSerializer.setSameSite(null);
//        return cookieSerializer;
//    }
//}