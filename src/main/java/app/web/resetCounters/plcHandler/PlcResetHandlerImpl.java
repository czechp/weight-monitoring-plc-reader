package app.web.resetCounters.plcHandler;

import app.web.resetCounters.plcData.PlcResetInfo;
import com.github.s7connector.api.factory.S7ConnectorFactory;
import com.github.s7connector.api.factory.S7SerializerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
class PlcResetHandlerImpl implements PlcResetHandler {
    @Override
    public void resetCounter(PlcResetInfo plcResetInfo) throws Exception {
        final var s7Connector = S7ConnectorFactory
                .buildTCPConnector()
                .withHost(plcResetInfo.getIpAddress())
                .withRack(0) //optional
                .withSlot(1) //optional
                .build();
        final var s7Serializer = S7SerializerFactory.buildSerializer(s7Connector);
        PlcResetBlock plcResetBlock = new PlcResetBlock();
        s7Serializer.store(plcResetBlock, plcResetInfo.getResetBitBlock(), 0);
        s7Connector.close();
    }


}
