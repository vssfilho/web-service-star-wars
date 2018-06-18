package com.b2wdigital.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.b2wdigital") // package que irá ser utilizado no rest
public class ApplicationConfiguration {

}