package com.javierdimastri.service;
import com.javierdimastri.model.Address;
import org.bson.types.ObjectId;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddress();
    Address saveAddress(String description, String name);
    Address changeAddressBy(String id, Address payload);
    void removeAddressById(String id);
}
