package com.javierdimastri.service;

import com.javierdimastri.model.Abc;
import com.javierdimastri.repository.AbcRepository;
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
public class AbcServiceImplTest {
    @InjectMocks
    AbcServiceImpl abcService;

    @Mock
    AbcRepository abcRepository;

    @Test
    public void getAllAbc_shouldCalledFindAllFromRepository_whenInvoked(){
        Abc firstCreatedAbc = new Abc("collection name", "blabla");
        List<Abc> mockedAbc = new ArrayList<>();
        mockedAbc.add(firstCreatedAbc);
        when(abcRepository.findAll()).thenReturn(mockedAbc);

        List<Abc> actualResult = abcService.getAllAbc();

        assertThat(actualResult).isEqualTo(mockedAbc);
        verify(abcRepository, times(1)).findAll();
    }
    @Test
    public void saveAbc_shouldCallSaveFromRepository_whenInvoked(){
        String name = "test name";
        String description = "test description";
        Abc firstCreatedAbc = new Abc("test name", "test description");
        when(abcRepository.save(firstCreatedAbc)).thenReturn(firstCreatedAbc);

        abcService.saveAbc(description, name);

        verify(abcRepository, times(1)).save(firstCreatedAbc);
    }

    @Test
    public void changeAbcBy_shouldCallUpdateAbcByFromRepository_whenInvoked() {
        String abcId = "5cc5e9914184de8673d7e1d1";
        final ObjectId ABC_ID = new ObjectId(abcId);
        Abc abcPayload = new Abc("test name", "test description");
        Abc updatedAbc = Abc.builder()
                .id(ABC_ID).name(abcPayload.getName()).description(abcPayload.getDescription())
                .build();
        when(abcRepository.updateAbcBy(ABC_ID, abcPayload)).thenReturn(updatedAbc);

        Abc actualResult = abcService.changeAbcBy(abcId, abcPayload);

        verify(abcRepository, times(1)).updateAbcBy(ABC_ID, abcPayload);
        assertThat(actualResult).isEqualTo(updatedAbc);

    }
}
