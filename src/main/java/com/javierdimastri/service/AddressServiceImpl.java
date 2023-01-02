package com.javierdimastri.service;
import com.javierdimastri.model.Address;
import com.javierdimastri.repository.AddressRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    public AddressRepository addressRepository;
    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }
    @Override
    public Address saveAddress(String description, String name) {
        Address payload = Address.builder()
                .name(name)
                .description(description)
                .build();
        return addressRepository.save(payload);
    }
    @Override
    public Address changeAddressBy(String id, Address payload) {
        ObjectId objectId = new ObjectId(id);
        return addressRepository.updateAddressBy(objectId, payload);
    }
}
