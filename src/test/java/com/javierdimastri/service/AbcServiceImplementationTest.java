package com.javierdimastri.service;

import com.javierdimastri.model.Abc;
import com.javierdimastri.repository.AbcRepository;
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
public class AbcServiceImplementationTest {
    @InjectMocks
    AbcServiceImplementation abcService;

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
}
