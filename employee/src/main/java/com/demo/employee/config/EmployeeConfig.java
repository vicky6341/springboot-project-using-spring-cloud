package com.demo.employee.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class EmployeeConfig {

    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }


    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory()
    {
        LettuceConnectionFactory lettuceConnectionFactory=new LettuceConnectionFactory();
        return lettuceConnectionFactory;
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate()
    {
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        return redisTemplate;
    }


}
