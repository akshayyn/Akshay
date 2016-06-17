/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Organization;

import Business.Organization.Organization.Type;
import java.util.ArrayList;

/**
 *
 * @author raunak
 */
public class OrganizationDirectory {
    
    private ArrayList<Organization> organizationList;

    public OrganizationDirectory() {
        organizationList = new ArrayList<>();
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }
    
    public Organization createOrganization(Type type){
        Organization organization = null;
        if (type.getValue().equals(Type.Doctor.getValue())){
            organization = new DoctorOrganization();
            organizationList.add(organization);
            
        }
        else if (type.getValue().equals(Type.AirPollutionControlOrganization.getValue())){
            organization = new AirPollutionControlOrganization();
            organizationList.add(organization);
        }else if (type.getValue().equals(Type.Citizen.getValue())){
            organization = new business.Organization.CitizenOrganization();
            organizationList.add(organization);
        }else if (type.getValue().equals(Type.AreaOfficial.getValue())){
            organization = new business.Organization.AreaOfficialOrganization();
            organizationList.add(organization);
        }else if (type.getValue().equals(Type.Citizen.getValue())){
            organization = new business.Organization.CitizenOrganization();
            organizationList.add(organization);
        }
        return organization;
    }
}