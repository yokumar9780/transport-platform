package com.transport.platform.partnerservice.service.partner;

import com.transport.platform.common.partnerservice.model.Action;
import com.transport.platform.common.partnerservice.model.EventEnvelope;
import com.transport.platform.partnerservice.config.rabbit.RabbitPartnerConfig;
import com.transport.platform.partnerservice.model.Partner;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PartnerEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishPartnerEvent(Partner partner, Action action) {
        EventEnvelope<Partner> eventEnvelope = new EventEnvelope<>(partner, action.name(), partner.getType().name());
        rabbitTemplate.convertAndSend(
                RabbitPartnerConfig.EXCHANGE_NAME,
                "", // Routing key ignored for fanout
                eventEnvelope,
                message -> {
                    message.getMessageProperties().setHeader("correlationId", UUID.randomUUID().toString());
                    return message;
                }
        );
    }
}
