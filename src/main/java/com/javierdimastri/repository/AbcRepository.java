package com.javierdimastri.repository;
import com.javierdimastri.model.Abc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AbcRepository extends MongoRepository<Abc, Long> {

}
