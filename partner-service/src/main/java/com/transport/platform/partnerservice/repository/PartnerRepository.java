package com.transport.platform.partnerservice.repository;

import com.transport.platform.partnerservice.model.Partner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PartnerRepository extends MongoRepository<Partner, String> {
    Set<Partner> findByOrganization_OrgId(String orgId);
}
