package app.web.resetCounters.plcData;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile({"production"})
class PlcDataCollectorProduction implements PlcDataCollector {

    @Override
    public List<PlcResetInfo> getPlcResetInfo() {
        return List.of(
                new PlcResetInfo("192.168.1.46", 1L),
                new PlcResetInfo("192.168.1.47", 3L),
                new PlcResetInfo("192.168.1.48", 4L)
        );
    }
}
