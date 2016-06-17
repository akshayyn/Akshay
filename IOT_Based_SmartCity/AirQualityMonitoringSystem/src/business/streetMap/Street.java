/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.streetMap;

import controller.IdGenerator.UniqueIdGenerator;
import java.util.ArrayList;

/**
 *
 * @author Akshay
 */
public class Street {
    private int streetNumber;
    
    private ArrayList<Plot> plotList;
    
    private String name;
    
    private static int count = 0;

    public Street() {
       UniqueIdGenerator ud = new UniqueIdGenerator();
        streetNumber = ud.getUniqueId();
        plotList = new ArrayList<>();
    }
    
    public void addPlot(){
        Plot plot  = new Plot();
        plotList.add(plot);
    }
    

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public ArrayList<Plot> getPlotList() {
        return plotList;
    }

    public void setPlotList(ArrayList<Plot> plotList) {
        this.plotList = plotList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
