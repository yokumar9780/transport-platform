package com.transport.platform.partnerservice.controller;

import com.transport.platform.common.partnerservice.command.user.CreatePartnerUserCommand;
import com.transport.platform.common.partnerservice.command.user.UpdatePartnerUserCommand;
import com.transport.platform.partnerservice.model.PartnerUser;
import com.transport.platform.partnerservice.service.user.PartnerUserCommandHandler;
import com.transport.platform.partnerservice.service.user.PartnerUserQueryHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partner-users")
@RequiredArgsConstructor
@Tag(name = "Partner User Management", description = "Manage users under partners")
public class PartnerUserController {

    private final PartnerUserCommandHandler commandHandler;
    private final PartnerUserQueryHandler queryHandler;

    // --- CREATE ---
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreatePartnerUserCommand command) {
        PartnerUser user = commandHandler.createPartnerUser(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(user.getId());
    }

    // --- GET ALL ---
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(queryHandler.getAll());
    }

    // --- GET BY ID ---
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntity.ok(queryHandler.getById(id));
    }

    // --- DELETE ---
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        commandHandler.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // --- ACTIVATE ---
    @PatchMapping("/{id}/enable")
    public ResponseEntity<?> enable(@PathVariable String id) {
        return ResponseEntity.ok(commandHandler.enableUser(id));
    }

    // --- DISABLE ---
    @PatchMapping("/{id}/disable")
    public ResponseEntity<?> disable(@PathVariable String id) {
        return ResponseEntity.ok(commandHandler.disableUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdatePartnerUserCommand command) {
        PartnerUser updated = commandHandler.updateUser(id, command);
        return ResponseEntity.ok(updated);
    }
}

