package com.bjike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

@EnableAutoConfiguration
@ComponentScan
@Configuration
@PropertySource({"classpath:config.properties"})
@EnableTransactionManagement(proxyTargetClass = true)
public class Application   {


    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
        System.in.read(); // 按任意键退出
    }

}

