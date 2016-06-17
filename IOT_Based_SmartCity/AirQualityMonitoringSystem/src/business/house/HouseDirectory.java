/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.house;

import business.streetMap.Plot;
import business.streetMap.Street;
import java.util.ArrayList;

/**
 *
 * @author Akshay
 */
public class HouseDirectory {
    private ArrayList<House> houseDirectory;
    
   

    public HouseDirectory() {
        this.houseDirectory = new ArrayList<>();
    }
    
    private House addHouse(Street s, Plot p){
        House house = new House();
        p.getApartmentMap().add(house);
        house.setApartmentNumber(house.str(p.getApartmentMap().size()));
        house.setStreet(s);
        house.setPlot(p);
        houseDirectory.add(house);
        return house;
    }

    public ArrayList<House> getHouseDirectory() {
        return houseDirectory;
    }
    
    
}
