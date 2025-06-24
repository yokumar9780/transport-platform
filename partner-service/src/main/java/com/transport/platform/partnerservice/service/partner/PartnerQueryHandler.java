package com.transport.platform.partnerservice.service.partner;

import com.transport.platform.common.exception.NotFoundException;
import com.transport.platform.partnerservice.model.Partner;
import com.transport.platform.partnerservice.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PartnerQueryHandler {

    private final PartnerRepository repo;

    @Cacheable(value = "partner", key = "#id")
    public Partner getById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Partner not found"));
    }

    @Cacheable(value = "partnersByOrg", key = "#orgId")
    public Set<Partner> getByOrg(String orgId) {
        return repo.findByOrganization_OrgId(orgId);
    }

    @Cacheable(value = "partners")
    public Set<Partner> getAll() {
        return repo.findAll().stream().collect(Collectors.toSet());
    }

}
