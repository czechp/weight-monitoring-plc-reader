package app.web.readFromPlc.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RequestSenderConfiguration {
    private long moduleId;
    private String serverUrl;
    private String endpoint;
    private String login;
    private String password;
}
