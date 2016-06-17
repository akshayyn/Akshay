/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Organization;


import Business.Role.CitizenRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Aks
 */
public class CitizenOrganization extends Business.Organization.Organization {

    public CitizenOrganization() {
       super(Business.Organization.Organization.Type.Citizen.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new CitizenRole());
        return roles;
    }
    
}
