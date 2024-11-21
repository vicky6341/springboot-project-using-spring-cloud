package com.demo.employee.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="gateway",path="/address-service")
public interface AddressClient {

    @GetMapping("/address")
    public ResponseEntity<AddressModel> getAddress();
}
