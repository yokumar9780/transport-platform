package com.transport.platform.partnerservice.service.user;

import com.transport.platform.common.exception.BadRequestException;
import com.transport.platform.common.exception.NotFoundException;
import com.transport.platform.common.partnerservice.command.user.CreatePartnerUserCommand;
import com.transport.platform.common.partnerservice.command.user.UpdatePartnerUserCommand;
import com.transport.platform.common.partnerservice.model.Action;
import com.transport.platform.partnerservice.mapper.PartnerUsersMapper;
import com.transport.platform.partnerservice.model.PartnerUser;
import com.transport.platform.partnerservice.repository.PartnerUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartnerUserCommandHandler {

    private final PartnerUserRepository userRepository;
    private final PartnerUsersMapper partnerUsersMapper;
    private final PartnerUserEventPublisher partnerUserEventPublisher;

    @CacheEvict(cacheNames = {
            "partnerUser",
            "partnerUsers"}, allEntries = true)
    public PartnerUser createPartnerUser(CreatePartnerUserCommand command) {
        PartnerUser partnerUser = partnerUsersMapper.map(command);
        PartnerUser savedPartnerUser = userRepository.save(partnerUser);
        partnerUserEventPublisher.publishPartnerUserEvent(savedPartnerUser, Action.CREATE);
        return savedPartnerUser;

    }

    @CacheEvict(cacheNames = {
            "partnerUser",
            "partnerUsers"}, allEntries = true)
    public PartnerUser updateUser(String userId, UpdatePartnerUserCommand command) {
        PartnerUser existing = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        PartnerUser updatedPartnerUser = partnerUsersMapper.map(existing, command);
        PartnerUser savePartnerUser = userRepository.save(updatedPartnerUser);
        partnerUserEventPublisher.publishPartnerUserEvent(savePartnerUser, Action.UPDATE);
        return savePartnerUser;
    }

    @CacheEvict(cacheNames = {
            "partnerUser",
            "partnerUsers"}, allEntries = true)
    public void deleteUser(String userId) {
        PartnerUser existing = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.deleteById(userId);
        partnerUserEventPublisher.publishPartnerUserEvent(existing, Action.DELETE);
    }

    @CacheEvict(cacheNames = {
            "partnerUser",
            "partnerUsers"}, allEntries = true)
    public PartnerUser disableUser(String userId) {
        PartnerUser user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found"));
        user.setActive(false);
        PartnerUser savePartnerUser = userRepository.save(user);
        partnerUserEventPublisher.publishPartnerUserEvent(savePartnerUser, Action.UPDATE);
        return savePartnerUser;
    }

    @CacheEvict(cacheNames = {
            "partnerUser",
            "partnerUsers"}, allEntries = true)
    public PartnerUser enableUser(String userId) {
        PartnerUser user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.setActive(true);
        PartnerUser savePartnerUser = userRepository.save(user);
        partnerUserEventPublisher.publishPartnerUserEvent(savePartnerUser, Action.UPDATE);
        return savePartnerUser;
    }
}

