package com.javierdimastri.repository;

import com.javierdimastri.model.ERole;
import com.javierdimastri.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
