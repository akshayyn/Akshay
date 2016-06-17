/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Area;

import Business.Organization.Organization;
import Business.Organization.OrganizationDirectory;
import Citizen.CitizenDirectory;
import business.sensors.PollutionMonitorSensor;
import business.sensors.SensorNetwork;
import business.streetMap.StreetMap;

/**
 *
 * @author Akshay
 */
public abstract class Area extends Organization{

    private AreaType enterpriseType;
    private OrganizationDirectory organizationDirectory;
    private CitizenDirectory citizenDirectory;
    private SensorNetwork sensorNetwork;
    private StreetMap streetMap;

   
    
    public Area(String name, AreaType type) {
        super(name);
        this.enterpriseType = type;
        organizationDirectory = new OrganizationDirectory();
        citizenDirectory = new CitizenDirectory();
        streetMap = new StreetMap();
        sensorNetwork = new SensorNetwork();
        sensor = new PollutionMonitorSensor();
    }
    
    public enum AreaType{
        Area("Area");
        
        private String value;

        private AreaType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public AreaType getAreaType() {
        return enterpriseType;
    }

    public OrganizationDirectory getOrganizationDirectory() {
        return organizationDirectory;
    }

    public CitizenDirectory getCitizenDirectory() {
        return citizenDirectory;
    }

    public void setCitizenDirectory(CitizenDirectory citizenDirectory) {
        this.citizenDirectory = citizenDirectory;
    }
     public SensorNetwork getSensorNetwork() {
        return sensorNetwork;
    }

    public void setSensorNetwork(SensorNetwork sensorNetwork) {
        this.sensorNetwork = sensorNetwork;
    }

    public StreetMap getStreetMap() {
        return streetMap;
    }

    public void setStreetMap(StreetMap streetMap) {
        this.streetMap = streetMap;
    }
    private PollutionMonitorSensor sensor;
    public PollutionMonitorSensor getSensor() {
        return sensor;
    }

    public void setSensor(PollutionMonitorSensor sensor) {
        this.sensor = sensor;
    }
    
    
}
