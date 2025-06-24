package com.transport.platform.partnerservice.config.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitPartnerUserConfig {
    public static final String EXCHANGE_NAME = "partner.user.fanout";

    @Bean
    public FanoutExchange partnerUserFanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue partnerUserReportingQueue() {
        return new Queue("reporting.partner.user.events", true);
    }


    @Bean
    public Binding partnerUserBindReportingQueue(FanoutExchange partnerUserFanoutExchange, Queue partnerUserReportingQueue) {
        return BindingBuilder.bind(partnerUserReportingQueue).to(partnerUserFanoutExchange);
    }
}


