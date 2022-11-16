package app.web.readFromPlc.weightModule;

import app.web.readFromPlc.weightModule.valueObject.WeightModuleFirstData;
import app.web.readFromPlc.weightModule.valueObject.WeightModuleLastData;

import java.io.IOException;

public interface PlcReader {
    void createSession();
    void closeSession() throws IOException;

    WeightModuleFirstData readFirstModuleData() throws IOException;
    WeightModuleLastData readLastModuleData() throws IOException;
}
