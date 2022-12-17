package com.javierdimastri.service;

import com.javierdimastri.model.Abc;
import com.javierdimastri.repository.AbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbcServiceImplementation implements AbcService {

    @Autowired
    public AbcRepository abcRepository;

    @Override
    public List<Abc> getAllAbc() {
        return abcRepository.findAll();
    }
}
