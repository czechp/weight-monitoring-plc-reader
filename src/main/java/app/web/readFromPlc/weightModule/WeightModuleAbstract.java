package app.web.readFromPlc.weightModule;

import app.web.readFromPlc.configuration.RequestSenderConfiguration;
import app.web.readFromPlc.configuration.PlcConfiguration;
import app.web.readFromPlc.requestSender.RequestSenderFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

abstract class WeightModuleAbstract <T> {
    private Logger logger = LoggerFactory.getLogger(WeightModuleAbstract.class);

    protected T moduleData;
    protected PlcConfiguration plcConfiguration;
    private RequestSenderConfiguration requestSenderConfiguration;

    public WeightModuleAbstract(PlcConfiguration plcConfiguration, RequestSenderConfiguration requestSenderConfiguration) {
        this.plcConfiguration = plcConfiguration;
        this.requestSenderConfiguration = requestSenderConfiguration;
    }

    abstract  T readDataFromPlc() throws IOException;

    public void processData(){
        try{
            this.moduleData = readDataFromPlc();
            sendDataToBackend();
        }catch (Exception e){
        }
    }

    private void sendDataToBackend() throws URISyntaxException, IOException, InterruptedException {
        RequestSender requestSender = new RequestSenderFacade(requestSenderConfiguration);
        requestSender.sendData(moduleData.toString());
    }
}
