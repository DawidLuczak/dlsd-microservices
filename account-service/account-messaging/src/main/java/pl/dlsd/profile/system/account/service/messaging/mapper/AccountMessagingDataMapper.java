package pl.dlsd.profile.system.account.service.messaging.mapper;

import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.account.service.domain.entity.Account;
import pl.dlsd.profile.system.account.service.domain.event.AccountCreatedEvent;
import pl.dlsd.profile.system.kafka.account.avro.model.AccountCreateAvroModel;

@Component
public class AccountMessagingDataMapper {
    public AccountCreateAvroModel accountCreatedEventToAccountCreateAvroModel(AccountCreatedEvent accountCreatedEvent) {
        Account account = accountCreatedEvent.getAccount();
        return AccountCreateAvroModel.newBuilder()
                .setId(account.getId().getValue())
                .build();
    }

}
