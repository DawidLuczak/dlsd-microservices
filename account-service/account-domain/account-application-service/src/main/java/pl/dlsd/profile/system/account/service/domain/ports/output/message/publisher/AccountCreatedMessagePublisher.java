package pl.dlsd.profile.system.account.service.domain.ports.output.message.publisher;

import pl.dlsd.profile.system.account.service.domain.event.AccountCreatedEvent;
import pl.dlsd.profile.system.domain.event.publisher.DomainEventPublisher;

public interface AccountCreatedMessagePublisher extends DomainEventPublisher<AccountCreatedEvent> {
}
