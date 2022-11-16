package app.web.resetCounters;

import app.web.resetCounters.listener.ResetCounterMessage;

public interface PlcResetService {
    void resetCounters(ResetCounterMessage msg);
}
