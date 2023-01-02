package com.javierdimastri.repository;
import com.javierdimastri.model.Address;
import org.bson.types.ObjectId;

public interface AddressRepositoryCustom {
    Address updateAddressBy(ObjectId id, Address payload);
}
