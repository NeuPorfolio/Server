package com.neuporfolio.server;

import com.neuporfolio.server.Utils.SpringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.neuporfolio.server")
public class ServerApplication {
    static SpringUtils springUtils = new SpringUtils();

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ServerApplication.class, args);
        springUtils.setApplicationContext(context);//绑定这个Spring实例的上下文
    }

}
