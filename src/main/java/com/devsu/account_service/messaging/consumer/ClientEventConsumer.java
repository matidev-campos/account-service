package com.devsu.account_service.messaging.consumer;

import com.devsu.account_service.config.RabbitMQConfig;
import com.devsu.account_service.messaging.event.ClientCreatedEvent;
import com.devsu.account_service.messaging.event.ClientDeletedEvent;
import com.devsu.account_service.service.AccountService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class ClientEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(ClientEventConsumer.class);

    private final AccountService accountService;

    public ClientEventConsumer(AccountService accountService) {
        this.accountService = accountService;
    }

    @RabbitListener(queues = RabbitMQConfig.ACCOUNT_QUEUE)
    public void onClientCreated(ClientCreatedEvent event) {
        log.info(
                "Received ClientCreatedEvent | clientId={} | name={} | identification={}",
                event.clientId(),
                event.name(),
                event.identification()
        );
        accountService.handleClientCreated(event.clientId());
    }

    @RabbitListener(queues = RabbitMQConfig.ACCOUNT_QUEUE)
    public void onClientDeleted(ClientDeletedEvent event) {
        log.info(
                "Received ClientDeletedEvent | clientId={}",
                event.clientId()
        );
        accountService.handleClientDeleted(event.clientId());
    }
}