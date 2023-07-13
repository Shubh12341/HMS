package com.hospitalmanagement.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PatientConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
