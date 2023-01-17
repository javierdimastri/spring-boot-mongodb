package com.javierdimastri.repository;

import com.javierdimastri.model.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RoleRepositoryTest {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    @Before
    public void setup() {
        mongoTemplate.dropCollection(Role.class);
    }

    @After
    public void tearDown() {
        mongoTemplate.dropCollection(Role.class);
    }
    @Test
    public void findAllByName_shouldReturnRoleModeratorByItsName_whenInvoked() {
        Role createdRole = new Role("ROLE_MODERATOR");
        roleRepository.save(createdRole);

        Role actualResult = roleRepository.findByName("ROLE_MODERATOR");

        assertThat(actualResult.getName()).isEqualTo(createdRole.getName());
    }
}
