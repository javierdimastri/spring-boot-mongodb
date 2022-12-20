package com.javierdimastri.service;
import com.javierdimastri.model.Abc;
import java.util.List;

public interface AbcService {
    List<Abc> getAllAbc();
    Abc saveAbc(String description, String name);

    Abc changeAbcBy(String id, Abc payload);
}
