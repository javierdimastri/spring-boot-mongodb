package com.javierdimastri.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javierdimastri.model.Abc;
import com.javierdimastri.service.AbcService;
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

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(AbcController.class)
@AutoConfigureMockMvc
public class AbcControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    AbcService abcService;

    @InjectMocks
    AbcController controller;

    private final ObjectId ABC_ID = new ObjectId("5cc5e9914184de8673d7e1d1");

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    public void fetchAllAbc_shouldReturnStatusOkAndCallGetAllAbcFromService_whenInvoked() throws Exception{
        Abc firstCreatedAbc = new Abc("collection name", "blabla");
        List<Abc> abcList = new ArrayList<>();
        abcList.add(firstCreatedAbc);
        when(abcService.getAllAbc()).thenReturn(abcList);

        mockMvc.perform(get("/abc"))
                .andExpect(status().isOk());

        verify(abcService, times(1)).getAllAbc();
    }

    @Test
    public void createAbc_shouldReturnStatusCreatedAndCallSaveAbcFromService_whenInvokedWithCorrectPayload() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Abc firstCreatedAbc = new Abc("collection name", "blabla");
        when(abcService.saveAbc(firstCreatedAbc.getName(), firstCreatedAbc.getDescription()))
                .thenReturn(firstCreatedAbc);

        mockMvc.perform(
                post("/abc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstCreatedAbc)))
                .andExpect(status().isCreated());

        verify(abcService, times(1))
                .saveAbc(firstCreatedAbc.getName(), firstCreatedAbc.getDescription());
    }

    @Test
    public void modifyAbc_shouldReturnStatusOkAndCallchangeAbcByFromService_whenInvokedWithCorrectPayload()
            throws Exception{
        String abcId ="5cc5e9914184de8673d7e1d1";
        String urlTemplate = "/abc"+ "/" + abcId;
        ObjectMapper objectMapper = new ObjectMapper();
        Abc abcPayload = new Abc("collection name", "blabla");
        Abc updatedAbc = Abc
                .builder().id(ABC_ID).name(abcPayload.getName()).description(abcPayload.getDescription())
                        .build();
        when(abcService.changeAbcBy(abcId, abcPayload))
                .thenReturn(updatedAbc);

        mockMvc.perform(
                put(urlTemplate)
                        .content(objectMapper.writeValueAsString(abcPayload))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect((status().isOk()));

        verify(abcService, times(1))
                .changeAbcBy(abcId, abcPayload);
    }
}
