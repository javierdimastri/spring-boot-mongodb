package com.javierdimastri.controller;

import com.javierdimastri.model.Address;
import com.javierdimastri.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @GetMapping
    public ResponseEntity <List<Address>> fetchAllAddress() {
        List<Address> fetchedData = addressService.getAllAddress();
        return ResponseEntity.ok().body(fetchedData);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity createAddress(@RequestBody Address payload) {
        Address createdAddress = addressService.saveAddress(payload.getName(), payload.getDescription());
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity modifyAddress(
            @PathVariable(value = "addressId") String addressId, @RequestBody Address payload
    ){
        Address updatedData = addressService.changeAddressBy(addressId, payload);
        return new ResponseEntity<>(updatedData, HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity removeAddress(@PathVariable(value = "addressId") String addressId){
        addressService.removeAddressById(addressId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
