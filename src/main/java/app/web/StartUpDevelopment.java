package app.web;

import app.web.readFromPlc.configuration.PlcConfiguration;
import app.web.readFromPlc.configuration.RequestSenderConfiguration;
import app.web.readFromPlc.weightModule.WeightModuleFirst;
import app.web.readFromPlc.weightModule.WeightModuleLast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Profile({"development"})
class StartUpDevelopment {
    private final Logger logger = LoggerFactory.getLogger(WeightModulePlcReaderApplication.class);

    @Value("${backend.url}")
    private String BACKEND_URL;

    @Value("${backend.login}")
    private String BACKEND_LOGIN;

    @Value("${backend.password}")
    private String BACKEND_PASSWORD;


    @Scheduled(fixedDelay = 5_000)
    void startUp() throws IOException {
        final var httpConfigurationModuleFirst = new RequestSenderConfiguration(1L, BACKEND_URL, "/api/weight-modules/data/", BACKEND_LOGIN, BACKEND_PASSWORD);
        final var httpConfigurationModuleLast = new RequestSenderConfiguration(1L, BACKEND_URL, "/api/weight-modules-last/data/", BACKEND_LOGIN, BACKEND_PASSWORD);
        final var plcConfigurationModuleFirst = new PlcConfiguration("192.168.1.47", 32, 10, 34);
        final var plcConfigurationModuleLast = new PlcConfiguration("192.168.1.47", 33,10,  35);

        List.of(new WeightModuleFirst(plcConfigurationModuleFirst, httpConfigurationModuleFirst))
                .forEach(WeightModuleFirst::processData);


        List.of(new WeightModuleLast(plcConfigurationModuleLast, httpConfigurationModuleLast))
                .forEach(WeightModuleLast::processData);
    }
}
