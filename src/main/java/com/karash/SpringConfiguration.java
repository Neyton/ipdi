package com.karash;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.karash.controllers.MyDoubleSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(SpringConfiguration.class, args);
    }

    @Bean
    public ObjectMapper getModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(double.class, new MyDoubleSerializer());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper;
    }

}
