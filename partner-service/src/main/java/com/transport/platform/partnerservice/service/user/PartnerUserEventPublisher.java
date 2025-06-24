package com.transport.platform.partnerservice.service.user;

import com.transport.platform.common.partnerservice.model.Action;
import com.transport.platform.common.partnerservice.model.EventEnvelope;
import com.transport.platform.partnerservice.config.rabbit.RabbitPartnerUserConfig;
import com.transport.platform.partnerservice.model.PartnerUser;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PartnerUserEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishPartnerUserEvent(PartnerUser partnerUser, Action action) {
        EventEnvelope<PartnerUser> eventEnvelope = new EventEnvelope<>(partnerUser, action.name(), partnerUser.getPartnerType().name());
        rabbitTemplate.convertAndSend(
                RabbitPartnerUserConfig.EXCHANGE_NAME,
                "", // Routing key ignored for fanout
                eventEnvelope,
                message -> {
                    message.getMessageProperties().setHeader("correlationId", UUID.randomUUID().toString());
                    return message;
                }
        );
    }

}
