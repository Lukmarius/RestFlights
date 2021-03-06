package com.codecool.restflights.Config;

import com.codecool.restflights.Service.Implementations.LoggingDispatcherServlet;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
class RestMVCConfig {

    // configuration of "/api" API, without controllers
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {

//        seting base path and rules for default rest repository exposing
        return new RepositoryRestConfigurerAdapter() {

            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.setBasePath("api/");
                config.setReturnBodyForPutAndPost(true);
                config.setReturnBodyOnCreate(true);
                config.setReturnBodyOnUpdate(true);
            }
        };
    }

//    Registration of DispatcherServlet
    @Bean
    public ServletRegistrationBean dispatcherRegistration() {
        return new ServletRegistrationBean(dispatcherServlet());
    }

    @Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
    public DispatcherServlet dispatcherServlet() {
        return new LoggingDispatcherServlet();
    }
}
