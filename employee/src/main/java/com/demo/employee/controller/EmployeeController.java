package com.demo.employee.controller;

import com.demo.employee.model.EmployeeRequest;
import com.demo.employee.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AddressClient addressClient;

    @Autowired
    private CacheService cacheService;

    @PostMapping("/data")
    public ResponseEntity<String> saveEmployee(@RequestBody EmployeeRequest employeeRequest)
    {
        employeeService.saveEmployee(employeeRequest);
        return new ResponseEntity<>("SUCESS", HttpStatus.CREATED);
    }


    @GetMapping("/data")
    public ResponseEntity<List<EmployeeRequest>> getAll()
    {
        return new ResponseEntity<>(employeeService.getAll(),HttpStatus.OK);
    }

    @PostMapping("/savedata")
    public ResponseEntity<String> saveEmp(EmployeeRequest employeeRequest)
    {
        employeeService.saveEmployee(employeeRequest);
        return new ResponseEntity<>("sucess",HttpStatus.OK);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<EmployeeRequest> getEmplyId(@PathVariable int id)
    {
        return new ResponseEntity<>(employeeService.getEmplId(id),HttpStatus.OK);
    }

    @PostMapping("/cachepost")
    public String postCache(@RequestBody EmployeeRequest employeeRequest)
    {
        return cacheService.saveCache(employeeRequest);
    }

    @GetMapping("/getcache")
    public EmployeeRequest getCache()
    {
        return cacheService.getCache();
    }

    @CircuitBreaker(name="address")
    @GetMapping("/demo")
    public ResponseEntity<AddressModel> getModel(@RequestHeader("name") String name)
    {
        System.out.println(name);
        return addressClient.getAddress();
    }

    public ResponseEntity<AddressModel> getModelFallBack(Throwable t)
    {
        return new ResponseEntity<>(new AddressModel(),HttpStatus.CREATED);
    }



}
