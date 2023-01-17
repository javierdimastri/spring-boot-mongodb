package com.javierdimastri.repository;
import com.javierdimastri.model.Address;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends MongoRepository<Address, Long>, AddressRepositoryCustom {
    void deleteById(ObjectId objectId);
}
