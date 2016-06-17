/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.streetMap;

import java.util.ArrayList;

/**
 *
 * @author Aks
 */
public class StreetMap {
    private ArrayList<Street> streetList;

    public StreetMap() {
        this.streetList = new ArrayList<>();
    }
    
    public Street addStreet(){
        Street s = new Street();
        streetList.add(s);
        return s;
    }

    public ArrayList<Street> getStreetList() {
        return streetList;
    }
    
}
