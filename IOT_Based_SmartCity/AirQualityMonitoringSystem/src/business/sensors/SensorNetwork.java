/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.sensors;

import Business.Organization.AirPollutionControlOrganization;
import java.util.ArrayList;

/**
 *
 * @author Akshay
 */
public class SensorNetwork {
    private ArrayList<PollutionMonitorSensor> houseHoldSensorList;
    
    private ArrayList<AsthamaInhalerSensor> athamaInhalerSensors;

    public ArrayList<PollutionMonitorSensor> gethouseHoldSensorList() {
        return houseHoldSensorList;
    }

    public void sethouseHoldSensorList(ArrayList<PollutionMonitorSensor> sensorList) {
        this.houseHoldSensorList = sensorList;
    }

    public SensorNetwork() {
        this.houseHoldSensorList = new ArrayList<>();
        this.athamaInhalerSensors = new ArrayList<>();
    }
    
    public PollutionMonitorSensor addHouseHoldSensor(){
        PollutionMonitorSensor ps = new PollutionMonitorSensor();
        this.houseHoldSensorList.add(ps);
        return ps;
    }
    public AsthamaInhalerSensor addAsthamaInhalerSensor(){
        AsthamaInhalerSensor ps = new AsthamaInhalerSensor();
        this.athamaInhalerSensors.add(ps);
        return ps;
    }
    
    public ArrayList<PollutionMonitorSensor> getHouseHoldSensorList() {
        return houseHoldSensorList;
    }

    public void setHouseHoldSensorList(ArrayList<PollutionMonitorSensor> houseHoldSensorList) {
        this.houseHoldSensorList = houseHoldSensorList;
    }

    public ArrayList<AsthamaInhalerSensor> getAthamaInhalerSensors() {
        return athamaInhalerSensors;
    }

    public void setAthamaInhalerSensors(ArrayList<AsthamaInhalerSensor> athamaInhalerSensors) {
        this.athamaInhalerSensors = athamaInhalerSensors;
    }
    
}
