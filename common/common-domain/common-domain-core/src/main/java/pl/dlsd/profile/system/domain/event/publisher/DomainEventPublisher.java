package pl.dlsd.profile.system.domain.event.publisher;

import pl.dlsd.profile.system.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent>{
    void publish(T domainEvent);
}
