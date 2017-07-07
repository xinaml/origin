package com.bjike.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.bjike.ser.user.IUserSer;
import com.mysql.jdbc.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
public class InitBeans {
    @Bean
    public DataSource getDataSource(Environment env) {
        DruidDataSource source = new  DruidDataSource();
        source.setDriverClassName(env.getProperty("db.driver"));
        source.setUrl(env.getProperty("db.url"));
        source.setUsername(env.getProperty("db.username"));
        source.setPassword(env.getProperty("db.password"));
        source.setMaxActive(Integer.parseInt(env.getProperty("db.maxActive").trim()));
        source.setMaxWait(Integer.parseInt(env.getProperty("db.maxWait").trim()));
        source.setMinIdle(Integer.parseInt(env.getProperty("db.minIdle").trim()));
        return source;
    }

    /**
     * 跨域
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*");
            }
        };
    }
}
