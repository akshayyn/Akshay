/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.sensors;

import business.Area.Area;
import controller.IdGenerator.UniqueIdGenerator;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Akshay
 */
public class AsthamaInhalerSensor {
    private int id;
    
    private static int count = 0;
    
    private int citizrnId;
    
    private HashMap<Date,Area> usageHistory;

    public AsthamaInhalerSensor() {
       UniqueIdGenerator ud = new UniqueIdGenerator();
        id = ud.getUniqueId();
        usageHistory = new HashMap<>();
    }
    
    public void addUsage(Date d,Area a){
        this.usageHistory.put(d, a);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCitizrnId() {
        return citizrnId;
    }

    public void setCitizrnId(int citizrnId) {
        this.citizrnId = citizrnId;
    }

    public HashMap<Date, Area> getUsageHistory() {
        return usageHistory;
    }

    public void setUsageHistory(HashMap<Date, Area> usageHistory) {
        this.usageHistory = usageHistory;
    }

   
    
    
}
