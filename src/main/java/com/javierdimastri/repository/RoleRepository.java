package com.javierdimastri.repository;

import com.javierdimastri.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, Long> {
    Role findByName(String Name);
}
