/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Role.PollutionControlOfficerRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Akshay
 */
public class AirPollutionControlOrganization extends Organization{

    public AirPollutionControlOrganization() {
        super(Organization.Type.AirPollutionControlOrganization.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new PollutionControlOfficerRole());
        return roles;
    }
     
   
    
    
}
