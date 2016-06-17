/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Role;

import Business.EcoSystem;
import business.Area.Area;
import business.Area.Area;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import business.City.City;

import javax.swing.JPanel;
import userinterface.PollutionControlOfficer.PolliutionControlOfficerWorkAreaJPanel;

/**
 *
 * @author raunak
 */
public class PollutionControlOfficerRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Area area,City city, EcoSystem business) {
        return new PolliutionControlOfficerWorkAreaJPanel(userProcessContainer, account, organization, area, city, business);
    }

  
   
    
}
