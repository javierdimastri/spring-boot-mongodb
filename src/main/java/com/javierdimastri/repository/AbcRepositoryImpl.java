package com.javierdimastri.repository;

import com.javierdimastri.model.Abc;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class AbcRepositoryImpl implements AbcRepositoryCustom{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public AbcRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Abc updateAbcBy(ObjectId id, Abc payload) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(id));
        FindAndReplaceOptions findAndReplaceOptions = new FindAndReplaceOptions().returnNew();
        return mongoTemplate
                .findAndReplace(query, payload, findAndReplaceOptions, Abc.class, "abc", Abc.class);
    }
}
