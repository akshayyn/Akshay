/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.sensors;

import controller.IdGenerator.UniqueIdGenerator;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Akshay
 */
public class PollutionMonitorSensor {
    private HashMap<Date,PollutionMonitorSensorUsage> usageHistory;
    
     private int sensorId;

    private static int count = 0;

    public PollutionMonitorSensor() {
        UniqueIdGenerator ud = new UniqueIdGenerator();
        sensorId = ud.getUniqueId();
        this.usageHistory = new HashMap<>();
    }

    public HashMap<Date,PollutionMonitorSensorUsage> getUsageHistory() {
        return usageHistory;
    }
    
    public PollutionMonitorSensorUsage addUsageInstance(Date d){
        PollutionMonitorSensorUsage pmsu = new PollutionMonitorSensorUsage();
        pmsu.setDate(d);
        usageHistory.put(d, pmsu);
        return pmsu;
    }
    private String houseId;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

   
    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }
}
