package com.javierdimastri.repository;

import com.javierdimastri.model.Abc;
import org.bson.types.ObjectId;
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
    private final ObjectId ABC_ID = new ObjectId("5cc5e9914184de8673d7e1d1");
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

    @Test
    public void save_shouldReturnCreatedAbc_whenSaveRepositoryInvoked(){
        Abc firstCreatedAbc = new Abc("collection name", "blabla");

        Abc actualResult = abcRepository.save(firstCreatedAbc);

        assertThat(actualResult).isEqualTo(firstCreatedAbc);
    }

    @Test
    public void updateAbcBy_shouldUpdateExistingData_whenInvoked(){
        Abc abcPayload = new Abc("collection name", "blabla");
        Abc existingAbc = new Abc("collection existing", "blabl2a");
        existingAbc = abcRepository.save(existingAbc);
        Abc expectedUpdatedPayload = Abc.builder().id(existingAbc.getId())
                .name(abcPayload.getName())
                .description(abcPayload.getDescription())
                .build();

        Abc actualResult = abcRepository.updateAbcBy(existingAbc.getId(), abcPayload);

        assertThat(actualResult).isEqualTo(expectedUpdatedPayload);
    }
}
