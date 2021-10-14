package com.neuporfolio.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;


@Slf4j
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    /***
     * 绑定自定义认证器
     * @param auth builder
     * @throws Exception none
     */

    /***
     * 注册账号密码验证器
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider());
    }

    @Bean
    MyAuthenticationManager myAuthenticationManager() {
        return new MyAuthenticationManager(myAuthenticationProvider());
    }

    @Bean
    MyAuthenticationProvider myAuthenticationProvider() {
        return new MyAuthenticationProvider();
    }

    /***
     * 注册账号密码捕获器
     */
    @Bean
    UserLoggingAuthFilter userLoggingAuthFilter() throws Exception {
        UserLoggingAuthFilter userLoggingAuthFilter = new UserLoggingAuthFilter();
        userLoggingAuthFilter.setAuthenticationManager(myAuthenticationManager());
        return userLoggingAuthFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();//暂时关闭csrf验证
        http.rememberMe();

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.antMatcher("/**").authorizeRequests();
        registry.antMatchers("/login", "api/login", "/register", "/api/register").permitAll();
        registry.antMatchers("/api/stu").hasAnyRole("STUDENT");
        registry.antMatchers("/api/tea").hasAnyRole("TEACHER");
        http
                .formLogin()
                .loginPage("/login")//指定登录页
                .loginProcessingUrl("/api/login")
                .usernameParameter("username")//指定默认的登录名参数名
                .passwordParameter("password")//指定默认的登陆密码参数名
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessUrl("/login")
                .clearAuthentication(true)
                .invalidateHttpSession(true);
    }

}
