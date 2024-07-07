package pl.dlsd.profile.system.account.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.account.service.domain.ports.input.message.listener.account.AccountCreateMessageListener;

@Slf4j
@Component
public class AccountCreateMessageListenerImpl implements AccountCreateMessageListener {
    @Override
    public void accountCreated(String id) {
        log.info("AccountCreateMessageListener: {}", id);
    }
}
