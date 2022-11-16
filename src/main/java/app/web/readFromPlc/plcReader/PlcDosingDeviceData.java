package app.web.readFromPlc.plcReader;

import com.github.s7connector.api.annotation.S7Variable;
import com.github.s7connector.impl.utils.S7Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public
class PlcDosingDeviceData {
    public   int recordNumber;
    @S7Variable(type = S7Type.REAL, byteOffset = 0)
    public  float lastMeasure;
    @S7Variable(type = S7Type.DWORD, byteOffset = 4)
    public  long amountBelowMeasures;
    @S7Variable(type = S7Type.DWORD, byteOffset = 8)
    public  long amountCorrectMeasures;
    @S7Variable(type = S7Type.DWORD, byteOffset = 12)
    public  long amountAboveMeasures;
    @S7Variable(type = S7Type.REAL, byteOffset = 16)
    public  float averageMeasure;
    @S7Variable(type = S7Type.WORD, byteOffset = 20)
    public  int correctMeasuresPercent;
    @S7Variable(type = S7Type.REAL, byteOffset = 22)
    public  float totalMaterial;
}
