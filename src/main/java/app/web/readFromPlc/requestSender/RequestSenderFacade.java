package app.web.readFromPlc.requestSender;

import app.web.readFromPlc.configuration.RequestSenderConfiguration;
import app.web.readFromPlc.weightModule.RequestSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;

public class RequestSenderFacade implements RequestSender {
    private final Logger logger = LoggerFactory.getLogger(RequestSenderFacade.class);
    private final RequestSenderConfiguration configuration;

    public RequestSenderFacade(RequestSenderConfiguration configuration) {
        this.configuration = configuration;
    }

    private static HttpRequest.BodyPublisher createRequestBody(String basicModuleJson) {
        return HttpRequest.BodyPublishers.ofString(basicModuleJson);
    }

    @Override
    public void sendData(String requestBody) throws URISyntaxException, IOException, InterruptedException {
        final var ENDPOINT = configuration.getEndpoint() + configuration.getModuleId();
        HttpRequest.Builder requestBuilder = createRequestBuilder(ENDPOINT);
        addJsonContentHeader(requestBuilder);
        final var httpRequest = requestBuilder.method("PATCH", createRequestBody(requestBody)).build();

        sendRequest(httpRequest);
    }

    private HttpRequest.Builder createRequestBuilder(String ENDPOINT) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(configuration.getServerUrl() + ENDPOINT))
                .header("Authorization", hashAuthorizationHeader())
                .timeout(Duration.of(10, ChronoUnit.SECONDS));
    }

    private String hashAuthorizationHeader() {
        final var hashCode = Base64.getEncoder().encodeToString((configuration.getLogin() + ":" + configuration.getPassword()).getBytes());
        final var basicAuthorizationHeader = "Basic " + hashCode;
        return basicAuthorizationHeader;
    }

    private void addJsonContentHeader(HttpRequest.Builder builder) {
        builder.header("Content-Type", "application/json");
    }

    private void sendRequest(HttpRequest httpRequest) {
        final var httpClient = HttpClient.newBuilder().build();
        CompletableFuture<HttpResponse<String>> futureHttpResponse = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
        futureHttpResponse.thenAccept((httpResponse) -> {});
    }
}
