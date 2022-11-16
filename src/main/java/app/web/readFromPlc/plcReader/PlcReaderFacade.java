package app.web.readFromPlc.plcReader;

import app.web.readFromPlc.configuration.PlcConfiguration;
import app.web.readFromPlc.weightModule.PlcReader;
import app.web.readFromPlc.weightModule.valueObject.WeightModuleFirstData;
import app.web.readFromPlc.weightModule.valueObject.WeightModuleLastData;
import com.github.s7connector.api.S7Connector;
import com.github.s7connector.api.S7Serializer;
import com.github.s7connector.api.factory.S7ConnectorFactory;
import com.github.s7connector.api.factory.S7SerializerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlcReaderFacade implements PlcReader {
    private final Logger logger = LoggerFactory.getLogger(PlcReaderFacade.class);
    private final PlcConfiguration plcConfiguration;
    private S7Connector s7Connector;
    private S7Serializer s7Serializer;

    public PlcReaderFacade(PlcConfiguration plcConfiguration) {
        this.plcConfiguration = plcConfiguration;
    }

    @Override
    public void createSession() {
        s7Connector = S7ConnectorFactory
                .buildTCPConnector()
                .withHost(plcConfiguration.getPlcAddress())
                .withRack(0) //optional
                .withSlot(1) //optional
                .build();
        s7Serializer = S7SerializerFactory.buildSerializer(s7Connector);
    }

    @Override
    public void closeSession() throws IOException {
        s7Connector.close();
    }

    @Override
    public WeightModuleFirstData readFirstModuleData() throws IOException {
        this.createSession();
        PlcModuleFirstData plcModuleFirstData = s7Serializer.dispense(PlcModuleFirstData.class, plcConfiguration.getGetModuleInfoDbNumber(), 0);
        List<PlcDosingDeviceData> dosingDevices = readInfoAboutDosingDevices(plcConfiguration.getAmountOfDosingDevices(),
                plcConfiguration.getDosingDevicesDbNumber());

        this.closeSession();
        return WeightModuleFirstData.create(plcModuleFirstData, dosingDevices);
    }

    @Override
    public WeightModuleLastData readLastModuleData() throws IOException {
        this.createSession();
        PlcModuleLastData plcModuleLastData = s7Serializer.dispense(PlcModuleLastData.class, plcConfiguration.getGetModuleInfoDbNumber(), 0);
        List<PlcDosingDeviceData> dosingDevices = readInfoAboutDosingDevices(plcConfiguration.getAmountOfDosingDevices(),
                plcConfiguration.getDosingDevicesDbNumber());
        this.closeSession();
        return WeightModuleLastData.create(plcModuleLastData, dosingDevices);
    }

    private List<PlcDosingDeviceData> readInfoAboutDosingDevices(int amountOfDosingDevices, int dosingDevicesDbNumber) {
        List<PlcDosingDeviceData> dosingDevicesData = IntStream.range(0, amountOfDosingDevices)
                .boxed()
                .map(n -> {
                    PlcDosingDeviceData plcData = s7Serializer.dispense(PlcDosingDeviceData.class, dosingDevicesDbNumber, n * 26);
                    plcData.recordNumber = n + 1;
                    return plcData;
                })
                .collect(Collectors.toList());
        return dosingDevicesData;
    }
}
