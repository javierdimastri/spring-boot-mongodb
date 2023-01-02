package com.javierdimastri.repository;

import com.javierdimastri.model.Address;
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
public class AddressRepositoryTest {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void setup() {
        mongoTemplate.dropCollection(Address.class);
    }

    @After
    public void tearDown() {
        mongoTemplate.dropCollection(Address.class);
    }

    @Test
    public void findAll_shouldReturnListAddress_whenRepositoryMethodInvoked(){
        Address firstCreatedAddress = new Address("collection name", "blabla");
        Address secondCreatedAddress = new Address("name2", "hihi");
        addressRepository.save(firstCreatedAddress);
        addressRepository.save(secondCreatedAddress);

        List<Address> fetchedData = addressRepository.findAll();

        assertThat(fetchedData.get(0)).isEqualTo(firstCreatedAddress);
        assertThat(fetchedData.size()).isEqualTo(2);
    }

    @Test
    public void save_shouldReturnCreatedAddress_whenSaveRepositoryInvoked(){
        Address firstCreatedAddress = new Address("collection name", "blabla");

        Address actualResult = addressRepository.save(firstCreatedAddress);

        assertThat(actualResult).isEqualTo(firstCreatedAddress);
    }

    @Test
    public void updateAddressBy_shouldUpdateExistingData_whenInvoked(){
        Address addressPayload = new Address("collection name", "blabla");
        Address existingAddress = new Address("collection existing", "blabl2a");
        existingAddress = addressRepository.save(existingAddress);
        Address expectedUpdatedPayload = Address.builder().id(existingAddress.getId())
                .name(addressPayload.getName())
                .description(addressPayload.getDescription())
                .build();

        Address actualResult = addressRepository.updateAddressBy(existingAddress.getId(), addressPayload);

        assertThat(actualResult).isEqualTo(expectedUpdatedPayload);
    }
}
