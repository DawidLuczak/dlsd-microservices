package pl.dlsd.profile.system.account.service.domain.ports.input.message.listener.account;


public interface AccountCreateMessageListener {
    void accountCreated(String id);
}
