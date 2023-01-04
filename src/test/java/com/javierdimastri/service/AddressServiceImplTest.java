package com.javierdimastri.service;

import com.javierdimastri.model.Address;
import com.javierdimastri.repository.AddressRepository;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {
    @InjectMocks
    AddressServiceImpl addressService;

    @Mock
    AddressRepository addressRepository;

    @Test
    public void getAllAddress_shouldCalledFindAllFromRepository_whenInvoked(){
        Address firstCreatedAddress = new Address("collection name", "blabla");
        List<Address> mockedAddress = new ArrayList<>();
        mockedAddress.add(firstCreatedAddress);
        when(addressRepository.findAll()).thenReturn(mockedAddress);

        List<Address> actualResult = addressService.getAllAddress();

        assertThat(actualResult).isEqualTo(mockedAddress);
        verify(addressRepository, times(1)).findAll();
    }
    @Test
    public void saveAddress_shouldCallSaveFromRepository_whenInvoked(){
        String name = "test name";
        String description = "test description";
        Address firstCreatedAddress = new Address("test name", "test description");
        when(addressRepository.save(firstCreatedAddress)).thenReturn(firstCreatedAddress);

        addressService.saveAddress(description, name);

        verify(addressRepository, times(1)).save(firstCreatedAddress);
    }

    @Test
    public void changeAddressBy_shouldCallUpdateAddressByFromRepository_whenInvoked() {
        String addressId = "5cc5e9914184de8673d7e1d1";
        final ObjectId ADDRESS_ID = new ObjectId(addressId);
        Address addressPayload = new Address("test name", "test description");
        Address updatedAddress = Address.builder()
                .id(ADDRESS_ID).name(addressPayload.getName()).description(addressPayload.getDescription())
                .build();
        when(addressRepository.updateAddressBy(ADDRESS_ID, addressPayload)).thenReturn(updatedAddress);

        Address actualResult = addressService.changeAddressBy(addressId, addressPayload);

        verify(addressRepository, times(1)).updateAddressBy(ADDRESS_ID, addressPayload);
        assertThat(actualResult).isEqualTo(updatedAddress);

    }

    @Test
    public void removeAddressById_shouldCallDeleteByIdFromRepository_whenInvoked() {
        String addressId = "5cc5e9914184de8673d7e1d1";
        final ObjectId ADDRESS_ID = new ObjectId(addressId);

        addressService.removeAddressById(addressId);

        verify(addressRepository, times(1)).deleteById(ADDRESS_ID);
    }
}
