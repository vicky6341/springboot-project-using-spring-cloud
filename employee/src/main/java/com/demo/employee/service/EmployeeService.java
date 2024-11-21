package com.demo.employee.service;

import com.demo.employee.entity.Employee;
import com.demo.employee.model.EmployeeRequest;
import com.demo.employee.repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;


    public void saveEmployee(EmployeeRequest employeeRequest)
    {
        Employee employee=modelMapper.map(employeeRequest,Employee.class);
        employeeRepo.save(employee);
    }

    @Cacheable(value="employees",key="'allemployees'")
    public List<EmployeeRequest> getAll()
    {
        List<Employee> lstOfEmplys= employeeRepo.findAll();
        return lstOfEmplys.stream().map(employee -> modelMapper.map(employee,EmployeeRequest.class)).collect(Collectors.toList());
    }

    @Cacheable(value="employees",key="#id")
    public EmployeeRequest getEmplId(int id) {
        EmployeeRequest employeeRequest=modelMapper.map(employeeRepo.findById(id),EmployeeRequest.class);
        return employeeRequest;
    }
}
