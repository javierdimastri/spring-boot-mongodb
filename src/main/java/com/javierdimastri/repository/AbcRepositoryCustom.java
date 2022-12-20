package com.javierdimastri.repository;
import com.javierdimastri.model.Abc;
import org.bson.types.ObjectId;

public interface AbcRepositoryCustom {
    Abc updateAbcBy(ObjectId id, Abc payload);
}
