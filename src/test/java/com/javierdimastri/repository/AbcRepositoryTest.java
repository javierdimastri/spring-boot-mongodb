package com.javierdimastri.repository;

import com.javierdimastri.model.Abc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class AbcRepositoryTest {
    @Autowired
    AbcRepository abcRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void setup() {
        mongoTemplate.dropCollection(Abc.class);
    }

    @After
    public void tearDown() {
        mongoTemplate.dropCollection(Abc.class);
    }

    @Test
    public void findAll_shouldReturnListAbc_whenRepositoryMethodInvoked(){
        Abc firstCreatedAbc = new Abc("collection name", "blabla");
        Abc secondCreatedAbc = new Abc("name2", "hihi");
        abcRepository.save(firstCreatedAbc);
        abcRepository.save(secondCreatedAbc);

        List<Abc> fetchedData = abcRepository.findAll();

        assertThat(fetchedData.get(0)).isEqualTo(firstCreatedAbc);
        assertThat(fetchedData.size()).isEqualTo(2);
    }
}
