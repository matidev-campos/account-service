package com.devsu.account_service.messaging.consumer;

import com.devsu.account_service.config.RabbitMQConfig;
import com.devsu.account_service.messaging.event.ClientCreatedEvent;
import com.devsu.account_service.messaging.event.ClientDeletedEvent;
import com.devsu.account_service.service.AccountService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class ClientEventConsumer {

    private final AccountService accountService;

    public ClientEventConsumer(AccountService accountService) {
        this.accountService = accountService;
    }

    @RabbitListener(queues = RabbitMQConfig.ACCOUNT_QUEUE)
    public void handleEvent(Object event) {

        if (event instanceof ClientCreatedEvent createdEvent) {
            handleClientCreated(createdEvent);
        }
        else if (event instanceof ClientDeletedEvent deletedEvent) {
            handleClientDeleted(deletedEvent);
        }
    }

    private void handleClientCreated(ClientCreatedEvent event) {

        accountService.handleClientCreated(event.clientId());
    }

    private void handleClientDeleted(ClientDeletedEvent event) {

        accountService.handleClientDeleted(event.clientId());
    }
}



