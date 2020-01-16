package com.thoughtworks.onlinebookstore.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    ModelMapper getModelMapperBean() {
        return new ModelMapper();
    }

}
