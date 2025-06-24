package com.transport.platform.partnerservice.service.partner;

import com.transport.platform.commonevents.partnerservice.command.partner.*;
import com.transport.platform.commonevents.partnerservice.model.Action;
import com.transport.platform.partnerservice.mapper.PartnersMapper;
import com.transport.platform.partnerservice.model.*;
import com.transport.platform.partnerservice.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PartnerCommandHandler {
    public final PartnerEventPublisher partnerEventPublisher;
    private final PartnersMapper partnerMapper;
    private final PartnerRepository partnerRepository;

    @CacheEvict(cacheNames = {
            "partner",
            "partnersByOrg",
            "partners"}, allEntries = true)
    public Partner createBuyer(CreateBuyerCommand command) {
        Buyer buyer = partnerMapper.map(command);
        Buyer savedBuyer = partnerRepository.save(buyer);
        partnerEventPublisher.publishPartnerEvent(savedBuyer, Action.CREATE);
        return savedBuyer;
    }

    @CacheEvict(cacheNames = {
            "partner",
            "partnersByOrg",
            "partners"}, allEntries = true)
    public Buyer updateBuyerPartner(String id, UpdateBuyerCommand command) {
        Buyer existingBuyer = (Buyer) partnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));
        partnerMapper.map(existingBuyer, command);
        Buyer updatedBuyer = partnerRepository.save(existingBuyer);
        partnerEventPublisher.publishPartnerEvent(updatedBuyer, Action.UPDATE);
        return updatedBuyer;
    }

    @CacheEvict(cacheNames = {
            "partner",
            "partnersByOrg",
            "partners"}, allEntries = true)
    public Partner createCarrier(CreateCarrierCommand command) {
        Carrier carrier = partnerMapper.map(command);
        Carrier savedCarrier = partnerRepository.save(carrier);
        partnerEventPublisher.publishPartnerEvent(savedCarrier, Action.CREATE);
        return savedCarrier;
    }

    @CacheEvict(cacheNames = {
            "partner",
            "partnersByOrg",
            "partners"}, allEntries = true)
    public Carrier updateCarrierPartner(String id, UpdateCarrierCommand command) {
        Carrier existingCarrier = (Carrier) partnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrier not found"));
        partnerMapper.map(existingCarrier, command);
        Carrier updatedCarrier = partnerRepository.save(existingCarrier);
        partnerEventPublisher.publishPartnerEvent(updatedCarrier, Action.UPDATE);
        return updatedCarrier;
    }

    @CacheEvict(cacheNames = {
            "partner",
            "partnersByOrg",
            "partners"}, allEntries = true)
    public Partner createOperationalAdmin(CreateOperationalAdminCommand command) {
        OperationalAdmin operationalAdmin = partnerMapper.map(command);
        OperationalAdmin savedOperationalAdmin = partnerRepository.save(operationalAdmin);
        partnerEventPublisher.publishPartnerEvent(savedOperationalAdmin, Action.CREATE);
        return savedOperationalAdmin;
    }

    @CacheEvict(cacheNames = {
            "partner",
            "partnersByOrg",
            "partners"}, allEntries = true)
    public Partner createFinancialAdmin(CreateFinancialAdminCommand command) {
        FinancialAdmin financialAdmin = partnerMapper.map(command);
        FinancialAdmin savedFinancialAdmin = partnerRepository.save(financialAdmin);
        partnerEventPublisher.publishPartnerEvent(savedFinancialAdmin, Action.CREATE);
        return savedFinancialAdmin;

    }

    @CacheEvict(cacheNames = {
            "partner",
            "partnersByOrg",
            "partners"}, allEntries = true)
    public void deletePartner(String id) {
        Partner existing = partnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partner not found"));
        partnerRepository.deleteById(id);
        partnerEventPublisher.publishPartnerEvent(existing, Action.DELETE);
    }


}
