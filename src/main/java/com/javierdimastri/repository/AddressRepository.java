package com.javierdimastri.repository;
import com.javierdimastri.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, Long>, AddressRepositoryCustom {
}
