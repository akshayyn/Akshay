/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Organization;

import Business.Role.MayorRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Aks
 */
public class MayorOrganization extends Business.Organization.Organization{

    public MayorOrganization() {
        super(Business.Organization.Organization.Type.Mayor.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new MayorRole());
        return roles;   
    }
    
}
