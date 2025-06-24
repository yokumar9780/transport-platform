package com.transport.platform.partnerservice.repository;

import com.transport.platform.partnerservice.model.PartnerUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface PartnerUserRepository extends MongoRepository<PartnerUser, String> {
    Set<PartnerUser> findByOrganizationId(String orgId);
}