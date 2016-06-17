/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.City;

import Business.Organization.Organization;
import Business.Role.MayorRole;
import Business.Role.Role;
import business.Area.AreaDirectory;
import java.util.ArrayList;

/**
 *
 * @author Akshay
 */
public class City extends Organization{
    
    private String name;
    private AreaDirectory enterpriseDirectory;

    public City(String name) {
        super(name);
        enterpriseDirectory = new AreaDirectory();
    }

    

    public AreaDirectory getAreaDirectory() {
        return enterpriseDirectory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(new MayorRole());
        return roleList;
    }
    
}
