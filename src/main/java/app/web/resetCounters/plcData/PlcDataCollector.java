package app.web.resetCounters.plcData;

import java.util.List;

public interface PlcDataCollector {
    List<PlcResetInfo> getPlcResetInfo();
}
