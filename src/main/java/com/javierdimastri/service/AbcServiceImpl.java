package com.javierdimastri.service;
import com.javierdimastri.model.Abc;
import com.javierdimastri.repository.AbcRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AbcServiceImpl implements AbcService {

    @Autowired
    public AbcRepository abcRepository;
    @Override
    public List<Abc> getAllAbc() {
        return abcRepository.findAll();
    }
    @Override
    public Abc saveAbc(String description, String name) {
        Abc payload = Abc.builder()
                .name(name)
                .description(description)
                .build();
        return abcRepository.save(payload);
    }
    @Override
    public Abc changeAbcBy(String id, Abc payload) {
        ObjectId objectId = new ObjectId(id);
        return abcRepository.updateAbcBy(objectId, payload);
    }
}