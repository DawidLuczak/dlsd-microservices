package pl.dlsd.profile.system.account.service.messaging.publisher.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.account.service.domain.config.AccountServiceConfigData;
import pl.dlsd.profile.system.account.service.domain.event.AccountCreatedEvent;
import pl.dlsd.profile.system.account.service.domain.ports.output.message.publisher.AccountCreatedMessagePublisher;
import pl.dlsd.profile.system.account.service.messaging.mapper.AccountMessagingDataMapper;
import pl.dlsd.profile.system.kafka.account.avro.model.AccountCreateAvroModel;

import java.util.function.BiConsumer;


@Slf4j
//@Component
public class CreateAccountKafkaMessagePublisher implements AccountCreatedMessagePublisher {
    private final AccountMessagingDataMapper accountMessagingDataMapper;
    private final AccountServiceConfigData accountServiceConfigData;
//    private final KafkaProducer<String, AccountCreateAvroModel> kafkaProducer;

    public CreateAccountKafkaMessagePublisher(AccountMessagingDataMapper accountMessagingDataMapper, AccountServiceConfigData accountServiceConfigData) {
        this.accountMessagingDataMapper = accountMessagingDataMapper;
        this.accountServiceConfigData = accountServiceConfigData;
    }

    @Override
    public void publish(AccountCreatedEvent domainEvent) {
        String accountId = domainEvent.getAccount().getId().getValue().toString();
        log.info("Received AccountCreatedEvent for account id: {}", accountId);

        AccountCreateAvroModel accountCreateAvroModel = accountMessagingDataMapper.accountCreatedEventToAccountCreateAvroModel(domainEvent);
//        kafkaProducer.send(accountServiceConfigData.getAccountTopicName(), accountId, accountCreateAvroModel, kafkaCallback());
    }

    private BiConsumer<SendResult<String, AccountCreateAvroModel>, Throwable> kafkaCallback() {
        return (result, o2) -> {
            if (result != null) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info("Received successful");
            } else {
                log.info("Failed");
            }
        };
    }
}
