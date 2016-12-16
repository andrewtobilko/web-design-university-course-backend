package com.tobilko.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

@SpringBootConfiguration
@EnableAutoConfiguration
@ServletComponentScan
public class Runner extends SpringBootServletInitializer {


    public @Bean Servlet getGenericServlet() {
        return new HttpServlet() {

        };
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Runner.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

}
