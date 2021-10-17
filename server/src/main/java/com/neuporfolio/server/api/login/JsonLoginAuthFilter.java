package com.neuporfolio.server.api.login;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuporfolio.server.config.JsonLoginAuthToken;
import com.neuporfolio.server.config.MyAuthenticationProvider;
import com.neuporfolio.server.service.IdentityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/***
 * Spring security账号密码过滤器
 * 从request中截取登录名和密码
 */

@Slf4j
public class JsonLoginAuthFilter extends UsernamePasswordAuthenticationFilter {
    private boolean postOnly = true;
    @Resource
    IdentityService identityService;

    @Resource
    HttpSession httpSession;

    /***
     * 相关配置
     */
    public JsonLoginAuthFilter() {
        this.setPostOnly(true);
        this.setAuthenticationSuccessHandler((request, response, authentication) -> {
            Object principal = authentication.getPrincipal();
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();//输出流
            response.setStatus(200);
            Map<String, Object> map = new HashMap<>();//对应响应体的树状图嵌套打印格式
            map.put("code", 200);
            log.debug((String) principal);
            map.put("identity", identityService.getByWholeName((String) principal).getSimplyRole());
            map.put("iscomplete", true);
            ObjectMapper om = new ObjectMapper();
            out.write(om.writeValueAsString(map));//转换为字符串
            out.flush();
            out.close();
        });
        this.setAuthenticationFailureHandler((request, response, exception) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();//输出流
            Map<String, Object> map = new HashMap<>();//对应响应体的树状图嵌套打印格式
            if (exception instanceof MyAuthenticationProvider.RegistrationNotFinishedException) {
                MyAuthenticationProvider.RegistrationNotFinishedException ex = (MyAuthenticationProvider.RegistrationNotFinishedException) exception;
                httpSession.setAttribute(MyAuthenticationProvider.getTempLoginCredFlagParameter(), Boolean.TRUE);
                httpSession.setAttribute(MyAuthenticationProvider.getTempLoginCredUsernameParameter(), ex.username);
                httpSession.setAttribute(MyAuthenticationProvider.getTempLoginCredPasswordParameter(), ex.password);
                map.put("code", ex.code);
                map.put("identity", identityService.getByWholeName(ex.identity).getSimplyRole());
                map.put("iscomplete", false);
                response.setStatus(200);
            } else {
                response.setStatus(403);
                map.put("code", 403);
                map.put("msg", exception.getMessage());
            }

            ObjectMapper om = new ObjectMapper();
            out.write(om.writeValueAsString(map));//转换为字符串
            out.flush();
            out.close();
        });
    }

    public boolean isPostOnly() {
        return postOnly;
    }

    public void setPostOnly(boolean value) {
        postOnly = value;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.debug(":12312131");

        if (!request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            throw new AuthenticationServiceException("Request content type must be json");
        }
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        JsonLoginAuthToken token = null;
        try (InputStream inputStream = request.getInputStream()) {
            JSONObject json = JSONObject.parseObject(inputStream, JSONObject.class);

            log.debug("In filter get username parameter:" + this.getUsernameParameter());
            token = new JsonLoginAuthToken(json.get(this.getUsernameParameter()), json.get(this.getPasswordParameter()));
        } catch (IOException e) {
            e.printStackTrace();
            token = new JsonLoginAuthToken("", "");
        }
        this.setDetails(request, token);
        return this.getAuthenticationManager().authenticate(token);
    }


    public void setDetails(HttpServletRequest request, JsonLoginAuthToken token) {
        token.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}
