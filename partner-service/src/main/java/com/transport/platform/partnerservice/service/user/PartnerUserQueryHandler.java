package com.transport.platform.partnerservice.service.user;

import com.transport.platform.common.exception.NotFoundException;
import com.transport.platform.partnerservice.model.PartnerUser;
import com.transport.platform.partnerservice.repository.PartnerUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PartnerUserQueryHandler {

    private final PartnerUserRepository repo;

    @Cacheable(value = "partnerUser", key = "#id")
    public PartnerUser getById(String id) {
        return repo.findById(id).
                orElseThrow(() -> new NotFoundException("Buyer not found"));
    }

    @Cacheable(value = "partnerUsers")
    public Set<PartnerUser> getAll() {
        return repo.findAll().stream().collect(Collectors.toSet());
    }
}
