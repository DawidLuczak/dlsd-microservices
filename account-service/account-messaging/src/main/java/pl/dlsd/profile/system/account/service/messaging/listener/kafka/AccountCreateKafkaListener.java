package pl.dlsd.profile.system.account.service.messaging.listener.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.account.service.domain.ports.input.message.listener.account.AccountCreateMessageListener;
import pl.dlsd.profile.system.account.service.messaging.mapper.AccountMessagingDataMapper;
import pl.dlsd.profile.system.kafka.account.avro.model.AccountCreateAvroModel;
import pl.dlsd.profile.system.kafka.consumer.KafkaConsumer;

import java.util.List;

@Slf4j
//@Component
public class AccountCreateKafkaListener implements KafkaConsumer<AccountCreateAvroModel> {
    private final AccountMessagingDataMapper accountMessagingDataMapper;
    private final AccountCreateMessageListener accountCreateMessageListener;

    public AccountCreateKafkaListener(AccountMessagingDataMapper accountMessagingDataMapper, AccountCreateMessageListener accountCreateMessageListener) {
        this.accountMessagingDataMapper = accountMessagingDataMapper;
        this.accountCreateMessageListener = accountCreateMessageListener;
    }

    @Override
    @KafkaListener(id = "kafka-consumer", topics = "account-service")
    public void receive(@Payload List<AccountCreateAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        messages.forEach(accountCreateAvroModel -> {
            accountCreateMessageListener.accountCreated(accountCreateAvroModel.getId().toString());
        });
    }
}
