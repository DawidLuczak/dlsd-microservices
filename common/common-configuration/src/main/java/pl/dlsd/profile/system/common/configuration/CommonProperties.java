package pl.dlsd.profile.system.common.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(
        prefix = "dlsd",
        ignoreUnknownFields = false
)
public class CommonProperties {
    private final Mail mail = new Mail();

    public CommonProperties() {}

    @Setter
    @Getter
    public static class Mail {
        private boolean enabled = false;
        private String from = "";
        private String baseUrl = "";

        public Mail() {
        }
    }
}
