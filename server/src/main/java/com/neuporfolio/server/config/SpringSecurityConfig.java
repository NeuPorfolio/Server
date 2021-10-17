package com.neuporfolio.server.config;

import com.neuporfolio.server.api.login.JsonLoginAuthFilter;
import com.neuporfolio.server.service.UserService;
import com.neuporfolio.server.utils.AuthChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;


@Slf4j
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String loginProcessUrl = "/api/login";

    @Resource
    UserService userService;

    /*
        临时身份验证器
     */
    @Bean
    AuthChecker authChecker() {
        return new AuthChecker();
    }


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
        auth
                .authenticationProvider(myAuthenticationProvider())
                .userDetailsService(userService);
    }

    @Bean
    MyAuthenticationManager myAuthenticationManager() {
        return new MyAuthenticationManager(myAuthenticationProvider());
    }

    @Bean
    MyAuthenticationProvider myAuthenticationProvider() {
        MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
        return new MyAuthenticationProvider();
    }

    /***
     * 注册账号密码捕获器、登录验证器、验证器管理器
     */
    @Bean
    JsonLoginAuthFilter userLoggingAuthFilter() {
        JsonLoginAuthFilter jsonLoginAuthFilter = new JsonLoginAuthFilter();
        jsonLoginAuthFilter.setAuthenticationManager(myAuthenticationManager());
        jsonLoginAuthFilter.setFilterProcessesUrl(this.loginProcessUrl);
        return jsonLoginAuthFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();//暂时关闭csrf验证
        http.rememberMe();

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>
                .ExpressionInterceptUrlRegistry registry = http.antMatcher("/**").authorizeRequests();
//        registry.antMatchers("/login", "/api/login", "/register", "/api/register").permitAll();
//        registry.antMatchers("/api/stu").hasAnyRole("STUDENT");
//        registry.antMatchers("/api/tea").hasAnyRole("TEACHER");
        registry
                .antMatchers("**/").permitAll();
        /*
        添加用户密码捕获器进捕获链
         */
        http.addFilterBefore(userLoggingAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/dasddasafdsasc")//指定登录页
                .loginProcessingUrl("/ascxzasafsgsaesad")
                .usernameParameter("username")//指定默认的登录名参数名
                .passwordParameter("password")//指定默认的登陆密码参数名
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessUrl("/login")
                .clearAuthentication(true)
                .invalidateHttpSession(true);


    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return myAuthenticationManager();
    }

}
