package app.web.resetCounters;

import app.web.resetCounters.listener.ResetCounterMessage;
import app.web.resetCounters.plcData.PlcDataCollector;
import app.web.resetCounters.plcHandler.PlcResetHandler;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
class PlcResetServiceImpl implements PlcResetService {
    private final PlcDataCollector plcDataCollector;
    private final PlcResetHandler plcResetHandler;
    private final Logger logger = LoggerFactory.getLogger(PlcResetServiceImpl.class);

    @Override
    public void resetCounters(ResetCounterMessage msg) {
        msg.getLineIds()
                .stream()
                .forEach(this::resetCounter);
    }

    private void resetCounter(long lineId) {
        plcDataCollector.getPlcResetInfo()
                .stream()
                .filter(plcResetInfo -> plcResetInfo.getLineId() == lineId)
                .findAny()
                .ifPresent(plcResetInfo -> {
                    try {
                        plcResetHandler.resetCounter(plcResetInfo);
                        logger.info("PLC Counters IP: {} line id: {} was reset", plcResetInfo.getIpAddress(), plcResetInfo.getLineId());
                    } catch (Exception e) {
                        logger.error("Cannot reset counter in line with id: {}", lineId);
                    }
                });
    }
}
