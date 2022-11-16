package app.web.resetCounters.plcHandler;

import app.web.resetCounters.plcData.PlcResetInfo;

import java.io.IOException;

public interface PlcResetHandler {
    void resetCounter(PlcResetInfo plcResetInfo) throws Exception;
}
