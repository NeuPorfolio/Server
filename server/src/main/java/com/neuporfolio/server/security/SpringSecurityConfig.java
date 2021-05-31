package com.neuporfolio.server.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//    private final AdminAuthenticationProcessingFilter

    @Resource(name = "dataSource")
    DataSource dataSource;

    @Resource(name = "UserService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .userDetailsService(userDetailsService);
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)//数据源
                .usersByUsernameQuery(
                        "select username, password, from users" +
                                "where username=?"
                )
                .authoritiesByUsernameQuery(
                        "select username, character from users" +
                                "where username=?"
                )
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http//写在前面的优先级更高
                .authorizeRequests()
                .antMatchers("/", "/**").permitAll()//白名单
                .and()
                .formLogin()
                .loginPage("/login")//指定登录页
                .loginProcessingUrl("/api/login")
                .usernameParameter("username")//指定默认的登录名参数名
                .passwordParameter("password")//指定默认的登陆密码参数名
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                                        HttpServletResponse httpServletResponse,
                                                        Authentication authentication)
                            throws IOException {
                        Object principal = authentication.getPrincipal();
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();//输出流
                        httpServletResponse.setStatus(401);
                        Map<String, Object> map = new HashMap<>();//对应响应体的树状图嵌套打印格式
                        map.put("status", 401);
                        map.put("msg", principal);
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));//转换为字符串
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                                        HttpServletResponse httpServletResponse,
                                                        AuthenticationException authenticationException)
                            throws IOException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();//输出流
                        httpServletResponse.setStatus(200);
                        Map<String, Object> map = new HashMap<>();//对应响应体的树状图嵌套打印格式
                        map.put("status", 200);
                        map.put("msg", authenticationException.getClass());
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));//转换为字符串
                        out.flush();
                        out.close();
                    }
                })
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessUrl("/login")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
                        //做一些登出时做的事情
                    }
                })
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse,
                                                Authentication authentication)
                            throws IOException {
                        //做一些登出成功时做的事情
                    }
                });

    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
