/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Citizen;

import java.util.ArrayList;

/**
 *
 * @author Akshay
 */
public class CitizenDirectory {
    private ArrayList<Citizen> citizenList;

    public CitizenDirectory() {
        citizenList =new ArrayList<Citizen>();
    }
    
    
    
    public Citizen addCitizen(){
        
        Citizen c = new Citizen();
        citizenList.add(c);
        
        return c;
    }

    public ArrayList<Citizen> getCitizenList() {
        return citizenList;
    }

    public void setCitizenList(ArrayList<Citizen> citizenList) {
        this.citizenList = citizenList;
    }
    
    
}
