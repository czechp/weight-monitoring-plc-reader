package app.web.readFromPlc.weightModule.valueObject;

import app.web.readFromPlc.plcReader.PlcDosingDeviceData;
import app.web.readFromPlc.utils.JsonConverter;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class DosingDevice {
    private int recordNumber;
    private float lastMeasure;
    private long amountBelowMeasures;
    private long amountCorrectMeasures;
    private long amountAboveMeasures;
    private float averageMeasure;
    private int correctMeasuresPercent;
    private float totalMaterial;


    static DosingDevice create(PlcDosingDeviceData plcData) {
        return new DosingDevice(
                plcData.getRecordNumber(),
                plcData.getLastMeasure(),
                plcData.getAmountBelowMeasures(),
                plcData.getAmountCorrectMeasures(),
                plcData.getAmountAboveMeasures(),
                plcData.getAverageMeasure(),
                plcData.getCorrectMeasuresPercent(),
                plcData.getTotalMaterial() / 1000.0f
        );
    }

    @Override
    public String toString() {
        try {
            return JsonConverter.toJson(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
