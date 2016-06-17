/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Area;

import Business.Role.Role;
import business.sensors.PollutionMonitorSensor;
import java.util.ArrayList;

/**
 *
 * @author Akshay
 */
public class CityArea extends Area{

    public CityArea(String name) {
        super(name, AreaType.Area);
       
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        return null;
    }
   
    
    
}
