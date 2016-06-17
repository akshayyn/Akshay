/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.DataProcessor;

import business.Area.Area;
import business.City.City;
import business.sensors.AsthamaInhalerSensor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Aks
 */
public class InhalerDataProcessor {

    /**
     * public void processInhalerData(Area a){ HashMap<String,Integer>
     * monthlyDataMap = new HashMap<>(); HashMap<Integer,String> calendar = new
     * HashMap<>(); calendar.put(0, "January"); calendar.put(1, "February");
     * calendar.put(2, "March"); calendar.put(3, "Aprl"); calendar.put(4,
     * "May"); calendar.put(6, "June"); calendar.put(7, "July"); calendar.put(8,
     * "August"); calendar.put(9, "Sepetember"); calendar.put(10, "October");
     * calendar.put(11, "November"); calendar.put(12, "December");
     *
     * for(AsthamaInhalerSensor sensor :
     * a.getSensorNetwork().getAthamaInhalerSensors()){ ArrayList<Date> dateList
     * = new ArrayList<>(sensor.getUsageHistory().keySet()); for(Date d :
     * dateList){ java.util.Date date= new Date(); Calendar cal =
     * Calendar.getInstance(); cal.setTime(date); int month =
     * cal.get(Calendar.MONTH); String value = calendar.get(month);
     * if(!monthlyDataMap.keySet().contains(value)){ monthlyDataMap.put(value,
     * 1); }else{ monthlyDataMap.put(value, monthlyDataMap.get(value)+1); } } }
    }
     */
    public int getAreaWiseInhalerUsage(Area a, City c) {
        int usage = 0;
        for (Area area : c.getAreaDirectory().getAreaList()) {
            for (AsthamaInhalerSensor sensor : area.getSensorNetwork().getAthamaInhalerSensors()) {
                ArrayList<Date> dateList = new ArrayList<>(sensor.getUsageHistory().keySet());
                for (Date d : dateList) {
                    if(a.getName().equals(sensor.getUsageHistory().get(d).getName())){
                        usage++;
                    }
                }
            }
        }
        return usage;
    }
}
