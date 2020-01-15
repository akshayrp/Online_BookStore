package com.thoughtworks.onlinebookstore.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    ModelMapper getModelMapperBean() {
        return new ModelMapper();
    }

}
