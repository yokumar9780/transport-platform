package com.transport.platform.partnerservice.config.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitPartnerConfig {
    public static final String EXCHANGE_NAME = "partner.fanout";

    @Bean
    public FanoutExchange partnerFanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue partnerReportingQueue() {
        return new Queue("reporting.partner.events", true);
    }


    @Bean
    public Binding partnerBindReportingQueue(FanoutExchange partnerFanoutExchange, Queue partnerReportingQueue) {
        return BindingBuilder.bind(partnerReportingQueue).to(partnerFanoutExchange);
    }
}


