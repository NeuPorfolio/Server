package com.neuporfolio.server.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuporfolio.server.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private DataSource dataSource;
    @Resource
    private UsersService usersService;

    @Bean
    @Primary
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {
        authenticationManagerBuilder
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password , enabled from users where username = ? ;")
                .authoritiesByUsernameQuery("select username , role from users where username = ? ;")
                .and()
                .userDetailsService(usersService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();//暂时关闭csrf验证
        http//写在前面的优先级更高
                .authorizeRequests()//开启登录请求
                .antMatchers("/api/stu").hasAnyRole("student", "super")
                .antMatchers("/api/tea").hasAnyRole("teacher", "super")
                .antMatchers("/", "/**").permitAll()//白名单
                .and()
                .formLogin()
                .loginPage("/login")//指定登录页
                .loginProcessingUrl("/api/login")
                .usernameParameter("username")//指定默认的登录名参数名
                .passwordParameter("password")//指定默认的登陆密码参数名
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    Object principal = authentication.getPrincipal();
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();//输出流
                    httpServletResponse.setStatus(200);
                    Map<String, Object> map = new HashMap<>();//对应响应体的树状图嵌套打印格式
                    map.put("status", 200);
                    map.put("msg", principal);
                    ObjectMapper om = new ObjectMapper();
                    out.write(om.writeValueAsString(map));//转换为字符串
                    out.flush();
                    out.close();
                })
                .failureHandler((httpServletRequest, httpServletResponse, authenticationException) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();//输出流
                    httpServletResponse.setStatus(401);
                    Map<String, Object> map = new HashMap<>();//对应响应体的树状图嵌套打印格式
                    map.put("status", 401);
                    map.put("msg", authenticationException.toString());
                    ObjectMapper om = new ObjectMapper();
                    out.write(om.writeValueAsString(map));//转换为字符串
                    out.flush();
                    out.close();
                })
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessUrl("/login")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    //做一些登出时做的事情
                })
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    //做一些登出成功时做的事情
                });
    }

}
