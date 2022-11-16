package app.web.readFromPlc.weightModule;

import app.web.readFromPlc.configuration.RequestSenderConfiguration;
import app.web.readFromPlc.configuration.PlcConfiguration;
import app.web.readFromPlc.plcReader.PlcReaderFacade;
import app.web.readFromPlc.weightModule.valueObject.WeightModuleFirstData;

import java.io.IOException;

public class WeightModuleFirst extends WeightModuleAbstract<WeightModuleFirstData> {
    public WeightModuleFirst(PlcConfiguration plcConfiguration, RequestSenderConfiguration requestSenderConfiguration) {
        super(plcConfiguration, requestSenderConfiguration);
    }

    @Override
    WeightModuleFirstData readDataFromPlc() throws IOException {
        PlcReader plcReader = new PlcReaderFacade(plcConfiguration);
        final  var dataFromPLC = plcReader.readFirstModuleData();
        return dataFromPLC;
    }
}
