package pl.dlsd.profile.system.account.service.domain.ports.input.message.listener.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountCreateMessageListenerImpl implements AccountCreateMessageListener {
    @Override
    public void accountCreated(String id) {
        log.info("AccountCreateMessageListener: {}", id);
    }
}
