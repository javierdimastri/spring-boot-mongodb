package com.javierdimastri.repository;

import com.javierdimastri.model.Address;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class AddressRepositoryImpl implements AddressRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public AddressRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Address updateAddressBy(ObjectId id, Address payload) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(id));
        FindAndReplaceOptions findAndReplaceOptions = new FindAndReplaceOptions().returnNew();
        return mongoTemplate
                .findAndReplace(query, payload, findAndReplaceOptions, Address.class, "address", Address.class);
    }
}
