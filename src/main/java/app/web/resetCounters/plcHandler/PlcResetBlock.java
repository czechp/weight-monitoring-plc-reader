package app.web.resetCounters.plcHandler;

import com.github.s7connector.api.annotation.S7Variable;
import com.github.s7connector.impl.utils.S7Type;
import lombok.Data;

@Data
public class PlcResetBlock {
    @S7Variable(type = S7Type.BOOL, byteOffset = 0)
    public boolean resetBit;

    public PlcResetBlock() {
        this.resetBit = true;
    }
}
