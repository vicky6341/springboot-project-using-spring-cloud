package com.demo.employee.controller;

import com.demo.employee.model.EmployeeRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    private HashOperations<String,String,Object> hashOperations;

    @PostConstruct
    public void init()
    {
        hashOperations=redisTemplate.opsForHash();
    }

    public String saveCache(EmployeeRequest employeeRequest)
    {
        hashOperations.put("Employee","111",employeeRequest);
        return "saved";
    }

    public EmployeeRequest getCache() {
        return (EmployeeRequest) hashOperations.get("Employee","111");
    }
}
