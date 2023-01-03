package com.javierdimastri.repository;
import com.javierdimastri.model.Address;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, Long>, AddressRepositoryCustom {
    void deleteById(ObjectId objectId);
}
