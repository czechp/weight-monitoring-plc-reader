package app.web.readFromPlc.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlcConfiguration {
    private final String plcAddress;
    private final int getModuleInfoDbNumber;
    private final int amountOfDosingDevices;
    private final int dosingDevicesDbNumber;
}
