package com.demo.address;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address-service")
public class AddressController {

    @GetMapping("/address")
    public ResponseEntity<AddressModel> getAddress()
    {
        AddressModel addressModel=new AddressModel();
        addressModel.setCity("palasa");
        addressModel.setDistrict("srikakulam");
        return new ResponseEntity<>(addressModel, HttpStatus.OK);
    }
}
