package com.javierdimastri.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javierdimastri.model.Address;
import com.javierdimastri.service.AddressService;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(AddressController.class)
@AutoConfigureMockMvc
public class AddressControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    AddressService addressService;

    @InjectMocks
    AddressController controller;

    private final ObjectId ADDRESS_ID = new ObjectId("5cc5e9914184de8673d7e1d1");

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    public void fetchAllAddress_shouldReturnStatusOkAndCallGetAllAddressFromService_whenInvoked() throws Exception{
        Address firstCreatedAddress = new Address("collection name", "blabla");
        List<Address> addressList = new ArrayList<>();
        addressList.add(firstCreatedAddress);
        when(addressService.getAllAddress()).thenReturn(addressList);

        mockMvc.perform(get("/address"))
                .andExpect(status().isOk());

        verify(addressService, times(1)).getAllAddress();
    }

    @Test
    public void createAddress_shouldReturnStatusCreatedAndCallSaveAddressFromService_whenInvokedWithCorrectPayload() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Address firstCreatedAddress = new Address("collection name", "blabla");
        when(addressService.saveAddress(firstCreatedAddress.getName(), firstCreatedAddress.getDescription()))
                .thenReturn(firstCreatedAddress);

        mockMvc.perform(
                post("/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstCreatedAddress)))
                .andExpect(status().isCreated());

        verify(addressService, times(1))
                .saveAddress(firstCreatedAddress.getName(), firstCreatedAddress.getDescription());
    }

    @Test
    public void modifyAddress_shouldReturnStatusOkAndCallchangeAddressByFromService_whenInvokedWithCorrectPayload()
            throws Exception{
        String addressId ="5cc5e9914184de8673d7e1d1";
        String urlTemplate = "/address"+ "/" + addressId;
        ObjectMapper objectMapper = new ObjectMapper();
        Address addressPayload = new Address("collection name", "blabla");
        Address updatedAddress = Address
                .builder().id(ADDRESS_ID).name(addressPayload.getName()).description(addressPayload.getDescription())
                        .build();
        when(addressService.changeAddressBy(addressId, addressPayload))
                .thenReturn(updatedAddress);

        mockMvc.perform(
                put(urlTemplate)
                        .content(objectMapper.writeValueAsString(addressPayload))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect((status().isOk()));

        verify(addressService, times(1))
                .changeAddressBy(addressId, addressPayload);
    }

    @Test
    public void removeAddress_shouldReturnRemoveAddressByIdFromServiceAndReturnStatusOk_whenInvoked() throws Exception{
        String addressId ="5cc5e9914184de8673d7e1d1";
        String urlTemplate = "/address"+ "/" + addressId;

        mockMvc.perform(
                delete(urlTemplate)
        ).andExpect(status().isOk());

        verify(addressService, times(1))
                .removeAddressById(addressId);
    }
}
