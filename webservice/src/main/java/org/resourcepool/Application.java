package org.resourcepool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@ImportResource("classpath:datasource.xml")
public class Application {

    public static final String JSON_UTF_8 = "application/json;charset=UTF-8";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
