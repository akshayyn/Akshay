/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Organization;

import Business.Role.AreaOfficialRole;
import Business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author Akshay
 */
public class AreaOfficialOrganization extends Business.Organization.Organization{

    public AreaOfficialOrganization() {
        super(Business.Organization.Organization.Type.AreaOfficial.getValue());
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new AreaOfficialRole());
        return roles;
    }
    
}
