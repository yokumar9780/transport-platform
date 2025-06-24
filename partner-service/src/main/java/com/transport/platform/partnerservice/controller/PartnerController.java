package com.transport.platform.partnerservice.controller;

import com.transport.platform.common.partnerservice.command.partner.*;
import com.transport.platform.partnerservice.model.Buyer;
import com.transport.platform.partnerservice.model.Carrier;
import com.transport.platform.partnerservice.model.Partner;
import com.transport.platform.partnerservice.service.partner.PartnerCommandHandler;
import com.transport.platform.partnerservice.service.partner.PartnerQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partners")
@RequiredArgsConstructor
@Tag(name = "Partners", description = "CRUD operations for Buyers, Carriers, Admins")
public class PartnerController {

    private final PartnerCommandHandler commandHandler;
    private final PartnerQueryHandler queryHandler;

    // --- CREATE BUYER ---
    @Operation(summary = "Create a Buyer Partner", tags = "Buyer")
    @PostMapping(value = "/buyers", produces = "application/json")
    public ResponseEntity<?> createBuyer(@RequestBody CreateBuyerCommand command) {
        Partner saved = commandHandler.createBuyer(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // --- CREATE CARRIER ---
    @Operation(summary = "Create a Carrier Partner", tags = "Carrier")
    @PostMapping("/carriers")
    public ResponseEntity<?> createCarrier(@RequestBody CreateCarrierCommand command) {
        Partner saved = commandHandler.createCarrier(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved.getId());
    }

    // --- CREATE OPERATIONAL ADMIN ---
    @Operation(summary = "Create a operational admin Partner", tags = "Operational Admin")
    @PostMapping("/ops")
    public ResponseEntity<?> createOperationalAdmin(@RequestBody CreateOperationalAdminCommand command) {
        Partner saved = commandHandler.createOperationalAdmin(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved.getId());
    }

    // --- CREATE FINANCIAL ADMIN ---
    @Operation(summary = "Create a financial admin Partner", tags = "Financial Admin")
    @PostMapping("/fin")
    public ResponseEntity<?> createFinancialAdmin(@RequestBody CreateFinancialAdminCommand command) {
        Partner saved = commandHandler.createFinancialAdmin(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved.getId());
    }

    // --- GET ALL PARTNERS ---
    @Operation(summary = "Get all Partners", tags = "Partner Management")
    @GetMapping
    public ResponseEntity<?> getAllPartners() {
        return ResponseEntity.ok(queryHandler.getAll());
    }

    // --- GET PARTNER BY ID ---
    @GetMapping("/{id}")
    public ResponseEntity<?> getPartner(@PathVariable String id) {
        return ResponseEntity.ok(queryHandler.getById(id));
    }

    // --- DELETE ---
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePartner(@PathVariable String id) {
        commandHandler.deletePartner(id);
        return ResponseEntity.noContent().build();
    }

    // --- UPDATE ---
    @PutMapping("/buyers/{id}")
    public ResponseEntity<?> updateBuyerPartner(@PathVariable String id, @RequestBody UpdateBuyerCommand command) {
        Buyer updated = commandHandler.updateBuyerPartner(id, command);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/carrier/{id}")
    public ResponseEntity<?> updateCarrierPartner(@PathVariable String id, @RequestBody UpdateCarrierCommand command) {
        Carrier updated = commandHandler.updateCarrierPartner(id, command);
        return ResponseEntity.ok(updated);
    }

}

