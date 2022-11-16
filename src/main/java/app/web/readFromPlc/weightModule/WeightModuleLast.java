package app.web.readFromPlc.weightModule;

import app.web.readFromPlc.configuration.PlcConfiguration;
import app.web.readFromPlc.configuration.RequestSenderConfiguration;
import app.web.readFromPlc.plcReader.PlcReaderFacade;
import app.web.readFromPlc.weightModule.valueObject.WeightModuleLastData;

import java.io.IOException;

public class WeightModuleLast extends WeightModuleAbstract<WeightModuleLastData> {

    public WeightModuleLast(PlcConfiguration plcConfiguration, RequestSenderConfiguration requestSenderConfiguration) {
        super(plcConfiguration, requestSenderConfiguration);
    }

    @Override
    WeightModuleLastData readDataFromPlc() throws IOException {
        final var plcReader = new PlcReaderFacade(plcConfiguration);
        return plcReader.readLastModuleData();
    }
}
