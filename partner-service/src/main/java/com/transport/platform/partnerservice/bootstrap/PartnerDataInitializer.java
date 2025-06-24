package com.transport.platform.partnerservice.bootstrap;


import com.transport.platform.commonevents.partnerservice.command.partner.CreateBuyerCommand;
import com.transport.platform.commonevents.partnerservice.command.partner.CreateCarrierCommand;
import com.transport.platform.commonevents.partnerservice.command.partner.CreateFinancialAdminCommand;
import com.transport.platform.commonevents.partnerservice.command.partner.CreateOperationalAdminCommand;
import com.transport.platform.commonevents.partnerservice.command.user.CreatePartnerUserCommand;
import com.transport.platform.commonevents.partnerservice.model.Address;
import com.transport.platform.commonevents.partnerservice.model.Contact;
import com.transport.platform.commonevents.partnerservice.model.Organization;
import com.transport.platform.commonevents.partnerservice.model.PartnerType;
import com.transport.platform.partnerservice.model.Partner;
import com.transport.platform.partnerservice.repository.PartnerRepository;
import com.transport.platform.partnerservice.repository.PartnerUserRepository;
import com.transport.platform.partnerservice.service.partner.PartnerCommandHandler;
import com.transport.platform.partnerservice.service.user.PartnerUserCommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class PartnerDataInitializer implements CommandLineRunner {

    private final PartnerRepository partnerRepository;
    private final PartnerUserRepository userRepository;
    private final PartnerCommandHandler partnerCommandHandler;
    private final PartnerUserCommandHandler partnerUserCommandHandler;

    @Override
    public void run(String... args) {
        if (partnerRepository.count() > 0) {
            System.out.println("Partner data already initialized, skipping...");
            return;
        }

        // Clear old data (optional)
        partnerRepository.deleteAll();
        userRepository.deleteAll();

        createBuyer();

        createCarrier();

        createOpsAccount();

        createFinAccount();
    }

    private void createBuyer() {
        // Buyer
        CreateBuyerCommand createBuyerCommand = new CreateBuyerCommand(
                "Volvo Buyer",
                "admin@volvo.com",
                new Organization("volvo", "Volvo AB", "EXTERNAL"),
                new Contact("buyer@volvo.com", "+46-111111"),
                new Address("Gothenburg", "SE", "41101", "Hisingen"),
                "buyerSpecificField"
        );
        Partner buyer = partnerCommandHandler.createBuyer(createBuyerCommand);

        // Buyer Admin User
        CreatePartnerUserCommand buyerAdmin = new CreatePartnerUserCommand(
                "admin@volvo.com",
                buyer.getId(),
                PartnerType.BUYER,
                Set.of("BUYER_ADMIN"),
                true,
                buyer.getOrganization().orgId()
        );
        partnerUserCommandHandler.createPartnerUser(buyerAdmin);
    }

    private void createCarrier() {
        // Carrier
        CreateCarrierCommand createCarrierCommand = new CreateCarrierCommand(
                "DHL Carrier",
                "ops@dhl.com",
                new Organization("dhl", "DHL Global", "EXTERNAL"),
                new Contact("ops@dhl.com", "+46-222222"),
                new Address("Stockholm", "SE", "11223", "Kungsholmen"),
                10
        );
        Partner carrier = partnerCommandHandler.createCarrier(createCarrierCommand);
        // Carrier Admin
        partnerUserCommandHandler.createPartnerUser(
                new CreatePartnerUserCommand(
                        "ops@dhl.com",
                        carrier.getId(),
                        PartnerType.CARRIER,
                        Set.of("CARRIER_ADMIN"),
                        true,
                        carrier.getOrganization().orgId()
                )
        );
    }

    private void createOpsAccount() {
        // Operational Admin
        CreateOperationalAdminCommand createOperationalAdminCommand = new CreateOperationalAdminCommand(
                "Ops Admin",
                "ops@gtt.com",
                new Organization("GTT", "GTT Global", "INTERNAL"),
                new Contact("ops@gtt.com", "+46-222222"),
                new Address("Stockholm", "SE", "11223", "Kungsholmen")
        );
        Partner operationalAdmin = partnerCommandHandler.createOperationalAdmin(createOperationalAdminCommand);

        // Operational Admin
        partnerUserCommandHandler.createPartnerUser(
                new CreatePartnerUserCommand(
                        "ops@platform.com",
                        operationalAdmin.getId(),
                        PartnerType.OPERATIONAL_ADMIN,
                        Set.of("OPS_ADMIN"),
                        true,
                        operationalAdmin.getOrganization().orgId()
                )
        );
    }

    private void createFinAccount() {
        // Financial Admin
        CreateFinancialAdminCommand createFinancialAdminCommand = new CreateFinancialAdminCommand(
                "Finance Admin",
                "fin@platform.com",
                new Organization("GTTFin", "GTTFin Global", "INTERNAL"),
                new Contact("ops@GTTFin.com", "+46-222222"),
                new Address("Stockholm", "SE", "11223", "Kungsholmen")
        );
        Partner financialAdmin = partnerCommandHandler.createFinancialAdmin(createFinancialAdminCommand);
        // Financial Admin
        partnerUserCommandHandler.createPartnerUser(
                new CreatePartnerUserCommand(
                        "fin@platform.com",
                        financialAdmin.getId(),
                        PartnerType.FINANCIAL_ADMIN,
                        Set.of("FIN_ADMIN"),
                        true,
                        financialAdmin.getOrganization().orgId()
                )
        );
    }


}
