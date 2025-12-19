package com.devsu.account_service.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;


@Configuration
public class RabbitMQConfig {

    public static final String CLIENT_EXCHANGE = "client.events";
    public static final String ACCOUNT_QUEUE = "account.client.queue";

    @Bean
    public TopicExchange clientExchange() {
        return new TopicExchange(CLIENT_EXCHANGE);
    }

    @Bean
    public Queue accountClientQueue() {
        return QueueBuilder.durable(ACCOUNT_QUEUE).build();
    }

    @Bean
    public Binding clientCreatedBinding() {
        return BindingBuilder
                .bind(accountClientQueue())
                .to(clientExchange())
                .with("client.created");
    }

    @Bean
    public Binding clientDeletedBinding() {
        return BindingBuilder
                .bind(accountClientQueue())
                .to(clientExchange())
                .with("client.deleted");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

