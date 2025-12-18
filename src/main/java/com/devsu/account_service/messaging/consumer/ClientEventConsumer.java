package com.devsu.account_service.messaging.consumer;

import com.devsu.account_service.messaging.event.ClientCreatedEvent;
import com.devsu.account_service.messaging.event.ClientDeletedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ClientEventConsumer {

    @RabbitListener(queues = "account.client.queue")
    public void handleClientCreated(ClientCreatedEvent event) {
        System.out.println("📥 Client created event received: " + event);

        // TODO:
        // - crear cuenta por defecto
        // - persistir relación cliente-cuenta
    }

    @RabbitListener(queues = "account.client.queue")
    public void handleClientDeleted(ClientDeletedEvent event) {
        System.out.println("📥 Client deleted event received: " + event);

        // TODO:
        // - deshabilitar cuentas
        // - marcar como inactivas
    }
}

