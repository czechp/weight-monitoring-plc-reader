package app.web.readFromPlc.plcReader;

import com.github.s7connector.api.annotation.S7Variable;
import com.github.s7connector.impl.utils.S7Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlcModuleLastData {
    @S7Variable(type = S7Type.REAL, byteOffset = 0)
    public float productUpRangeWeight;
    @S7Variable(type = S7Type.REAL, byteOffset = 4)
    public float productDownRangeWeight;
    @S7Variable(type = S7Type.WORD, byteOffset = 8)
    public int currentDosingDevice;
    @S7Variable(type = S7Type.REAL, byteOffset = 10)
    public float currentMeasure;
    @S7Variable(type = S7Type.BOOL, byteOffset = 14)
    public boolean status;
    @S7Variable(type = S7Type.REAL, byteOffset = 16)
    public float totalMaterialWeight;
    @S7Variable(type = S7Type.DWORD, byteOffset = 20)
    public long totalProductPcs;
    @S7Variable(type = S7Type.REAL, byteOffset = 24)
    public float correctProductPercent;
    @S7Variable(type = S7Type.DWORD, byteOffset = 28)
    public long incorrectProductPcs;
    @S7Variable(type = S7Type.REAL, byteOffset = 32)
    public float weightDifference;
    @S7Variable(type = S7Type.REAL, byteOffset = 36)
    public float correctToOverDosePercent;
    @S7Variable(type = S7Type.DWORD, byteOffset = 40)
    public long notRefilledProductPcs;
    @S7Variable(type = S7Type.DWORD, byteOffset = 44)
    public long overFilledProductPcs;
    @S7Variable(type = S7Type.REAL, byteOffset = 48)
    public float overFilledToNotRefilledPercent;
}
