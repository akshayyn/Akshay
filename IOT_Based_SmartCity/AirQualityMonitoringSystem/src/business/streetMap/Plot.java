/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.streetMap;

import business.house.House;
import controller.IdGenerator.UniqueIdGenerator;
import java.util.ArrayList;

/**
 *
 * @author Aks
 */
public class Plot {
    
    private int plotNumber;
    
    private Street street;
    
    private static int count = 0;
   

    

    public int getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(int plotNumber) {
        this.plotNumber = plotNumber;
    }
    
    private ArrayList<House> apartmentMap;
    
   
    
    public Plot() {
        apartmentMap = new ArrayList<>();
        UniqueIdGenerator ud = new UniqueIdGenerator();
        plotNumber = ud.getUniqueId();
    }

    public ArrayList<House> getApartmentMap() {
        return apartmentMap;
    }

  
    public House addHouse(Street s){
        House house = new House();
        this.getApartmentMap().add(house);
        //house.setApartmentNumber(house.str(this.getApartmentMap().size()));
        house.setStreet(s);
        house.setPlot(this);
        return house;
    }
    
   public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return String.valueOf(plotNumber);
    }
    
}
