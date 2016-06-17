/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.house;

import Citizen.Citizen;
import business.sensors.PollutionMonitorSensor;
import business.streetMap.Plot;
import business.streetMap.Street;
import controller.IdGenerator.UniqueIdGenerator;
import java.util.ArrayList;
import sun.java2d.Disposer;

/**
 *
 * @author Aks
 */
public class House {
    
    private ArrayList<Citizen> residents;
    
    private String houseId;
    
    private static int count = 0;
    
    private Street street;
    
    private Plot plot;
    
    private PollutionMonitorSensor pollutionMonitorSensor;

    public PollutionMonitorSensor getPollutionMonitorSensor() {
        return pollutionMonitorSensor;
    }

    public void setPollutionMonitorSensor(PollutionMonitorSensor pollutionMonitorSensor) {
        this.pollutionMonitorSensor = pollutionMonitorSensor;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }
    
    private String apartmentNumber;
    
    private String area;

    public House() {
        UniqueIdGenerator ud = new UniqueIdGenerator();
        houseId = str(ud.getUniqueId());
        this.residents = new ArrayList<Citizen>();
    }
    public static String str(int i) {
        return i < 0 ? "" : str((i / 26) - 1) + (char) (65 + i % 26);
    }
    public void addResident(Citizen c){
        this.residents.add(c);
    }
    
    public ArrayList<Citizen> getResidents() {
        return residents;
    }

    public void setResidents(ArrayList<Citizen> residents) {
        this.residents = residents;
    }
    
    

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        House.count = count;
    }

   

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return houseId;
    }
    
    
}
