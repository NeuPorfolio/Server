package com.neuporfolio.server.config;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class UserLoggingAuthFilter extends UsernamePasswordAuthenticationFilter {
    private boolean postOnly = true;

    UserLoggingAuthFilter() throws Exception {
        super.setPostOnly(true);
        super.setAuthenticationSuccessHandler((request, response, authentication) -> {
            Object principal = authentication.getPrincipal();
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();//输出流
            response.setStatus(200);
            Map<String, Object> map = new HashMap<>();//对应响应体的树状图嵌套打印格式
            map.put("code", 200);
            map.put("identity", principal);
            ObjectMapper om = new ObjectMapper();
            out.write(om.writeValueAsString(map));//转换为字符串
            out.flush();
            out.close();
        });
        super.setAuthenticationFailureHandler((request, response, exception) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();//输出流
            response.setStatus(401);
            Map<String, Object> map = new HashMap<>();//对应响应体的树状图嵌套打印格式
            map.put("status", 401);
            map.put("msg", response.toString());
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
        if (!request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            throw new AuthenticationServiceException("Request content type must be json");
        }
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        UsernamePasswordAuthenticationToken token = null;
        try (InputStream inputStream = request.getInputStream()) {
            JSONObject json = JSONObject.parseObject(inputStream, JSONObject.class);
            token = new UsernamePasswordAuthenticationToken(json.get(super.getUsernameParameter()), json.get(super.getPasswordParameter()));
        } catch (IOException e) {
            e.printStackTrace();
            token = new UsernamePasswordAuthenticationToken("", "");
        } finally {
            // Allow subclasses to set the "details" property
            setDetails(request, token);
        }
        return this.getAuthenticationManager().authenticate(token);
    }
}
